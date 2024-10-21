package com.comercio.producto.service;
import com.comercio.producto.dto.CategoriaDTO;
import java.util.List;

public interface CategoriaService {
    CategoriaDTO getCategoriaById(Long id);
    CategoriaDTO createCategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO);
    void deleteCategoria(Long id);
}
