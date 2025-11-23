package com.crudlandia.exceptions;

@ApiException("EXEMPLO_NOME_DUPLICADO")
public class ExemploNomeDuplicadoException extends Exception {
    private static final long serialVersionUID = 1L;

    private Long idExistente;
    private String nome;

    public ExemploNomeDuplicadoException(String nome) {
        this.nome = nome;
    }

    public ExemploNomeDuplicadoException(String nome, Long idExistente) {
        this.nome = nome;
        this.idExistente = idExistente;
    }

    public Long getIdExistente() {
        return idExistente;
    }

    public String getNome() {
        return nome;
    }
}
