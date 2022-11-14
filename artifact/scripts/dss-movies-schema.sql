-- Table: public.dss_movie

-- DROP TABLE IF EXISTS public.dss_movie;

CREATE TABLE IF NOT EXISTS public.dss_movie
(
    movie_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    category character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country character varying(100) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    directed_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    duration character varying(15) COLLATE pg_catalog."default" NOT NULL,
    language character varying(25) COLLATE pg_catalog."default" NOT NULL,
    last_modification_date timestamp without time zone,
    last_modified_by character varying(100) COLLATE pg_catalog."default",
    movie_cost double precision NOT NULL,
    movie_title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    music_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    produced_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    writers character varying(255) COLLATE pg_catalog."default" NOT NULL,
    year character varying(4) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dss_movie_pkey PRIMARY KEY (movie_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dss_movie
    OWNER to postgres;