--liquibase formatted sql
--changeset luan-pedrosa:2025-04-04_01 author:luan-pedrosa

CREATE SEQUENCE public.materiais_id_seq;

CREATE TABLE public.material (
                id INTEGER NOT NULL DEFAULT nextval('public.materiais_id_seq'),
                nome VARCHAR(25) NOT NULL,
                descricao VARCHAR(50),
                quantidade INTEGER,
                CONSTRAINT materiais_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.materiais_id_seq OWNED BY public.material.id;

CREATE SEQUENCE public.atividade_id_seq;

CREATE TABLE public.atividade (
                id INTEGER NOT NULL DEFAULT nextval('public.atividade_id_seq'),
                data DATE NOT NULL,
                relatorio VARCHAR(250),
                CONSTRAINT atividade_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.atividade_id_seq OWNED BY public.atividade.id;

CREATE SEQUENCE public.materiais_atividade_id_seq;

CREATE TABLE public.materiais_atividade (
                id INTEGER NOT NULL DEFAULT nextval('public.materiais_atividade_id_seq'),
                quantidade_usada INTEGER DEFAULT 1 NOT NULL,
                CONSTRAINT mat_atv_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.materiais_atividade_id_seq OWNED BY public.materiais_atividade.id;

CREATE SEQUENCE public.imagens_atividade_id_seq;

CREATE TABLE public.imagens_atividade (
                id INTEGER NOT NULL DEFAULT nextval('public.imagens_atividade_id_seq'),
                imagem BYTEA  NULL,
                CONSTRAINT imagens_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.imagens_atividade_id_seq OWNED BY public.imagens_atividade.id;

CREATE SEQUENCE public.pessoas_id_seq;

CREATE TABLE public.pessoa (
                id INTEGER NOT NULL DEFAULT nextval('public.pessoas_id_seq'),
                nome VARCHAR(100) NOT NULL,
                cpf VARCHAR(15) NOT NULL,
                CONSTRAINT pessoa_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.pessoas_id_seq OWNED BY public.pessoa.id;

CREATE SEQUENCE public.voluntarios_id_seq;

CREATE TABLE public.voluntario (
                id INTEGER NOT NULL DEFAULT nextval('public.voluntarios_id_seq'),
                ocupacao VARCHAR(25) NOT NULL,
                telefone VARCHAR(17) NOT NULL,
                CONSTRAINT voluntario_id PRIMARY KEY (id)
);

ALTER SEQUENCE public.voluntarios_id_seq OWNED BY public.voluntario.id;



CREATE SEQUENCE public.atividade_voluntarios_id_seq;

CREATE TABLE public.atividadevoluntarios (
                id INTEGER NOT NULL DEFAULT nextval('public.atividade_voluntarios_id_seq'),
                CONSTRAINT vatv_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.atividade_voluntarios_id_seq OWNED BY public.atividadevoluntarios.id;

CREATE SEQUENCE public.membro_comunidade_id_seq;

CREATE TABLE public.membrocomunidade (
                id INTEGER NOT NULL DEFAULT nextval('public.membro_comunidade_id_seq'),
                telefone VARCHAR(17),
                foto BYTEA  NULL,
                CONSTRAINT mc_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.membro_comunidade_id_seq OWNED BY public.membrocomunidade.id;

CREATE SEQUENCE public.atividade_membros_id_seq;

CREATE TABLE public.atividademembros (
                id INTEGER NOT NULL DEFAULT nextval('public.atividade_membros_id_seq'),
                CONSTRAINT matv_id PRIMARY KEY (id)
);


ALTER SEQUENCE public.atividade_membros_id_seq OWNED BY public.atividademembros.id;

CREATE TABLE public.cesta_basica (
                id INTEGER NOT NULL,
                valor DOUBLE PRECISION NOT NULL,
                data_oferecimento DATE NOT NULL,
                CONSTRAINT cesta_basica_id PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.materiais_atividade
    ADD material_id integer;
ALTER TABLE public.materiais_atividade ADD CONSTRAINT materiais_materiais_atividade_fk
FOREIGN KEY (material_id)
REFERENCES public.material (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.imagens_atividade
    ADD atividade_id integer;
ALTER TABLE public.imagens_atividade ADD CONSTRAINT atividade_imagens_atividade_fk
FOREIGN KEY (atividade_id)
REFERENCES public.atividade (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.atividademembros
    ADD atividade_id integer;
ALTER TABLE public.atividademembros ADD CONSTRAINT atividade_atividade_membros_fk
FOREIGN KEY (atividade_id)
REFERENCES public.atividade (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.atividadevoluntarios
    ADD atividade_id integer;
ALTER TABLE public.atividadevoluntarios ADD CONSTRAINT atividade_atividade_voluntarios_fk
FOREIGN KEY (atividade_id)
REFERENCES public.atividade (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.materiais_atividade
    ADD atividade_id integer;
ALTER TABLE public.materiais_atividade ADD CONSTRAINT atividade_materiais_atividade_fk
FOREIGN KEY (atividade_id)
REFERENCES public.atividade (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.membrocomunidade
    ADD pessoa_id integer;
ALTER TABLE public.membrocomunidade ADD CONSTRAINT pessoas_membro_comunidade_fk
FOREIGN KEY (pessoa_id)
REFERENCES public.pessoa (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.voluntario
    ADD pessoa_id integer;
ALTER TABLE public.voluntario ADD CONSTRAINT pessoas_voluntarios_fk
FOREIGN KEY (pessoa_id)
REFERENCES public.pessoa (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.atividadevoluntarios
    ADD voluntario_id integer;
ALTER TABLE public.atividadevoluntarios ADD CONSTRAINT voluntarios_atividadevoluntarios_fk
FOREIGN KEY (voluntario_id)
REFERENCES public.voluntario (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.cesta_basica
    ADD membro_id integer;
ALTER TABLE public.cesta_basica ADD CONSTRAINT membro_comunidade_cesta_basica_fk
FOREIGN KEY (membro_id)
REFERENCES public.membrocomunidade (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE IF EXISTS public.atividademembros
    ADD membro_id integer;
ALTER TABLE public.atividademembros ADD CONSTRAINT membro_comunidade_atividade_membros_fk
FOREIGN KEY (membro_id)
REFERENCES public.membrocomunidade (id)
ON DELETE CASCADE
ON UPDATE NO ACTION
NOT DEFERRABLE;