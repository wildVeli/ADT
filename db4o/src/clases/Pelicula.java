package clases;

public class Pelicula extends Contenido {
	private String director;

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
		
	}

	public Pelicula(int id,String nombre,String genero,boolean recomendado,short puntuacion,String director) {
		super(id,nombre,genero,recomendado,puntuacion);
		this.director = director;
	}


	public Pelicula() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
