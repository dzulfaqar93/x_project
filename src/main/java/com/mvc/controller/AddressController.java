package com.mvc.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entity.Address;
import com.mvc.service.AddressService;

//@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 1800)
@RestController
public class AddressController {

	@Autowired
	private AddressService addressSvc;

	@RequestMapping(value = "/address", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAll(@RequestParam(value = "find", defaultValue = "", required = false) String search) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Address> list = addressSvc.getAllBySearch(search);
		HttpHeaders headers = new HttpHeaders();
		returnData.put("status", "1");
		returnData.put("desc", "success");
		returnData.put("data", list);
		return new ResponseEntity<>(returnData, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/address", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody Address add) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		List<Address> list = addressSvc.getAll();
		for (Address a : list) {
			if (add.getAddressId() != 0 && a.getAddressId() == add.getAddressId()) {
				returnData.put("status", "0");
				returnData.put("desc", "There is already existing entity with such ID in the database.");
				return new ResponseEntity<>(returnData,HttpStatus.NOT_FOUND);
			}
		}
		add.setAddressId(addressSvc.nextID());
		add.setLastUpdate(new Timestamp(date.getTime()));
		addressSvc.save(add);
		returnData.put("status", "1");
		returnData.put("desc", "success");
		returnData.put("data", add);
		return new ResponseEntity<>(returnData,HttpStatus.CREATED);
	}
}
