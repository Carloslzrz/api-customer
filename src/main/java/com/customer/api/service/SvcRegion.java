package com.customer.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.customer.api.dto.DtoRegionIn;
import com.customer.api.entity.Region;
import com.customer.common.ApiResponse;

public interface SvcRegion {

	public ResponseEntity<List<Region>> getRegions();
	public ResponseEntity<List<Region>> getActiveRegions();
	public ResponseEntity<Region> getRegion(Integer id);
	public ResponseEntity<ApiResponse> createRegion(DtoRegionIn in);
	public ResponseEntity<ApiResponse> updateRegion(Integer id, DtoRegionIn in);
	public ResponseEntity<ApiResponse> enableRegion(Integer id);
	public ResponseEntity<ApiResponse> disableRegion(Integer id);
}
