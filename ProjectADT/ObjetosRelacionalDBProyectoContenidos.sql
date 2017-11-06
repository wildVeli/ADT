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
create type  tipoCalendarios as object(
	usu ref tipoUsuarios,
	dia varchar2(9),
	serie tabla_anidadaSeries
);
/ 

create table  calendarios of tipoCalendarios(
	--primary key (usu,dia)
)NESTED TABLE serie STORE AS serie_anidada;
/ 


INSERT INTO usuarios VALUES('1','passi',
tabla_anidadaAmigos(tipoAmigos('juan')),
tabla_anidadaSeries(tipoSeries('house','fantasia',1,8,4)),
tabla_anidadaMusica(tipoMusica('chocolate','metal',1,8,'cancion','baby metal')),
tabla_anidadaLibros(tipoLibros('cincuenta sombras de grey','novela erotica',0,1,'E.L.James')),
tabla_anidadaPeliculas(tipoPeliculas('pesadillas antes de navidad','fantasia',1,10,'Henry Selick')));




