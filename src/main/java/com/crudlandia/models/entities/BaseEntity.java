package com.crudlandia.models.entities;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

/**
 * Classe base abstrata para todas as entidades do sistema.
 * 
 * <p>
 * Fornece campos comuns de auditoria que são automaticamente gerenciados pelo Spring Data JPA,
 * incluindo informações sobre criação, atualização e versionamento.
 * </p>
 * 
 * <p>
 * Todas as entidades devem estender esta classe para herdar os campos de auditoria.
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Obtém o identificador único da entidade.
     * 
     * @return o ID da entidade
     */
    protected abstract Long getId();

    @CreatedDate
    @Column(name = "dthrCriacao", updatable = false)
    private LocalDateTime dthrCriacao;

    @CreatedBy
    @Column(name = "criadoPor", updatable = false)
    private String creadoPor;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "dthrAtualizacao")
    private LocalDateTime dthrAtualizacao;

    @CreatedBy
    @LastModifiedBy
    @Column(name = "atualizadoPor")
    private String atualizadoPor;

    @Version
    @Column(name = "versao", nullable = false)
    private Integer versao;

    public LocalDateTime getDthrCriacao() {
        return dthrCriacao;
    }

    public void setDthrCriacao(LocalDateTime dthrCriacao) {
        this.dthrCriacao = dthrCriacao;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public LocalDateTime getDthrAtualizacao() {
        return dthrAtualizacao;
    }

    public void setDthrAtualizacao(LocalDateTime dthrAtualizacao) {
        this.dthrAtualizacao = dthrAtualizacao;
    }

    public String getAtualizadoPor() {
        return atualizadoPor;
    }

    public void setAtualizadoPor(String atualizadoPor) {
        this.atualizadoPor = atualizadoPor;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    /**
     * Mapeia uma entidade para outro tipo usando uma função de transformação.
     * 
     * <p>
     * Método utilitário que aplica uma função de mapeamento apenas se a entidade não for null.
     * </p>
     * 
     * @param <T> tipo da entidade de origem
     * @param <R> tipo do resultado do mapeamento
     * @param entity entidade a ser mapeada
     * @param mapper função de mapeamento
     * @return resultado do mapeamento, ou null se a entidade for null
     */
    public static <T, R> R mapEntity(T entity, Function<T, R> mapper) {
        return entity != null ? mapper.apply(entity) : null;
    }

    /**
     * Retorna o ID de uma entidade, se existir.
     * 
     * <p>
     * Método utilitário para extrair o ID de uma entidade de forma segura, retornando null se a
     * entidade for null.
     * </p>
     * 
     * @param entity entidade da qual extrair o ID
     * @return o ID da entidade, ou null se a entidade for null
     */
    public Long id(BaseEntity entity) {
        if (entity != null) {
            return entity.getId();
        }
        return null;
    }

}
