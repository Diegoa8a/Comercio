package com.comercio.pedidos.moldel;

import com.comercio.pedidos.dto.ClienteDTO;
import com.comercio.pedidos.dto.ProductoDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pedidos")
@Data
public class Pedido {
    @Id
    private String id;
    @NotEmpty
    private ClienteDTO cliente;
    @NotEmpty
    private List<ProductoDTO> productos;
    @NotEmpty
    private Double total;
    private List<String> errores;
}
