// src/main/java/com/beesion/ms/test/dto/AddressDto.java
package com.beesion.ms.test.dto;

import lombok.Data; // Asegúrate de tener Lombok en tu pom.xml para @Data

@Data // Genera getters, setters, toString, equals y hashCode
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private Long personId; // Para asociar la dirección a una persona
}