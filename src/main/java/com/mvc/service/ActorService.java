package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.entity.Actor;

public interface ActorService {

	public List<Actor> getAll();
	public List<Actor> getAllBySearch(String search);
	public Actor getOne(int id);
	
	public void save(Actor act);
	public void update(Actor act);
	public void delete(int noId);
	public void dele(Actor act);
	
	public int nextID();
	
	public Map<String, Object> listPagination(String search, int page);
}
