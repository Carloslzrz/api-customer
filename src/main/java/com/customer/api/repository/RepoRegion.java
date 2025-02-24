package com.customer.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Region;

@Repository
public interface RepoRegion extends JpaRepository<Region, Integer>{

	@Query(value ="SELECT * FROM region ORDER BY region", nativeQuery = true)
	List<Region> getRegions();

	@Query(value ="SELECT * FROM region WHERE status = 1 ORDER BY region", nativeQuery = true)
	List<Region> getActiveRegions();

	@Query(value ="SELECT * FROM region WHERE region_id = :region_id ORDER BY region", nativeQuery = true)
	Region getRegion(@Param("region_id") Integer region_id);
}
