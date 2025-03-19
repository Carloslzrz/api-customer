package com.customer.api.service;

import org.springframework.http.ResponseEntity;

import com.customer.api.dto.in.DtoCustomerImageIn;
import com.customer.common.dto.ApiResponse;

public interface SvcCustomerImage {

	public ResponseEntity<ApiResponse> uploadCustomerImage(DtoCustomerImageIn in);
}
