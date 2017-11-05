package db;

import java.util.ArrayList;

import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;

public class DbManager {

	public static void guardarDatosBasicos() {
		Manager.guardarDatosBasicos();
	}
	public static ArrayList<Pelicula> selectTodasLasPeliculas() {
		// TODO Auto-generated method stub
		return  Manager.selectTodasLasPeliculas();
	}

	public static ArrayList<Pelicula> selectPelisFantasia() {
		return Manager.selectPelisFantasia();
	}

	public static ArrayList<String> selectPelisMayor7() {
		return Manager.selectPelisMayor7();
	}


	public static int selectCountLibrosPuntuacion9() {
		return Manager.selectCountLibrosPuntuacion9();
	}

	public static ArrayList<String> selectNombreMusicaSinCantante() {
		return Manager.selectNombreMusicaSinCantante();

	}

	public static void modificarPuntuacionPeliculaIT() {
		Manager.modificarPuntuacionPeliculaIT();
		
	}
	public static void modificarPuntuacionMusicaChocolate() {
		Manager.modificarPuntuacionMusicaChocolate();
		
	}

	public static void borrarPeliculasPuntuacionMenor3() {
		Manager.borrarPeliculasPuntuacionMenor3();
		
	}
	public static void borrarLibrosNombreConH() {
		Manager.borrarLibrosNombreConH();
		
	}
	public static void borrarSeriesNoRecomendadas() {
		Manager.borrarSeriesNoRecomendadas();
		
	}
	public static ArrayList<Libro> selectTodosLosLibros() {
		return Manager.selectTodosLosLibros();
	}
	public static ArrayList<Serie> selectTodasLasSeries() {
		return Manager.selectTodasLasSeries();
	}
	public static ArrayList<Musica> selectTodaLaMusica() {
		return Manager.selectTodaLaMusica();
	}


}
