package com.crudlandia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configuração do Swagger/OpenAPI para documentação da API REST.
 * 
 * <p>
 * Esta classe configura o Swagger UI e a especificação OpenAPI 3.0, fornecendo documentação
 * interativa para todos os endpoints da API.
 * </p>
 * 
 * <p>
 * Acesse a documentação em: http://localhost:5001/crudlandia/swagger-ui.html
 * </p>
 * 
 * @author Crudlandia Team
 * @version 1.0
 * @since 2025-11-01
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura as informações da API OpenAPI.
     * 
     * @return objeto OpenAPI configurado com metadados da API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Crudlandia API").version("1.0.0")
                .description("API REST para gerenciamento de Exemplos e Referências")
                .contact(new Contact().name("Crudlandia Team").email("contato@crudlandia.com")
                        .url("https://github.com/geartech/crudlandia"))
                .license(new License().name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addServersItem(new Server().url("http://localhost:5001/crudlandia")
                        .description("Servidor de Desenvolvimento"));
    }
}
