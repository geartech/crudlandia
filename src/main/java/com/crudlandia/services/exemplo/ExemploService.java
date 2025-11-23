package com.crudlandia.services.exemplo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.exceptions.ExemploNaoEncontradoException;
import com.crudlandia.exceptions.ExemploNomeDuplicadoException;
import com.crudlandia.exceptions.ReferenciaNaoEncontradoException;

/**
 * Interface de serviço para operações de negócio relacionadas a Exemplos.
 * 
 * <p>
 * Define o contrato para gerenciamento de exemplos, incluindo validações de negócio como
 * verificação de nome único e definição automática de status.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
public interface ExemploService {

    /**
     * Cria um novo exemplo no sistema.
     * 
     * <p>
     * Regras de negócio aplicadas:
     * </p>
     * <ul>
     * <li>Não permite criar exemplo com nome duplicado</li>
     * <li>Define status como ATIVO automaticamente</li>
     * <li>Valida existência da referência informada</li>
     * </ul>
     * 
     * @param referenciaId identificador da referência associada
     * @param nome nome do exemplo (deve ser único)
     * @param descricao descrição detalhada do exemplo
     * @param sequencia número de sequência do exemplo
     * @param valor valor monetário associado
     * @param peso peso do exemplo
     * @param dthrEmissao data e hora de emissão (se null, usa data/hora atual)
     * @return ExemploDTO com os dados do exemplo criado
     * @throws com.crudlandia.exceptions.ExemploNomeDuplicadoException se já existir exemplo com o
     *         mesmo nome
     * @throws com.crudlandia.exceptions.ReferenciaNaoEncontradoException se a referência não
     *         existir
     */
    public ExemploDTO criar(Long referenciaId, String nome, String descricao, Integer sequencia,
            BigDecimal valor, Double peso, LocalDateTime dthrEmissao) throws ReferenciaNaoEncontradoException;

    /**
     * Atualiza um exemplo existente.
     * 
     * <p>
     * Valida se o novo nome não está sendo usado por outro exemplo.
     * </p>
     * 
     * @param id identificador único do exemplo a ser atualizado
     * @param referenciaId identificador da referência associada
     * @param nome novo nome do exemplo
     * @param descricao nova descrição do exemplo
     * @param sequencia nova sequência
     * @param valor novo valor
     * @param peso novo peso
     * @param dthrEmissao nova data/hora de emissão
     * @return ExemploDTO com os dados atualizados
     * @throws com.crudlandia.exceptions.ExemploNaoEncontradoException se o exemplo não existir
     * @throws com.crudlandia.exceptions.ExemploNomeDuplicadoException se o nome já estiver em uso
     *         por outro exemplo
     * @throws com.crudlandia.exceptions.ReferenciaNaoEncontradoException se a referência não
     *         existir
     */
    public ExemploDTO atualizar(Long id, Long referenciaId, String nome, String descricao,
            Integer sequencia, BigDecimal valor, Double peso, LocalDateTime dthrEmissao) throws ExemploNaoEncontradoException, ReferenciaNaoEncontradoException, ExemploNomeDuplicadoException;

    /**
     * Busca um exemplo por seu identificador único.
     * 
     * @param id identificador único do exemplo
     * @return ExemploDTO com os dados do exemplo
     * @throws com.crudlandia.exceptions.ExemploNaoEncontradoException se o exemplo não existir
     */
    public ExemploDTO buscarPorId(Long id) throws ExemploNaoEncontradoException;

    /**
     * Deleta um exemplo do sistema.
     * 
     * @param id identificador único do exemplo a ser deletado
     * @throws com.crudlandia.exceptions.ExemploNaoEncontradoException se o exemplo não existir
     */
    public void deletar(Long id) throws ExemploNaoEncontradoException;

    public void desativar(Long id) throws ExemploNaoEncontradoException;
    
    
}
