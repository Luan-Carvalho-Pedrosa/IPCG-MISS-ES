--liquibase formatted sql
--changeset luan-pedrosa:2025-04-25_01 author:luan-pedrosa



ALTER TABLE IF EXISTS public.pessoa
    ADD CONSTRAINT cpf_uk UNIQUE (cpf);

ALTER TABLE IF EXISTS public.voluntario
    ADD CONSTRAINT pessoa_uk UNIQUE (pessoa_id);
    
-- ALTER TABLE IF EXISTS public.voluntario DROP CONSTRAINT IF EXISTS pessoa_uk;
--  ALTER TABLE IF EXISTS public.pessoa DROP CONSTRAINT IF EXISTS cpf_uk;