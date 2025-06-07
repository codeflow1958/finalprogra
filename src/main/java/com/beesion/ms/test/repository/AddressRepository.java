package com.beesion.ms.test.repository;

import com.beesion.ms.model.Address;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional; // Aunque Panache a menudo maneja transacciones en métodos de escritura, es bueno indicarlo.

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
    // Panache proporciona automáticamente métodos CRUD.
    // No necesitas implementar nada aquí a menos que requieras consultas personalizadas.
}