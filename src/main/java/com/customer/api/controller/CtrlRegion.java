package com.customer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.entity.Region;
import com.customer.api.service.SvcRegion;

@RestController
@RequestMapping("/region")
public class CtrlRegion {
	
	@Autowired
	SvcRegion svc;

	@GetMapping
	public List<Region> getRegions(){
		return svc.getRegions();
	}

	@GetMapping("/active")
	public List<Region> getActiveRegions(){
		return svc.getActiveRegions();
	}

	@GetMapping("/{region_id}")
	public Region getRegion(@PathVariable Integer region_id){
		return svc.getRegion(region_id);
	}

}
