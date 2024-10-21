package com.comercio.pedidos.service;

import com.comercio.pedidos.dto.PeticionDTO;
import com.comercio.pedidos.moldel.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PedidoService {
    Flux<Pedido> getAllPedidos();
    Mono<Pedido> getPedidoById(String id);
    Mono<Pedido> createPedido(List<PeticionDTO> peticionDTOS, String numeroCuenta);
    Mono<Pedido> updatePedido(Pedido pedido);
    Mono<Void> deletePedido(String id);
}
