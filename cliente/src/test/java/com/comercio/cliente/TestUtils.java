package com.comercio.cliente;

import com.comercio.cliente.dto.ClienteDTO;
import com.comercio.cliente.model.ClienteEntity;

public class TestUtils {
    public static ClienteDTO createClienteDTO() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNumeroDocumento("123456");
        clienteDTO.setNombre("John");
        clienteDTO.setCorreo("john.doe@example.com");
        return clienteDTO;
    }

    public static ClienteEntity createClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNumeroDocumento("123456");
        clienteEntity.setNombre("John");
        clienteEntity.setCorreo("john.doe@example.com");
        return clienteEntity;
    }
}
