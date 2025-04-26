--liquibase formatted sql
--changeset luan-pedrosa:2025-04-26_01 author:luan-pedrosa



ALTER TABLE IF EXISTS public.membrocomunidade
    ADD CONSTRAINT membro_pessoa_uk UNIQUE (pessoa_id);

-- ALTER TABLE IF EXISTS public.membrocomunidade DROP CONSTRAINT IF EXISTS membro_pessoa_uk;
