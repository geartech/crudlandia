package com.crudlandia.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "centers")
@DynamicUpdate
@DynamicInsert
public class CenterEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    private CenterEntity parent;

    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "code_external", length = 20)
    private String codeExternal;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "description", length = 300)
    private String description;

 
    @Column(name = "active", nullable = false)
    private Boolean active;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CenterEntity getParent() {
        return parent;
    }

    public void setParent(CenterEntity parent) {
        this.parent = parent;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeExternal() {
        return codeExternal;
    }

    public void setCodeExternal(String codeExternal) {
        this.codeExternal = codeExternal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}

