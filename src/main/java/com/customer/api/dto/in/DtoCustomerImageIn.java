package com.customer.api.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class DtoCustomerImageIn {

	@JsonProperty("customer_id")
	@NotNull(message="El customer_id es obligatorio")
	private Integer customer_id;
	
	@JsonProperty("image")
	@NotNull(message="El image es obligatorio")
	private String image;

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
