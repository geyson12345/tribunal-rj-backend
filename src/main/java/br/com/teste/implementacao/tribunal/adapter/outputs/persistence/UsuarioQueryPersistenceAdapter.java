package br.com.teste.implementacao.tribunal.adapter.outputs.persistence;

import br.com.teste.implementacao.tribunal.application.outputs.UsuarioQueryPort;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UsuarioQueryPersistenceAdapter implements UsuarioQueryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorOrigem(String origem) {

        String sql = """
                SELECT
                    id_usu,
                    nome_usu,
                    matr_usu,
                    data_nasc,
                    email,
                    origem
                FROM fn_buscar_usuarios_por_origem(?)
                """;

        return entityManager
                .createNativeQuery(sql, Usuario.class)
                .setParameter(1, origem)
                .getResultList();
    }
}