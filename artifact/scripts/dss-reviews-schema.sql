-- Table: public.dss_reviews

-- DROP TABLE IF EXISTS public.dss_reviews;

CREATE TABLE IF NOT EXISTS public.dss_reviews
(
    review_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_modification_date timestamp without time zone,
    last_modified_by character varying(100) COLLATE pg_catalog."default",
    movie_id character varying(10) COLLATE pg_catalog."default" NOT NULL,
    rate integer NOT NULL,
    review_content character varying(255) COLLATE pg_catalog."default" NOT NULL,
    review_headline character varying(255) COLLATE pg_catalog."default" NOT NULL,
    dss_movie_id character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT dss_reviews_pkey PRIMARY KEY (review_id),
    CONSTRAINT fkasdwu0hx25osvtlof9p5fkrn0 FOREIGN KEY (dss_movie_id)
    REFERENCES public.dss_movie (movie_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dss_reviews
    OWNER to postgres;