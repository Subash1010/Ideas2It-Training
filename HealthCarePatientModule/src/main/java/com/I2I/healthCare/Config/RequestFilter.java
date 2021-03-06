package com.I2I.healthCare.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class RequestFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String proxyForwardedHostHeader = request.getHeader("X-Forwarded-Host");
		String healthCheck = request.getRequestURI();
		if (proxyForwardedHostHeader == null && !(healthCheck.equals("/actuator/health"))) {
			byte[] responseToSend = restResponseBytes("Unauthorized Access, you should pass through the API gateway");
			((HttpServletResponse) response).setHeader("Content-Type", "application/json");
			((HttpServletResponse) response).setStatus(401);
			response.getOutputStream().write(responseToSend);
			return;
		}
		chain.doFilter(request, response);
	}

	private byte[] restResponseBytes(String errorResponse) throws IOException {
		return errorResponse.getBytes();
	}
}