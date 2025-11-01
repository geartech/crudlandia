package com.crudlandia.models.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.enums.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa um Exemplo no sistema.
 * 
 * <p>
 * Armazena informações completas sobre exemplos, incluindo relacionamento com referências, dados
 * descritivos, valores numéricos e status.
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
@Table(name = "exemplos")
@DynamicUpdate
@DynamicInsert
public class ExemploEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "referencia_id", nullable = false)
	private ReferenciaEntity referencia;

	@Column(name = "nome", nullable = false, length = 80)
	private String nome;

	@Column(name = "descricao", nullable = true, length = 200)
	private String descricao;

	@Column(name = "sequencia", nullable = false)
	private Integer sequencia;

	@Column(name = "valor", nullable = true, precision = 10, scale = 2)
	private BigDecimal valor;

	@Column(name = "peso", nullable = true)
	private Double peso;

	@Column(name = "dthr_emissao", nullable = false)
	private LocalDateTime dthrEmissao;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 20)
	private StatusEnum status;

	/**
	 * Converte a entidade para um DTO (Data Transfer Object).
	 * 
	 * <p>
	 * Método utilitário que mapeia todos os campos da entidade para um objeto de transferência de
	 * dados {@link ExemploDTO}.
	 * </p>
	 * 
	 * @return ExemploDTO com os dados da entidade
	 */
	public ExemploDTO getDRO() {
		return new ExemploDTO(this.id, id(this.referencia), this.nome, this.descricao,
				this.sequencia, this.valor, this.peso, this.dthrEmissao, this.status);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReferenciaEntity getReferencia() {
		return referencia;
	}

	public void setReferencia(ReferenciaEntity referencia) {
		this.referencia = referencia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public LocalDateTime getDthrEmissao() {
		return dthrEmissao;
	}

	public void setDthrEmissao(LocalDateTime dthrEmissao) {
		this.dthrEmissao = dthrEmissao;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}


}
