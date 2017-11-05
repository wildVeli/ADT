package clases;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Pelicula.
 * 
 * @author casa
 */
public class Pelicula extends Contenido {
	/**
	 * Description of the property director.
	 */
	private String director = "";

	// Start of user code (user defined attributes for Pelicula)

	// End of user code

	/**
	 * The constructor.
	 */
	public Pelicula() {
		// Start of user code constructor for Pelicula)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Pelicula)

	// End of user code
	/**
	 * Returns director.
	 * @return director 
	 */
	public String getDirector() {
		return this.director;
	}

	/**
	 * Sets a value to attribute director. 
	 * @param newDirector 
	 */
	public void setDirector(String newDirector) {
		this.director = newDirector;
	}

}
