CREATE TABLE product (
    id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (id)
);