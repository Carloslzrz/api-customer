package com.customer.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.entity.Region;

@RestController
@RequestMapping("/region")
public class CtrlRegion {

	@GetMapping
	public List<Region> getRegions(){
		return getRegionList();
	}
	
	private List<Region> getRegionList(){
		List<Region> regions = new ArrayList<Region>();
		regions.add(new Region(1,"Norte","N",1));
		regions.add(new Region(2,"Sur","S",1));
		regions.add(new Region(3,"Este","E",0));
		
		return regions;
	}
}
