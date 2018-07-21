CREATE TABLE public.person (id serial NOT NULL, name character varying(60), age integer,
    CONSTRAINT person_pkey PRIMARY KEY (id));

INSERT INTO person (name, age) VALUES ('Pelé', 18);
INSERT INTO person (name, age) VALUES ('Garrincha', 22);
