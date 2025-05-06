--liquibase formatted sql
--changeset luan-pedrosa:2025-05-05_01 author:luan-pedrosa


CREATE SEQUENCE public.cesta_basica_id_seq;

ALTER TABLE public.cesta_basica
    ALTER COLUMN id SET DEFAULT nextval('public.cesta_basica_id_seq'),
    ALTER COLUMN id SET NOT NULL;

ALTER SEQUENCE public.cesta_basica_id_seq OWNED BY public.cesta_basica.id;

