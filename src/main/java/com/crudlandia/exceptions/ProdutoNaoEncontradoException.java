package com.crudlandia.exceptions;

@ApiException(value = "PRODUTO_NAO_ENCONTRADO")
public class ProdutoNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 1L;

    private Long id;

    public ProdutoNaoEncontradoException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
