package com.customer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.in.DtoCustomerIn;
import com.customer.api.dto.out.DtoCustomerListOut;
import com.customer.api.dto.out.DtoCustomerOut;
import com.customer.api.service.SvcCustomer;
import com.customer.common.dto.ApiResponse;
import com.customer.exception.ApiException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer", description = "Administración de clientes")
public class CtrlCustomer {

	@Autowired
	SvcCustomer svc;

	@GetMapping
	public ResponseEntity<List<DtoCustomerListOut>> getCustomers() {
		return svc.getCustomers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DtoCustomerOut> getCustomer(@PathVariable Integer id) {
		return svc.getCustomer(id);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody DtoCustomerIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.createCustomer(in);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Integer id, @Valid @RequestBody DtoCustomerIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.updateCustomer(id, in);
	}

	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableCustomer(@PathVariable Integer id) {
		return svc.enableCustomer(id);
	}

	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableCustomer(@PathVariable Integer id) {
		return svc.disableCustomer(id);
	}
}
