package br.com.teste.implementacao.tribunal.adapter.in.web;


import br.com.teste.implementacao.tribunal.adapter.inputs.UsuarioUseCase;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.UsuarioController;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.dto.UsuarioResponse;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.mapper.UsuarioWebMapper;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import br.com.teste.implementacao.tribunal.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioUseCase usuarioUseCase;

    @MockitoBean
    private UsuarioWebMapper mapper;

    @Test
    void deveRetornar401QuandoNaoEstiverAutenticado()
            throws Exception {

        mockMvc.perform(
                        get("/api/v1/usuarios")
                                .param("origem", "E")
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = "USER"
    )
    void deveBuscarUsuariosPorOrigemComSucesso()
            throws Exception {

        Usuario usuario = criarUsuario();

        UsuarioResponse response =
                criarUsuarioResponse();

        when(usuarioUseCase.buscarPorOrigem("E"))
                .thenReturn(List.of(usuario));

        when(mapper.toResponse(usuario))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/v1/usuarios")
                                .param("origem", "E")
                )
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .contentTypeCompatibleWith(
                                        "application/json"
                                )
                )
                .andExpect(
                        jsonPath("$[0].id")
                                .value(18241)
                )
                .andExpect(
                        jsonPath("$[0].nome")
                                .value("VIVIANE GOMES DELZI")
                )
                .andExpect(
                        jsonPath("$[0].origem")
                                .value("E")
                );
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = "USER"
    )
    void deveBuscarUsuarioPorIdComSucesso()
            throws Exception {

        Usuario usuario = criarUsuario();

        UsuarioResponse response =
                criarUsuarioResponse();

        when(usuarioUseCase.buscarPorId(18241L))
                .thenReturn(usuario);

        when(mapper.toResponse(usuario))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/v1/usuarios/18241")
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.id")
                                .value(18241)
                )
                .andExpect(
                        jsonPath("$.nome")
                                .value("VIVIANE GOMES DELZI")
                );
    }

    @Test
    @WithMockUser(
            username = "admin",
            roles = "USER"
    )
    void deveRetornar404QuandoUsuarioNaoExistir()
            throws Exception {

        when(usuarioUseCase.buscarPorId(99999L))
                .thenThrow(
                        new ResourceNotFoundException(
                                "Usuário não encontrado: 99999"
                        )
                );

        mockMvc.perform(
                        get("/api/v1/usuarios/99999")
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.status")
                                .value(404)
                )
                .andExpect(
                        jsonPath("$.message")
                                .value(
                                        "Usuário não encontrado: 99999"
                                )
                );
    }

    private Usuario criarUsuario() {

        Usuario usuario = new Usuario();

        usuario.setId(18241L);
        usuario.setNome("VIVIANE GOMES DELZI");
        usuario.setMatricula("RJ162042");
        usuario.setDataNascimento(
                LocalDate.of(2025, 1, 13)
        );
        usuario.setEmail("teste@email.com");
        usuario.setOrigem("E");

        return usuario;
    }

    private UsuarioResponse criarUsuarioResponse() {

        return new UsuarioResponse(
                18241L,
                "VIVIANE GOMES DELZI",
                "RJ162042",
                LocalDate.of(2025, 1, 13),
                "teste@email.com",
                "E",
                "Externo"
        );
    }
}