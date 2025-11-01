package com.crudlandia.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudlandia.models.entities.ReferenciaEntity;

/**
 * Repositório JPA para operações de persistência de {@link ReferenciaEntity}.
 * 
 * <p>
 * Utiliza os métodos padrão herdados de {@link JpaRepository} para operações CRUD básicas de
 * referências.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@Repository
public interface ReferenciaRepository extends JpaRepository<ReferenciaEntity, Long> {

}
