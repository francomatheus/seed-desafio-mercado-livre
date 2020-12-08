CREATE TABLE caracteristica_produto(
    id VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor VARCHAR(255) NOT NULL
);

CREATE TABLE produto(
    id VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    valor DECIMAL NOT NULL,
    quantidade_disponivel INT NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    instante_cadastro TIMESTAMP NOT NULL,
    categoria_id VARCHAR(255) NOT NULL,
    usuario_dono_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (usuario_dono_id) REFERENCES usuario(id)
);

CREATE TABLE produto_caracteristica_produto(
    produto_id VARCHAR(255) NOT NULL,
    caracteristica_produto_id VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    FOREIGN KEY (caracteristica_produto_id) REFERENCES caracteristica_produto(id)
);

