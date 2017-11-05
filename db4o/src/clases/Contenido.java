package clases;

public class Contenido {
	private int id;
	private String nombre;
	private String genero;
	private boolean recomendado;
	private short puntuacion;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public boolean isRecomendado() {
		return recomendado;
	}
	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}
	public short getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(short puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Contenido(int id, String nombre, String genero, boolean recomendado, short puntuacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.genero = genero;
		this.recomendado = recomendado;
		this.puntuacion = puntuacion;
	}

	public Contenido() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
