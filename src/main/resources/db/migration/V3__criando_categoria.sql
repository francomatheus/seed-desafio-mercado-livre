CREATE TABLE categoria(
    id VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    categoria_mae_id VARCHAR(255),
    FOREIGN KEY(categoria_mae_id) REFERENCES categoria(id)
);

