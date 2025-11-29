package com.crudlandia.exceptions;

import java.io.Serializable;


@ApiException("PRODUTO_NAO_ENCONTRADO")
public class ProdutoNaoEncontradoException extends Exception implements Serializable {
  private static final long serialVersionUID = 1L;
}
