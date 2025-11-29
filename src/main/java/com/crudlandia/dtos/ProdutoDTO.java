package com.crudlandia.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String codigo;
  private String nome;
  private BigDecimal valor;
  private String marca;
  private LocalDate validade;
  private boolean ativo;
}
