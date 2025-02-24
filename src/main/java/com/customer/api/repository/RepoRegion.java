package com.customer.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Region;

@Repository
public interface RepoRegion extends JpaRepository<Region, Integer>{

	@Query(value ="SELECT * FROM region WHERE status = :status ORDER BY region", nativeQuery = true)
	List<Region> getRegions();
}
