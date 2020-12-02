CREATE TABLE autor(
    id VARCHAR(255) PRIMARY KEY,
    login VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    hash_senha VARCHAR(255) NOT NULL,
    instante_criacao TIMESTAMP NOT NULL
) ;