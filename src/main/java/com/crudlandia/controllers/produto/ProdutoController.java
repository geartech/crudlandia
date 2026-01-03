package com.crudlandia.controllers.produto;

import com.crudlandia.controllers.produto.request.PesquisarProdutoRequest;
import com.crudlandia.controllers.produto.request.SalvarProdutoRequest;
import com.crudlandia.dtos.ProdutoDTO;
import com.crudlandia.exceptions.ProdutoDuplicadoException;
import com.crudlandia.exceptions.ProdutoNaoEncontradoException;
import com.crudlandia.mappers.IProdutoMapper;
import com.crudlandia.services.produto.IProdutoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private IProdutoService iProdutoService;

    @Autowired
    protected IProdutoMapper iProdutoMapper;

    @PostMapping("/criar")
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody SalvarProdutoRequest request)
            throws ProdutoNaoEncontradoException, ProdutoDuplicadoException {
        ProdutoDTO criado = iProdutoService.criar(request.getCodigo(),
                request.getNome(),
                request.getValor(),
                request.getMarca(),
                request.getValidade(),
                request.getAtivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@NotNull @PathVariable("id") Long id,
            @Valid @RequestBody SalvarProdutoRequest request)
            throws ProdutoNaoEncontradoException, ProdutoDuplicadoException {
        ProdutoDTO atualizado = iProdutoService.atualizar(id,
                request.getCodigo(),
                request.getNome(),
                request.getValor(),
                request.getMarca(),
                request.getValidade(),
                request.getAtivo());
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@NotNull @PathVariable("id") Long id)
            throws ProdutoNaoEncontradoException {
        ProdutoDTO dto = iProdutoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/listagem")
    public PageInfo<ProdutoDTO> listagem(@RequestBody PesquisarProdutoRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        return new PageInfo<ProdutoDTO>(iProdutoMapper.listagemProduto(request.getCodigo(),
                request.getNome(),
                request.getAtivo(),
                request.getColumnType(),
                request.getOrderType()));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@NotNull @PathVariable("id") Long id)
            throws ProdutoNaoEncontradoException {
        iProdutoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<Void> desativar(@NotNull @PathVariable("id") Long id)
            throws ProdutoNaoEncontradoException {
        iProdutoService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
