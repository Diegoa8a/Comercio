package com.comercio.cliente.repository;

import com.comercio.cliente.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    ClienteEntity findByNumeroDocumento(String numeroDocumento);
}
