package com.beesion.ms.test.resource;

import com.beesion.ms.test.dto.AddressDto;
import com.beesion.ms.test.service.IAddressService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/addresses") // La ruta base para las direcciones
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    IAddressService addressService;

    @POST
    public Response createAddress(AddressDto addressDto) {
        try {
            AddressDto createdAddress = addressService.createAddress(addressDto);
            return Response.status(Response.Status.CREATED).entity(createdAddress).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<AddressDto> listAllAddresses() {
        return addressService.listAllAddresses();
    }

    @GET
    @Path("/{id}")
    public Response getAddressById(@PathParam("id") Long id) {
        return addressService.findAddressById(id)
                .map(addressDto -> Response.ok(addressDto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{id}")
    public Response updateAddress(@PathParam("id") Long id, AddressDto addressDto) {
        AddressDto updatedAddress = addressService.updateAddress(id, addressDto);
        if (updatedAddress != null) {
            return Response.ok(updatedAddress).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Long id) {
        if (addressService.deleteAddress(id)) {
            return Response.noContent().build(); // 204 No Content for successful deletion
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}