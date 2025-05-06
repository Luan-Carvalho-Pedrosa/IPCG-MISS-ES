--liquibase formatted sql
--changeset luan-pedrosa:2025-05-05_01 author:luan-pedrosa



ALTER TABLE IF EXISTS public.cesta_basica
    ALTER COLUMN membro_id SET NOT NULL;
ALTER TABLE IF EXISTS public.cesta_basica
    ADD UNIQUE (membro_id);


/*
rollback
ALTER TABLE IF EXISTS public.cesta_basica
    ALTER COLUMN membro_id DROP NOT NULL;
ALTER TABLE IF EXISTS public.cesta_basica DROP CONSTRAINT IF EXISTS cesta_basica_membro_id_key;


*/