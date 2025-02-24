package com.customer.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;

@RestController
@RequestMapping("/region")
public class CtrlRegion {
	
	@Autowired
	RepoRegion repo;

	@GetMapping
	public List<Region> getRegions(){
		return repo.getRegions();
	}

}
