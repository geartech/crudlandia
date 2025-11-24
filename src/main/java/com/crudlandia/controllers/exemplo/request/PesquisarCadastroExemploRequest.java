package com.crudlandia.controllers.exemplo.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.crudlandia.enums.StatusEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PesquisarCadastroExemploRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private LocalDate dthrInicio;
	
	@NotNull
	private LocalDate dthrFim;
	
	private String nome;
	private StatusEnum status;
	
	@NotNull
	private Integer pageNum = 1;
	
	@NotNull
	private Integer pageSize = 10;
	
	@NotBlank
	private String columnType;
	
	@NotBlank
	private String orderType;
	
}
