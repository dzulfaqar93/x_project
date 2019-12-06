package com.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.City;

public interface CityDao extends JpaRepository<City, Integer> {
	
	@Query("select c from City c where c.city like %:search%")
	public List<City> findAllBySearch(@Param("search") String searchName);

	@Query(nativeQuery = true, value = "SELECT city_id FROM city order by city_id DESC LIMIT 1")
	public int lastID();
	
}
