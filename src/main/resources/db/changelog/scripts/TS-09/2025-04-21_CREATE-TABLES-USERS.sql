--liquibase formatted sql
--changeset luan-pedrosa:2025-04-21_01 author:luan-pedrosa


CREATE TABLE IF NOT EXISTS perfil
(
    id serial NOT NULL,
    nome character varying NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT name_uk UNIQUE (nome)
);

CREATE TABLE  IF NOT EXISTS usuario
(
    id serial NOT NULL,
    nome character varying NOT NULL,
    username character varying NOT NULL,
    senha character varying NOT NULL,
 
    PRIMARY KEY (id),
    CONSTRAINT username_uk UNIQUE (username)
);

ALTER TABLE IF EXISTS usuario
    ADD COLUMN perfil_id integer;
ALTER TABLE IF EXISTS pusuario
    ADD CONSTRAINT perfil_fk FOREIGN KEY (perfil_id)
    REFERENCES perfil (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

INSERT INTO perfil(
	id, nome)
	VALUES (1, 'Adm'); 

INSERT INTO usuario(
    id, nome, username, senha, perfil_id)
    VALUES (1, 'Luan Pedrosa', 'luan_c_pedrosa','1234',1),
            (2, 'Mateus Medeiros', 'mateus_med', '4321',1);
