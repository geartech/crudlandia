package com.crudlandia.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.crudlandia.dtos.ProdutoDTO;

@Mapper()
public interface IProdutoMapper {

    List<ProdutoDTO> listagemProduto(@Param("codigo") String codigo, @Param("nome") String nome, @Param("ativo") Boolean ativo, @Param("columnType") String columnType, @Param("orderType") String orderType);
}
