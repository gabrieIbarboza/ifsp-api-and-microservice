CREATE TABLE contact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    contact_id BIGINT NOT NULL,
    CONSTRAINT fk_address_contact FOREIGN KEY (contact_id) REFERENCES contact(id) ON DELETE CASCADE
);