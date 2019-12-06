package com.mvc.service;

import java.util.List;

import com.mvc.entity.Category;


public interface CategoryService {
	
	public List<Category> getAll();

	public void save(Category cat);

	public void update(Category cat);

	public void delete(int noId);

	public int nextID();

}
