package com.customer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.CustomerImage;

@Repository
public interface RepoCustomerImage extends JpaRepository<CustomerImage, Integer> {

	@Query(value = "SELECT * FROM customer_image WHERE customer_id = :customer_id;", nativeQuery = true)
	CustomerImage findByCustomer_id(Integer customer_id);
}
