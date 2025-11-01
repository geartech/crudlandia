package com.crudlandia.mappers;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.enums.StatusEnum;
import com.github.pagehelper.Page;

/**
 * Mapper MyBatis para consultas customizadas de Exemplos.
 * 
 * <p>
 * Fornece métodos de consulta que utilizam queries SQL definidas em arquivos XML do MyBatis,
 * permitindo consultas complexas e otimizadas com suporte a paginação.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@Mapper
public interface ExemploMapper {

	/**
	 * Realiza listagem paginada de exemplos com filtros.
	 * 
	 * <p>
	 * Permite filtrar exemplos por período (data início e fim), nome, status, e ordenar os
	 * resultados por uma coluna específica em ordem ascendente ou descendente.
	 * </p>
	 * 
	 * <p>
	 * A paginação é gerenciada pelo PageHelper, que deve ser configurado antes de chamar este
	 * método usando {@code PageHelper.startPage(pageNum, pageSize)}.
	 * </p>
	 * 
	 * @param dthrInicio data de início do período de filtro
	 * @param dthrFim data de fim do período de filtro
	 * @param nome nome para filtro (permite busca parcial)
	 * @param status status para filtro (ATIVO ou INATIVO)
	 * @param columnType nome da coluna para ordenação
	 * @param orderType tipo de ordenação (ASC ou DESC)
	 * @return Page contendo a lista de ExemploDTO encontrados
	 */
	public Page<ExemploDTO> listagemExemplo(@Param("dthrInicio") LocalDate dthrInicio,
			@Param("dthrFim") LocalDate dthrFim, @Param("nome") String nome,
			@Param("status") StatusEnum status, @Param("columnType") String columnType,
			@Param("orderType") String orderType);

}
