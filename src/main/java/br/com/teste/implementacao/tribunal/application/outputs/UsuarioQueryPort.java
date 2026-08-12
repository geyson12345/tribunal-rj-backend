package br.com.teste.implementacao.tribunal.application.outputs;


import br.com.teste.implementacao.tribunal.domain.entity.Usuario;

import java.util.List;

public interface UsuarioQueryPort {

    List<Usuario> buscarPorOrigem(String origem);
}
