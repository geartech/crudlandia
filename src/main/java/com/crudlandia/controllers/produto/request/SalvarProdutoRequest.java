package com.crudlandia.controllers.produto.request;

import java.math.BigDecimal;
import lombok.Setter;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Getter()
@Setter()
public class SalvarProdutoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String codigo;

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valor;

    private String marca;

    private LocalDate validade;

    @NotNull
    private Boolean ativo;
}
