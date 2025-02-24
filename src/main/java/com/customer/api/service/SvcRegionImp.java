package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;

@Service
public class SvcRegionImp implements SvcRegion {
	
	@Autowired
	RepoRegion repo;

	@Override
	public List<Region> getRegions() {
		return repo.getRegions();
	}

	@Override
	public List<Region> getActiveRegions() {
		return repo.getActiveRegions();
	}

	@Override
	public Region getRegion(Integer id) {
		try {
			Region region = repo.getRegion(id);
			if(region == null) {
				throw new Exception("No existe la región");
			}
			return region;
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

}
