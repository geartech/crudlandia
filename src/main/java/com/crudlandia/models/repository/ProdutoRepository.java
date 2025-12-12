package com.crudlandia.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudlandia.models.entities.ProdutoEntity;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

  Optional<ProdutoEntity> findById(Long id);

  boolean existsByCodigo(String codigo);

  boolean existsByNome(String nome);
}
