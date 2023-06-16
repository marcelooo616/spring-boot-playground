CREATE TABLE CLIENTE (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100),
    EMAIL VARCHAR(100),
    ENDERECO VARCHAR(100)
);

CREATE TABLE PRODUTO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR(100),
    URL_IMAGEM VARCHAR(255),
    PRECO_UNITARIO NUMERIC(20,2)
);

CREATE TABLE ITEM_PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PRODUTO_ID INTEGER REFERENCES PRODUTO (ID),
    QUANTIDADE INTEGER
);

CREATE TABLE PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENTE_ID INTEGER REFERENCES CLIENTE (ID),
    DATA_PEDIDO TIMESTAMP,
    TOTAL NUMERIC (20,2)
);

