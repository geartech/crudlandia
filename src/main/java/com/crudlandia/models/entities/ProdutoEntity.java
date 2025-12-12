package com.crudlandia.models.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.crudlandia.dtos.ProdutoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "produtos")
@DynamicUpdate
@DynamicInsert
public class ProdutoEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "codigo", nullable = false, length = 20)
  private String codigo;

  @Column(name = "nome", nullable = false, length = 80)
  private String nome;

  @Column(name = "valor", nullable = false, precision = 10, scale = 2)
  private BigDecimal valor;

  @Column(name = "marca", length = 50)
  private String marca;

  @Column(name = "validade")
  private LocalDate validade;

  @Column(name = "ativo", nullable = false)
  private Boolean ativo;

  public ProdutoDTO getDTO() {
    return new ProdutoDTO(
        this.id, this.codigo, this.nome, this.valor, this.marca, this.validade, this.ativo);
  }

  @Override
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

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public LocalDate getValidade() {
    return validade;
  }

  public void setValidade(LocalDate validade) {
    this.validade = validade;
  }

  public Boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }
}
