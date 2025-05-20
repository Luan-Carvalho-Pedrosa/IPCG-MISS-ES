--liquibase formatted sql
--changeset luan-pedrosa:2025-05-20_01 author:luan-pedrosa


ALTER TABLE IF EXISTS public.atividademembros
    ADD CONSTRAINT atv_mem_uk UNIQUE (atividade_id, membro_id);


-- rollback ALTER TABLE IF EXISTS public.atividademembros DROP CONSTRAINT IF EXISTS atv_mem_uk;