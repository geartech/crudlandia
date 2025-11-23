package com.crudlandia.services.exemplo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.enums.StatusEnum;
import com.crudlandia.exceptions.ExemploNaoEncontradoException;
import com.crudlandia.exceptions.ExemploNomeDuplicadoException;
import com.crudlandia.exceptions.ReferenciaNaoEncontradoException;
import com.crudlandia.models.entities.ExemploEntity;
import com.crudlandia.models.entities.ReferenciaEntity;
import com.crudlandia.models.repository.ExemploRepository;
import com.crudlandia.models.repository.ReferenciaRepository;

/**
 * Implementação do serviço de negócio para operações relacionadas a Exemplos.
 * 
 * <p>
 * Esta classe implementa todas as regras de negócio definidas na interface {@link ExemploService},
 * incluindo validações e transações de banco de dados.
 * </p>
 * 
 * <p>
 * Todas as operações de escrita (criar, atualizar, deletar) são transacionais.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 * @see ExemploService
 */
@Service
@Transactional
public class ExemploServiceImpl implements ExemploService {

    @Autowired
    private ExemploRepository exemploRepository;

    @Autowired
    protected ReferenciaRepository referenciaRepository;

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Implementação que valida nome único, busca a referência no banco, e define status ATIVO
     * automaticamente na criação.
     * </p>
     * @throws ReferenciaNaoEncontradoException 
     */
    public ExemploDTO criar(Long referenciaId, String nome, String descricao, Integer sequencia,
            BigDecimal valor, Double peso, LocalDateTime dthrEmissao) throws ReferenciaNaoEncontradoException {

        // Validar se já existe exemplo com o mesmo nome
        exemploRepository.findByNome(nome).ifPresent(exemplo -> new ExemploNomeDuplicadoException(nome, exemplo.getId()));

        ReferenciaEntity referencia = referenciaRepository.findById(referenciaId)
                .orElseThrow(() -> new ReferenciaNaoEncontradoException(referenciaId));

        ExemploEntity entity = new ExemploEntity();
        entity.setReferencia(referencia);
        entity.setNome(nome);
        entity.setDescricao(descricao);
        entity.setSequencia(sequencia);
        entity.setValor(valor);
        entity.setPeso(peso);
        entity.setDthrEmissao(dthrEmissao != null ? dthrEmissao : LocalDateTime.now());

        // Status sempre ATIVO na criação
        entity.setStatus(StatusEnum.ATIVO);

        ExemploEntity saved = exemploRepository.save(entity);
        return saved.getDRO();
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Implementação que valida se o exemplo existe, verifica duplicidade de nome com outros
     * exemplos, e atualiza todos os campos.
     * </p>
     * @throws ExemploNaoEncontradoException 
     * @throws ReferenciaNaoEncontradoException 
     * @throws ExemploNomeDuplicadoException 
     */
    public ExemploDTO atualizar(Long id, Long referenciaId, String nome, String descricao,
            Integer sequencia, BigDecimal valor, Double peso, LocalDateTime dthrEmissao) throws ExemploNaoEncontradoException, 
    		ReferenciaNaoEncontradoException, ExemploNomeDuplicadoException {

        ExemploEntity entity = exemploRepository.findById(id)
                .orElseThrow(() -> new ExemploNaoEncontradoException(id));

        // Validar se já existe outro exemplo com o mesmo nome
        Optional<ExemploEntity> exemploExistente = exemploRepository.findByNome(nome);
        if (exemploExistente.isPresent()) {
        	if (!exemploExistente.get().getId().equals(id)) {
                throw new ExemploNomeDuplicadoException(nome, exemploExistente.get().getId());
             }	
        }
        
       

        ReferenciaEntity referencia = referenciaRepository.findById(referenciaId)
                .orElseThrow(() -> new ReferenciaNaoEncontradoException(referenciaId));

        entity.setReferencia(referencia);
        entity.setNome(nome);
        entity.setDescricao(descricao);
        entity.setSequencia(sequencia);
        entity.setValor(valor);
        entity.setPeso(peso);
        entity.setDthrEmissao(dthrEmissao);

        ExemploEntity updated = exemploRepository.save(entity);
        return updated.getDRO();
    }

    /**
     * {@inheritDoc}
     * @throws ExemploNaoEncontradoException 
     */
    public ExemploDTO buscarPorId(Long id) throws ExemploNaoEncontradoException {
        ExemploEntity entity = exemploRepository.findById(id).orElseThrow(() -> new ExemploNaoEncontradoException(id));
        return entity.getDRO();
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * Implementação que valida se o exemplo existe antes de deletar.
     * </p>
     * @throws ExemploNaoEncontradoException 
     */
    public void deletar(Long id) throws ExemploNaoEncontradoException {
        ExemploEntity entity = exemploRepository.findById(id).orElseThrow(() -> new ExemploNaoEncontradoException(id));
        exemploRepository.delete(entity);
    }
    

    public void desativar(Long id) throws ExemploNaoEncontradoException {
        ExemploEntity entity = exemploRepository.findById(id).orElseThrow(() -> new ExemploNaoEncontradoException(id));
        entity.setStatus(StatusEnum.INATIVO);
    }
    

}
