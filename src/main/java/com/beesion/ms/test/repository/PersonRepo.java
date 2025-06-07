package com.beesion.ms.test.repository;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.impl.IPersonRepo; // Keep this import

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional; // Import Optional

/**
 * Implementación concreta del repositorio para la entidad Person
 * utilizando EntityManager clásico.
 */
@ApplicationScoped
public class PersonRepo implements IPersonRepo {

	@Inject
	EntityManager em;

	/**
	 * Guarda una nueva persona en la base de datos o actualiza una existente.
	 * @param person La entidad Person a guardar.
	 */
	@Override
	@Transactional
	public void save(Person person) {
		em.persist(person); // For new entities
		// For updating existing entities, persist also works if the entity is managed,
		// or you might use em.merge(person); if it's detached.
		// However, em.persist() is fine for saving new ones.
	}

	/**
	 * Recupera todas las personas de la base de datos.
	 * @return Una lista de todas las entidades Person.
	 */
	@Override
	public List<Person> findAll() {
		// Example using JPQL (Java Persistence Query Language)
		return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
		// Or if you want to use criteria API or named queries, you can do that too.
	}

	/**
	 * Busca una persona por su ID.
	 * @param id El ID de la persona a buscar.
	 * @return Un Optional que contiene la Person si se encuentra, o un Optional vacío si no.
	 */
	@Override
	public Optional<Person> findById(Long id) {
		// find() returns null if not found
		return Optional.ofNullable(em.find(Person.class, id));
	}

	/**
	 * Elimina una persona de la base de datos por su ID.
	 * @param id El ID de la persona a eliminar.
	 */
	@Override
	@Transactional // Deleting needs a transaction
	public void deleteById(Long id) {
		Person person = em.find(Person.class, id);
		if (person != null) {
			em.remove(person);
		}
	}
}