package com.comercio.producto.model;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    private String nombre;
    @Column(name = "precio", nullable = false, length = 50)
    private Double precio;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "activo", nullable = false, length = 50)
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;
}
