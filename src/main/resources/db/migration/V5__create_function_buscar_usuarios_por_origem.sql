CREATE OR REPLACE FUNCTION fn_buscar_usuarios_por_origem(
    p_origem VARCHAR(1)
)
RETURNS TABLE (
    id_usu BIGINT,
    nome_usu VARCHAR(150),
    matr_usu VARCHAR(30),
    data_nasc DATE,
    email VARCHAR(200),
    origem VARCHAR(1),
    tipo_usuario VARCHAR(100)
)
LANGUAGE SQL
AS $$
SELECT
    u.id_usu,
    u.nome_usu,
    u.matr_usu,
    u.data_nasc,
    u.email,
    u.origem,
    t.descr
FROM usuario u
         INNER JOIN tipo_usuario t
                    ON t.origem = u.origem
WHERE u.origem = p_origem
ORDER BY u.nome_usu;
$$;