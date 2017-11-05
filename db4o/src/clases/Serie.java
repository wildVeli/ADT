package clases;

public class Serie extends Contenido{
	private short temporadas;

	public short getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(short temporadas) {
		this.temporadas = temporadas;
	}

	public Serie(int id, String nombre, String genero, boolean recomendado, short puntuacion, short temporadas) {
		super(id, nombre, genero, recomendado, puntuacion);
		this.temporadas = temporadas;
	}

	public Serie() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
