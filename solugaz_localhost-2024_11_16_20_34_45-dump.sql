--
-- PostgreSQL database dump
--

-- Dumped from database version 15.7
-- Dumped by pg_dump version 15.7

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry and geography spatial types and functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: commande; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commande (
    id integer NOT NULL,
    montant double precision NOT NULL,
    status smallint,
    utilisateur_id integer,
    creation_date timestamp(6) with time zone,
    date_commande timestamp(6) without time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    last_modified_by character varying(255),
    reference character varying(255),
    CONSTRAINT commande_status_check CHECK (((status >= 0) AND (status <= 2)))
);


ALTER TABLE public.commande OWNER TO postgres;

--
-- Name: commande_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.commande ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.commande_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: dataset; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dataset (
    date date,
    fabricant smallint,
    id integer NOT NULL,
    quantite_vendu integer,
    taille_du_foyer integer,
    type_gaz smallint,
    evenement_locaux character varying(255),
    region character varying(255),
    saison character varying(255),
    CONSTRAINT dataset_fabricant_check CHECK (((fabricant >= 0) AND (fabricant <= 2))),
    CONSTRAINT dataset_type_gaz_check CHECK (((type_gaz >= 0) AND (type_gaz <= 2)))
);


ALTER TABLE public.dataset OWNER TO postgres;

--
-- Name: dataset_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.dataset ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.dataset_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: distributeur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.distributeur (
    altitude double precision NOT NULL,
    id integer NOT NULL,
    id_utilisateur integer,
    is_verified boolean NOT NULL,
    latitude double precision NOT NULL,
    localisation_id integer,
    longitude double precision NOT NULL,
    creation_date timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    description character varying(255),
    last_modified_by character varying(255),
    nom character varying(255),
    location public.geography(Point,4326)
);


ALTER TABLE public.distributeur OWNER TO postgres;

--
-- Name: distributeur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.distributeur ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.distributeur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: gaz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gaz (
    fabricant smallint,
    id integer NOT NULL,
    type smallint,
    creation_date timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    last_modified_by character varying(255),
    CONSTRAINT gaz_fabricant_check CHECK (((fabricant >= 0) AND (fabricant <= 2))),
    CONSTRAINT gaz_type_check CHECK (((type >= 0) AND (type <= 2)))
);


ALTER TABLE public.gaz OWNER TO postgres;

--
-- Name: gaz_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.gaz ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.gaz_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: ligne_commande; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ligne_commande (
    commande_id integer,
    distributeur_id integer,
    gaz_id integer,
    id integer NOT NULL,
    prix_unitaire double precision NOT NULL,
    quantite integer,
    creation_date timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    last_modified_by character varying(255)
);


ALTER TABLE public.ligne_commande OWNER TO postgres;

--
-- Name: ligne_commande_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.ligne_commande ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.ligne_commande_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: localisation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.localisation (
    id integer NOT NULL,
    creation_date timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    description character varying(255),
    last_modified_by character varying(255),
    pays character varying(255),
    ville character varying(255)
);


ALTER TABLE public.localisation OWNER TO postgres;

--
-- Name: localisation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.localisation ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.localisation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: mouvement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mouvement (
    id integer NOT NULL,
    stock_id integer,
    type_mouvement smallint,
    creation_date timestamp(6) with time zone,
    date timestamp(6) without time zone,
    last_modified_date timestamp(6) with time zone,
    quantite bigint NOT NULL,
    created_by character varying(255),
    last_modified_by character varying(255),
    motif character varying(255),
    CONSTRAINT mouvement_type_mouvement_check CHECK (((type_mouvement >= 0) AND (type_mouvement <= 2)))
);


ALTER TABLE public.mouvement OWNER TO postgres;

--
-- Name: mouvement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.mouvement ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.mouvement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: payment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.payment (
    id integer NOT NULL,
    id_commande integer,
    montant double precision NOT NULL,
    payment_methode smallint,
    payment_status smallint,
    creation_date timestamp(6) with time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    last_modified_by character varying(255),
    reference character varying(255),
    CONSTRAINT payment_payment_methode_check CHECK (((payment_methode >= 0) AND (payment_methode <= 2))),
    CONSTRAINT payment_payment_status_check CHECK (((payment_status >= 0) AND (payment_status <= 2)))
);


