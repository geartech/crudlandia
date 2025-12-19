package com.crudlandia.models.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudlandia.models.entities.ProdutoEntity;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

  boolean existsByCodigo(String codigo);

  boolean existsByNome(String nome);
}
