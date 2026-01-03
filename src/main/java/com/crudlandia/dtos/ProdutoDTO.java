package com.crudlandia.dtos;

import java.math.BigDecimal;
import lombok.Setter;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Getter()
@Setter()
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank()
    private String codigo;

    @NotBlank()
    private String nome;

    @NotNull()
    private BigDecimal valor;

    private String marca;

    private LocalDate validade;

    @NotNull()
    private Boolean ativo;

    public ProdutoDTO(Long id, String codigo, String nome, BigDecimal valor, String marca, LocalDate validade, Boolean ativo) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.marca = marca;
        this.validade = validade;
        this.ativo = ativo;
    }
}
