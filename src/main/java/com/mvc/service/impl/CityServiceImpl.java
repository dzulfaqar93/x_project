package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CityDao;
import com.mvc.entity.City;
import com.mvc.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getAll() {
		List<City> list = cityDao.findAll();
		List<City> newList = new ArrayList<City>();
		for (City c : list) {
			City newCity = new City();
			newCity.setCityId(c.getCityId());
			newCity.setCity(c.getCity());
			newCity.setCountry(c.getCountry());
			newCity.setAddresses(c.getAddresses());
			newCity.setLastUpdate(c.getLastUpdate());
			newList.add(c);
		}
		return newList;
	}
	
	@Override
	public List<City> getAllBySearch(String search) {
		List<City> list = cityDao.findAllBySearch(search);
		List<City> newList = new ArrayList<City>();
		for (City c : list) {
			City newCity = new City();
			newCity.setCityId(c.getCityId());
			newCity.setCity(c.getCity());
			newCity.setCountry(c.getCountry());
			newCity.setAddresses(c.getAddresses());
			newCity.setLastUpdate(c.getLastUpdate());
			newList.add(c);
		}
		return newList;
	}

	@Override
	public void save(City cty) {
		int next = this.nextID();
		City newCty = new City();
		newCty.setCityId(next);
		newCty.setCity(cty.getCity());
		newCty.setCountry(cty.getCountry());
		newCty.setAddresses(cty.getAddresses());
		newCty.setLastUpdate(cty.getLastUpdate());
		cityDao.save(newCty);
	}

	@Override
	public void update(City cty) {
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
