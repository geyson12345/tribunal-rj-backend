package br.com.teste.implementacao.tribunal.adapter.outputs.persistence;

import br.com.teste.implementacao.tribunal.adapter.outputs.persistence.repository.UsuarioJpaRepository;
import br.com.teste.implementacao.tribunal.application.outputs.UsuarioRepositoryPort;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository repository;

    public UsuarioPersistenceAdapter(UsuarioJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
