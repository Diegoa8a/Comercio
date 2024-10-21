package com.comercio.producto.service.impl;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.model.ProductoEntity;
import com.comercio.producto.repository.ProductoRepository;
import com.comercio.producto.service.ExecuteService;
import com.comercio.producto.stategy.ActiveProductValidationStrategy;
import com.comercio.producto.stategy.ProductNotFoundValidationStrategy;
import com.comercio.producto.stategy.ProductValidationStrategy;
import com.comercio.producto.stategy.StockValidationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class ExecuteServiceImpl implements ExecuteService {

    @Autowired
    private ProductoRepository productoRepository;

    private static final List<ProductValidationStrategy> VALIDATION_STRATEGIES = Arrays.asList(
            new ProductNotFoundValidationStrategy(),
            new ActiveProductValidationStrategy(),
            new StockValidationStrategy()
    );
    @Override
    public ResponseEntity<RespuestaDTO> validateProducts(List<PeticionDTO> productos) {
        List<Long> productIds = productos.stream()
                .map(PeticionDTO::getId)
                .collect(Collectors.toList());

        Map<Long, ProductoEntity> productMap = findProducts(productIds);
        RespuestaDTO respuestaDTO = new RespuestaDTO(new ArrayList<>(), new ArrayList<>());

        for (PeticionDTO peticionDTO: productos) {
            ProductoEntity productoEntityObject = productMap.get(peticionDTO.getId());
            VALIDATION_STRATEGIES.get(0).validationPeticionDTO(productoEntityObject, peticionDTO, respuestaDTO);
            VALIDATION_STRATEGIES.get(1).validationPeticionDTO(productoEntityObject, peticionDTO, respuestaDTO);
            VALIDATION_STRATEGIES.get(2).validationPeticionDTO(productoEntityObject, peticionDTO, respuestaDTO);
        }

        return ResponseEntity.ok(respuestaDTO);
    }

    @Override
    public ResponseEntity<List<ProductoDTO>> executeProducts(List<ProductoDTO> productos) {

        List<Long> productIds = productos.stream()
                .map(ProductoDTO::getId)
                .collect(Collectors.toList());

        Map<Long, ProductoEntity> productMap = findProducts(productIds);

        List<ProductoEntity> updatedProducts = new ArrayList<>();
        for (ProductoDTO peticionDTO : productos) {
            ProductoEntity productoEntity = productMap.get(peticionDTO.getId());
            productoEntity.setStock(productoEntity.getStock() - peticionDTO.getStock());
            updatedProducts.add(productoEntity);
        }
        productoRepository.saveAll(updatedProducts);
        return ResponseEntity.ok(productos);
    }

    private Map<Long, ProductoEntity> findProducts(List<Long> productIds) {
        List<ProductoEntity> productoEntity = productoRepository.findByIdIn(productIds);
        return productoEntity.stream()
                .collect(Collectors.toMap(ProductoEntity::getId, Function.identity()));
    }

}
