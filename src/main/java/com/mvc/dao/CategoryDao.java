package com.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mvc.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

	@Query(nativeQuery = true, value = "SELECT category_id FROM category order by category_id DESC LIMIT 1")
	public int lastID();
	
}
