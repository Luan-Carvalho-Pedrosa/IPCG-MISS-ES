--liquibase formatted sql
--changeset luan-pedrosa:2025-05-19_01 author:luan-pedrosa


ALTER TABLE IF EXISTS public.atividadevoluntarios
    ADD CONSTRAINT atv_vol_uk UNIQUE (atividade_id, voluntario_id);


-- rollback ALTER TABLE IF EXISTS public.atividadevoluntarios DROP CONSTRAINT IF EXISTS atv_vol_uk;