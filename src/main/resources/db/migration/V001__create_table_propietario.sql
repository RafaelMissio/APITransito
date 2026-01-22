CREATE TABLE propietario (
                             id BIGSERIAL PRIMARY KEY,
                             nome VARCHAR(60) NOT NULL,
                             email VARCHAR(255) NOT NULL,
                             telefone VARCHAR(20) NOT NULL,
                             CONSTRAINT uk_propietario UNIQUE (email)
);
