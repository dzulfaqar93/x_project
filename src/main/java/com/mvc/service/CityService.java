package com.mvc.service;

import java.util.List;

import com.mvc.entity.City;

public interface CityService {

	public List<City> getAll();
	public List<City> getAllBySearch(String search);
	
	public void save(City cty);
	public void update(City cty);
	public void delete(int noId);
	
	public int nextID();
	
}
