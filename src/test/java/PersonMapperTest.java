package com.beesion.ms.test.service.impl;

import com.beesion.ms.model.Person;
import com.beesion.ms.test.dto.PersonDto;
import com.beesion.ms.test.mapper.PersonMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitario para la clase PersonMapper.
 * Verifica que las conversiones entre PersonDto y Person se realicen correctamente.
 */
public class PersonMapperTest {

    // Instancia del mapper que se va a probar.
    // No se necesita inyección de CDI aquí, ya que es una prueba unitaria pura.
    private PersonMapper personMapper = new PersonMapper();

    /**
     * Verifica la conversión de PersonDto a Person (toEntity).
     * Asegura que los campos se mapean correctamente y que la entidad no es nula.
     */
    @Test
    void testToEntity() {
        // 1. Prepara el DTO de entrada
        PersonDto dto = new PersonDto();
        dto.setId(1L); // El ID puede estar presente si es una actualización, o nulo para creación
        dto.setName("Alice Smith");

        // 2. Realiza la conversión
        Person person = personMapper.toEntity(dto);

        // 3. Verifica el resultado
        assertNotNull(person, "La entidad Person no debería ser nula.");
        assertEquals(dto.getId(), person.getId(), "El ID debería mapearse correctamente.");
        assertEquals(dto.getName(), person.getName(), "El nombre debería mapearse correctamente.");
    }

    /**
     * Verifica la conversión de Person a PersonDto (toDto).
     * Asegura que los campos se mapean correctamente y que el DTO no es nulo.
     */
    @Test
    void testToDto() {
        // 1. Prepara la entidad de entrada
        Person person = new Person();
        person.setId(2L);
        person.setName("Bob Johnson");

        // 2. Realiza la conversión
        PersonDto dto = personMapper.toDto(person);

        // 3. Verifica el resultado
        assertNotNull(dto, "El DTO PersonDto no debería ser nulo.");
        assertEquals(person.getId(), dto.getId(), "El ID debería mapearse correctamente.");
        assertEquals(person.getName(), dto.getName(), "El nombre debería mapearse correctamente.");
    }

    /**
     * Verifica que el mapeo de un DTO nulo a entidad resulta en una entidad nula.
     */
    @Test
    void testToEntityWithNullDto() {
        assertNull(personMapper.toEntity(null), "Mapear un DTO nulo debería resultar en una entidad nula.");
    }

    /**
     * Verifica que el mapeo de una entidad nula a DTO resulta en un DTO nulo.
     */
    @Test
    void testToDtoWithNullEntity() {
        assertNull(personMapper.toDto(null), "Mapear una entidad nula debería resultar en un DTO nulo.");
    }
}
