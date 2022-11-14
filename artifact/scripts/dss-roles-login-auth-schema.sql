-- Table: public.dss_roles

-- DROP TABLE IF EXISTS public.dss_roles;

CREATE TABLE IF NOT EXISTS public.dss_roles
(
    role_id character varying(100) COLLATE pg_catalog."default" NOT NULL,
    user_role character varying(20) COLLATE pg_catalog."default" NOT NULL,
    user_dss_user_id character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT dss_roles_pkey PRIMARY KEY (role_id),
    CONSTRAINT fkltw0xnvi25aba6khyj4ykq434 FOREIGN KEY (user_dss_user_id)
    REFERENCES public.dss_users (dss_user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.dss_roles
    OWNER to postgres;