package com.mvc.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mvc.entity.Actor;

public interface ActorDao extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.firstName like %:search% OR a.lastName like %:search%")
	public List<Actor> findActorsBySearch(@Param("search") String searchName);
	
	@Query("select a from Actor a where a.firstName like %:search% OR a.lastName like %:search%")
	public List<Actor> findActorsWithPage(@Param("search") String searchName, Pageable page);
	
	@Query(nativeQuery = true, value = "SELECT actor_id FROM actor order by actor_id DESC LIMIT 1")
	public int lastID();
	
	@Query("select count(a.actorId) from Actor a")
	public int countData();
	
}
