package br.com.teste.implementacao.tribunal.application.service;

import br.com.teste.implementacao.tribunal.application.outputs.UsuarioQueryPort;
import br.com.teste.implementacao.tribunal.application.outputs.UsuarioRepositoryPort;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepository;

    @Mock
    private UsuarioQueryPort usuarioQueryPort;

    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {

        usuarioService = new UsuarioService(
                usuarioRepository,
                usuarioQueryPort
        );

        usuario = new Usuario();
        usuario.setId(18241L);
        usuario.setNome("VIVIANE GOMES DELZI");
        usuario.setMatricula("RJ162042");
        usuario.setEmail("teste@email.com");
        usuario.setOrigem("E");
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {

        when(usuarioRepository.salvar(usuario))
                .thenReturn(usuario);

        Usuario resultado =
                usuarioService.cadastrar(usuario);

        assertNotNull(resultado);

        assertEquals(
                18241L,
                resultado.getId()
        );

        assertEquals(
                "VIVIANE GOMES DELZI",
                resultado.getNome()
        );

        verify(usuarioRepository, times(1))
                .salvar(usuario);
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {

        when(usuarioRepository.buscarPorId(18241L))
                .thenReturn(Optional.of(usuario));

        Usuario resultado =
                usuarioService.buscarPorId(18241L);

        assertNotNull(resultado);

        assertEquals(
                18241L,
                resultado.getId()
        );

        assertEquals(
                "VIVIANE GOMES DELZI",
                resultado.getNome()
        );

        verify(usuarioRepository, times(1))
                .buscarPorId(18241L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {

        when(usuarioRepository.buscarPorId(99999L))
                .thenReturn(Optional.empty());

        br.com.teste.implementacao.tribunal.exception.ResourceNotFoundException exception =
                assertThrows(
                        br.com.teste.implementacao.tribunal.exception.ResourceNotFoundException.class,
                        () -> usuarioService.buscarPorId(99999L)
                );

        assertEquals(
                "Usuário não encontrado: 99999",
                exception.getMessage()
        );

        verify(usuarioRepository, times(1))
                .buscarPorId(99999L);
    }

    @Test
    void deveBuscarUsuariosPorOrigem() {

        when(usuarioQueryPort.buscarPorOrigem("E"))
                .thenReturn(List.of(usuario));

        List<Usuario> resultado =
                usuarioService.buscarPorOrigem("E");

        assertNotNull(resultado);

        assertEquals(
                1,
                resultado.size()
        );

        assertEquals(
                "E",
                resultado.getFirst().getOrigem()
        );

        verify(usuarioQueryPort, times(1))
                .buscarPorOrigem("E");
    }

    @Test
    void deveExcluirUsuario() {

        doNothing()
                .when(usuarioRepository)
                .excluir(18241L);

        usuarioService.excluir(18241L);

        verify(usuarioRepository, times(1))
                .excluir(18241L);
    }
}
