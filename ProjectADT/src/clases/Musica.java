package clases;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Musica.
 * 
 * @author casa
 */
public class Musica extends Contenido {
	/**
	 * Description of the property tipo.
	 */
	private TipoMusica tipo = null;

	/**
	 * Description of the property cantante.
	 */
	private String cantante = "";

	// Start of user code (user defined attributes for Musica)

	// End of user code

	/**
	 * The constructor.
	 */
	public Musica() {
		// Start of user code constructor for Musica)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Musica)

	// End of user code
	/**
	 * Returns tipo.
	 * @return tipo 
	 */
	public TipoMusica getTipo() {
		return this.tipo;
	}

	/**
	 * Sets a value to attribute tipo. 
	 * @param newTipo 
	 */
	public void setTipo(TipoMusica newTipo) {
		this.tipo = newTipo;
	}

	/**
	 * Returns cantante.
	 * @return cantante 
	 */
	public String getCantante() {
		return this.cantante;
	}

	/**
	 * Sets a value to attribute cantante. 
	 * @param newCantante 
	 */
	public void setCantante(String newCantante) {
		this.cantante = newCantante;
	}

}
