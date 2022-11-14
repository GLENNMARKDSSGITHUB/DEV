-- Table: public.dss_actors

-- DROP TABLE IF EXISTS public.dss_actors;

CREATE TABLE IF NOT EXISTS public.dss_actors
(
    actor_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    age integer NOT NULL,
    created_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    gender character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_modification_date timestamp without time zone,
    last_modified_by character varying(100) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    movie_cast character varying(255) COLLATE pg_catalog."default" NOT NULL,
    movie_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    dss_movie_id character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT dss_actors_pkey PRIMARY KEY (actor_id),
    CONSTRAINT fkj91xlmb82bwtrtpau1sx0ho2q FOREIGN KEY (dss_movie_id)
    REFERENCES public.dss_movie (movie_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dss_actors
    OWNER to postgres;