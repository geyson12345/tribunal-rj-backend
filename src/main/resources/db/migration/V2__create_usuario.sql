CREATE TABLE usuario (
                         id_usu    BIGINT NOT NULL,
                         nome_usu  VARCHAR(150) NOT NULL,
                         matr_usu  VARCHAR(30),
                         data_nasc DATE,
                         email     VARCHAR(200),
                         origem    VARCHAR(1) NOT NULL,

                         CONSTRAINT pk_usuario
                             PRIMARY KEY (id_usu),

                         CONSTRAINT fk_usuario_tipo
                             FOREIGN KEY (origem)
                                 REFERENCES tipo_usuario (origem)
);