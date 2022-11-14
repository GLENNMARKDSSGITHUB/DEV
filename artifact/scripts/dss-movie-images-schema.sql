-- Table: public.dss_images

-- DROP TABLE IF EXISTS public.dss_images;

CREATE TABLE IF NOT EXISTS public.dss_images
(
    image_id bigint NOT NULL,
    file_name character varying(255) COLLATE pg_catalog."default",
    file_size character varying(255) COLLATE pg_catalog."default",
    url character varying(255) COLLATE pg_catalog."default",
    dss_movie_id character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT dss_images_pkey PRIMARY KEY (image_id),
    CONSTRAINT fkej5if3ofkunpg2apk7fgbt1ia FOREIGN KEY (dss_movie_id)
    REFERENCES public.dss_movie (movie_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dss_images
    OWNER to postgres;