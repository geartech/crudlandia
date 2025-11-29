package com.crudlandia.controllers.exemplo.request;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SalvarProdutoRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  @NotBlank private String codigo;

  @NotBlank private String nome;

  @NotNull private BigDecimal valor;
}
