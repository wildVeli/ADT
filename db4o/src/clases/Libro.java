package clases;

public class Libro extends Contenido{
	private String autor;

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Libro(int id, String nombre, String genero, boolean recomendado, short puntuacion, String autor) {
		super(id, nombre, genero, recomendado, puntuacion);
		this.autor = autor;
	}

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
}
