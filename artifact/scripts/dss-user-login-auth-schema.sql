-- Table: public.dss_users

-- DROP TABLE IF EXISTS public.dss_users;

CREATE TABLE IF NOT EXISTS public.dss_users
(
    dss_user_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cellphone_no character varying(20) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(100) COLLATE pg_catalog."default" NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    last_modification_date timestamp without time zone,
    last_modified_by character varying(100) COLLATE pg_catalog."default",
    last_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    old_password character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status character varying(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dss_users_pkey PRIMARY KEY (dss_user_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dss_users
    OWNER to postgres;