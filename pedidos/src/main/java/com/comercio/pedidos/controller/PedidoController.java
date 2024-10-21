package com.comercio.pedidos.controller;

import com.comercio.pedidos.dto.PeticionDTO;
import com.comercio.pedidos.moldel.Pedido;
import com.comercio.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService pedidoService;
    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public Flux<Pedido> getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Mono<Pedido> getPedidoById(@PathVariable String id) {
        return pedidoService.getPedidoById(id);
    }

    @PostMapping("/{numeroCuenta}")
    public Mono<Pedido> createPedido(@RequestBody List<PeticionDTO> pedido, @PathVariable String numeroCuenta) {
        return pedidoService.createPedido(pedido, numeroCuenta);
    }

    @PutMapping
    public Mono<Pedido> updatePedido(@RequestBody Pedido pedido) {
        return pedidoService.updatePedido(pedido);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePedido(@PathVariable String id) {
        return pedidoService.deletePedido(id);
    }
}