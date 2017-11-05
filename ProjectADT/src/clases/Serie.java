package clases;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Serie.
 * 
 * @author casa
 */
public class Serie extends Contenido {
	/**
	 * Description of the property temporadas.
	 */
	private Integer temporadas = Integer.valueOf(0);

	// Start of user code (user defined attributes for Serie)

	// End of user code

	/**
	 * The constructor.
	 */
	public Serie() {
		// Start of user code constructor for Serie)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Serie)

	// End of user code
	/**
	 * Returns temporadas.
	 * @return temporadas 
	 */
	public Integer getTemporadas() {
		return this.temporadas;
	}

	/**
	 * Sets a value to attribute temporadas. 
	 * @param newTemporadas 
	 */
	public void setTemporadas(Integer newTemporadas) {
		this.temporadas = newTemporadas;
	}

}
