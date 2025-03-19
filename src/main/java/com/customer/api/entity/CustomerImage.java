package com.customer.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_image")
public class CustomerImage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_image_id")
   private Integer customerImageId;

   @Column(name = "customer_id")
   private Integer customer_id;

   @Column(name = "image")
   private String image;

   @Column(name = "status")
   private Integer status;

public Integer getCustomerImageId() {
	return customerImageId;
}

public void setCustomerImageId(Integer customerImageId) {
	this.customerImageId = customerImageId;
}

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

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

   
   
   
}
