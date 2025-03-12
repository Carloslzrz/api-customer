package com.customer.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.customer.api.dto.in.DtoCustomerIn;
import com.customer.api.dto.out.DtoCustomerListOut;
import com.customer.api.dto.out.DtoCustomerOut;
import com.customer.common.dto.ApiResponse;

public interface SvcCustomer {

	public ResponseEntity<List<DtoCustomerListOut>> getCustomers();
	public ResponseEntity<DtoCustomerOut> getCustomer(Integer id);
	public ResponseEntity<ApiResponse> createCustomer(DtoCustomerIn in);
	public ResponseEntity<ApiResponse> updateCustomer(Integer id, DtoCustomerIn in);
	public ResponseEntity<ApiResponse> enableCustomer(Integer id);
	public ResponseEntity<ApiResponse> disableCustomer(Integer id);

}
