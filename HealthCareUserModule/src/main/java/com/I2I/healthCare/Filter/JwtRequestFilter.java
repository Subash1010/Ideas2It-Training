package com.I2I.healthCare.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.I2I.healthCare.Service.UserService;
import com.I2I.healthCare.Util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Lazy
	@Autowired
	public JwtRequestFilter(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	private final UserService userService;

	private final JwtUtil jwtUtil;

	Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		String jwt = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			try {
				userName = jwtUtil.extractUserName(jwt);
			} catch (Exception exception) {
				logger.error("Unable to find Username for the requested JWT" + exception);
			}
		} else {
			logger.error("AuthorizationHeader Doesn't Satisfy the condition");
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(userName);

			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				userNamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
