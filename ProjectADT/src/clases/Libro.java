package clases;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Libro.
 * 
 * @author casa
 */
public class Libro extends Contenido {
	/**
	 * Description of the property autor.
	 */
	private String autor = "";

	// Start of user code (user defined attributes for Libro)

	// End of user code

	/**
	 * The constructor.
	 */
	public Libro() {
		// Start of user code constructor for Libro)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Libro)

	// End of user code
	/**
	 * Returns autor.
	 * @return autor 
	 */
	public String getAutor() {
		return this.autor;
	}

	/**
	 * Sets a value to attribute autor. 
	 * @param newAutor 
	 */
	public void setAutor(String newAutor) {
		this.autor = newAutor;
	}

}
