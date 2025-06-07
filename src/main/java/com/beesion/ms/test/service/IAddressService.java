package com.beesion.ms.test.service;

import com.beesion.ms.test.dto.AddressDto;
import java.util.List;
import java.util.Optional; // Para métodos que puedan devolver un resultado o no

public interface IAddressService {
    AddressDto createAddress(AddressDto addressDto);
    List<AddressDto> listAllAddresses();
    List<AddressDto> findAddressesByPersonId(Long personId);
    Optional<AddressDto> findAddressById(Long id); // Opcional, pero útil para GET individual
    AddressDto updateAddress(Long id, AddressDto addressDto); // Opcional, pero útil para PUT
    boolean deleteAddress(Long id); // Opcional, pero útil para DELETE
}