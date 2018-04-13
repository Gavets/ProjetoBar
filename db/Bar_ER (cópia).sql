CREATE TABLE IF NOT EXISTS Cliente (
 cpf CHAR(11) NOT NULL,
 nome VARCHAR(200) NOT NULL,
 idade INT NOT NULL,
 genero BOOLEAN NOT NULL,
 socio BOOLEAN DEFAULT FALSE NOT NULL,
 numsocio VARCHAR(20)
);

ALTER TABLE Cliente ADD CONSTRAINT PK_Cliente PRIMARY KEY (cpf);


CREATE TABLE IF NOT EXISTS Caixa (
 id_caixa INT NOT NULL AUTO_INCREMENT,
 dia DATE NOT NULL,
 aberto BOOLEAN DEFAULT FALSE NOT NULL
);

ALTER TABLE Caixa ADD CONSTRAINT PK_Caixa PRIMARY KEY (id_caixa);


CREATE TABLE IF NOT EXISTS Cliente_Caixa (
 id_cliente_caixa INT NOT NULL AUTO_INCREMENT,
 cpf CHAR(11) NOT NULL,
 id_caixa INT NOT NULL
);

ALTER TABLE Cliente_Caixa ADD CONSTRAINT PK_Cliente_Caixa PRIMARY KEY (id_cliente_caixa,cpf,id_caixa);


CREATE TABLE IF NOT EXISTS RegistroEntradaSaida (
 id_registro INT NOT NULL AUTO_INCREMENT,
 id_cliente_caixa INT NOT NULL,
 cpf CHAR(11) NOT NULL,
 id_caixa INT NOT NULL,
 entrou BOOLEAN DEFAULT FALSE NOT NULL,
 horario_entrada TIME NOT NULL,
 horario_saida TIME
);

ALTER TABLE RegistroEntradaSaida ADD CONSTRAINT PK_RegistroEntradaSaida PRIMARY KEY (id_registro,id_cliente_caixa,cpf,id_caixa);

ALTER TABLE Cliente_Caixa ADD CONSTRAINT FK_Cliente_Caixa_0 FOREIGN KEY (cpf) REFERENCES Cliente (cpf);
ALTER TABLE Cliente_Caixa ADD CONSTRAINT FK_Cliente_Caixa_1 FOREIGN KEY (id_caixa) REFERENCES Caixa (id_caixa);
ALTER TABLE RegistroEntradaSaida ADD CONSTRAINT FK_RegistroEntradaSaida_0 FOREIGN KEY (id_cliente_caixa,cpf,id_caixa) REFERENCES Cliente_Caixa (id_cliente_caixa,cpf,id_caixa);


