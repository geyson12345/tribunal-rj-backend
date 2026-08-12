package br.com.teste.implementacao.tribunal.application.outputs;

import br.com.teste.implementacao.tribunal.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    void excluir(Long id);
}
