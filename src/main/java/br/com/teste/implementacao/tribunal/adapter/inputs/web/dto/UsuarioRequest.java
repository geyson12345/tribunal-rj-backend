package br.com.teste.implementacao.tribunal.adapter.inputs.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastro de um usuário")
public record UsuarioRequest(

        @Schema(
                description = "Identificador do usuário",
                example = "50000"
        )
        @NotNull(message = "O ID do usuário é obrigatório")
        Long id,

        @Schema(
                description = "Nome completo do usuário",
                example = "João da Silva"
        )
        @NotBlank(message = "O nome do usuário é obrigatório")
        @Size(max = 150)
        String nome,

        @Schema(
                description = "Matrícula do usuário",
                example = "RJ123456"
        )
        String matricula,

        @Schema(
                description = "Data de nascimento",
                example = "1990-05-20"
        )
        LocalDate dataNascimento,

        @Schema(
                description = "E-mail do usuário",
                example = "joao.silva@email.com"
        )
        @Email(message = "E-mail inválido")
        String email,

        @Schema(
                description = "Código da origem do usuário",
                example = "E"
        )
        @NotBlank(message = "A origem é obrigatória")
        @Size(min = 1, max = 1)
        String origem

) {
}