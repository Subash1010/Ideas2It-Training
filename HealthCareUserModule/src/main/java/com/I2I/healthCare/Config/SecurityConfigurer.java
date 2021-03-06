package com.I2I.healthCare.Config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.I2I.healthCare.Filter.JwtRequestFilter;
import com.I2I.healthCare.Service.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Lazy
	@Autowired
	public SecurityConfigurer(UserServiceImpl userServiceImpl, JwtRequestFilter jwtRequestFilter) {
		this.userServiceImpl = userServiceImpl;
		this.jwtRequestFilter = jwtRequestFilter;
	}

	private final UserServiceImpl userServiceImpl;

	private final JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/authenticate/authenticate", "/actuator/**", "/beans/reset", "/swagger**").permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		List<String> allowedMethods = Collections.unmodifiableList(Arrays.asList(HttpMethod.GET.name(),
				HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
		CorsConfiguration otherConfig = new CorsConfiguration();
		otherConfig.setAllowedMethods(allowedMethods);
		source.registerCorsConfiguration("/**",
				new CorsConfiguration().applyPermitDefaultValues().combine(otherConfig));
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
