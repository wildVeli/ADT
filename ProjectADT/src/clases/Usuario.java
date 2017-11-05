package clases;

import java.util.HashSet;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Usuario.
 * 
 * @author casa
 */
public class Usuario {
	/**
	 * Description of the property amigos.
	 */
	public HashSet<Amigo> amigos = new HashSet<Amigo>();

	/**
	 * Description of the property id.
	 */
	private String id = "";

	/**
	 * Description of the property calendarios.
	 */
	public HashSet<Calendario> calendarios = new HashSet<Calendario>();

	/**
	 * Description of the property password.
	 */
	private String password = "";

	/**
	 * Description of the property contenidos.
	 */
	public HashSet<Contenido> contenidos = new HashSet<Contenido>();

	// Start of user code (user defined attributes for Usuario)

	// End of user code

	/**
	 * The constructor.
	 */
	public Usuario() {
		// Start of user code constructor for Usuario)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Usuario)

	// End of user code
	/**
	 * Returns amigos.
	 * @return amigos 
	 */
	public HashSet<Amigo> getAmigos() {
		return this.amigos;
	}

	/**
	 * Returns id.
	 * @return id 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets a value to attribute id. 
	 * @param newId 
	 */
	public void setId(String newId) {
		this.id = newId;
	}

	/**
	 * Returns calendarios.
	 * @return calendarios 
	 */
	public HashSet<Calendario> getCalendarios() {
		return this.calendarios;
	}

	/**
	 * Returns password.
	 * @return password 
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets a value to attribute password. 
	 * @param newPassword 
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	/**
	 * Returns contenidos.
	 * @return contenidos 
	 */
	public HashSet<Contenido> getContenidos() {
		return this.contenidos;
	}

}
