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

import com.customer.api.dto.DtoRegionIn;
import com.customer.api.entity.Region;
import com.customer.api.service.SvcRegion;
import com.customer.common.ApiResponse;
import com.customer.exception.ApiException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/region")
public class CtrlRegion {

	@Autowired
	SvcRegion svc;

	@GetMapping
	public ResponseEntity<List<Region>> getRegions() {
		return svc.getRegions();
	}

	@GetMapping("/active")
	public ResponseEntity<List<Region>> getActiveRegions() {
		return svc.getActiveRegions();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Region> getRegion(@PathVariable Integer id) {
		return svc.getRegion(id);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> createRegion(@Valid @RequestBody DtoRegionIn in, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.createRegion(in);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateRegion(@PathVariable Integer id, @Valid @RequestBody DtoRegionIn in,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getFieldError().getDefaultMessage());

		return svc.updateRegion(id, in);
	}

	@PatchMapping("/{id}/enable")
	public ResponseEntity<ApiResponse> enableRegion(@PathVariable Integer id) {
		return svc.enableRegion(id);
	}

	@PatchMapping("/{id}/disable")
	public ResponseEntity<ApiResponse> disableRegion(@PathVariable Integer id) {
		return svc.disableRegion(id);
	}
	
	

}
