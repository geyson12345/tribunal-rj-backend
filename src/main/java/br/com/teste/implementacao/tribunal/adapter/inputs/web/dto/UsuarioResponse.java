package br.com.teste.implementacao.tribunal.adapter.inputs.web.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados pela API para um usuário")
public record UsuarioResponse(

        @Schema(
                description = "Identificador do usuário",
                example = "18241"
        )
        Long id,

        @Schema(
                description = "Nome completo do usuário",
                example = "VIVIANE GOMES DELZI"
        )
        String nome,

        @Schema(
                description = "Matrícula do usuário",
                example = "RJ162042"
        )
        String matricula,

        @Schema(
                description = "Data de nascimento",
                example = "2025-01-13"
        )
        LocalDate dataNascimento,

        @Schema(
                description = "E-mail do usuário",
                example = "usuario@email.com"
        )
        String email,

        @Schema(
                description = "Código da origem",
                example = "E"
        )
        String origem,

        @Schema(
                description = "Descrição do tipo de usuário",
                example = "Externo"
        )
        String tipoUsuario

) {
}