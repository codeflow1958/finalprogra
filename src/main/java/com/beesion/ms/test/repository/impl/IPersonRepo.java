package com.beesion.ms.test.repository.impl; // Matches the import in PersonRepo

import com.beesion.ms.model.Person;
import java.util.List;
import java.util.Optional; // Use Optional for findById, it's good practice

/**
 * Interfaz para el repositorio de Personas.
 * Define las operaciones CRUD básicas para la entidad Person.
 */
public interface IPersonRepo {
	void save(Person person);
	List<Person> findAll();
	Optional<Person> findById(Long id);
	void deleteById(Long id);
	// Add any other methods your services might need to call on the repository
}