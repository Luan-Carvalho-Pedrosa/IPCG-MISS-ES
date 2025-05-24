--liquibase formatted sql
--changeset luan-pedrosa:2025-05-20_01 author:luan-pedrosa


ALTER TABLE IF EXISTS public.materiais_atividade
    ADD CONSTRAINT atv_mat_uk UNIQUE (atividade_id, material_id);


-- rollback ALTER TABLE IF EXISTS public.materiais_atividade DROP CONSTRAINT IF EXISTS atv_mat_uk;