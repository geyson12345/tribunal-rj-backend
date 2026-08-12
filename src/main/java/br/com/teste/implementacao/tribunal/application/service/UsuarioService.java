package br.com.teste.implementacao.tribunal.application.service;

import br.com.teste.implementacao.tribunal.adapter.inputs.UsuarioUseCase;
import br.com.teste.implementacao.tribunal.application.outputs.UsuarioQueryPort;
import br.com.teste.implementacao.tribunal.application.outputs.UsuarioRepositoryPort;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import br.com.teste.implementacao.tribunal.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;


@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final UsuarioQueryPort usuarioQueryPort;

    public UsuarioService(
            UsuarioRepositoryPort usuarioRepository,
            UsuarioQueryPort usuarioQueryPort) {

        this.usuarioRepository = usuarioRepository;
        this.usuarioQueryPort = usuarioQueryPort;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.salvar(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado: " + id
                        )
                );
    }

    @Override
    public List<Usuario> buscarPorOrigem(String origem) {
        return usuarioQueryPort.buscarPorOrigem(origem);
    }

    @Override
    public void excluir(Long id) {
        usuarioRepository.excluir(id);
    }
}