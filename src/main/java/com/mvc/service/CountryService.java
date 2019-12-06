package com.mvc.service;

import java.util.List;

import com.mvc.entity.Country;

public interface CountryService {
	
	public List<Country> getAll();
	public List<Country> getAllBySearch(String search);
	
	public void save(Country cty);
	public void update(Country cty);
	public void delete(int noId);
	
	public int nextID();

}
