CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO _INCREMENT;
    nome VARCHAR(245) NOT NULL,
    logradouro VARCHAR(120) NOT NULL,
    complemento VARCHAR(20),
    bairro VARCHAR(50) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 1','Rua teste 1','apt teste 1','complemento 1',
            'bairro 1','cep 1','Cidade teste 1','A' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 2','Rua teste 2','apt teste 2','complemento 2',
            'bairro 2','cep 2','Cidade teste 2','B' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 3','Rua teste 3','apt teste 3','complemento 3',
            'bairro 3','cep 3','Cidade teste 3','C' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 4','Rua teste 4','apt teste 4','complemento 4',
            'bairro 4','cep 4','Cidade teste 4','D' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 5','Rua teste 5','apt teste 5','complemento 5',
            'bairro 5','cep 5','Cidade teste 5','E' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 6','Rua teste 6','apt teste 6','complemento 6',
            'bairro 6','cep 6','Cidade teste 6','F' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 7','Rua teste 7','apt teste 7','complemento 7',
            'bairro 7','cep 7','Cidade teste 7','G' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 8','Rua teste 8','apt teste 8','complemento 8',
            'bairro 8','cep 8','Cidade teste 8','H' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 9','Rua teste 9','apt teste 9','complemento 9',
            'bairro 9','cep 9','Cidade teste 9','I' );
INSERT INTO pessoa(nome, logradouro, complemento, bairro, cep, cidade, estado)
            values ('Antonio 10','Rua teste 10','apt teste 10','complemento 10',
            'bairro 10','cep 10','Cidade teste 10','J' );