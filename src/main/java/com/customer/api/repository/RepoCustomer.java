package com.customer.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Customer;

@Repository
public interface RepoCustomer extends JpaRepository<Customer, Integer> {
	
}
