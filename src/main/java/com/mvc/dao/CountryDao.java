package com.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Country;

public interface CountryDao extends JpaRepository<Country, Integer>{

	@Query("select c from Country c where c.country like %:search%")
	public List<Country> findAllBySearch(@Param("search") String searchName);

	@Query(nativeQuery = true, value = "SELECT country_id FROM country order by country_id DESC LIMIT 1")
	public int lastID();
	
}
