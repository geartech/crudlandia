package com.crudlandia.controllers;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.crudlandia.controllers.exemplo.request.PesquisarCadastroExemploRequest;
import com.crudlandia.controllers.exemplo.request.SalvarCadastroExemploRequest;
import com.crudlandia.dtos.ExemploDTO;
import com.crudlandia.enums.StatusEnum;
import com.crudlandia.models.entities.ReferenciaEntity;
import com.crudlandia.models.repository.ExemploRepository;
import com.crudlandia.models.repository.ReferenciaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class CadastroExemploControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private ExemploRepository exemploRepository;

        @Autowired
        private ReferenciaRepository referenciaRepository;

        private ReferenciaEntity referencia;
        private SalvarCadastroExemploRequest salvarRequest;
        private PesquisarCadastroExemploRequest pesquisarRequest;

        @BeforeEach
        void setUp() {
                // Limpar repositórios
                exemploRepository.deleteAll();
                referenciaRepository.deleteAll();

                // Criar referência para testes
                referencia = new ReferenciaEntity();
                referencia.setCodigo("REF001");
                referencia.setNome("Referência Teste");
                referencia = referenciaRepository.save(referencia);

                // Preparar request de salvamento
                salvarRequest = new SalvarCadastroExemploRequest();
                salvarRequest.setReferenciaId(referencia.getId());
                salvarRequest.setNome("Exemplo Teste");
                salvarRequest.setDescricao("Descrição do exemplo");
                salvarRequest.setSequencia(1);
                salvarRequest.setValor(new BigDecimal("100.50"));
                salvarRequest.setPeso(50.5);
                salvarRequest.setDthrEmissao(LocalDateTime.of(2025, 11, 1, 10, 0));
                salvarRequest.setStatus(StatusEnum.ATIVO);

                // Preparar request de pesquisa
                pesquisarRequest = new PesquisarCadastroExemploRequest();
                pesquisarRequest.setDthrInicio(LocalDate.of(2025, 1, 1));
                pesquisarRequest.setDthrFim(LocalDate.of(2025, 12, 31));
                pesquisarRequest.setNome("Exemplo");
                pesquisarRequest.setStatus(StatusEnum.ATIVO);
                pesquisarRequest.setPageNum(1);
                pesquisarRequest.setPageSize(10);
                pesquisarRequest.setColumnType("nome");
                pesquisarRequest.setOrderType("ASC");
        }

        @Test
        @DisplayName("Deve criar um novo exemplo com sucesso")
        void deveCriarExemploComSucesso() throws Exception {
                mockMvc.perform(post("/cadastro/exemplo/criar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.nome").value("Exemplo Teste"))
                                .andExpect(jsonPath("$.descricao").value("Descrição do exemplo"))
                                .andExpect(jsonPath("$.status").value("ATIVO"))
                                .andExpect(jsonPath("$.valor").value(100.50))
                                .andExpect(jsonPath("$.peso").value(50.5));
        }

        @Test
        @DisplayName("Deve retornar erro 409 ao tentar criar exemplo com nome duplicado")
        void deveRetornarErroAoCriarComNomeDuplicado() throws Exception {
                // Criar primeiro exemplo
                mockMvc.perform(post("/cadastro/exemplo/criar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated());

                // Tentar criar com mesmo nome
                mockMvc.perform(post("/cadastro/exemplo/criar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isConflict())
                                .andExpect(jsonPath("$.status").value(409));
        }

        @Test
        @DisplayName("Deve atualizar um exemplo existente com sucesso")
        void deveAtualizarExemploComSucesso() throws Exception {
                // Criar exemplo
                String createResponse = mockMvc
                                .perform(post("/cadastro/exemplo/criar")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper
                                                                .writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated()).andReturn().getResponse()
                                .getContentAsString();

                ExemploDTO criado = objectMapper.readValue(createResponse, ExemploDTO.class);
                salvarRequest.setNome("Exemplo Atualizado");

                mockMvc.perform(put("/cadastro/exemplo/atualizar/{id}", criado.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nome").value("Exemplo Atualizado"));
        }

        @Test
        @DisplayName("Deve retornar erro 404 ao tentar atualizar exemplo inexistente")
        void deveRetornarErroAoAtualizarExemploInexistente() throws Exception {
                mockMvc.perform(put("/cadastro/exemplo/atualizar/{id}", 999L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.status").value(404));
        }

        @Test
        @DisplayName("Deve retornar erro 409 ao tentar atualizar com nome duplicado")
        void deveRetornarErroAoAtualizarComNomeDuplicado() throws Exception {
                // Criar dois exemplos
                String createResponse1 = mockMvc
                                .perform(post("/cadastro/exemplo/criar")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper
                                                                .writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated()).andReturn().getResponse()
                                .getContentAsString();

                salvarRequest.setNome("Outro Exemplo");
                String createResponse2 = mockMvc
                                .perform(post("/cadastro/exemplo/criar")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper
                                                                .writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated()).andReturn().getResponse()
                                .getContentAsString();

                ExemploDTO exemplo1 = objectMapper.readValue(createResponse1, ExemploDTO.class);
                ExemploDTO exemplo2 = objectMapper.readValue(createResponse2, ExemploDTO.class);

                // Tentar atualizar exemplo2 com nome do exemplo1
                salvarRequest.setNome(exemplo1.getNome());

                mockMvc.perform(put("/cadastro/exemplo/atualizar/{id}", exemplo2.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isConflict())
                                .andExpect(jsonPath("$.status").value(409));
        }

        @Test
        @DisplayName("Deve buscar exemplo por ID com sucesso")
        void deveBuscarExemploPorIdComSucesso() throws Exception {
                // Criar exemplo
                String createResponse = mockMvc
                                .perform(post("/cadastro/exemplo/criar")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper
                                                                .writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated()).andReturn().getResponse()
                                .getContentAsString();

                ExemploDTO criado = objectMapper.readValue(createResponse, ExemploDTO.class);

                mockMvc.perform(get("/cadastro/exemplo/buscarPorId/{id}", criado.getId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.nome").value("Exemplo Teste"))
                                .andExpect(jsonPath("$.status").value("ATIVO"));
        }

        @Test
        @DisplayName("Deve retornar erro 404 ao buscar exemplo inexistente")
        void deveRetornarErroAoBuscarExemploInexistente() throws Exception {
                mockMvc.perform(get("/cadastro/exemplo/buscarPorId/{id}", 999L))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.status").value(404));
        }

        @Test
        @DisplayName("Deve listar exemplos com paginação")
        void deveListarExemplosComPaginacao() throws Exception {
                // Criar exemplo para listar
                mockMvc.perform(post("/cadastro/exemplo/criar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated());

                // Listar com paginação
                mockMvc.perform(post("/cadastro/exemplo/listagem")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(pesquisarRequest)))
                                .andExpect(status().isOk()).andExpect(jsonPath("$.list").isArray())
                                .andExpect(jsonPath("$.list[0].nome").value("Exemplo Teste"));
        }

        @Test
        @DisplayName("Deve deletar exemplo com sucesso")
        void deveDeletarExemploComSucesso() throws Exception {
                // Criar exemplo
                String createResponse = mockMvc
                                .perform(post("/cadastro/exemplo/criar")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper
                                                                .writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated()).andReturn().getResponse()
                                .getContentAsString();

                ExemploDTO criado = objectMapper.readValue(createResponse, ExemploDTO.class);

                mockMvc.perform(delete("/cadastro/exemplo/deletar/{id}", criado.getId()))
                                .andExpect(status().isNoContent());
        }

        @Test
        @DisplayName("Deve retornar erro 404 ao tentar deletar exemplo inexistente")
        void deveRetornarErroAoDeletarExemploInexistente() throws Exception {
                mockMvc.perform(delete("/cadastro/exemplo/deletar/{id}", 999L))
                                .andExpect(status().isNotFound())
                                .andExpect(jsonPath("$.status").value(404));
        }

        @Test
        @DisplayName("Deve criar exemplo com status ATIVO automaticamente")
        void deveCriarExemploComStatusAtivoAutomaticamente() throws Exception {
                mockMvc.perform(post("/cadastro/exemplo/criar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(salvarRequest)))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.status").value("ATIVO"));
        }
}
