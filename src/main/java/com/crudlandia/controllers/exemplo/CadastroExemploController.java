package com.crudlandia.controllers.exemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudlandia.controllers.exemplo.request.PesquisarCadastroExemploRequest;
import com.crudlandia.controllers.exemplo.request.SalvarCadastroExemploRequest;
import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.exceptions.ExemploNaoEncontradoException;
import com.crudlandia.exceptions.ExemploNomeDuplicadoException;
import com.crudlandia.exceptions.ReferenciaNaoEncontradoException;
import com.crudlandia.mappers.ExemploMapper;
import com.crudlandia.services.exemplo.ExemploService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Controller REST responsável pelo gerenciamento de Exemplos.
 * 
 * <p>
 * Fornece endpoints para operações CRUD (Create, Read, Update, Delete) de exemplos, incluindo
 * validação de nome único e listagem paginada.
 * </p>
 * 
 * <p>
 * Todas as requisições e respostas utilizam o formato JSON.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@RestController
@RequestMapping("/cadastro/exemplo")
public class CadastroExemploController {

    @Autowired
    private ExemploService exemploService;

    @Autowired
    protected ExemploMapper exemploMapper;

    /**
     * Cria um novo exemplo no sistema.
     * 
     * <p>
     * O exemplo criado sempre terá status ATIVO inicialmente. Valida se já existe um exemplo com o
     * mesmo nome antes de criar.
     * </p>
     * 
     * @param request objeto contendo os dados do exemplo a ser criado
     * @return ResponseEntity contendo o ExemploDTO criado com status HTTP 201 (Created)
     * @throws com.crudlandia.exceptions.ExemploNomeDuplicadoException se já existir exemplo com o
     *         mesmo nome
     * @throws com.crudlandia.exceptions.ReferenciaNaoEncontradoException se a referência informada
     *         não existir
     * @throws com.crudlandia.exceptions.ExemploNomeDuplicadoException se já existir exemplo com o
     *         mesmo nome
     */
    @PostMapping("/criar")
    public ResponseEntity<ExemploDTO> criar(@RequestBody SalvarCadastroExemploRequest request)
            throws ReferenciaNaoEncontradoException, ExemploNomeDuplicadoException {
        ExemploDTO criado = exemploService.criar(request.getReferenciaId(), request.getNome(),
                request.getDescricao(), request.getSequencia(), request.getValor(),
                request.getPeso(), request.getDthrEmissao());

        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    /**
     * Atualiza um exemplo existente.
     * 
     * <p>
     * Valida se já existe outro exemplo com o mesmo nome antes de atualizar.
     * </p>
     * 
     * @param id identificador único do exemplo a ser atualizado
     * @param request objeto contendo os dados atualizados do exemplo
     * @return ResponseEntity contendo o ExemploDTO atualizado com status HTTP 200 (OK)
     * @throws com.crudlandia.exceptions.ExemploNaoEncontradoException se o exemplo não for
     *         encontrado
     * @throws com.crudlandia.exceptions.ExemploNomeDuplicadoException se já existir outro exemplo
     *         com o mesmo nome
     * @throws com.crudlandia.exceptions.ReferenciaNaoEncontradoException se a referência informada
     *         não existir
     */
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ExemploDTO> atualizar(@PathVariable("id") Long id,
            @RequestBody SalvarCadastroExemploRequest request) throws ExemploNaoEncontradoException,
            ReferenciaNaoEncontradoException, ExemploNomeDuplicadoException {
        ExemploDTO atualizado = exemploService.atualizar(id, request.getReferenciaId(),
                request.getNome(), request.getDescricao(), request.getSequencia(),
                request.getValor(), request.getPeso(), request.getDthrEmissao());
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Busca um exemplo por seu identificador único.
     * 
     * @param id identificador único do exemplo
     * @return ResponseEntity contendo o ExemploDTO encontrado com status HTTP 200 (OK)
     * @throws com.crudlandia.exceptions.ExemploNaoEncontradoException se o exemplo não for
     *         encontrado
     */
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ExemploDTO> buscarPorId(@PathVariable("id") Long id)
            throws ExemploNaoEncontradoException {
        ExemploDTO dto = exemploService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Lista exemplos com paginação e filtros.
     * 
     * <p>
     * Permite filtrar por período (data início e fim), nome, status, e ordenar por coluna
     * específica em ordem ascendente ou descendente.
     * </p>
     * 
     * @param request objeto contendo os critérios de pesquisa e paginação
     * @return PageInfo contendo a lista paginada de ExemploDTO
     */
    @PostMapping("/listagem")
    public PageInfo<ExemploDTO> listagem(@RequestBody PesquisarCadastroExemploRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        return new PageInfo<ExemploDTO>(exemploMapper.listagemExemplo(request.getDthrInicio(),
                request.getDthrFim(), request.getNome(), request.getStatus(),
                request.getColumnType(), request.getOrderType()));
    }

    /**
     * Deleta um exemplo do sistema.
     * 
     * @param id identificador único do exemplo a ser deletado
     * @return ResponseEntity vazio com status HTTP 204 (No Content)
     * @throws com.crudlandia.exceptions.ExemploNaoEncontradoException se o exemplo não for
     *         encontrado
     */
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id)
            throws ExemploNaoEncontradoException {
        exemploService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@PathVariable("id") Long id)
            throws ExemploNaoEncontradoException {
        exemploService.desativar(id);
        return ResponseEntity.noContent().build();
    }

}
