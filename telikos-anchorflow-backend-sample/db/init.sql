-- Table: public.demo

-- DROP TABLE IF EXISTS public.demo;

CREATE TABLE IF NOT EXISTS public.demo
(

    id VARCHAR ( 50 ) UNIQUE NOT NULL,
    name VARCHAR ( 50 ) NOT NULL
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.demo
    OWNER to postgres;