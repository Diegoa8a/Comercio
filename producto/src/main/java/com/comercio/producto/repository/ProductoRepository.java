package com.comercio.producto.repository;

import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.model.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    ProductoEntity findByNombre(String nombre);
    void deleteByNombre(String nombre);

    List<ProductoEntity> findByIdIn (List<Long> id);
}
