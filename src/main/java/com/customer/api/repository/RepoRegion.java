package com.customer.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Region;

import jakarta.transaction.Transactional;

@Repository
public interface RepoRegion extends JpaRepository<Region, Integer> {

	@Query(value = "SELECT * FROM region ORDER BY region", nativeQuery = true)
	List<Region> getRegions();

	@Query(value = "SELECT * FROM region WHERE status = 1 ORDER BY region", nativeQuery = true)
	List<Region> getActiveRegions();

	@Query(value = "SELECT * FROM region WHERE region_id = :region_id ORDER BY region", nativeQuery = true)
	Region getRegion(@Param("region_id") Integer region_id);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO region (region, tag, status) VALUES (:region, :tag, 1)", nativeQuery = true)
	void createRegion(@Param("region") String region, @Param("tag") String tag);

	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET region = :region, tag = :tag WHERE region_id = :region_id;", nativeQuery = true)
	void updateRegion(@Param("region_id") Integer region_id, @Param("region") String region, @Param("tag") String tag);

	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET status = :status WHERE region_id = :region_id;", nativeQuery = true)
	void updateRegionStatus(@Param("region_id") Integer region_id, @Param("status") Integer status);

	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET status = 1 WHERE region_id = :region_id;", nativeQuery = true)
	void enableRegion(@Param("region_id") Integer region_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE region SET status = 0 WHERE region_id = :region_id;", nativeQuery = true)
	void disableRegion(@Param("region_id") Integer region_id);

}
