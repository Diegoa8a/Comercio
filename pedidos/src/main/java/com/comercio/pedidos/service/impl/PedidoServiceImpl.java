package com.comercio.pedidos.service.impl;

import com.comercio.pedidos.builder.PedidoBuilder;
import com.comercio.pedidos.client.ExternalServiceClient;
import com.comercio.pedidos.dto.ClienteDTO;
import com.comercio.pedidos.dto.PeticionDTO;
import com.comercio.pedidos.dto.ProductoDTO;
import com.comercio.pedidos.dto.RespuestaDTO;
import com.comercio.pedidos.moldel.Pedido;
import com.comercio.pedidos.repository.PedidoRepository;
import com.comercio.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ExternalServiceClient externalServiceClient;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository, ExternalServiceClient externalServiceClient) {
        this.pedidoRepository = pedidoRepository;
        this.externalServiceClient = externalServiceClient;
    }

    @Override
    public Flux<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Mono<Pedido> getPedidoById(String id) {
        return pedidoRepository.findById(id);
    }

    public Mono<Pedido> createPedido(List<PeticionDTO> pedido, String numeroCuenta) {
        return getCliente(numeroCuenta)
                .flatMap(cliente -> validateAndAdjustStock(pedido)
                        .flatMap(productosConStock -> buildAndExecutePedido(cliente, productosConStock)));
    }

    private Mono<ClienteDTO> getCliente(String numeroCuenta) {
        return externalServiceClient.getClienteByNumeroDocumento(numeroCuenta)
                .flatMap(clienteResponse -> {
                    ClienteDTO cliente = clienteResponse.getBody();
                    if (cliente == null) {
                        return Mono.error(new RuntimeException("Client not found"));
                    }
                    return Mono.just(cliente);
                });
    }

    private Mono<List<ProductoDTO>> validateAndAdjustStock(List<PeticionDTO> pedido) {
        return validateProductos(pedido)
                .flatMap(productosValidos -> {
                    List<ProductoDTO> productosConStock = productosValidos.stream().map(productoValido -> {
                        PeticionDTO peticion = pedido.stream()
                                .filter(p -> p.getId().equals(productoValido.getId()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Product not found in request"));
                        productoValido.setStock(peticion.getStock());
                        return productoValido;
                    }).collect(Collectors.toList());
                    return Mono.just(productosConStock);
                });
    }

    private Mono<Pedido> buildAndExecutePedido(ClienteDTO cliente, List<ProductoDTO> productosConStock) {
        Pedido newPedido = new PedidoBuilder()
                .cliente(cliente)
                .productos(productosConStock)
                .total(calculateTotal(productosConStock))
                .build();
        return executeProductos(newPedido);
    }
    @Override
    public Mono<Pedido> updatePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Mono<Void> deletePedido(String id) {
        return pedidoRepository.deleteById(id);
    }

    // Método para validar los productos del pedido
    private Mono<List<ProductoDTO>> validateProductos(List<PeticionDTO> productos) {
        // Llama al servicio externo para validar los productos
        return externalServiceClient.postValidateProductos(productos)
                .flatMap(validateResponse ->
                        // Verifica si la respuesta es exitosa
                        isSuccessfulResponse(validateResponse)
                                ? Mono.just(validateResponse.getBody().getProductos())
                                : Mono.error(new RuntimeException("Error validating products"))
                );
    }

    // Método para ejecutar los productos y guardar el pedido
    private Mono<Pedido> executeProductos(Pedido pedido) {
        return externalServiceClient.postExecuteProductos(pedido.getProductos())
                .flatMap(executeResponse ->
                        isSuccessfulResponse(executeResponse)
                                ? pedidoRepository.save(pedido)
                                : Mono.error(new RuntimeException("Error executing products"))
                );
    }

    // Método para verificar si la respuesta es exitosa
    private <T> boolean isSuccessfulResponse(ResponseEntity<T> response) {
        return response.getStatusCode().is2xxSuccessful() && response.getBody() != null;
    }

    private Double calculateTotal(List<ProductoDTO> productos) {
        return productos.stream()
                .mapToDouble(ProductoDTO::getPrecio)
                .sum();
    }
}
