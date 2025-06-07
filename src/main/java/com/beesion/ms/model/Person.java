// src/main/java/com/beesion/ms/model/Person.java
package com.beesion.ms.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons") // Buena práctica: usar un nombre de tabla explícito
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Address> addresses = new ArrayList<>(); // Inicialización de la lista

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	// Método de conveniencia para añadir una dirección
	public void addAddress(Address address) {
		addresses.add(address);
		address.setPerson(this);
	}

	// Método de conveniencia para remover una dirección
	public void removeAddress(Address address) {
		addresses.remove(address);
		address.setPerson(null);
	}
}