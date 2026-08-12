package br.com.teste.implementacao.tribunal.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @Column(name = "id_usu")
    private Long id;

    @Column(name = "nome_usu", nullable = false, length = 150)
    private String nome;

    @Column(name = "matr_usu", length = 30)
    private String matricula;

    @Column(name = "data_nasc")
    private LocalDate dataNascimento;

    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "origem", nullable = false, length = 1)
    private String origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "origem",
            referencedColumnName = "origem",
            insertable = false,
            updatable = false
    )
    private TipoUsuario tipoUsuario;

}