package controlador;

import java.util.ArrayList;

import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;
import db.DbManager;

public class Main {

	public static void main(String[] args) {

		Short option;
		
		do {
			System.out.println("Selecciona opción"
					+ "\n 1-Añadir datos basicos"
					+ "\n 2-Seleccionar todas las películas"
					+ "\n 3-Seleccionar películas de fantasía"
					+ "\n 4-Seleccionar películas con mas puntuación que 7"
					+ "\n 5-Cantidad libros con puntuación 9"
					+ "\n 6-Seleccionar música sin cantante registrado"
					+ "\n 7-Modificar puntuación película IT a 2"
					+ "\n 8-Modificar puntuación musica chocolate a 10"
					+ "\n 9-Borrar peliculas con puntuación menor que 3"
					+ "\n 10-Borrar libros que contengan h en su nombre"
					+ "\n 11-Borrar series no recomendadas"
					+ "\n 12-Seleccionar todos los libros"
					+ "\n 13-Seleccionar toda la musica"
					+ "\n 14-Seleccionar todas las series"
					+ "\n 15-Salir");
			option=(short) Utilidades.leerInt(1, 15);
			
			switch(option) {	
				
				case 1:
					DbManager.guardarDatosBasicos();
					break;
				case 2:
					peliculas();
					break;
				case 3:
					peliculasFantasia();
					break;
				case 4:
					peliculasPuncuacionMayor7();
					break;
				case 5:
					librosPuntuacion9();
					break;
				case 6:
					musicaSinCantante();
					break;
				case 7:
					DbManager.modificarPuntuacionPeliculaIT();
					break;
				case 8:
					DbManager.modificarPuntuacionMusicaChocolate();
					break;
				case 9:
					DbManager.borrarPeliculasPuntuacionMenor3();
					break;
				case 10:
					DbManager.borrarLibrosNombreConH();
					break;
				case 11:
					DbManager.borrarSeriesNoRecomendadas();
					break;
				case 12:
					seleccionarLibros();
					break;
				case 13:
					seleccionarMusica();
					break;
				case 14:
					seleccionarSeries();
					break;
			}
		}while(option!=15);	
		
	}



	private static void musicaSinCantante() {
		ArrayList<String> musica= DbManager.selectNombreMusicaSinCantante();
		System.out.println(musica.size());
		for (int i = 0; i < musica.size(); i++) {
			System.out.println("Nombre: "+musica.get(i));

		}
		System.out.println();
		
	}


	private static void librosPuntuacion9() {
		int cantidadLibros= DbManager.selectCountLibrosPuntuacion9();
		
		System.out.println("Cantidad: "+ cantidadLibros);
		System.out.println();
	}


	private static void peliculasPuncuacionMayor7() {
		
		ArrayList<String> peliculas= DbManager.selectPelisMayor7();
		
		for (int i = 0; i < peliculas.size(); i++) {
			System.out.println("Nombre: "+peliculas.get(i));

		}
		System.out.println();
	}


	private static void peliculas() {
		
		ArrayList<Pelicula> peliculas= DbManager.selectTodasLasPeliculas();
		
		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula x=peliculas.get(i);
			System.out.println("Nombre: "+x.getNombre());
			System.out.println("Genero: "+x.getGenero());
			System.out.println("Recomendado: "+ x.isRecomendado());
			System.out.println("Puntuación: "+x.getPuntuacion());
			System.out.println("Director: "+x.getDirector());
			System.out.println();
		}
		System.out.println();
	}
	

	private static void peliculasFantasia() {
		
		ArrayList<Pelicula> peliculas= DbManager.selectPelisFantasia();
		
		for (int i = 0; i < peliculas.size(); i++) {
			Pelicula x=peliculas.get(i);
			System.out.println("Nombre: "+x.getNombre());
			System.out.println("Genero: "+x.getGenero());
			System.out.println("Recomendado: "+ x.isRecomendado());
			System.out.println("Puntuación: "+x.getPuntuacion());
			System.out.println("Director: "+x.getDirector());
			System.out.println();
		}
		System.out.println();
	}
	private static void seleccionarLibros() {
		ArrayList<Libro> libros= DbManager.selectTodosLosLibros();
		
		for (int i = 0; i < libros.size(); i++) {
			Libro x=libros.get(i);
			System.out.println("Nombre: "+x.getNombre());
			System.out.println("Genero: "+x.getGenero());
			System.out.println("Recomendado: "+ x.isRecomendado());
			System.out.println("Puntuación: "+x.getPuntuacion());
			System.out.println("Autor: "+x.getAutor());
			System.out.println();
		}
		System.out.println();
		
	}

	private static void seleccionarMusica() {
		ArrayList<Musica> musica= DbManager.selectTodaLaMusica();
		
		for (int i = 0; i < musica.size(); i++) {
			Musica x=musica.get(i);
			System.out.println("Nombre: "+x.getNombre());
			System.out.println("Genero: "+x.getGenero());
			System.out.println("Recomendado: "+ x.isRecomendado());
			System.out.println("Puntuación: "+x.getPuntuacion());
			System.out.println("Director: "+x.getCantante());
			System.out.println();
		}
		System.out.println();
		
	}

	private static void seleccionarSeries() {
		ArrayList<Serie> series= DbManager.selectTodasLasSeries();
		
		for (int i = 0; i < series.size(); i++) {
			Serie x=series.get(i);
			System.out.println("Nombre: "+x.getNombre());
			System.out.println("Genero: "+x.getGenero());
			System.out.println("Recomendado: "+ x.isRecomendado());
			System.out.println("Puntuación: "+x.getPuntuacion());
			System.out.println("Temporadas: "+x.getTemporadas());
			System.out.println();
		}
		System.out.println();
		
	}
}
