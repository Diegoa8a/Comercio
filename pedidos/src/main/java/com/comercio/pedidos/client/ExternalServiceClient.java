package com.comercio.pedidos.client;

import com.comercio.pedidos.dto.ClienteDTO;
import com.comercio.pedidos.dto.PeticionDTO;
import com.comercio.pedidos.dto.ProductoDTO;
import com.comercio.pedidos.dto.RespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ExternalServiceClient {

    private final RestTemplate restTemplate;

    @Autowired
    public ExternalServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Mono<ResponseEntity<RespuestaDTO>> postValidateProductos(List<PeticionDTO> productos) {

        return Mono.fromCallable(() -> restTemplate.postForEntity("http://localhost:9092/api/stock/validate-products", productos, RespuestaDTO.class));
    }

    public Mono<ResponseEntity<List<ProductoDTO>>> postExecuteProductos(List<ProductoDTO> productos) {
        return Mono.fromCallable(() -> {
            ResponseEntity<ProductoDTO[]> responseEntity = restTemplate.postForEntity("http://localhost:9092/api/stock/execute-products", productos, ProductoDTO[].class);
            List<ProductoDTO> productoList = List.of(responseEntity.getBody());
            return new ResponseEntity<>(productoList, responseEntity.getStatusCode());
        });
    }

    public Mono<ResponseEntity<ClienteDTO>> getClienteByNumeroDocumento(String numeroDocumento) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:9091/api/clientes/{numeroDocumento}")
                .buildAndExpand(numeroDocumento)
                .toUriString();
        return Mono.fromCallable(() -> restTemplate.getForEntity(url, ClienteDTO.class));
    }
}
