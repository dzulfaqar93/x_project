package com.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entity.City;
import com.mvc.service.CityService;

//@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 1800)
@RestController
public class CityController {

	@Autowired
	private CityService citySvc;
	
	@RequestMapping(value = "/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAll(@RequestParam(value = "find", defaultValue = "", required = false) String search) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<City> list = citySvc.getAllBySearch(search);
		HttpHeaders headers = new HttpHeaders();
		if (list.isEmpty()) {
			returnData.put("status", "0");
			returnData.put("desc", "success");
			returnData.put("data", "not found");
			return new ResponseEntity<>(returnData, headers, HttpStatus.NOT_FOUND);
		}
		returnData.put("status", "1");
		returnData.put("desc", "success");
		returnData.put("data", list);
		return new ResponseEntity<>(returnData, headers, HttpStatus.OK);
	}
}
