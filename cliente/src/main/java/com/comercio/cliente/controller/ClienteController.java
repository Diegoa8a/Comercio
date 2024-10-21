package com.comercio.cliente.controller;


import com.comercio.cliente.dto.ClienteDTO;
import com.comercio.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.createCliente(clienteDTO);
    }

    @GetMapping("/{numeroDocumento}")
    public ResponseEntity<ClienteDTO> getClienteByNumeroDocumento(@PathVariable String numeroDocumento) {
        return clienteService.getClienteByNumeroDocumento(numeroDocumento);
    }

    @PutMapping("/{numeroDocumento}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable String numeroDocumento, @RequestBody ClienteDTO clienteDTO) {
        return clienteService.updateCliente(clienteDTO);
    }

    @DeleteMapping("/{numeroDocumento}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String numeroDocumento) {
        return clienteService.deleteCliente(numeroDocumento);
    }

}
