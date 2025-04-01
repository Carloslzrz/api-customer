package com.customer.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.in.AuthRequest;
import com.customer.api.dto.out.AuthAPIResponse;
import com.customer.api.service.SvcAuth;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/secured")
public class CtrlAuth {
	
	@Autowired
	SvcAuth authSvc;
	
	@PostMapping(value = "/usuario")
	public AuthAPIResponse consultaUsuariosRegistrados(@Valid @RequestBody AuthRequest request) throws InterruptedException {
		return authSvc.consultaUsuarios(request);
	}

}
