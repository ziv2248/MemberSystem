package co.ziv.handler;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.ziv.constant.MessageCode;
import co.ziv.response.GeneralResponse;
import co.ziv.util.MessageUtil;

@RestControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageUtil messageUtil;
	
	@ExceptionHandler(Exception.class)
	public GeneralResponse exception(Exception ex) {
		String stackTrace = ExceptionUtils.getStackTrace(ex);
		LOGGER.info("unhandled exception: " + stackTrace);
		
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessageCode(MessageCode.ERROR);
		generalResponse.setMessage(messageUtil.get("co.ziv.status.system.error"));
		return generalResponse;
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessageCode(MessageCode.ERROR);
		generalResponse.setMessage(messageUtil.get("co.ziv.status.validate.error"));
		return new ResponseEntity<>(generalResponse, headers, status);
	}
}
