package com.comercio.cliente.service.impl;


import com.comercio.cliente.TestUtils;
import com.comercio.cliente.dto.ClienteDTO;
import com.comercio.cliente.exception.ClienteNotFoundException;
import com.comercio.cliente.exception.DuplicateKeyException;
import com.comercio.cliente.model.ClienteEntity;
import com.comercio.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClienteServiceImpl clienteService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateCliente_Success() {
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(TestUtils.createClienteEntity());

        ResponseEntity<ClienteDTO> response = clienteService.createCliente(TestUtils.createClienteDTO());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void testCreateCliente_DuplicateKeyException() {
        when(clienteRepository.save(any(ClienteEntity.class))).thenThrow(new DataIntegrityViolationException("Duplicate key"));

        DuplicateKeyException exception = assertThrows(DuplicateKeyException.class, () -> {
            clienteService.createCliente(TestUtils.createClienteDTO());
        });

        assertEquals("Duplicate key error: Duplicate key", exception.getMessage());
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void testGetClienteByNumeroDocumento_Success() {
        when(clienteRepository.findByNumeroDocumento("123456")).thenReturn(TestUtils.createClienteEntity());

        ResponseEntity<ClienteDTO> response = clienteService.getClienteByNumeroDocumento("123456");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clienteRepository, times(1)).findByNumeroDocumento("123456");
    }

    @Test
    void testGetClienteByNumeroDocumento_ClienteNotFound() {
        when(clienteRepository.findByNumeroDocumento("123456")).thenReturn(null);

        ResponseEntity<ClienteDTO> response = clienteService.getClienteByNumeroDocumento("123456");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(clienteRepository, times(1)).findByNumeroDocumento("123456");
    }

    @Test
    void testUpdateCliente_Success() {
        when(clienteRepository.findByNumeroDocumento("123456")).thenReturn(TestUtils.createClienteEntity());
        when(clienteRepository.save(any(ClienteEntity.class))).thenReturn(TestUtils.createClienteEntity());

        ResponseEntity<ClienteDTO> response = clienteService.updateCliente(TestUtils.createClienteDTO());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(clienteRepository, times(1)).findByNumeroDocumento("123456");
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    void testUpdateCliente_ClienteNotFoundException() {
        when(clienteRepository.findByNumeroDocumento("123456")).thenReturn(null);

        ClienteNotFoundException exception = assertThrows(ClienteNotFoundException.class, () -> {
            clienteService.updateCliente(TestUtils.createClienteDTO());
        });

        assertEquals("Cliente with numeroDocumento 123456 not found", exception.getMessage());
        verify(clienteRepository, times(1)).findByNumeroDocumento("123456");
    }

    @Test
    void testDeleteCliente_Success() {
        when(clienteRepository.findByNumeroDocumento(any())).thenReturn(TestUtils.createClienteEntity());

        ResponseEntity<Void> response = clienteService.deleteCliente("123456");

        assertEquals(ResponseEntity.ok().build(), response);
        verify(clienteRepository, times(1)).findByNumeroDocumento("123456");
        verify(clienteRepository, times(1)).delete(any(ClienteEntity.class));
    }

    @Test
    void testDeleteCliente_ClienteNotFoundException() {
        when(clienteRepository.findByNumeroDocumento("123456")).thenReturn(null);

        ClienteNotFoundException exception = assertThrows(ClienteNotFoundException.class, () -> {
            clienteService.deleteCliente("123456");
        });

        assertEquals("Cliente with numeroDocumento 123456 not found", exception.getMessage());
        verify(clienteRepository, times(1)).findByNumeroDocumento("123456");
    }
}