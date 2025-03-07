package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.customer.api.dto.DtoRegionIn;
import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;
import com.customer.common.ApiResponse;
import com.customer.exception.ApiException;
import com.customer.exception.DBAccessException;

@Service
public class SvcRegionImp implements SvcRegion {
	
	@Autowired
	RepoRegion repo;

	@Override
	public ResponseEntity<List<Region>> getRegions() {
		try {
			return new ResponseEntity<>(repo.getRegions(), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<List<Region>> getActiveRegions() {
		try {
			return new ResponseEntity<>(repo.getActiveRegions(), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<Region> getRegion(Integer id) {
		try {
			validateRegionId(id);
			return new ResponseEntity<>(repo.getRegion(id), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> createRegion(DtoRegionIn in) {
		try {
			repo.createRegion(in.getRegion(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La región ha sido registrada"), HttpStatus.CREATED);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_region"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la región ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la región ya está registrado");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> updateRegion(Integer id, DtoRegionIn in) {
		try {
			validateRegionId(id);
			repo.updateRegion(id, in.getRegion(), in.getTag());
			return new ResponseEntity<>(new ApiResponse("La región ha sido actualizada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			if (e.getLocalizedMessage().contains("ux_region"))
				throw new ApiException(HttpStatus.CONFLICT, "El nombre de la región ya está registrado");
			if (e.getLocalizedMessage().contains("ux_tag"))
				throw new ApiException(HttpStatus.CONFLICT, "El tag de la región ya está registrado");

			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> enableRegion(Integer id) {
		try {
			validateRegionId(id);
			repo.enableRegion(id);
			return new ResponseEntity<>(new ApiResponse("La región ha sido activada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public ResponseEntity<ApiResponse> disableRegion(Integer id) {
		try {
			validateRegionId(id);
			repo.disableRegion(id);
			return new ResponseEntity<>(new ApiResponse("La región ha sido desactivada"), HttpStatus.OK);
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
	
	private void validateRegionId(Integer id) {
		try {
			if(repo.getRegion(id) == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de la región no existe");
			}
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
}
