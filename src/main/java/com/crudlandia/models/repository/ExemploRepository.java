package com.crudlandia.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudlandia.models.entities.ExemploEntity;

/**
 * Repositório JPA para operações de persistência de {@link ExemploEntity}.
 * 
 * <p>
 * Além dos métodos padrão herdados de {@link JpaRepository}, fornece métodos customizados para
 * consultas específicas de Exemplos.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@Repository
public interface ExemploRepository extends JpaRepository<ExemploEntity, Long> {

	/**
	 * Busca um exemplo pelo nome.
	 * 
	 * <p>
	 * Este método é utilizado para validar unicidade de nome antes de criar ou atualizar um
	 * exemplo.
	 * </p>
	 * 
	 * @param nome nome do exemplo a ser buscado
	 * @return Optional contendo o ExemploEntity se encontrado, ou vazio
	 */
	public Optional<ExemploEntity> findByNome(String nome);


}
