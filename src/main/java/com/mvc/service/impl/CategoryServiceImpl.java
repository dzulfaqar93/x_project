package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CategoryDao;
import com.mvc.entity.Category;
import com.mvc.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getAll() {
		List<Category> list = categoryDao.findAll();
		List<Category> newList = new ArrayList<Category>();
		for (Category c : list) {
			Category newCat = new Category();
			newCat.setCategoryId(c.getCategoryId());
			newCat.setName(c.getName());
			newCat.setLastUpdate(c.getLastUpdate());
			newList.add(newCat);
		}
		return newList;
	}

	@Override
	public void save(Category cat) {
		int next = this.nextID();
		Category newCat = new Category();
		newCat.setCategoryId(next);
		newCat.setName(cat.getName());
		newCat.setLastUpdate(cat.getLastUpdate());
		categoryDao.save(newCat);
	}

	@Override
	public void update(Category cat) {
		Category newCat = categoryDao.findOne(cat.getCategoryId());
		if (cat != null) {
			newCat.setCategoryId(cat.getCategoryId());
			newCat.setName(cat.getName());
			newCat.setLastUpdate(cat.getLastUpdate());
			categoryDao.save(newCat);
		}
	}

	@Override
	public void delete(int noId) {
		categoryDao.delete(noId);
	}

	@Override
	public int nextID() {
		int id = categoryDao.lastID() + 1;
		return id;
	}

}
