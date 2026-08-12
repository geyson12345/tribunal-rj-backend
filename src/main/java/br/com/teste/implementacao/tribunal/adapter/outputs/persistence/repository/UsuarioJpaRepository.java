package br.com.teste.implementacao.tribunal.adapter.outputs.persistence.repository;

import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository
        extends JpaRepository<Usuario, Long> {
}
