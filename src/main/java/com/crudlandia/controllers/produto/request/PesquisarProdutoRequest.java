package com.crudlandia.controllers.produto.request;

import lombok.Setter;
import lombok.Getter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

@Getter()
@Setter()
public class PesquisarProdutoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;

    private String nome;

    private Boolean ativo;

    private Integer pageNum;

    private Integer pageSize;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dthrInicio;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dthrFim;

    private String columnType;

    private String orderType;
}
