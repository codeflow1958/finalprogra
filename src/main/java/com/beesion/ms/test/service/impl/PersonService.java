package com.beesion.ms.test.service.impl;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.repository.PersonRepo; // Importa tu implementación de PersonRepo
import com.beesion.ms.test.service.IPersonService; // Importa la interfaz
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional; // Necesario para métodos que modifican la DB
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio para la gestión de entidades Person.
 * Este servicio actúa como la capa de negocio, orquestando las operaciones
 * con la base de datos a través del repositorio y aplicando la lógica de negocio.
 *
 * Aplica el Principio de Responsabilidad Única (SRP) al centrarse en la lógica de negocio
 * de Personas.
 *
 * Utiliza @ApplicationScoped para ser un bean CDI gestionado por Quarkus.
 */
@ApplicationScoped
public class PersonService implements IPersonService {

	// Inyección del repositorio. Quarkus se encarga de crear e inyectar la instancia.
	// Es CRÍTICO NO USAR 'new PersonRepo()' aquí, ya que rompería el manejo de CDI/Quarkus.
	@Inject
	PersonRepo personRepository; // Nombre de la variable inyectada

	/**
	 * Guarda una nueva entidad Person en la base de datos.
	 * La operación de persistencia se ejecuta dentro de una transacción.
	 *
	 * @param person La entidad Person a guardar.
	 */
	@Override // Indica que este método implementa un método de la interfaz IPersonService
	@Transactional // Las operaciones de escritura siempre deben ser transaccionales
	public void save(Person person) {
		// Usa el repositorio inyectado para persistir la persona
		// PersonRepo.save(Person) es void, por lo que este método también lo es.
		personRepository.save(person);
	}

	/**
	 * Recupera todas las entidades Person de la base de datos.
	 *
	 * @return Una lista de todas las personas.
	 */
	@Override // Indica que este método implementa un método de la interfaz IPersonService
	public List<Person> findAll() {
		// Usa el repositorio inyectado para listar todas las personas
		return personRepository.findAll();
	}

	/**
	 * Busca una entidad Person por su ID.
	 *
	 * @param id El ID de la persona a buscar.
	 * @return Un Optional que contiene la Person si se encuentra, o un Optional vacío si no.
	 */
	@Override // Indica que este método implementa un método de la interfaz IPersonService
	public Optional<Person> findById(Long id) {
		// Usa el repositorio inyectado para encontrar por ID
		// PersonRepo.findById(Long) devuelve Optional<Person>.
		return personRepository.findById(id);
	}

	/**
	 * Elimina una entidad Person de la base de datos por su ID.
	 * La operación de eliminación se ejecuta dentro de una transacción.
	 *
	 * @param id El ID de la persona a eliminar.
	 */
	@Override // Indica que este método implementa un método de la interfaz IPersonService
	@Transactional // Las operaciones de escritura siempre deben ser transaccionales
	public void deleteById(Long id) { // El tipo de retorno debe coincidir con la interfaz (void)
		// Usa el repositorio inyectado para borrar por ID
		personRepository.deleteById(id);
	}
}
