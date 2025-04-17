CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    prioridade VARCHAR(15) NOT NULL,
    data_limite DATE NOT NULL,
    concluida BOOLEAN NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    criada_em DATE NOT NULL
);