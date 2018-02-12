--CREACIÓN DE USUARIO
-- USER SQL
CREATE USER root IDENTIFIED BY ubuntu 
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";

-- ROLES
GRANT "RESOURCE" TO root ;
GRANT "CONNECT" TO root ;
ALTER USER root DEFAULT ROLE "RESOURCE","CONNECT";

-- SYSTEM PRIVILEGES
GRANT CREATE SEQUENCE TO root ;
GRANT CREATE SYNONYM TO root ;
GRANT CREATE SESSION TO root ;
GRANT UNLIMITED TABLESPACE TO root ;
GRANT CREATE DATABASE LINK TO root ;
GRANT ALTER SESSION TO root ;

-- QUOTAS
ALTER USER root QUOTA UNLIMITED ON USERS;

/*Funciones y procesos Objeto relacional */
/*SERGIO PROYECTO CONTENIDOS*/

create type tipoContenidos as object(
	nombre varchar2(40),
	genero varchar2(30),
	recomendado number(1),
	puntuacion number(2)
)NOT FINAL;
/

create type tipoSeries under tipoContenidos(
	temporadas number(2)
);
/

create type tabla_anidadaSeries as table of tipoSeries;
/
create type tipoMusica under tipoContenidos(
	tipo varchar2(8),
	cantante varchar2(40)
);
/
create type tabla_anidadaMusica as table of tipoMusica;
/
create type tipoLibros under tipoContenidos(
	autor varchar2(40)
);
/
create type tabla_anidadaLibros as table of tipoLibros;
/
create type tipoPeliculas under tipoContenidos(
	director varchar2(40)
);
/
create type tabla_anidadaPeliculas as table of tipoPeliculas;
/
create type tipoAmigos as object(
	amigo varchar2(40)
);
/
create type tabla_anidadaAmigos as table of tipoAmigos;
/
create type tipoUsuarios as object(
	id varchar2(40),
	password varchar2(40),
	amigosAni tabla_anidadaAmigos,
	seriesAni tabla_anidadaSeries,
	musicaAni tabla_anidadaMusica,
	librosAni tabla_anidadaLibros,
	peliculasAni tabla_anidadaPeliculas
);
/
create table usuarios of tipoUsuarios(
	id primary key
)NESTED table amigosAni store as amigos_anidada,
NESTED table seriesAni store as series_anidada,
NESTED table musicaAni store as musicas_anidada,
NESTED table librosAni store as libros_anidada,
NESTED table peliculasAni store as peliculas_anidada;
/
create or replace type  tipoCalendarios as object(
	usu varchar2(40),
	dia varchar2(10),
	serie varchar2(40)
);
/ 
--();
--NESTED TABLE serie STORE AS serie_anidada;
create table calendarios of tipoCalendarios;
/ 

/*---------------------FUNCIONES Y PROCESOS*----------------------------------*/
---usuarios
--Comprobar si existe el usuario
CREATE OR REPLACE FUNCTION existeUsuario (idv varchar2) return integer
is
	exist integer;
	contador number;
begin
	exist:=0;
	SELECT count(*) into contador FROM usuarios WHERE lower(id) like idv;
	if(contador>0) THEN
		exist:=1;
	END IF;
	return exist;
end;
/
--Comprobar si existe ese usuario con la contraseÃ±a
CREATE OR REPLACE FUNCTION existeUsuarioyPass (idv varchar2, passv varchar2) return integer
is
	exist integer;
	contador number;
begin
	exist:=0;
	SELECT count(*) into contador FROM usuarios WHERE lower(id) like idv and lower(password) like passv;
	if(contador>0) THEN
		exist:=1;
	END IF;
	return exist;
end;
/
--Crear usuario 1 bien 0 mal
CREATE OR REPLACE PROCEDURE crearUsuario (idv varchar2, passv varchar2)
is
begin
	/*
	INSERT INTO usuarios VALUES(idv,passv,
	tabla_anidadaAmigos(),
	tabla_anidadaSeries(),
	tabla_anidadaMusica(),
	tabla_anidadaLibros(),
	tabla_anidadaPeliculas());
	*/
	INSERT INTO usuarios VALUES(idv,passv,
	tabla_anidadaAmigos(tipoAmigos('juan')),
	tabla_anidadaSeries(tipoSeries('house','fantasia',1,8,4)),
	tabla_anidadaMusica(tipoMusica('chocolate','metal',1,8,'cancion','baby metal')),
	tabla_anidadaLibros(tipoLibros('cincuenta sombras de grey','novela erotica',0,1,'E.L.James')),
	tabla_anidadaPeliculas(tipoPeliculas('pesadillas antes de navidad','fantasia',1,10,'Henry Selick')));
end;
/
--------contenido


/
/*----------------CONSULTAS----------------*/
--Borrar contenido concreto
DELETE from table (select "+tipo+" from usuarios where lower(id) like lower('"+nombreUsuario+"')) contenido where lower(contenido.nombre) like lower('"+nombreContenido+"');
--getContenidoDeUnTipo
select a.* from usuarios u, table("+tipo+") a where lower(u.id) like lower('"+nombreUsuario+"');
select a.* from usuarios u, table(seriesAni) a where lower(u.id) like lower('juan');

--recomendarContenidoSeleccionado
 UPDATE table (select musicaAni from usuarios where lower(id) like lower('juan')) contenido set contenido.recomendado = 0 where lower(contenido.nombre) like lower('chocolate') ;
--modificar contenido
UPDATE table (select "+tipo+" from usuarios where lower(id) like lower('"+nombreUsuario+"')) contenido set "
						+ "contenido.genero = ?"
						+ ",contenido.recomendado = ? "
						+ ",contenido.puntuacion = ? "
						+ ",contenido.temporadas = ? "
						+ "where lower(contenido.nombre) like lower('"+serie.getNombre()+"');
--anadirNuevoContenido
insert into TABLE (SELECT seriesAni from usuarios where lower(id) like lower('juan')) values(tabla_anidadaSeries(tipoSeries('house','fantasia',1,8,4)));