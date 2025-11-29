package com.crudlandia.mappers;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.crudlandia.dtos.ProdutoDTO;
import com.github.pagehelper.Page;


@Mapper
public interface ProdutoMapper {

  Page<ProdutoDTO> listagemProduto(
      @Param("dthrInicio") LocalDate dthrInicio,
      @Param("dthrFim") LocalDate dthrFim,
      @Param("nome") String nome,
      @Param("valorMinimo") BigDecimal valorMinimo,
      @Param("valorMaximo") BigDecimal valorMaximo,
      @Param("marca") String marca,
      @Param("ativo") Boolean ativo,
      @Param("columnType") String columnType,
      @Param("orderType") String orderType);
}
