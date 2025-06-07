package com.beesion.ms.test.resource;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.dto.PersonDto; // Assuming you have a PersonDto
import com.beesion.ms.test.mapper.PersonMapper; // Assuming you have a PersonMapper
import com.beesion.ms.test.service.IPersonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional; // Import for Optional

/**
 * Recurso REST para la gestión de entidades Person.
 * Proporciona endpoints para operaciones CRUD.
 */
@Path("/personas") // Define el path base para este recurso
@Produces(MediaType.APPLICATION_JSON) // Por defecto, produce JSON
@Consumes(MediaType.APPLICATION_JSON) // Por defecto, consume JSON
public class PersonaResource {

	@Inject
	IPersonService personService; // Inyecta la interfaz del servicio

	@Inject
	PersonMapper personMapper; // Inyecta el mapper para convertir entre entidades y DTOs

	/**
	 * Endpoint para crear una nueva persona.
	 * @param personDto El DTO de la persona a crear.
	 * @return Respuesta HTTP con el DTO de la persona creada y estado 201 Created.
	 */
	@POST
	public Response createPerson(PersonDto personDto) {
		Person person = personMapper.toEntity(personDto); // Convierte DTO a entidad
		personService.save(person); // Guarda la persona (el servicio ya no retorna Person, ahora es void)
		// Para obtener el ID generado (si tu entidad lo genera al guardar),
		// la entidad 'person' después de 'save' debería tener el ID si está gestionada por JPA.
		// Si no, necesitarías un servicio.save que retorne la entidad guardada con ID.
		// Asumiendo que 'person' se actualiza con el ID después de 'save'.
		PersonDto createdDto = personMapper.toDto(person);
		return Response.status(Response.Status.CREATED).entity(createdDto).build();
	}

	/**
	 * Endpoint para obtener todas las personas.
	 * @return Una lista de DTOs de personas.
	 */
	@GET
	public List<PersonDto> getAllPersons() {
		List<Person> persons = personService.findAll(); // Obtiene todas las entidades
		return personMapper.toDtoList(persons); // Convierte la lista de entidades a lista de DTOs
	}

	/**
	 * Endpoint para obtener una persona por su ID.
	 * @param id El ID de la persona.
	 * @return Respuesta HTTP con el DTO de la persona y estado 200 OK, o 404 Not Found si no existe.
	 */
	@GET
	@Path("/{id}")
	public Response getPersonById(@PathParam("id") Long id) {
		// Manejo de Optional:
		Optional<Person> personOptional = personService.findById(id);

		// Si la persona está presente, la obtenemos y mapeamos a DTO
		if (personOptional.isPresent()) {
			PersonDto personDto = personMapper.toDto(personOptional.get()); // Obtiene la entidad y la mapea a DTO
			return Response.ok(personDto).build(); // Retorna 200 OK con la persona
		} else {
			// Si la persona no se encuentra, retornamos 404 Not Found
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	/**
	 * Endpoint para actualizar una persona existente por su ID.
	 * @param id El ID de la persona a actualizar.
	 * @param personDto El DTO de la persona con los datos actualizados.
	 * @return Respuesta HTTP con el DTO de la persona actualizada y estado 200 OK,
	 * o 404 Not Found si la persona no existe.
	 */
	@PUT
	@Path("/{id}")
	public Response updatePerson(@PathParam("id") Long id, PersonDto personDto) {
		// Primero, verifica si la persona existe
		Optional<Person> existingPersonOptional = personService.findById(id);

		if (existingPersonOptional.isPresent()) {
			Person existingPerson = existingPersonOptional.get();
			// Actualiza los campos de la entidad existente con los del DTO
			// Asumiendo que el mapper maneja la actualización parcial o completa.
			// Si tu mapper no tiene 'updateEntityFromDto', deberías actualizar manualmente
			// los campos: existingPerson.setName(personDto.getName()); etc.
			Person updatedPerson = personMapper.updateEntityFromDto(personDto, existingPerson);
			// Asegúrate de que el ID de la entidad se mantenga, ya que es una actualización.
			updatedPerson.setId(id); // Importante para que el save actualice el registro correcto
			personService.save(updatedPerson); // Guarda la persona (el servicio ya no retorna Person, ahora es void)
			PersonDto updatedDto = personMapper.toDto(updatedPerson);
			return Response.ok(updatedDto).build(); // Retorna 200 OK con la persona actualizada
		} else {
			return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 Not Found
		}
	}


	/**
	 * Endpoint para eliminar una persona por su ID.
	 * @param id El ID de la persona a eliminar.
	 * @return Respuesta HTTP con estado 204 No Content si la eliminación fue exitosa,
	 * o 404 Not Found si la persona no existe.
	 */
	@DELETE
	@Path("/{id}")
	public Response deletePerson(@PathParam("id") Long id) {
		// Optamos por verificar primero para dar un feedback más preciso (404 vs 204).
		Optional<Person> personOptional = personService.findById(id);
		if (personOptional.isPresent()) {
			personService.deleteById(id); // Llama al servicio, que ahora es void
			return Response.noContent().build(); // Retorna 204 No Content
		} else {
			return Response.status(Response.Status.NOT_FOUND).build(); // Retorna 404 Not Found
		}
	}
}
