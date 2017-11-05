package db;

import java.util.ArrayList;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;

public class Manager {
	
	static String DBPer = "DBContenido.yap";
	
	public static void guardarDatosBasicos() {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		
				Pelicula p1= new Pelicula(1,"pesadillas antes de navidad","fantasia",true,(short)10,"Henry Selick");
				Pelicula p2= new Pelicula(2,"it","terror",false,(short)1,"");
				Pelicula p3= new Pelicula(3,"la momia","aventuras",false,(short)3,"");
				Pelicula p4= new Pelicula(4,"los juegos del hambre","fantasia",false,(short)7,"");
				
				Libro l1=new Libro(1,"crepusculo","fantasia",false,(short)1,"");
				Libro l2=new Libro(2,"cincuenta sombras de grey","novela erotica",false,(short)1,"E.L.James");
				Libro l3=new Libro(3,"el camino de las sombras","aventuras",true,(short)8,"Brent Weeks");
				Libro l4=new Libro(4,"los juegos del hambre","fantasia",true,(short)9,"");
				
				Serie s1=new Serie(1,"house","fantasia",true,(short)8,(short) 4);
				Serie s2=new Serie(2,"vikings","accion",true,(short)7,(short)5);
				Serie s3=new Serie(3,"icarly","comedia",false,(short)7,(short)3);
				Serie s4=new Serie(4,"konosuba","fantasia",true,(short)10,(short)1);
				
				Musica m1=new Musica(1,"principe alí","pop",true,(short)10,"cancion","");
				Musica m2=new Musica(2,"chocolate","metal",true,(short)8,"cancion","");
				Musica m3=new Musica(3,"de cero a héroe","música popular",true,(short)8,"cancion","juan");
				Musica m4=new Musica(4,"no hay un genio tan genia","pop",true,(short)7,"cancion","pedro");
				
				db.store(p1);
				db.store(p2);
				db.store(p3);
				db.store(p4);

				db.store(l1);
				db.store(l2);
				db.store(l3);
				db.store(l4);
				
				db.store(s1);
				db.store(s2);
				db.store(s3);
				db.store(s4);
				
				db.store(m1);
				db.store(m2);
				db.store(m3);
				db.store(m4);
				
				db.close();
			

	}
	//CONSULTAS----------------------------------
	//PELICULAS
	public static ArrayList<Pelicula> selectTodasLasPeliculas () {
		ArrayList<Pelicula> peliculas = new ArrayList <Pelicula>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Pelicula p1=new Pelicula();
		ObjectSet<Pelicula> result = db.queryByExample(p1);

		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Pelicula p = result.next();
				peliculas.add(p);
			}
		}
		db.close();
		return peliculas;
		
	}
	
	public static ArrayList<Pelicula> selectPelisFantasia () {
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Pelicula p1=new Pelicula();
		p1.setGenero("fantasia");
		ObjectSet<Pelicula> result = db.queryByExample(p1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Pelicula p = result.next();
				peliculas.add(p);
			}
		}
		db.close();
		return peliculas;
		
	}
	public static ArrayList<String> selectPelisMayor7 () {
		ArrayList<String> peliculas = new ArrayList<String>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Pelicula p1=new Pelicula();
		ObjectSet<Pelicula> result = db.queryByExample(p1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Pelicula p = result.next();
				if(p.getPuntuacion()>7) {
					peliculas.add(p.getNombre());
				}
				
			}
		}
		db.close();
		return peliculas;
		
	}
	//LIBROS
	public static ArrayList<Libro> selectTodosLosLibros () {
		ArrayList<Libro> libros = new ArrayList <Libro>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Libro l1=new Libro();
		ObjectSet<Libro> result = db.queryByExample(l1);

		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Libro p = result.next();
				libros.add(p);
			}
		}
		db.close();
		return libros;
		
	}
	//COMPLETAR, CONSULTA 1 LIBROS
	/*
	public ArrayList<Pelicula> select () {
		ArrayList<Pelicula> peliculas;
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Pelicula p1= new Pelicula((Integer)null,null,"fantasia",(Boolean) null,(Short) null,null);
		ObjectSet<Libro> result = db.queryByExample(p1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Pelicula p = result.next();
				if(p.getPuntuacion()>7) {
					peliculas.add(p);
				}
				
			}
		}
		db.close();
		return peliculas;
		
	}
	*/
	
	public static int selectCountLibrosPuntuacion9 () {
		int contador;
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Libro l1=new Libro();
		l1.setPuntuacion((short) 9);
		ObjectSet<Libro> result = db.queryByExample(l1);
		
		contador=result.size();
		db.close();
		return contador;
		
	}
	//SERIES
	public static ArrayList<Serie> selectTodasLasSeries() {
		ArrayList<Serie> series = new ArrayList <Serie>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Serie s1=new Serie();
		ObjectSet<Serie> result = db.queryByExample(s1);

		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Serie p = result.next();
				series.add(p);
			}
		}
		db.close();
		return series;
		
	}
	//MUSICA
	public static ArrayList<Musica> selectTodaLaMusica() {
		ArrayList<Musica> musica = new ArrayList <Musica>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Musica s1=new Musica();
		ObjectSet<Musica> result = db.queryByExample(s1);

		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Musica p = result.next();
				musica.add(p);
			}
		}
		db.close();
		return musica;
		
	}
	public static ArrayList<String> selectNombreMusicaSinCantante () {
		ArrayList<String> musica = new ArrayList<String>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Musica m1= new Musica();
		m1.setCantante("");
		ObjectSet<Musica> result = db.queryByExample(m1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Musica p = result.next();
				musica.add(p.getNombre());
			}
		}
		db.close();
		return musica;
		
	}
	
	//Revisar
	public ArrayList<String> selectCantanteContadorCanciones () {
		ArrayList<String> musica = new ArrayList<String>();
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		//Musica m1= new Musica((Integer)null,null,null,(Boolean) null,(Short) null,"cancion", "");
		Musica m1=new Musica();
		m1.setTipo("cancion");
		ObjectSet<String> result = db.queryByExample(m1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				String p = result.next();
					musica.add(p);
			}
		}
		db.close();
		return musica;
		
	}
	
	//MODIFICAR--------------------------------
	public static void modificarPuntuacionPeliculaIT () {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Pelicula p1=new Pelicula();
		p1.setNombre("it");
		ObjectSet<Pelicula> result = db.queryByExample(p1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			Pelicula p=result.next();
			p.setPuntuacion((short)2);
			db.store(p);
		}
		db.close();
	}
	//FALTA EL UPDATE 2
	public static void modificarPuntuacionMusicaChocolate () {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Musica m1=new Musica();
		m1.setNombre("chocolate");
		ObjectSet<Musica> result = db.queryByExample(m1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			Musica p=result.next();
			p.setPuntuacion((short)10);
			db.store(p);
		}
		db.close();
	}
	
	
	
	//BORRAR-------------------------------------
	public static void borrarPeliculasPuntuacionMenor3 () {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Pelicula p1=new Pelicula();
		ObjectSet<Pelicula> result = db.queryByExample(p1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Pelicula p = result.next();
				if(p.getPuntuacion()<3) {
					db.delete(p);
				}
				
			}
		}
		db.close();
	}
	public static void borrarLibrosNombreConH () {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Libro l1=new Libro();
		ObjectSet<Libro> result = db.queryByExample(l1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Libro p = result.next();
				if(p.getNombre().contains("h")) {
					db.delete(p);
				}
			}
		}
		db.close();
	}
	
	public static void borrarSeriesNoRecomendadas () {
		ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),DBPer);
		Serie s1=new Serie();
		ObjectSet<Serie> result = db.queryByExample(s1);
		
		if(result.size() == 0) {
			System.out.println("No existe niguno");
		}else {
			System.out.printf("Número de registros: %d %n", result.size());
			while(result.hasNext()) {
				Serie p = result.next();
				if(!p.isRecomendado()) {
					db.delete(p);
				}

			}
		}
		db.close();
	}
}
