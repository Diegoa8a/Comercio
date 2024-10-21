package com.comercio.cliente.utils;

import com.comercio.cliente.builder.ClienteDTOBuilder;
import com.comercio.cliente.builder.ClienteEntityBuilder;
import com.comercio.cliente.dto.ClienteDTO;
import com.comercio.cliente.model.ClienteEntity;

public class BuilderUtil {

    public static ClienteDTO buildClienteDTO(ClienteEntity clienteEntity) {
        return new ClienteDTOBuilder.Builder()
                .withId(clienteEntity.getId())
                .withNombre(clienteEntity.getNombre())
                .withTipoDocumento(clienteEntity.getTipoDocumento())
                .withNumeroDocumento(clienteEntity.getNumeroDocumento())
                .withCorreo(clienteEntity.getCorreo())
                .withDireccion(clienteEntity.getDireccion())
                .withActivo(clienteEntity.getActivo())
                .build();

    }

    public static ClienteEntity buildClienteEntity(ClienteDTO clienteDTO) {
        return new ClienteEntityBuilder.Builder()
                .withId(clienteDTO.getId())
                .withNombre(clienteDTO.getNombre())
                .withTipoDocumento(clienteDTO.getTipoDocumento())
                .withNumeroDocumento(clienteDTO.getNumeroDocumento())
                .withCorreo(clienteDTO.getCorreo())
                .withDireccion(clienteDTO.getDireccion())
                .withActivo(clienteDTO.getActivo())
                .build();

    }

}
