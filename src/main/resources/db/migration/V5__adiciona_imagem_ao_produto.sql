CREATE TABLE produto_imagens(
    produto_id VARCHAR(255) NOT NULL,
    imagens VARCHAR(255) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);