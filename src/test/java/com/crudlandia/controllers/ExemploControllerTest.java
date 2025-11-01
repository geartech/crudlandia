package com.crudlandia.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.crudlandia.controllers.request.PesquisarExemploRequest;
import com.crudlandia.controllers.request.SalvarExemploRequest;
import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.enums.StatusEnum;
import com.crudlandia.exceptions.ExemploNaoEncontradoException;
import com.crudlandia.exceptions.ExemploNomeDuplicadoException;
import com.crudlandia.mappers.ExemploMapper;
import com.crudlandia.services.exemplo.ExemploService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;

@WebMvcTest(ExemploController.class)
class ExemploControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExemploService exemploService;

    @MockitoBean
    private ExemploMapper exemploMapper;

    private ExemploDTO exemploDTO;
    private SalvarExemploRequest salvarRequest;
    private PesquisarExemploRequest pesquisarRequest;

    @BeforeEach
    void setUp() {
        // Preparar DTO de exemplo
        exemploDTO = new ExemploDTO();
        exemploDTO.setId(1L);
        exemploDTO.setReferenciaId(10L);
        exemploDTO.setNome("Exemplo Teste");
        exemploDTO.setDescricao("Descrição do exemplo");
        exemploDTO.setSequencia(1);
        exemploDTO.setValor(new BigDecimal("100.50"));
        exemploDTO.setPeso(50.5);
        exemploDTO.setDthrEmissao(LocalDateTime.of(2025, 11, 1, 10, 0));
        exemploDTO.setStatus(StatusEnum.ATIVO);

        // Preparar request de salvamento
        salvarRequest = new SalvarExemploRequest();
        salvarRequest.setReferenciaId(10L);
        salvarRequest.setNome("Exemplo Teste");
        salvarRequest.setDescricao("Descrição do exemplo");
        salvarRequest.setSequencia(1);
        salvarRequest.setValor(new BigDecimal("100.50"));
        salvarRequest.setPeso(50.5);
        salvarRequest.setDthrEmissao(LocalDateTime.of(2025, 11, 1, 10, 0));
        salvarRequest.setStatus(StatusEnum.ATIVO);

        // Preparar request de pesquisa
        pesquisarRequest = new PesquisarExemploRequest();
        pesquisarRequest.setDthrInicio(LocalDate.of(2025, 1, 1));
        pesquisarRequest.setDthrFim(LocalDate.of(2025, 12, 31));
        pesquisarRequest.setNome("Teste");
        pesquisarRequest.setStatus(StatusEnum.ATIVO);
        pesquisarRequest.setPageNum(1);
        pesquisarRequest.setPageSize(10);
        pesquisarRequest.setColumnType("nome");
        pesquisarRequest.setOrderType("ASC");
    }

    @Test
    @DisplayName("Deve criar um novo exemplo com sucesso")
    void deveCriarExemploComSucesso() throws Exception {
        // Given
        when(exemploService.criar(anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class)))
                        .thenReturn(exemploDTO);

        // When & Then
        mockMvc.perform(post("/api/exemplo/criar").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvarRequest)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Exemplo Teste"))
                .andExpect(jsonPath("$.descricao").value("Descrição do exemplo"))
                .andExpect(jsonPath("$.status").value("ATIVO"))
                .andExpect(jsonPath("$.valor").value(100.50))
                .andExpect(jsonPath("$.peso").value(50.5));

        verify(exemploService, times(1)).criar(anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class));
    }

    @Test
    @DisplayName("Deve retornar erro 409 ao tentar criar exemplo com nome duplicado")
    void deveRetornarErroAoCriarComNomeDuplicado() throws Exception {
        // Given
        when(exemploService.criar(anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class)))
                        .thenThrow(new ExemploNomeDuplicadoException("Exemplo Teste", 1L));

        // When & Then
        mockMvc.perform(post("/api/exemplo/criar").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvarRequest)))
                .andExpect(status().isConflict()).andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"));

        verify(exemploService, times(1)).criar(anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class));
    }

    @Test
    @DisplayName("Deve atualizar um exemplo existente com sucesso")
    void deveAtualizarExemploComSucesso() throws Exception {
        // Given
        Long id = 1L;
        exemploDTO.setNome("Exemplo Atualizado");
        salvarRequest.setNome("Exemplo Atualizado");

        when(exemploService.atualizar(anyLong(), anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class)))
                        .thenReturn(exemploDTO);

        // When & Then
        mockMvc.perform(put("/api/exemplo/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvarRequest))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Exemplo Atualizado"));

        verify(exemploService, times(1)).atualizar(anyLong(), anyLong(), anyString(), anyString(),
                anyInt(), any(BigDecimal.class), anyDouble(), any(LocalDateTime.class));
    }

    @Test
    @DisplayName("Deve retornar erro 404 ao tentar atualizar exemplo inexistente")
    void deveRetornarErroAoAtualizarExemploInexistente() throws Exception {
        // Given
        Long id = 999L;
        when(exemploService.atualizar(anyLong(), anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class)))
                        .thenThrow(new ExemploNaoEncontradoException(id));

        // When & Then
        mockMvc.perform(put("/api/exemplo/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvarRequest)))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    @DisplayName("Deve retornar erro 409 ao tentar atualizar com nome duplicado")
    void deveRetornarErroAoAtualizarComNomeDuplicado() throws Exception {
        // Given
        Long id = 1L;
        when(exemploService.atualizar(anyLong(), anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class)))
                        .thenThrow(new ExemploNomeDuplicadoException("Exemplo Teste", 2L));

        // When & Then
        mockMvc.perform(put("/api/exemplo/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvarRequest)))
                .andExpect(status().isConflict()).andExpect(jsonPath("$.status").value(409));
    }

    @Test
    @DisplayName("Deve buscar exemplo por ID com sucesso")
    void deveBuscarExemploPorIdComSucesso() throws Exception {
        // Given
        Long id = 1L;
        when(exemploService.buscarPorId(id)).thenReturn(exemploDTO);

        // When & Then
        mockMvc.perform(get("/api/exemplo/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Exemplo Teste"))
                .andExpect(jsonPath("$.status").value("ATIVO"));

        verify(exemploService, times(1)).buscarPorId(id);
    }

    @Test
    @DisplayName("Deve retornar erro 404 ao buscar exemplo inexistente")
    void deveRetornarErroAoBuscarExemploInexistente() throws Exception {
        // Given
        Long id = 999L;
        when(exemploService.buscarPorId(id)).thenThrow(new ExemploNaoEncontradoException(id));

        // When & Then
        mockMvc.perform(get("/api/exemplo/{id}", id)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));

        verify(exemploService, times(1)).buscarPorId(id);
    }

    @Test
    @DisplayName("Deve listar exemplos com paginação")
    void deveListarExemplosComPaginacao() throws Exception {
        // Given
        Page<ExemploDTO> page = new Page<>();
        page.add(exemploDTO);
        page.setPageNum(1);
        page.setPageSize(10);
        page.setTotal(1);

        when(exemploMapper.listagemExemplo(any(LocalDate.class), any(LocalDate.class), anyString(),
                any(StatusEnum.class), anyString(), anyString())).thenReturn(page);

        // When & Then
        mockMvc.perform(get("/api/exemplo").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pesquisarRequest)))
                .andExpect(status().isOk());

        verify(exemploMapper, times(1)).listagemExemplo(any(LocalDate.class), any(LocalDate.class),
                anyString(), any(StatusEnum.class), anyString(), anyString());
    }

    @Test
    @DisplayName("Deve deletar exemplo com sucesso")
    void deveDeletarExemploComSucesso() throws Exception {
        // Given
        Long id = 1L;
        doNothing().when(exemploService).deletar(id);

        // When & Then
        mockMvc.perform(delete("/api/exemplo/{id}", id)).andExpect(status().isNoContent());

        verify(exemploService, times(1)).deletar(id);
    }

    @Test
    @DisplayName("Deve retornar erro 404 ao tentar deletar exemplo inexistente")
    void deveRetornarErroAoDeletarExemploInexistente() throws Exception {
        // Given
        Long id = 999L;
        doThrow(new ExemploNaoEncontradoException(id)).when(exemploService).deletar(id);

        // When & Then
        mockMvc.perform(delete("/api/exemplo/{id}", id)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));

        verify(exemploService, times(1)).deletar(id);
    }

    @Test
    @DisplayName("Deve criar exemplo com status ATIVO automaticamente")
    void deveCriarExemploComStatusAtivoAutomaticamente() throws Exception {
        // Given
        ExemploDTO dtoComStatusAtivo = new ExemploDTO();
        dtoComStatusAtivo.setId(1L);
        dtoComStatusAtivo.setReferenciaId(10L);
        dtoComStatusAtivo.setNome("Novo Exemplo");
        dtoComStatusAtivo.setDescricao("Descrição");
        dtoComStatusAtivo.setSequencia(1);
        dtoComStatusAtivo.setValor(new BigDecimal("100.00"));
        dtoComStatusAtivo.setPeso(50.0);
        dtoComStatusAtivo.setDthrEmissao(LocalDateTime.now());
        dtoComStatusAtivo.setStatus(StatusEnum.ATIVO);

        when(exemploService.criar(anyLong(), anyString(), anyString(), anyInt(),
                any(BigDecimal.class), anyDouble(), any(LocalDateTime.class)))
                        .thenReturn(dtoComStatusAtivo);

        // When & Then
        mockMvc.perform(post("/api/exemplo/criar").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salvarRequest)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.status").value("ATIVO"));
    }
}
