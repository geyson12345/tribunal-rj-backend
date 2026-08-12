package br.com.teste.implementacao.tribunal.adapter.inputs.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "basicAuth";

    @Bean
    public OpenAPI tribunalOpenAPI() {

        return new OpenAPI()

                .info(new Info()
                        .title("Tribunal RJ - API de Usuários")
                        .description("""
                                API REST responsável pelo gerenciamento de usuários.

                                Funcionalidades disponíveis:
                                - Cadastro de usuários
                                - Consulta de usuário por ID
                                - Consulta de usuários por origem
                                - Exclusão de usuários

                                Autenticação:
                                - HTTP Basic Authentication
                                """)
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")))

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("basic")
                                )
                );
    }
}