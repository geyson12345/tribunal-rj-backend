CREATE TABLE tipo_usuario (
                              id_tipousuario BIGINT NOT NULL,
                              origem         VARCHAR(1) NOT NULL,
                              descr          VARCHAR(100) NOT NULL,

                              CONSTRAINT pk_tipo_usuario
                                  PRIMARY KEY (id_tipousuario),

                              CONSTRAINT uk_tipo_usuario_origem
                                  UNIQUE (origem)
);