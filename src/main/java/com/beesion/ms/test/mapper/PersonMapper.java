package com.beesion.ms.test.mapper;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.dto.PersonDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // Anotado para que pueda ser inyectado como un bean CDI
public class PersonMapper {

    // Justificación: Esta clase PersonMapper encapsula la lógica de conversión entre Person y PersonDto,
    // aplicando el Principio de Responsabilidad Única al separar el mapeo del recurso y el servicio.

    public Person toEntity(PersonDto dto) {
        if (dto == null) {
            return null;
        }
        Person person = new Person();
        person.setName(dto.getName());
        // Asume que Person tiene un setter para 'id' si lo necesitas para actualizaciones,
        // pero para creación, el ID generalmente lo maneja la base de datos.
        if (dto.getId() != null) {
            person.setId(dto.getId());
        }
        return person;
    }

    public PersonDto toDto(Person entity) {
        if (entity == null) {
            return null;
        }
        PersonDto dto = new PersonDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
