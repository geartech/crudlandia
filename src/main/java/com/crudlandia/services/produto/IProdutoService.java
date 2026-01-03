package com.crudlandia.services.produto;

import com.crudlandia.exceptions.ProdutoNaoEncontradoException;
import com.crudlandia.dtos.ProdutoDTO;
import java.time.LocalDate;
import java.math.BigDecimal;
import com.crudlandia.exceptions.ProdutoDuplicadoException;

public interface IProdutoService {

    ProdutoDTO criar(String codigo, String nome, BigDecimal valor, String marca, LocalDate validade, Boolean ativo) throws ProdutoNaoEncontradoException, ProdutoDuplicadoException;

    ProdutoDTO atualizar(Long id, String codigo, String nome, BigDecimal valor, String marca, LocalDate validade, Boolean ativo) throws ProdutoNaoEncontradoException, ProdutoDuplicadoException;

    ProdutoDTO buscarPorId(Long id) throws ProdutoNaoEncontradoException;

    void deletar(Long id) throws ProdutoNaoEncontradoException;

    void desativar(Long id) throws ProdutoNaoEncontradoException;
}
