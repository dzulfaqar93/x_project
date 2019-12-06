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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entity.Category;
import com.mvc.service.CategoryService;

//@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 1800)
@RestController
public class CategoryController {

	@Autowired
	private CategoryService svc;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAll() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Category> list = svc.getAll();
		HttpHeaders headers = new HttpHeaders();
		if (!list.isEmpty()) {
			returnData.put("status", "1");
			returnData.put("desc", "success");
			returnData.put("data", list);
			return new ResponseEntity<>(returnData, headers, HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData, headers, HttpStatus.NOT_FOUND);
		}
	}
		
	@RequestMapping(value = "/categories", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody Category cat) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		List<Category> list = svc.getAll();
		for (Category c : list) {
			if (cat.getCategoryId() != 0 && c.getCategoryId() == cat.getCategoryId()) {
				returnData.put("status", "0");
				returnData.put("desc", "There is already existing entity with such ID in the database.");
				return new ResponseEntity<>(returnData,HttpStatus.NOT_FOUND);
			}
		}
		cat.setCategoryId(svc.nextID());
		cat.setLastUpdate(new Timestamp(date.getTime()));
		svc.save(cat);
		returnData.put("status", "1");
		returnData.put("desc", "success");
		returnData.put("data", cat);
		return new ResponseEntity<>(returnData,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/categories/{noId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Category cat, @PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		if (cat.getCategoryId() == noId) {
			cat.setLastUpdate(new Timestamp(date.getTime()));
			svc.update(cat);
			returnData.put("status", "1");
			returnData.put("desc", "success");
			returnData.put("data", cat);
			return new ResponseEntity<>(returnData,HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/categories/{noId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (noId != 0) {
			svc.delete(noId);
			returnData.put("status", "1");
			returnData.put("desc", "success");
			return new ResponseEntity<>(returnData,HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData,HttpStatus.NOT_FOUND);
		}
	}
	
}
