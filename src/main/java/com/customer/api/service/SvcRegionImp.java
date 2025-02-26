package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;
import com.customer.exception.ApiException;
import com.customer.exception.DBAccessException;

@Service
public class SvcRegionImp implements SvcRegion {
	
	@Autowired
	RepoRegion repo;

	@Override
	public List<Region> getRegions() {
		try {
			return repo.getRegions();
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public List<Region> getActiveRegions() {
		try {
			return repo.getActiveRegions();
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}

	@Override
	public Region getRegion(Integer id) {
		try {
			Region region = repo.getRegion(id);
			if(region == null) {
				throw new ApiException(HttpStatus.NOT_FOUND, "El id de la región no existe");
			}
			return region;
		}catch (DataAccessException e) {
			throw new DBAccessException(e);
		}
	}
}
