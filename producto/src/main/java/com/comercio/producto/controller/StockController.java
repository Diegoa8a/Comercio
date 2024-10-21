package com.comercio.producto.controller;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final ExecuteService executeService;
    @Autowired
    public StockController(ExecuteService executeService) {
        this.executeService = executeService;
    }

    @PostMapping("/validate-products")
    public ResponseEntity<RespuestaDTO> postValidateProductos(@RequestBody List<PeticionDTO> productos) {
        return executeService.validateProducts(productos);
    }

    @PostMapping("/execute-products")
    public ResponseEntity<List<ProductoDTO>> postExecuteProductos(@RequestBody List<ProductoDTO> productos) {
        return executeService.executeProducts(productos);
    }

}
