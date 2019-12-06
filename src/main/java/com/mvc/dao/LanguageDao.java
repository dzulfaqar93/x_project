package com.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mvc.entity.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

	@Query(nativeQuery = true, value = "SELECT language_id FROM language order by language_id DESC LIMIT 1")
	public int lastID();
	
}
