package com.crudlandia.mappers;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.crudlandia.dtos.ProdutoDTO;
import com.github.pagehelper.Page;


@Mapper
public interface ProdutoMapper {

  Page<ProdutoDTO> listagemProdutos(
      @Param("codigo") String codigo, @Param("nome") String nome, @Param("ativo") Boolean ativo);
}
