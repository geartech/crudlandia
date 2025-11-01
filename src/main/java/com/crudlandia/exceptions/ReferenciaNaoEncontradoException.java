package com.crudlandia.exceptions;

@ApiException("REFERENCIA_NAO_ENCONTRADO")
public class ReferenciaNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Long id;

    public ReferenciaNaoEncontradoException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
