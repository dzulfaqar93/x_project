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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entity.Language;
import com.mvc.service.LanguageService;

//@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 1800)
@RestController
public class LanguageController {

	@Autowired
	private LanguageService svc;

	@RequestMapping(value = "/lang", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAll() {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Language> list = svc.getAll();
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
	
	@RequestMapping(value = "/lang/{noId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showActor(@PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Language lang = svc.getOne(noId);
		HttpHeaders headers = new HttpHeaders();
		if (lang != null) {
			returnData.put("status", "1");
			returnData.put("desc", "success");
			returnData.put("data", lang);
			return new ResponseEntity<>(returnData, headers, HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData, headers, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/lang", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody Language lang) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		List<Language> list = svc.getAll();
		for (Language l : list) {
			if (lang.getLanguageId() != 0 && l.getLanguageId() == lang.getLanguageId()) {
				returnData.put("status", "0");
				returnData.put("desc", "There is already existing entity with such ID in the database.");
				return new ResponseEntity<>(returnData, HttpStatus.NOT_FOUND);
			}
		}
		lang.setLanguageId(svc.nextID());
		lang.setLastUpdate(new Timestamp(date.getTime()));
		svc.save(lang);
		returnData.put("status", "1");
		returnData.put("desc", "success");
		returnData.put("data", lang);
		return new ResponseEntity<>(returnData, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/lang/{noId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Language lang, @PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		if (lang.getLanguageId() == noId) {
			lang.setLastUpdate(new Timestamp(date.getTime()));
			svc.update(lang);
			returnData.put("status", "1");
			returnData.put("desc", "success");
			returnData.put("data", lang);
			return new ResponseEntity<>(returnData, HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/lang/{noId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (noId != 0) {
			svc.delete(noId);
			returnData.put("status", "1");
			returnData.put("desc", "success");
			return new ResponseEntity<>(returnData, HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData, HttpStatus.NOT_FOUND);
		}
	}

}
