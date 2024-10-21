package com.comercio.pedidos.builder;

import com.comercio.pedidos.dto.ClienteDTO;
import com.comercio.pedidos.dto.ProductoDTO;
import com.comercio.pedidos.moldel.Pedido;

import java.util.List;

public class PedidoBuilder {
    private String id;
    private ClienteDTO cliente;
    private List<ProductoDTO> productos;
    private Double total;
    private List<String> errores;

    public PedidoBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PedidoBuilder cliente(ClienteDTO cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder productos(List<ProductoDTO> productos) {
        this.productos = productos;
        return this;
    }

    public PedidoBuilder total(Double total) {
        this.total = total;
        return this;
    }

    public PedidoBuilder errores(List<String> errores) {
        this.errores = errores;
        return this;
    }

    public Pedido build() {
        Pedido pedido = new Pedido();
        pedido.setId(this.id);
        pedido.setCliente(this.cliente);
        pedido.setProductos(this.productos);
        pedido.setTotal(this.total);
        pedido.setErrores(this.errores);
        return pedido;
    }
}
