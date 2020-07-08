package co.ziv.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.ziv.constant.SystemInstance;

public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (MediaType.APPLICATION_JSON_UTF8_VALUE.equals(request.getContentType()) || MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken authRequest = null;
			try (InputStream stream = request.getInputStream()) {
				TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>(){};
				Map<String, String> body = mapper.readValue(stream, typeRef);
				authRequest = new UsernamePasswordAuthenticationToken(body.get("account"), body.get("password"));
			} catch (IOException e) {
				e.printStackTrace();
				authRequest = new UsernamePasswordAuthenticationToken(SystemInstance.EMPTY, SystemInstance.EMPTY);
			} finally {
				setDetails(request, authRequest);
			}
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
			return super.attemptAuthentication(request, response);
		}
	}
}
