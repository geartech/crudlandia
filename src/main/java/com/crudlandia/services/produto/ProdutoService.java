package com.crudlandia.services.produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.crudlandia.dtos.ProdutoDTO;
import com.crudlandia.exceptions.ProdutoNaoEncontradoException;


public interface ProdutoService {

  ProdutoDTO criar(String codigo, String nome, BigDecimal valor, String marca, LocalDate validade)
      throws RuntimeException;

  ProdutoDTO atualizar(
      Long id, String codigo, String nome, BigDecimal valor, String marca, LocalDate validade)
      throws ProdutoNaoEncontradoException;

  ProdutoDTO buscarPorId(Long id) throws ProdutoNaoEncontradoException;

  void deletar(Long id) throws ProdutoNaoEncontradoException;

  List<ProdutoDTO> listar();
}
