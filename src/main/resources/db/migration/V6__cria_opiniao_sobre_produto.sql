CREATE TABLE opiniao_produto(
    id VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    nota INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    usuario_id VARCHAR(255) NOT NULL,
    produto_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
)