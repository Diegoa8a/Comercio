package com.comercio.cliente.service.impl;

import com.comercio.cliente.dto.ClienteDTO;
import com.comercio.cliente.exception.ClienteNotFoundException;
import com.comercio.cliente.exception.DuplicateKeyException;
import com.comercio.cliente.model.ClienteEntity;
import com.comercio.cliente.repository.ClienteRepository;
import com.comercio.cliente.service.ClienteService;
import com.comercio.cliente.utils.BuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ResponseEntity<ClienteDTO> createCliente(ClienteDTO clienteDTO) {
        try {
            ClienteEntity clienteEntity = BuilderUtil.buildClienteEntity(clienteDTO);
            clienteRepository.save(clienteEntity);
            return ResponseEntity.ok(clienteDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateKeyException("Duplicate key error: " + ex.getMostSpecificCause().getMessage());
        }
    }

    @Override
    public ResponseEntity<ClienteDTO> getClienteByNumeroDocumento(String numeroDocumento) {
        ClienteEntity clienteEntity = clienteRepository.findByNumeroDocumento(numeroDocumento);
        return clienteEntity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(BuilderUtil.buildClienteDTO(clienteEntity));
    }

    @Override
    public ResponseEntity<ClienteDTO> updateCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteRepository.findByNumeroDocumento(clienteDTO.getNumeroDocumento());
        Optional.ofNullable(clienteEntity).orElseThrow(() -> new ClienteNotFoundException("Cliente with numeroDocumento " + clienteDTO.getNumeroDocumento() + " not found"));
        return ResponseEntity.ok(BuilderUtil.buildClienteDTO(clienteRepository.save(BuilderUtil.buildClienteEntity(clienteDTO))));
    }

    @Override
    public ResponseEntity<Void> deleteCliente(String numeroDocumento) {
        ClienteEntity clienteEntity = clienteRepository.findByNumeroDocumento(numeroDocumento);
        Optional.ofNullable(clienteEntity).orElseThrow(() -> new ClienteNotFoundException("Cliente with numeroDocumento " + numeroDocumento + " not found"));
        clienteRepository.delete(clienteEntity);
        return ResponseEntity.ok().build();
    }

}
