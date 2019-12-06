package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CountryDao;
import com.mvc.entity.Country;
import com.mvc.service.CountryService;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;
	
	@Override
	public List<Country> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> getAllBySearch(String search) {
		List<Country> list = countryDao.findAllBySearch(search);
		List<Country> newList = new ArrayList<Country>();
		for (Country c : list) {
			Country newCoun = new Country();
			newCoun.setCountryId(c.getCountryId());
			newCoun.setCountry(c.getCountry());
			newCoun.setLastUpdate(c.getLastUpdate());
			newCoun.setCities(c.getCities());
			newList.add(c);
		}
		return newList;
	}

	@Override
	public void save(Country cty) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Country cty) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int noId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int nextID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
