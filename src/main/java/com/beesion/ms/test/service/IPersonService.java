package com.beesion.ms.test.service;

import com.beesion.ms.model.Person;
import java.util.List;
import java.util.Optional;

public interface IPersonService {
	void save(Person person);
	List<Person> findAll();
	Optional<Person> findById(Long id);
	void deleteById(Long id);
}