package com.crudlandia.exceptions;

@ApiException(value = "PRODUTO_DUPLICADO")
public class ProdutoDuplicadoException extends Exception {

    private static final long serialVersionUID = 1L;

    private Long idExistente;

    private String codigo;

    public ProdutoDuplicadoException(String codigo) {
        this.codigo = codigo;
    }

    public ProdutoDuplicadoException(String codigo, Long idExistente) {
        this.codigo = codigo;
        this.idExistente = idExistente;
    }

    public String getCodigo() {
        return codigo;
    }

    public Long getIdExistente() {
        return idExistente;
    }
}
