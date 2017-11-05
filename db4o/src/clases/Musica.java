package clases;

public class Musica extends Contenido {
	private String tipo;
	private String cantante;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCantante() {
		return cantante;
	}
	public void setCantante(String cantante) {
		this.cantante = cantante;
	}
	public Musica(int id, String nombre, String genero, boolean recomendado, short puntuacion, String tipo,
			String cantante) {
		super(id, nombre, genero, recomendado, puntuacion);
		this.tipo = tipo;
		this.cantante = cantante;
	}
	public Musica() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
