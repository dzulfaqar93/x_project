package com.mvc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.ActorDao;
import com.mvc.entity.Actor;
import com.mvc.service.ActorService;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorDao actorDao;
	
	@Override
	public List<Actor> getAll() {
		List<Actor> list = actorDao.findAll();
		List<Actor> newList = new ArrayList<Actor>();
		for (Actor a : list) {
			a.getActorId();
			a.getFirstName();
			a.getLastName();
			a.getLastUpdate();
			newList.add(a);
		}
		return newList;
	}

	@Override
	public List<Actor> getAllBySearch(String search) {
		List<Actor> list = actorDao.findActorsBySearch(search);
		List<Actor> newList = new ArrayList<Actor>();
		for (Actor a : list) {
			Actor newAct = new Actor();
			newAct.setActorId(a.getActorId());
			newAct.setFirstName(a.getFirstName());
			newAct.setLastName(a.getLastName());
			newAct.setLastUpdate(a.getLastUpdate());
//			Date date = new Date(a.getLastUpdate().getTime());
			newList.add(a);
		}
		return newList;
	}
	
	@Override
	public Actor getOne(int id) {
		Actor a = actorDao.findOne(id);
		Actor newAct = new Actor();
		newAct.setActorId(a.getActorId());
		newAct.setFirstName(a.getFirstName());
		newAct.setLastName(a.getLastName());
		newAct.setLastUpdate(a.getLastUpdate());
		return newAct;
	}

	@Override
	public void save(Actor act) {
		int next = this.nextID();
		Actor newAct = new Actor();
		newAct.setActorId(next);
		newAct.setFirstName(act.getFirstName());
		newAct.setLastName(act.getLastName());
		newAct.setLastUpdate(act.getLastUpdate());
		actorDao.save(newAct);
	}

	@Override
	public void update(Actor act) {
		Actor a = actorDao.findOne(act.getActorId());
		Date date = new Date();
		if (act != null) {
			a.setActorId(act.getActorId());
			a.setFirstName(act.getFirstName());
			a.setLastName(act.getLastName());
//			a.setLastUpdate(act.getLastUpdate());
			a.setLastUpdate(new Timestamp(date.getTime()));
			actorDao.save(a);
		}
	}

	@Override
	public void delete(int noId) {
		actorDao.delete(noId);
	}

	@Override
	public void dele(Actor act) {
		actorDao.delete(act);
	}

	@Override
	public int nextID() {
		int id = actorDao.lastID() + 1;
		return id;
	}

	@Override
	public Map<String, Object> listPagination(String search, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		int perPage = 10;
		
		Pageable pageable = new PageRequest(page-1, perPage, new Sort(new Sort.Order(Direction.fromString("asc"), "actorId")));
		List<Actor> list = actorDao.findActorsWithPage(search, pageable);
		List<Actor> newList = new ArrayList<Actor>();
		for (Actor a : list) {
			Actor newAct = new Actor();
			newAct.setActorId(a.getActorId());
			newAct.setFirstName(a.getFirstName());
			newAct.setLastName(a.getLastName());
			newAct.setLastUpdate(a.getLastUpdate());
			newList.add(newAct);
		}
		
		int totalPage = 0;
		int countData = actorDao.countData();
		
		totalPage = countData / perPage;
		if (countData % perPage > 0) {
			totalPage = totalPage + 1;
		}
		
		map.put("data", newList);
		map.put("total", totalPage);
		
		return map;
	}

}
