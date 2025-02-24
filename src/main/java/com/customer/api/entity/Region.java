package com.customer.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "region")
public class Region {	
	
	@Id
	private Integer region_id;
	
	private String region;
	private String tag;
	private Integer status;
	
	public Region() {
		
	}
	
	public Region(Integer region_id, String region, String tag, Integer status) {
		super();
		this.region_id = region_id;
		this.region = region;
		this.tag = tag;
		this.status = status;
	}

	public Integer getRegion_id() {
		return region_id;
	}

	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
