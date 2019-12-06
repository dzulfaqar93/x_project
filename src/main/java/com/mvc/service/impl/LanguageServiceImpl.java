package com.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.LanguageDao;
import com.mvc.entity.Language;
import com.mvc.service.LanguageService;

@Service
@Transactional
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageDao languageDao;
	
	@Override
	public List<Language> getAll() {
		List<Language> list = languageDao.findAll();
		List<Language> newList = new ArrayList<Language>();
		for (Language l : list) {
			Language newLang = new Language();
			newLang.setLanguageId(l.getLanguageId());
			newLang.setName(l.getName());
			newLang.setLastUpdate(l.getLastUpdate());
			newList.add(newLang);
		}
		return newList;
	}
	
	@Override
	public Language getOne(int id) {
		Language l = languageDao.getOne(id);
		Language newLang = new Language();
		newLang.setLanguageId(l.getLanguageId());
		newLang.setName(l.getName());
		newLang.setLastUpdate(l.getLastUpdate());
		return newLang;
	}

	@Override
	public void save(Language lang) {
		int next = this.nextID();
		Language newLang = new Language();
		newLang.setLanguageId(next);
		newLang.setName(lang.getName());
		newLang.setLastUpdate(lang.getLastUpdate());
		languageDao.save(newLang);
	}

	@Override
	public void update(Language lang) {
		Language newLang = languageDao.findOne(lang.getLanguageId());
		if (lang != null) {
			newLang.setLanguageId(lang.getLanguageId());
			newLang.setName(lang.getName());
			newLang.setLastUpdate(lang.getLastUpdate());
			languageDao.save(newLang);
		}
	}

	@Override
	public void delete(int noId) {
		languageDao.delete(noId);;
	}

	@Override
	public int nextID() {
		int id = languageDao.lastID() + 1;
		return id;
	}

}
