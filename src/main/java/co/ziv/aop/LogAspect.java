package co.ziv.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* co.ziv.controller..*(..))")
    public void logPointcut() {
    }
    
	@Around("logPointcut()")
	public Object getTimeConsuming(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object[] args = joinPoint.getArgs();
		long startTime = System.currentTimeMillis();

		Object proceed = joinPoint.proceed(args);
		
		long endTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
		
		printExecTime(methodName, startTime, endTime);
		return proceed;
	}
	
	private void printExecTime(String methodName, long startTime, long endTime) {
		long diffTime = endTime - startTime;
		LOGGER.info(methodName + " time-consuming：" + diffTime + " ms");
	}
}