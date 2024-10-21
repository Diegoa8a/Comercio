package com.comercio.producto.config;

import com.comercio.producto.model.CategoriaEntity;
import com.comercio.producto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public DataInitializer(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            List<CategoriaEntity> categorias = Arrays.asList(
                    new CategoriaEntity(1l, "Electronics"),
                    new CategoriaEntity(2l, "Books"),
                    new CategoriaEntity(3l, "Clothing")
            );
            categoriaRepository.saveAll(categorias);
        }
    }
}