ALTER TABLE public.payment OWNER TO postgres;

--
-- Name: payment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.payment ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.payment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: privilege; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.privilege (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.privilege OWNER TO postgres;

--
-- Name: privilege_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.privilege_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.privilege_seq OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_seq OWNER TO postgres;

--
-- Name: roles_privileges; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles_privileges (
    privilege_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.roles_privileges OWNER TO postgres;

--
-- Name: stock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stock (
    distributeur_id integer,
    gaz_id integer,
    id integer NOT NULL,
    creation_date timestamp(6) with time zone,
    date timestamp(6) without time zone,
    last_modified_date timestamp(6) with time zone,
    quantite bigint NOT NULL,
    created_by character varying(255),
    last_modified_by character varying(255),
    motif character varying(255)
);


ALTER TABLE public.stock OWNER TO postgres;

--
-- Name: stock_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.stock ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_roles (
    user_id integer NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    altitude double precision,
    enabled boolean NOT NULL,
    id integer NOT NULL,
    is_locked boolean NOT NULL,
    latitude double precision,
    localisation_id integer,
    longitude double precision,
    creation_date timestamp(6) with time zone,
    datedenaissance timestamp(6) without time zone,
    last_modified_date timestamp(6) with time zone,
    phone character varying(15),
    email character varying(50),
    motdepasse character varying(60),
    created_by character varying(255),
    imageurl character varying(255),
    last_modified_by character varying(255),
    nom character varying(255),
    prenom character varying(255)
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- Name: utilisateur_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.utilisateur ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.utilisateur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: vente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vente (
    distributeur_id integer,
    gaz_id integer,
    id integer NOT NULL,
    localisation_id integer,
    prix integer NOT NULL,
    prix_unitaire integer NOT NULL,
    quantite integer NOT NULL,
    creation_date timestamp(6) with time zone,
    date_vendu timestamp(6) without time zone,
    last_modified_date timestamp(6) with time zone,
    created_by character varying(255),
    last_modified_by character varying(255)
);


ALTER TABLE public.vente OWNER TO postgres;

--
-- Name: vente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.vente ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.vente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: commande; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.commande (id, montant, status, utilisateur_id, creation_date, date_commande, last_modified_date, created_by, last_modified_by, reference) FROM stdin;
\.


--
-- Data for Name: dataset; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dataset (date, fabricant, id, quantite_vendu, taille_du_foyer, type_gaz, evenement_locaux, region, saison) FROM stdin;
\.


--
-- Data for Name: distributeur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.distributeur (altitude, id, id_utilisateur, is_verified, latitude, localisation_id, longitude, creation_date, last_modified_date, created_by, description, last_modified_by, nom, location) FROM stdin;
0	1	\N	f	0	1	0	\N	\N	\N	string	\N	Total	0101000020E610000000000000000000000000000000000000
0	2	\N	f	0	1	0	\N	\N	\N	string	\N	Tradex	0101000020E610000000000000000000000000000000000000
0	3	\N	f	0	1	0	\N	\N	\N	string	\N	Kamga etc	0101000020E610000000000000000000000000000000000000
0	4	\N	f	0	2	0	\N	\N	\N	string	\N	 moussa etc	0101000020E610000000000000000000000000000000000000
0	5	\N	f	0	3	0	\N	\N	\N	string	\N	 Gaz expresso	0101000020E610000000000000000000000000000000000000
0	6	\N	f	0	2	0	\N	\N	\N	string	\N	 Depot Gaz	0101000020E610000000000000000000000000000000000000
\.


--
-- Data for Name: gaz; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gaz (fabricant, id, type, creation_date, last_modified_date, created_by, last_modified_by) FROM stdin;
0	1	0	\N	\N	\N	\N
1	2	0	\N	\N	\N	\N
2	3	0	\N	\N	\N	\N
2	4	1	\N	\N	\N	\N
0	5	1	\N	\N	\N	\N
1	6	1	\N	\N	\N	\N
\.


--
-- Data for Name: ligne_commande; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ligne_commande (commande_id, distributeur_id, gaz_id, id, prix_unitaire, quantite, creation_date, last_modified_date, created_by, last_modified_by) FROM stdin;
\.


--
-- Data for Name: localisation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.localisation (id, creation_date, last_modified_date, created_by, description, last_modified_by, pays, ville) FROM stdin;
1	\N	\N	\N	string	\N	cameroun	Yaounde
2	\N	\N	\N	string	\N	cameroun	douala
3	\N	\N	\N	string	\N	cameroun	garoua
4	\N	\N	\N	string	\N	cameroun	maroua
5	\N	\N	\N	string	\N	cameroun	bafoussam
\.


--
-- Data for Name: mouvement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mouvement (id, stock_id, type_mouvement, creation_date, date, last_modified_date, quantite, created_by, last_modified_by, motif) FROM stdin;
\.


--
-- Data for Name: payment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.payment (id, id_commande, montant, payment_methode, payment_status, creation_date, last_modified_date, created_by, last_modified_by, reference) FROM stdin;
\.


--
-- Data for Name: privilege; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.privilege (id, name) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
\.


--
-- Data for Name: roles_privileges; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles_privileges (privilege_id, role_id) FROM stdin;
\.


--
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.stock (distributeur_id, gaz_id, id, creation_date, date, last_modified_date, quantite, created_by, last_modified_by, motif) FROM stdin;
1	1	1	\N	2024-11-16 20:28:06.231	\N	20	\N	\N	string
1	2	2	\N	2024-11-16 20:28:06.231	\N	20	\N	\N	string
2	2	3	\N	2024-11-16 20:28:06.231	\N	10	\N	\N	string
2	4	4	\N	2024-11-16 20:28:06.231	\N	50	\N	\N	string
4	4	5	\N	2024-11-16 20:28:06.231	\N	50	\N	\N	string
5	4	6	\N	2024-11-16 20:28:06.231	\N	50	\N	\N	string
5	3	7	\N	2024-11-16 20:28:06.231	\N	10	\N	\N	string
6	3	8	\N	2024-11-16 20:28:06.231	\N	10	\N	\N	string
\.


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_roles (user_id, role_id) FROM stdin;
\.


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur (altitude, enabled, id, is_locked, latitude, localisation_id, longitude, creation_date, datedenaissance, last_modified_date, phone, email, motdepasse, created_by, imageurl, last_modified_by, nom, prenom) FROM stdin;
\.


--
-- Data for Name: vente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vente (distributeur_id, gaz_id, id, localisation_id, prix, prix_unitaire, quantite, creation_date, date_vendu, last_modified_date, created_by, last_modified_by) FROM stdin;
\.


--
-- Name: commande_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.commande_id_seq', 1, false);


--
-- Name: dataset_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.dataset_id_seq', 1, false);


--
-- Name: distributeur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.distributeur_id_seq', 6, true);


--
-- Name: gaz_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.gaz_id_seq', 6, true);


--
-- Name: ligne_commande_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ligne_commande_id_seq', 1, false);


--
-- Name: localisation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.localisation_id_seq', 5, true);


--
-- Name: mouvement_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mouvement_id_seq', 1, false);


--
-- Name: payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_id_seq', 1, false);


--
-- Name: privilege_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.privilege_seq', 1, false);


--
-- Name: role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_seq', 1, false);


--
-- Name: stock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.stock_id_seq', 8, true);


--
-- Name: utilisateur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilisateur_id_seq', 1, false);


--
-- Name: vente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vente_id_seq', 1, false);


--
-- Name: commande commande_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commande
    ADD CONSTRAINT commande_pkey PRIMARY KEY (id);


--
-- Name: dataset dataset_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dataset
    ADD CONSTRAINT dataset_pkey PRIMARY KEY (id);


--
-- Name: distributeur distributeur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.distributeur
    ADD CONSTRAINT distributeur_pkey PRIMARY KEY (id);


--
-- Name: gaz gaz_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gaz
    ADD CONSTRAINT gaz_pkey PRIMARY KEY (id);


--
-- Name: ligne_commande ligne_commande_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ligne_commande
    ADD CONSTRAINT ligne_commande_pkey PRIMARY KEY (id);


--
-- Name: localisation localisation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.localisation
    ADD CONSTRAINT localisation_pkey PRIMARY KEY (id);


--
-- Name: mouvement mouvement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mouvement
    ADD CONSTRAINT mouvement_pkey PRIMARY KEY (id);


--
-- Name: payment payment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (id);


--
-- Name: privilege privilege_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilege
    ADD CONSTRAINT privilege_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id);


--
-- Name: vente vente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT vente_pkey PRIMARY KEY (id);


--
-- Name: users_roles fk4jf1hkpfeswf8ijcpqpwko74a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fk4jf1hkpfeswf8ijcpqpwko74a FOREIGN KEY (user_id) REFERENCES public.utilisateur(id);


--
-- Name: mouvement fk53htvuwdp7hwfldbph68qd0lj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mouvement
    ADD CONSTRAINT fk53htvuwdp7hwfldbph68qd0lj FOREIGN KEY (stock_id) REFERENCES public.stock(id);


--
-- Name: distributeur fk55apwemwc587u7uf0t6ytpxi2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.distributeur
    ADD CONSTRAINT fk55apwemwc587u7uf0t6ytpxi2 FOREIGN KEY (localisation_id) REFERENCES public.localisation(id);


--
-- Name: stock fk588d3hnhf3ltyia5q06vqucen; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT fk588d3hnhf3ltyia5q06vqucen FOREIGN KEY (gaz_id) REFERENCES public.gaz(id);


--
-- Name: roles_privileges fk5yjwxw2gvfyu76j3rgqwo685u; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_privileges
    ADD CONSTRAINT fk5yjwxw2gvfyu76j3rgqwo685u FOREIGN KEY (privilege_id) REFERENCES public.privilege(id);


--
-- Name: vente fk7vvvp34j5orrvkvmqpl15by0h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT fk7vvvp34j5orrvkvmqpl15by0h FOREIGN KEY (gaz_id) REFERENCES public.gaz(id);


--
-- Name: vente fk974njqds91qsddkt3bq30lp4e; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT fk974njqds91qsddkt3bq30lp4e FOREIGN KEY (localisation_id) REFERENCES public.localisation(id);


--
-- Name: roles_privileges fk9h2vewsqh8luhfq71xokh4who; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles_privileges
    ADD CONSTRAINT fk9h2vewsqh8luhfq71xokh4who FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: ligne_commande fkaff2bjyreiuyi723relg10spm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ligne_commande
    ADD CONSTRAINT fkaff2bjyreiuyi723relg10spm FOREIGN KEY (commande_id) REFERENCES public.commande(id);


--
-- Name: commande fkgbm3eiixq6swvndmq81ycarnc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commande
    ADD CONSTRAINT fkgbm3eiixq6swvndmq81ycarnc FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id);


--
-- Name: stock fkjiu135h3f62gfaxvsufauwfhj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT fkjiu135h3f62gfaxvsufauwfhj FOREIGN KEY (distributeur_id) REFERENCES public.distributeur(id);


--
-- Name: ligne_commande fkjtkk0e64ysjexo3t2vtot95oo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ligne_commande
    ADD CONSTRAINT fkjtkk0e64ysjexo3t2vtot95oo FOREIGN KEY (gaz_id) REFERENCES public.gaz(id);


--
-- Name: ligne_commande fkpl6m4nxm0ndefvmwiirlow0d3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ligne_commande
    ADD CONSTRAINT fkpl6m4nxm0ndefvmwiirlow0d3 FOREIGN KEY (distributeur_id) REFERENCES public.distributeur(id);


--
-- Name: vente fkr3wyhy4xeqbu306tmc0uq5ht; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente
    ADD CONSTRAINT fkr3wyhy4xeqbu306tmc0uq5ht FOREIGN KEY (distributeur_id) REFERENCES public.distributeur(id);


--
-- Name: utilisateur fksqx17lcwybo148fer02lw1f4d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT fksqx17lcwybo148fer02lw1f4d FOREIGN KEY (localisation_id) REFERENCES public.localisation(id);


--
-- Name: users_roles fkt4v0rrweyk393bdgt107vdx0x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT fkt4v0rrweyk393bdgt107vdx0x FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- PostgreSQL database dump complete
--

