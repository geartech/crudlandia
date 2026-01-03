package com.crudlandia.models.repository;

import com.crudlandia.models.entities.ProdutoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository()
public interface IProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    boolean existsByCodigo(String codigo);

    boolean existsByNome(String nome);
}
