package com.farming_production.farming_production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.farming_production.farming_production.auth.JWTAuthenticationFilter;
import com.farming_production.farming_production.auth.JWTAuthorizationFilter;
import com.farming_production.farming_production.services.JWTService;
import com.farming_production.farming_production.services.impl.UserServiceImpl;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
	private UserServiceImpl userService;
		
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{
		build.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Autowired
	private JWTService jwtService;

	
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
            .antMatchers("/users/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))		
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
    
}
