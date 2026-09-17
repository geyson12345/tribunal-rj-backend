package br.com.teste.implementacao.tribunal.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TipoUsuarioTest {

    private TipoUsuario tipoUsuario;

    @BeforeEach
    void setUp() {
        tipoUsuario = new TipoUsuario();
        tipoUsuario.setOrigem("M");
        tipoUsuario.setDescricao("Magistrado");
    }

    @Test
    void deveRetornarDescricaoCompleta() {

        String resultado = tipoUsuario.obterDescricaoCompleta();

        assertEquals(
                "M - Magistrado",
                resultado
        );
    }
}
