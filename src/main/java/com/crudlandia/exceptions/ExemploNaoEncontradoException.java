package com.crudlandia.exceptions;

@ApiException("EXEMPLO_NAO_ENCONTRADO")
public class ExemploNaoEncontradoException extends Exception {
    private static final long serialVersionUID = 1L;

    private Long id;

    public ExemploNaoEncontradoException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
