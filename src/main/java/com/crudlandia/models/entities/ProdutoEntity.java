package com.crudlandia.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import com.crudlandia.dtos.ProdutoDTO;
import java.time.LocalDate;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicInsert;

@Entity()
@Table(name = "produtos")
@DynamicUpdate()
@DynamicInsert()
public class ProdutoEntity extends BaseEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo", nullable = false, length = 100)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Override()
    protected Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getValidade() {
        return this.validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public ProdutoDTO getDTO() {
        return new ProdutoDTO(this.id, this.codigo, this.nome, this.valor, this.marca, this.validade, this.ativo);
    }
}
