package br.com.teste.implementacao.tribunal.adapter.inputs.web;

import br.com.teste.implementacao.tribunal.adapter.inputs.UsuarioUseCase;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.dto.UsuarioRequest;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.dto.UsuarioResponse;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.mapper.UsuarioWebMapper;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(
        name = "Usuários",
        description = "Operações para cadastro, consulta e exclusão de usuários"
)
@SecurityRequirement(name = "basicAuth")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;
    private final UsuarioWebMapper mapper;

    public UsuarioController(
            UsuarioUseCase usuarioUseCase,
            UsuarioWebMapper mapper) {

        this.usuarioUseCase = usuarioUseCase;
        this.mapper = mapper;
    }

    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastra um novo usuário no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados informados são inválidos"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(
            @Valid @RequestBody UsuarioRequest request) {

        Usuario usuario = mapper.toDomain(request);

        Usuario salvo = usuarioUseCase.cadastrar(usuario);

        UsuarioResponse response = mapper.toResponse(salvo);

        URI location = URI.create(
                "/api/v1/usuarios/" + salvo.getId()
        );

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados de um usuário a partir do seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UsuarioResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(

            @Parameter(
                    description = "Identificador do usuário",
                    example = "18241",
                    required = true
            )
            @PathVariable Long id) {

        Usuario usuario = usuarioUseCase.buscarPorId(id);

        return ResponseEntity.ok(
                mapper.toResponse(usuario)
        );
    }

    @Operation(
            summary = "Buscar usuários por origem",
            description = "Retorna os usuários vinculados à origem informada."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Origem informada é inválida"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> buscarPorOrigem(

            @Parameter(
                    description = "Código da origem do usuário",
                    example = "E",
                    required = true
            )
            @RequestParam String origem) {

        List<UsuarioResponse> response = usuarioUseCase
                .buscarPorOrigem(origem)
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Excluir usuário",
            description = "Exclui um usuário a partir do seu identificador."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Usuário não autenticado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(

            @Parameter(
                    description = "Identificador do usuário que será excluído",
                    example = "18241",
                    required = true
            )
            @PathVariable Long id) {

        usuarioUseCase.excluir(id);

        return ResponseEntity.noContent().build();
    }
}