package br.com.teste.implementacao.tribunal.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_usuario")
@Getter
@Setter
public class TipoUsuario {

    @Id
    @Column(name = "id_tipousuario")
    private Long id;

    @Column(name = "origem", nullable = false, unique = true, length = 1)
    private String origem;

    @Column(name = "descr", nullable = false, length = 100)
    private String descricao;
}