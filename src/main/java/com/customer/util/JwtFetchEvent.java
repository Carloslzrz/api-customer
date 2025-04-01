package com.customer.util;

import org.springframework.context.ApplicationEvent;

import com.customer.api.dto.in.AuthRequest;

public class JwtFetchEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8062053822630575957L;
	
	private AuthRequest request;

	public JwtFetchEvent(Object object, AuthRequest request) {
		super(object);
		this.request = request;
	}

	public AuthRequest getRequest() {
		return request;
	}

	public void setRequest(AuthRequest request) {
		this.request = request;
	}
	
	
}