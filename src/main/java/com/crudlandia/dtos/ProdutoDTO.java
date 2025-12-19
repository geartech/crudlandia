package com.crudlandia.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProdutoDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String codigo;
  private String nome;
  private BigDecimal valor;
  private String marca;
  private LocalDate validade;
  private Boolean ativo;

  public ProdutoDTO(
      Long id,
      String codigo,
      String nome,
      BigDecimal valor,
      String marca,
      LocalDate validade,
      Boolean ativo) {
    this.id = id;
    this.codigo = codigo;
    this.nome = nome;
    this.valor = valor;
    this.marca = marca;
    this.validade = validade;
    this.ativo = ativo;
  }
}
