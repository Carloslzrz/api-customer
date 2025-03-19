package com.customer.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.in.DtoCustomerImageIn;
import com.customer.api.service.SvcCustomerImage;
import com.customer.common.dto.ApiResponse;
import com.customer.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer-image")
public class CtrlCustomerImage {
	
	@Autowired
	SvcCustomerImage svc;

	@PostMapping
    public ResponseEntity<ApiResponse> createCustomerImage(@Valid @RequestBody DtoCustomerImageIn in, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        	throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

        return svc.uploadCustomerImage(in);
    }

}
