package clases;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Contenido.
 * 
 * @author casa
 */
public class Contenido {
	/**
	 * Description of the property nombre.
	 */
	private String nombre = "";

	/**
	 * Description of the property genero.
	 */
	private String genero = "";

	/**
	 * Description of the property recomendado.
	 */
	private Boolean recomendado = Boolean.FALSE;

	/**
	 * Description of the property puntucacion.
	 */
	private short puntucacion = 0;

	// Start of user code (user defined attributes for Contenido)

	// End of user code

	/**
	 * The constructor.
	 */
	public Contenido() {
		// Start of user code constructor for Contenido)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Contenido)

	// End of user code
	/**
	 * Returns nombre.
	 * @return nombre 
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * Sets a value to attribute nombre. 
	 * @param newNombre 
	 */
	public void setNombre(String newNombre) {
		this.nombre = newNombre;
	}

	/**
	 * Returns genero.
	 * @return genero 
	 */
	public String getGenero() {
		return this.genero;
	}

	/**
	 * Sets a value to attribute genero. 
	 * @param newGenero 
	 */
	public void setGenero(String newGenero) {
		this.genero = newGenero;
	}

	/**
	 * Returns recomendado.
	 * @return recomendado 
	 */
	public Boolean getRecomendado() {
		return this.recomendado;
	}

	/**
	 * Sets a value to attribute recomendado. 
	 * @param newRecomendado 
	 */
	public void setRecomendado(Boolean newRecomendado) {
		this.recomendado = newRecomendado;
	}

	/**
	 * Returns puntucacion.
	 * @return puntucacion 
	 */
	public short getPuntucacion() {
		return this.puntucacion;
	}

	/**
	 * Sets a value to attribute puntucacion. 
	 * @param newPuntucacion 
	 */
	public void setPuntucacion(short newPuntucacion) {
		this.puntucacion = newPuntucacion;
	}

}
