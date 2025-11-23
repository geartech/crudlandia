package com.crudlandia.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.crudlandia.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExemploDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long referenciaId;
	private String nome;
    private String descricao;
    private Integer sequencia;
	private BigDecimal valor;
	private Double peso;
	private LocalDateTime dthrEmissao;
	private StatusEnum status;
	private Boolean ativo;
	
}
