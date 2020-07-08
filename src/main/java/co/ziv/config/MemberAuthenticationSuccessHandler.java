package co.ziv.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.ziv.constant.MessageCode;
import co.ziv.constant.SystemInstance;
import co.ziv.response.GeneralResponse;

public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final String LOGGED_IN = "logged_in";
	private final String USER_TYPE = "user_type";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
		String account = authentication.getName();
		Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
		String authority = collection.iterator().next().toString();
		HttpSession session = req.getSession();
		session.setAttribute(LOGGED_IN, account);
		session.setAttribute(USER_TYPE, authority);
        GeneralResponse result = new GeneralResponse(MessageCode.SUCCESS, SystemInstance.LOGIN.SUCCESS + " - " + authority);
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		resp.setStatus(200);
		ObjectMapper om = new ObjectMapper();
		out.write(om.writeValueAsString(result));
		out.flush();
		out.close();
	}
}
