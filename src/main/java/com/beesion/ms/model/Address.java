// src/main/java/com/beesion/ms/model/Address.java
package com.beesion.ms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses") // Buena práctica: usar un nombre de tabla explícito
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de ID
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY) // Muchas direcciones a una persona
    @JoinColumn(name = "person_id", nullable = false) // Columna de clave foránea
    private Person person;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}