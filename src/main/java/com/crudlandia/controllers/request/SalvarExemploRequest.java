package com.crudlandia.controllers.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.crudlandia.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarExemploRequest implements Serializable {
    private static final long serialVersionUID = 1L;

	@NotNull
    private Long referenciaId;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer sequencia;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Double peso;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dthrEmissao;

    @NotNull
    private StatusEnum status;

}
