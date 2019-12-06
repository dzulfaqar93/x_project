package com.mvc.service;

import java.util.List;

import com.mvc.entity.Language;

public interface LanguageService {

	public List<Language> getAll();
	public Language getOne(int id);

	public void save(Language lang);

	public void update(Language lang);

	public void delete(int noId);

	public int nextID();

}
