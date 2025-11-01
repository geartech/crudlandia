package com.crudlandia.models.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa uma Referência no sistema.
 * 
 * <p>
 * Referências são utilizadas como entidade relacionada aos Exemplos, permitindo categorização e
 * organização através de código e nome.
 * </p>
 * 
 * <p>
 * Esta entidade utiliza anotações do Hibernate para otimização de queries SQL (DynamicUpdate e
 * DynamicInsert).
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@Entity
@Table(name = "referencias")
@DynamicUpdate
@DynamicInsert
public class ReferenciaEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "codigo", nullable = false, length = 10)
	private String codigo;

	@Column(name = "nome", nullable = false, length = 80)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
