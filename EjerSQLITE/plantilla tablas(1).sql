DROP TABLE peliculas;
DROP TABLE libros;
DROP TABLE series;
DROP TABLE musica;




CREATE TABLE peliculas (
id INT(3) NOT NULL PRIMARY KEY,
nombre VARCHAR(40) NOT NULL,
genero VARCHAR(20),
recomendado BOOLEAN(1),
puntuacion TINYINT(2) CHECK(puntuacion>0 AND puntuacion<11),
director VARCHAR(40)
);

CREATE TABLE libros (
id INT(3) NOT NULL PRIMARY KEY,
nombre VARCHAR(40) NOT NULL,
genero VARCHAR(20),
recomendado BOOLEAN(1),
puntuacion TINYINT(2) CHECK(puntuacion>0 AND puntuacion<11),
autor VARCHAR(20)
);

CREATE TABLE series (
id INT(3) NOT NULL PRIMARY KEY,
nombre VARCHAR(40) NOT NULL,
genero VARCHAR(20),
recomendado BOOLEAN(1),
puntuacion TINYINT(2) CHECK(puntuacion>0 AND puntuacion<11),
temporadas TINYINT (2) CHECK (temporadas > 0)
);

CREATE TABLE musica (
id INT(3) NOT NULL PRIMARY KEY,
nombre VARCHAR(40) NOT NULL,
genero VARCHAR(20),
recomendado BOOLEAN(1),
puntuacion TINYINT(2) CHECK(puntuacion>0 AND puntuacion<11),
tipo VARCHAR(7) CHECK (tipo IN('disco','cancion')),
cantante VARCHAR(40)
);



--//INSERTS----------------------------

--//peliculas
INSERT INTO peliculas VALUES(1,'pesadillas antes de navidad','fantasia',1,10,'Henry Selick');
INSERT INTO peliculas VALUES(2,'it','terror',0,1,'');
INSERT INTO peliculas VALUES(3,'la momia','aventuras',0,3,'');
INSERT INTO peliculas VALUES(4,'los juegos del hambre','fantasia',1,7,'');

--//libros
INSERT INTO libros VALUES(1,'crepusculo','fantasia',0,1,'');
INSERT INTO libros VALUES(2,'cincuenta sombras de grey','novela erotica',0,1,'E.L.James');
INSERT INTO libros VALUES(3,'el camino de las sombras','aventuras',1,8,'Brent Weeks');
INSERT INTO libros VALUES(4,'los juegos del hambre','fantasia',1,9,'');

--//series
INSERT INTO series VALUES(1,'house','fantasia',1,8,4);
INSERT INTO series VALUES(2,'vikings','accion',1,7,5);
INSERT INTO series VALUES(3,'icarly','comedia',0,7,3);
INSERT INTO series VALUES(4,'konosuba','fantasia',1,10,1);

--//musica
INSERT INTO musica VALUES(1,'principe alí','pop',1,10,'cancion','');
INSERT INTO musica VALUES(2,'chocolate','metal',1,8,'cancion','baby metal');
INSERT INTO musica VALUES(3,'de cero a héroe','música popular',1,8,'cancion','juan');
INSERT INTO musica VALUES(4,'no hay un genio tan genia','pop',1,7,'cancion','pedro');

--//Consultas-------------------

--//peliculas
SELECT * FROM peliculas; 
SELECT * FROM peliculas WHERE lower(genero) like 'fantasia';
SELECT nombre FROM peliculas WHERE puntuacion > 7;


---//libros
--SELECT nombre, recomendado FROM libros WHERE
SELECT * FROM libros; 
--SELECT genero,count(*) cantidad  FROM libros GROUP BY genero ORDER BY cantidad  ;
SELECT count(*) FROM libros WHERE puntuacion =9;


--//series
SELECT * FROM series;
--SELECT nombre, recomendado FROM series WHERE lower(nombre) like '%i%' and puntuacion >6;
--SELECT nombre FROM series WHERE lower(nombre) like (SELECT lower(nombre) FROM libros WHERE puntuacion >5);
--SELECT nombre,genero,puntuacion FROM series WHERE lower(nombre) like 'k%';


--//musica
SELECT * FROM musica;
SELECT nombre FROM musica WHERE cantante like '';
--SELECT cantante,count(*) FROM musica WHERE tipo like 'cancion' GROUP BY cantante;


   
--//Updates
UPDATE peliculas SET puntuacion = 2 WHERE lower(nombre) like 'it';
--UPDATE libros SET genero= 'fantasía' WHERE lower(genero) like 'fantasia';
UPDATE musica SET puntuacion= 10 WHERE lower(nombre) like 'chocolate';


--//Borrar
DELETE FROM peliculas WHERE puntuacion <3;
DELETE FROM libros WHERE lower(nombre) like '%h%';
DELETE FROM series WHERE recomendado = 0;