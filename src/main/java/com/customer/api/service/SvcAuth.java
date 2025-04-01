package com.customer.api.service;

import com.customer.api.dto.in.AuthRequest;
import com.customer.api.dto.out.AuthAPIResponse;

public interface SvcAuth {
	
	AuthAPIResponse consultaUsuarios(AuthRequest request) throws InterruptedException;
	String solicitaToken(AuthRequest authRequest);
	
}
