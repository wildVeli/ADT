package clasesHibernate;
// Generated 09-feb-2018 19:50:59 by Hibernate Tools 5.2.8.Final

/**
 * Libros generated by hbm2java
 */
public class Libros implements java.io.Serializable {

	private LibrosId id;
	private String genero;
	private int recomendado;
	private int puntuacion;

	public Libros() {
	}

	public Libros(LibrosId id, String genero, int recomendado, int puntuacion) {
		this.id = id;
		this.genero = genero;
		this.recomendado = recomendado;
		this.puntuacion = puntuacion;
	}

	public LibrosId getId() {
		return this.id;
	}

	public void setId(LibrosId id) {
		this.id = id;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getRecomendado() {
		return this.recomendado;
	}

	public void setRecomendado(int recomendado) {
		this.recomendado = recomendado;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

}