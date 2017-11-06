/*SERGIO PROYECTO CONTENIDOS*/

---usuarios
--Comprobar si existe el usuario
SELECT count(*) FROM usuarios WHERE lower(id) like variable;

--Comprobar si existe ese usuario con la contraseña
SELECT count(*) FROM usuarios WHERE lower(id) like variable and lower(password) like variable;

--Crear usuario
INSERT INTO usuarios VALUES('pedro','passi',
tabla_anidadaAmigos(),
tabla_anidadaSeries(),
tabla_anidadaMusica(),
tabla_anidadaLibros(),
tabla_anidadaPeliculas());

------contenido
--Conseguir contenido de un tipo
SELECT series.* from usuarios, table (seriesAni) series where lower (id)like variable(nombreUsuario);
SELECT musica.* from usuarios, table (musicaAni) musica where lower (id)like variable;
SELECT libros.* from usuarios, table (librosAni) libros where lower (id)like variable;
SELECT pelis.* from usuarios, table (peliculasAni) pelis where lower (id)like variable;

--Modificar contenido concreto
--Borrar contenido concreto
DELETE from table (select seriesAni from usuarios where lower(id)like '1') series where series.nombre like 'house'
DELETE from table (select musicaAni from usuarios where lower(id)like '1') musica where musica.nombre like 'house'
DELETE from table (select librosAni from usuarios where lower(id)like '1') libros where libros.nombre like 'house'
DELETE from table (select peliculasAni from usuarios where lower(id)like '1') pelis where pelis.nombre like 'house'

--Insertar contenido a usuario existente
INSERT INTO TABLE (SELECT seriesAni from usuarios where lower(id) like '1') VALUES (tipoSeries('mayo-nee sama','fantasia',1,8,4));
INSERT INTO TABLE (SELECT musicaAni from usuarios where lower(id) like '1') VALUES (tipoMusica('chocolate','metal',1,8,'cancion','baby metal'));
INSERT INTO TABLE (SELECT librosAni from usuarios where lower(id) like '1') VALUES (tipoLibros('cincuenta sombras de grey','novela erotica',0,1,'E.L.James')) ;
INSERT INTO TABLE (SELECT peliculasAni from usuarios where lower(id) like '1') VALUES (tipoPeliculas('pesadillas antes de navidad','fantasia',1,10,'Henry Selick'));

--Todo lo anterior funciona
------calendario

--Insertar
INSERT INTO calendarios VALUES(tipoCalendarios(NULL,'lunes',
(tabla_anidadaSeries(tipoSeries('mayo-nee sama','fantasia',1,8,4)))));

INSERT INTO calendarios SELECT tipoCalendarios () FROM CALENDARIOS E WHERE E.NOMBRE = '1';

INSERT INTO calendarios VALUES(NULL,'martes',
(tabla_anidadaSeries(tipoSeries('mayo-nee sama','fantasia',1,8,4))));
--Seleccionar
SELECT series.* from calendarios C,table(serie) series where C.id like '1';
SELECT dia,DEREF(P.ID) from calendarios,usuarios P;
SELECT dia,DEREF(usu) from calendarios where DEREF(usu).id like '1';

--Borrar
-------recomendados
---Recomendados de los amigos
SELECT series.nombre from usuarios, table (seriesAni) series where lower (id)like nombreAmigo and series.recomendado = 1;
--------amigos
SELECT amigos.* from usuarios, table (amigosAni) amigos where lower (id)like usuarioConectado;



----------FUNCIONES Y PROCESOS
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
--Comprobación

SET SERVEROUTPUT ON;
DECLARE 
	a boolean;
BEGIN
	a:=existeUsuario('1');
	if(a) then
		DBMS_OUTPUT.PUT_LINE(' it is true');
	else
		DBMS_OUTPUT.PUT_LINE(' it is false');
	end if;
END;

--Comprobar si existe ese usuario con la contraseña
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

--Comprobación

SET SERVEROUTPUT ON;
DECLARE 
	a integer;
BEGIN
	a:=existeUsuarioyPass('1','passi');
	if(a=1) then
		DBMS_OUTPUT.PUT_LINE(' it is true');
	else
		DBMS_OUTPUT.PUT_LINE(' it is false');
	end if;
END;
--Crear usuario 1 bien 0 mal
CREATE OR REPLACE PROCEDURE crearUsuario (idv varchar2, passv varchar2)
is
begin
	INSERT INTO usuarios VALUES(idv,passv,
	tabla_anidadaAmigos(),
	tabla_anidadaSeries(),
	tabla_anidadaMusica(),
	tabla_anidadaLibros(),
	tabla_anidadaPeliculas());
end;
--Comprobación

SET SERVEROUTPUT ON;
DECLARE 
	a boolean;
BEGIN
	a:=crearUsuario('juan','pasi');
	if(a) then
		DBMS_OUTPUT.PUT_LINE(' it is true');
	else
		DBMS_OUTPUT.PUT_LINE(' it is false');
	end if;
END;
--------contenido
--Conseguir contenido de un tipo

--Insertar contenido 
CREATE OR REPLACE FUNCTION insertarContenido (
--comprobación
--Borrar contenido concreto
CREATE OR REPLACE PROCEDURE borrarContenido (usuario varchar2, nombreSerie varchar2)
is
begin
	DELETE from table (select seriesAni from usuarios where lower(id)like usuario) series where series.nombre like nombreSerie;
end;
--comprobación
SET SERVEROUTPUT ON;
DECLARE 
BEGIN
	borrarContenido('1','juanes');
END;

