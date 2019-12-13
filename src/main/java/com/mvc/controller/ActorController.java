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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.entity.Actor;
import com.mvc.service.ActorService;

//@CrossOrigin(origins = "http://localhost:7800", allowedHeaders = "*", allowCredentials = "true", maxAge = 1800)
@RestController
public class ActorController {

	@Autowired
	private ActorService actSvc;

	@RequestMapping(value = "/actor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAllSearch(
			@RequestParam(value = "find", defaultValue = "", required = false) String search) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		List<Actor> list = actSvc.getAllBySearch(search);
		HttpHeaders headers = new HttpHeaders();
//		headers.set("Access-Control-Allow-Origin", "*");
//		headers.set("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
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

	//PAGINATIONS
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/actors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showAllWithPage(
			@RequestParam(value = "find", defaultValue = "", required = false) String search,
			@RequestParam(value = "page", defaultValue = "1", required = false) int page) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Map<String, Object> map = actSvc.listPagination(search, page);
		List<Actor> list = (List<Actor>) map.get("data");
//		int total = (int) map.get("total");
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

	@RequestMapping(value = "/actor/{noId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showActor(@PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Actor act = actSvc.getOne(noId);
		HttpHeaders headers = new HttpHeaders();
		if (act != null) {
			returnData.put("status", "1");
			returnData.put("desc", "success");
			returnData.put("data", act);
			return new ResponseEntity<>(returnData, headers, HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData, headers, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/actor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> insert(@RequestBody Actor act) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		List<Actor> list = actSvc.getAll();
		for (Actor a : list) {
			if (act.getActorId() != 0 && a.getActorId() == act.getActorId()) {
				returnData.put("status", "0");
				returnData.put("desc", "There is already existing entity with such ID in the database.");
				return new ResponseEntity<>(returnData, HttpStatus.NOT_FOUND);
			}
		}
		act.setActorId(actSvc.nextID());
		act.setLastUpdate(new Timestamp(date.getTime()));
		actSvc.save(act);
		returnData.put("status", "1");
		returnData.put("desc", "success");
		returnData.put("data", act);
		return new ResponseEntity<>(returnData, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/actor/{noId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Actor act, @PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		Date date = new Date();
		if (act.getActorId() == noId) {
			act.setLastUpdate(new Timestamp(date.getTime()));
			actSvc.update(act);
			returnData.put("status", "1");
			returnData.put("desc", "success");
			returnData.put("data", act);
			return new ResponseEntity<>(returnData, HttpStatus.OK);
		} else {
			returnData.put("status", "0");
			returnData.put("desc", "There is no entity with such ID in the database.");
			return new ResponseEntity<>(returnData, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/actor/{noId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable("noId") int noId) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		if (noId != 0) {
			actSvc.delete(noId);
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
