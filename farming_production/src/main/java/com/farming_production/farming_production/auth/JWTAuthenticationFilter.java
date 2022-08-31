package com.farming_production.farming_production.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.farming_production.farming_production.models.User;
import com.farming_production.farming_production.services.JWTService;
import com.farming_production.farming_production.services.impl.JWTServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
	private JWTService jwtService;
    
 	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(
			new AntPathRequestMatcher("/users/login", "POST") );
			this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
			
            User user = null;		
			try {				
                user = new ObjectMapper().readValue(request.getInputStream(), User.class);
				logger.info("Username from request InputStream: " + user.getName());
				logger.info("Password from request InputStream: " + user.getPassword());
				
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
					
		UsernamePasswordAuthenticationToken authToken = 
		new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
		
		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String name = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();

		String token = jwtService.create(authResult);		
		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("username", name);
		body.put("message", String.format("Hello %s, login success", name ) );
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Authentication error");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}

}
