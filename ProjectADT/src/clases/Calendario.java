package clases;

import java.util.ArrayList;
import java.util.HashSet;

/*******************************************************************************
 * 2017, All rights reserved.
 *******************************************************************************/

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Calendario.
 * 
 * @author casa
 */
public class Calendario {
	/**
	 * Description of the property series.
	 */
	public ArrayList<Serie> series = new ArrayList<Serie>();

	/**
	 * Description of the property dia.
	 */
	
	private String dia = "";

	// Start of user code (user defined attributes for Calendario)

	// End of user code

	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}

	/**
	 * The constructor.
	 */
	public Calendario() {
		// Start of user code constructor for Calendario)
		super();
		// End of user code
	}

	// Start of user code (user defined methods for Calendario)

	// End of user code
	/**
	 * Returns series.
	 * @return series 
	 */
	public ArrayList<Serie> getSeries() {
		return this.series;
	}

	/**
	 * Returns dia.
	 * @return dia 
	 */
	public String getDia() {
		return this.dia;
	}

	/**
	 * Sets a value to attribute dia. 
	 * @param newDia 
	 */
	public void setDia(String newDia) {
		this.dia = newDia;
	}

}
