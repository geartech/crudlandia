package com.crudlandia.exceptions;

import java.io.Serializable;


@ApiException("PRODUTO_DUPLICADO")
public class ProdutoDuplicadoException extends Exception implements Serializable {
  private static final long serialVersionUID = 1L;

  private String codigo;
  private String nome;

  public ProdutoDuplicadoException(String codigo, String nome) {
    super("Produto com código " + codigo + " e nome " + nome + " já existe.");
    this.codigo = codigo;
    this.nome = nome;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }
}
