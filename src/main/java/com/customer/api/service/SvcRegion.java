package com.customer.api.service;

import java.util.List;

import com.customer.api.entity.Region;

public interface SvcRegion {

	public List<Region> getRegions();
	public List<Region> getActiveRegions();
	public Region getRegion(Integer id);
}
