package br.com.teste.implementacao.tribunal.adapter.inputs.web.mapper;

import br.com.teste.implementacao.tribunal.adapter.inputs.web.dto.UsuarioRequest;
import br.com.teste.implementacao.tribunal.adapter.inputs.web.dto.UsuarioResponse;
import br.com.teste.implementacao.tribunal.domain.entity.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UsuarioWebMapper {

    public Usuario toDomain(UsuarioRequest request) {

        Usuario usuario = new Usuario();

        usuario.setId(request.id());
        usuario.setNome(request.nome());
        usuario.setMatricula(request.matricula());
        usuario.setDataNascimento(request.dataNascimento());
        usuario.setEmail(request.email());
        usuario.setOrigem(request.origem());

        return usuario;
    }

    public UsuarioResponse toResponse(Usuario usuario) {

        String tipoUsuario = usuario.getTipoUsuario() != null
                ? usuario.getTipoUsuario().getDescricao()
                : null;

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getMatricula(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                usuario.getOrigem(),
                tipoUsuario
        );
    }
}
