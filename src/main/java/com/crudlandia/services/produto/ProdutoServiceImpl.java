package com.crudlandia.services.produto;

import java.math.BigDecimal;
import com.crudlandia.exceptions.ProdutoNaoEncontradoException;
import com.crudlandia.models.repository.IProdutoRepository;
import com.crudlandia.dtos.ProdutoDTO;
import org.springframework.stereotype.Service;
import com.crudlandia.models.entities.ProdutoEntity;
import java.time.LocalDate;
import com.crudlandia.exceptions.ProdutoDuplicadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service()
@Transactional()
public class ProdutoServiceImpl implements IProdutoService {

    @Autowired()
    private IProdutoRepository iProdutoRepository;

    @Override()
    public ProdutoDTO criar(String codigo, String nome, BigDecimal valor, String marca, LocalDate validade, Boolean ativo) throws ProdutoNaoEncontradoException, ProdutoDuplicadoException {
        if (iProdutoRepository.existsByCodigo(codigo)) {
            throw new ProdutoDuplicadoException(codigo);
        }
        if (iProdutoRepository.existsByNome(nome)) {
            throw new ProdutoDuplicadoException(nome);
        }
        ProdutoEntity entity = new ProdutoEntity();
        entity.setCodigo(codigo);
        entity.setNome(nome);
        entity.setValor(valor);
        entity.setMarca(marca);
        entity.setValidade(validade);
        entity.setAtivo(ativo);
        return iProdutoRepository.save(entity).getDTO();
    }

    @Override()
    public ProdutoDTO atualizar(Long id, String codigo, String nome, BigDecimal valor, String marca, LocalDate validade, Boolean ativo) throws ProdutoNaoEncontradoException, ProdutoDuplicadoException {
        ProdutoEntity entity = iProdutoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        entity.setCodigo(codigo);
        entity.setNome(nome);
        entity.setValor(valor);
        if (marca != null) {
            entity.setMarca(marca);
        }
        if (validade != null) {
            entity.setValidade(validade);
        }
        entity.setAtivo(ativo);
        return iProdutoRepository.save(entity).getDTO();
    }

    @Override()
    public ProdutoDTO buscarPorId(Long id) throws ProdutoNaoEncontradoException {
        return iProdutoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id)).getDTO();
    }

    @Override()
    public void deletar(Long id) throws ProdutoNaoEncontradoException {
        ProdutoEntity entity = iProdutoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        iProdutoRepository.delete(entity);
    }

    @Override()
    public void desativar(Long id) throws ProdutoNaoEncontradoException {
        ProdutoEntity entity = iProdutoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        entity.setAtivo(false);
        iProdutoRepository.save(entity);
    }
}
