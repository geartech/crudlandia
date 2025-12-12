package com.crudlandia.exceptions;

@ApiException("PRODUTO_DUPLICADO")
public class ProdutoDuplicadoException extends Exception {
  private static final long serialVersionUID = 1L;

  private String codigo;
  private String nome;

  public ProdutoDuplicadoException(String codigo, String nome) {
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
