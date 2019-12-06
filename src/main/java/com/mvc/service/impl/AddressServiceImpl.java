package com.mvc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.AddressDao;
import com.mvc.entity.Address;
import com.mvc.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public List<Address> getAll() {
		List<Address> list = addressDao.findAll();
		List<Address> newList = new ArrayList<Address>();
		for (Address a : list) {
			a.getAddressId();
			a.getAddress();
			a.getAddress2();
			a.getDistrict();
			a.getCity();
			a.getPostalCode();
			a.getPhone();
			a.getLastUpdate();
			newList.add(a);
		}
		return newList;
	}
	
	@Override
	public List<Address> getAllBySearch(String search) {
		List<Address> list = addressDao.findAllBySearch(search);
		List<Address> addressList = new ArrayList<Address>();
		for (Address a : list) {
			Address newAdd = new Address();
			newAdd.setAddressId(a.getAddressId());
			newAdd.setAddress(a.getAddress());
			newAdd.setAddress2(a.getAddress2());
			newAdd.setCity(a.getCity());
			newAdd.setDistrict(a.getDistrict());
			newAdd.setPostalCode(a.getPostalCode());
			newAdd.setPhone(a.getPhone());
			newAdd.setLastUpdate(a.getLastUpdate());
			
			addressList.add(newAdd);
		}
		return addressList;
	}

	@Override
	public void save(Address add) {
		Date date = new Date();
		Address newAdd = new Address();
		newAdd.setAddressId(this.nextID());
		newAdd.setAddress(add.getAddress());
		newAdd.setAddress2(add.getAddress2());
		newAdd.setDistrict(add.getDistrict());
		newAdd.setCity(add.getCity());
		newAdd.setPostalCode(add.getPostalCode());
		newAdd.setPhone(add.getPhone());
		newAdd.setLastUpdate(new Timestamp(date.getTime()));
		addressDao.save(newAdd);
	}

	@Override
	public void update(Address add) {
		Address a = addressDao.findOne(add.getAddressId());
		Date date = new Date();
		if (add != null) {
			a.setAddressId(add.getAddressId());
			a.setAddress(add.getAddress());
			a.setAddress2(add.getAddress2());
			a.setDistrict(add.getDistrict());
			a.setCity(add.getCity());
			a.setPostalCode(add.getPostalCode());
			a.setPhone(add.getPhone());
			a.setLastUpdate(new Timestamp(date.getTime()));
			addressDao.save(a);
		}
	}

	@Override
	public void delete(int noId) {
		addressDao.delete(noId);
	}

	@Override
	public int nextID() {
		int next = addressDao.lastID() + 1;
		return next;
	}

}
