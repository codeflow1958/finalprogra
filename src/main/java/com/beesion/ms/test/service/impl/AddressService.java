package com.beesion.ms.test.service.impl;

import com.beesion.ms.model.Person;
import com.beesion.ms.model.Address; // Asumiendo que tienes una entidad Address
import com.beesion.ms.test.repository.PersonRepo; // Tu repositorio de Personas
import com.beesion.ms.test.repository.AddressRepo; // Asumiendo que tienes un AddressRepo
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional; // Necesario para Optional
import java.util.List; // Para métodos que devuelvan listas

/**
 * Servicio para la gestión de entidades Address.
 * Este servicio orquesta las operaciones relacionadas con las direcciones,
 * incluyendo interacciones con el repositorio de Personas.
 */
@ApplicationScoped
public class AddressService {

    @Inject
    PersonRepo personRepository; // Repositorio de Personas inyectado

    @Inject
    AddressRepo addressRepository; // Repositorio de Direcciones inyectado (asumiendo que existe)

    /**
     * Ejemplo de método que podría usar personRepository.findById(id).
     * Simula el escenario donde ocurre el error en la línea 54.
     * @param personId El ID de la persona para la cual se busca la dirección.
     * @return La persona asociada a la dirección, si existe.
     * @throws RuntimeException Si la persona no es encontrada.
     */
    public Person getPersonForAddress(Long personId) {
        // Corrección para la línea 54: Manejar Optional<Person>
        Optional<Person> personOptional = personRepository.findById(personId); // La llamada retorna Optional

        // Desenvolver el Optional. Lanza una excepción si la persona no está presente.
        // Se utiliza RuntimeException como ejemplo; considera una excepción de negocio más específica.
        Person person = personOptional.orElseThrow(() ->
                new RuntimeException("Persona con ID " + personId + " no encontrada para la dirección. (Línea 54 simulada)"));

        // ... más lógica relacionada con la persona y la dirección ...
        return person;
    }

    /**
     * Guarda una nueva dirección en la base de datos.
     * @param address La entidad Address a guardar.
     * @param ownerId El ID de la persona propietaria de la dirección.
     * @return La dirección guardada.
     * @throws RuntimeException Si el propietario (persona) no es encontrado.
     */
    @Transactional
    public Address saveAddress(Address address, Long ownerId) {
        // Corrección para la línea 101: Manejar Optional<Person>
        Optional<Person> ownerOptional = personRepository.findById(ownerId); // La llamada retorna Optional

        // Desenvolver el Optional. Lanza una excepción si el propietario no está presente.
        Person owner = ownerOptional.orElseThrow(() ->
                new RuntimeException("Propietario con ID " + ownerId + " no encontrado para la dirección. (Línea 101 simulada)"));

        address.setPerson(owner); // Asocia la persona propietaria a la dirección
        addressRepository.persist(address); // Asumiendo que AddressRepo.persist() existe
        return address;
    }

    // Otros métodos de AddressService que podrías tener:

    /**
     * Obtiene todas las direcciones.
     * @return Lista de todas las direcciones.
     */
    public List<Address> findAllAddresses() {
        return addressRepository.listAll(); // Asumiendo que AddressRepo.listAll() existe
    }

    /**
     * Elimina una dirección por su ID.
     * @param id El ID de la dirección a eliminar.
     */
    @Transactional
    public void deleteAddress(Long id) {
        // Puedes buscarla primero o directamente llamar al método de borrado del repo
        addressRepository.deleteById(id); // Asumiendo que AddressRepo.deleteById() existe
    }
}
