package com.crudlandia.controllers.produto.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PesquisarProdutoRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  private String codigo;
  private String nome;
  private Boolean ativo;
  private Integer pageNum;
  private Integer pageSize;
  private String columnType;
  private String orderType;
}
