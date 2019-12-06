package com.mvc.service;

import java.util.List;

import com.mvc.entity.Address;


public interface AddressService {

	public List<Address> getAll();
	public List<Address> getAllBySearch(String search);
	
	public void save(Address add);
	public void update(Address add);
	public void delete(int noId);
	
	public int nextID();
	
}
