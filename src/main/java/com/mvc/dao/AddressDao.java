package com.mvc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Address;

public interface AddressDao extends JpaRepository<Address, Integer> {

	@Query("select a from Address a where a.address like %:search% or a.district like %:search%")
	public List<Address> findAllBySearch(@Param("search") String searchName);
	
	@Query(nativeQuery = true, value = "SELECT address_id FROM address order by address_id DESC LIMIT 1")
	public int lastID();
}
