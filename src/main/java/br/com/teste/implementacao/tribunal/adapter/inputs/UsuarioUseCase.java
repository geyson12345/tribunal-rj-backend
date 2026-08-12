package br.com.teste.implementacao.tribunal.adapter.inputs;

import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioUseCase {

    Usuario cadastrar(Usuario usuario);

    Usuario buscarPorId(Long id);

    List<Usuario> buscarPorOrigem(String origem);

    void excluir(Long id);
}

