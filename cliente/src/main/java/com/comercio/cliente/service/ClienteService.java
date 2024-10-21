package com.comercio.cliente.service;

import com.comercio.cliente.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClienteService {
    ResponseEntity<ClienteDTO> createCliente(ClienteDTO clienteDTO);
    ResponseEntity<ClienteDTO> getClienteByNumeroDocumento(String numeroDocumento);
    ResponseEntity<ClienteDTO> updateCliente(ClienteDTO clienteDTO);
    ResponseEntity<Void> deleteCliente(String numeroDocumento);
}
