--CONFIGURANDO BD

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

CREATE DATABASE microservicios WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE microservicios OWNER TO postgres;

\connect microservicios

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

SET default_tablespace = '';

SET default_with_oids = false;


--------------CLIENTES-------------

CREATE SEQUENCE public.clientes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.clientes  ( 
	id_cliente    	int8 NOT NULL,
	identificacion  	varchar(25) NOT NULL,
	nombre   	varchar(100) NOT NULL,
	apellido 	varchar(100) NOT NULL,
	direccion       	varchar(250) NULL,
	telefono        	varchar(25) NULL,
	fecha_nacimiento	date NOT NULL,
	genero	        	varchar(1) NULL, 
	numero_cliente    	int8 NOT NULL,
	contrasena    	varchar(50) NOT NULL,
	estado        	varchar(3) NULL 
	);


ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente);
	
ALTER TABLE public.clientes OWNER TO postgres;
ALTER TABLE public.clientes_id_seq OWNER TO postgres;
ALTER SEQUENCE public.clientes_id_seq OWNED BY public.clientes.id_cliente;
ALTER TABLE ONLY public.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('public.clientes_id_seq'::regclass);

ALTER TABLE public.clientes
	ADD CONSTRAINT uix_clientes_numero_cliente
	UNIQUE (numero_cliente);
	
ALTER TABLE public.clientes
	ADD CONSTRAINT uix_clientes_identificacion
	UNIQUE (identificacion);



--------------CUENTAS-------------

CREATE SEQUENCE public.cuentas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.cuentas  ( 
	id_cuenta    	int8 NOT NULL,
	id_cliente    	int8 NOT NULL,
	tipo_cuenta  	varchar(25) NOT NULL,
	numero_cuenta	int8 NOT NULL,
	saldo_inicial	numeric(15,5) NOT NULL,
	saldo_disponible numeric(15,5) NOT NULL,
	limite_diario	numeric(15,5) NULL, 
	estado_cuenta	varchar(25) NOT NULL,
	estado       	varchar(3) NOT NULL
	);
	
ALTER TABLE ONLY public.cuentas
    ADD CONSTRAINT cuentas_pkey PRIMARY KEY (id_cuenta);
	
ALTER TABLE public.cuentas OWNER TO postgres;
ALTER TABLE public.cuentas_id_seq OWNER TO postgres;
ALTER SEQUENCE public.cuentas_id_seq OWNED BY public.cuentas.id_cuenta;
ALTER TABLE ONLY public.cuentas ALTER COLUMN id_cuenta SET DEFAULT nextval('public.cuentas_id_seq'::regclass);

ALTER TABLE public.cuentas
	ADD CONSTRAINT uix_cuentas_numero_cuenta
	UNIQUE (numero_cuenta);
	
	
ALTER TABLE public.cuentas
	ADD CONSTRAINT fk_cuentas_clientes
	FOREIGN KEY(id_cliente)
	REFERENCES public.clientes(id_cliente)	;
	
--------------MOVIMIENTOS-------------

CREATE SEQUENCE public.movimientos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE public.movimientos  ( 
	id_movimiento    	int8 NOT NULL,
	id_cuenta        	int8 NOT NULL,
	fecha_movimiento 	timestamp NOT NULL,
	tipo_movimiento  	varchar(3) NOT NULL,
	valor            	numeric(15,5) NOT NULL,
	saldo            	numeric(15,5) NOT NULL,
	estado_movimiento	varchar(25) NOT NULL,
	estado           	varchar(3) NOT NULL 
	);

	
ALTER TABLE ONLY public.movimientos
    ADD CONSTRAINT movimientos_pkey PRIMARY KEY (id_movimiento);
	
ALTER TABLE public.movimientos OWNER TO postgres;
ALTER TABLE public.movimientos_id_seq OWNER TO postgres;
ALTER SEQUENCE public.movimientos_id_seq OWNED BY public.movimientos.id_movimiento;
ALTER TABLE ONLY public.movimientos ALTER COLUMN id_movimiento SET DEFAULT nextval('public.movimientos_id_seq'::regclass);

ALTER TABLE public.movimientos
	ADD CONSTRAINT fk_movimientos_cuentas
	FOREIGN KEY(id_cuenta)
	REFERENCES public.cuentas(id_cuenta);




------DATOS CLIENTES------


INSERT INTO "public"."clientes"("identificacion", "nombre", "apellido", "direccion", "telefono", "fecha_nacimiento","genero", "numero_cliente", "contrasena", "estado")
VALUES('1722249875', 'Alberto',  'Pérez',  'Quito - La floresta', '0998532625', '1987-04-26', 'M', 100101, '12345678', 'ACT');

INSERT INTO "public"."clientes"("identificacion", "nombre", "apellido", "direccion", "telefono", "fecha_nacimiento","genero", "numero_cliente", "contrasena", "estado")
VALUES('1700000001', 'Jose', 'Lema', 'Otavalo sn y principal', '098254785', '2000-01-01', 'M', 100102, '12345678',  'ACT');


------DATOS CUENTAS------

INSERT INTO "public"."cuentas"("id_cliente", "tipo_cuenta", "numero_cuenta", "saldo_inicial", "saldo_disponible", "limite_diario", "estado_cuenta", "estado")
VALUES(1, 'AHORROS', 257575, 100, 100, 500, 'ACTIVA', 'ACT');


INSERT INTO "public"."cuentas"("id_cliente", "tipo_cuenta", "numero_cuenta", "saldo_inicial", "saldo_disponible", "limite_diario", "estado_cuenta", "estado")
VALUES(2, 'AHORROS', 478758, 2000, 2000, 5000, 'ACTIVA', 'ACT');

------MOVIMIENTOS------

INSERT INTO "public"."movimientos"("id_cuenta", "fecha_movimiento", "tipo_movimiento", "valor", "saldo", "estado_movimiento", "estado")
VALUES(1, '2022-01-01 00:00:00.0', 'CRE', 100, 100, 'PROCESADO', 'ACT');