package com.crudlandia.entities;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

    protected abstract Long getId();

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreatedBy
    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    /**
     * Returns the entity or null
     * 
     * @param <T>
     * @param <R>
     * @param entity
     * @param mapper
     * @return
     */
    public static <T, R> R mapEntity(T entity, Function<T, R> mapper) {
        return entity != null ? mapper.apply(entity) : null;
    }

    /**
     * Return id entity if it exists
     * 
     * @param entity
     * @return
     */
    public Long id(BaseEntity entity) {
        if (entity != null) {
            return entity.getId();
        }
        return null;
    }

}
