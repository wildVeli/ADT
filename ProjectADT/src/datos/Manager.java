package datos;

import java.io.IOException;
import java.util.ArrayList;

import clases.Calendario;
import clases.Contenido;
import clases.Serie;

public class Manager {

	
	//GESTI�N DE USUARIO -----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo validarUsuario del manager correspondiente
	 * @param nombreUsuario Nombre del usuario
	 * @param password contrase�a del usuario que se comprueba
	 * @param tipo que base de datos se utilizar�
	 * @param tipoAccion puede ser "registro" comprueba si existe el nombre de usuario o "login" comprueba si existe el nombre y contrase�a del usuario
	 * @return devuelve si existe en la base de datos
	 * @throws IOException 
	 */
	public boolean validarUsuario(String nombreUsuario,String password,String tipoAccion,short tipo) throws IOException  {
		boolean existe=true;
		switch(tipo){
		
			case 1:
				DBObjectsManager dbManager = new DBObjectsManager();
				existe = dbManager.validarUsuario(nombreUsuario,password,tipoAccion);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
		return existe;
	}
	/**
	 * M�todo que llama al m�todo registrarUsuario del manager correspondiente
	 * @param nombreUsuario Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @param tipo que base de datos se utilizar�
	 * @throws IOException 
	 */
	public void registrarUsuario(String nombreUsuario,String password,short tipo) throws IOException {
		switch(tipo){
			case 1:
				DBObjectsManager dbManager = new DBObjectsManager();
				dbManager.registrarUsuario(nombreUsuario, password);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
	}
	
	//CONTENIDOS-------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo getContenidoDeUnTipo del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la busqueda
	 * @param tipoContenido por que tipo de contenido se filtra
	 * @param tipo que base de datos se utiliza
	 * @return devuelve una lista del contenido del usuario de dicho tipo
	 * @throws IOException 
	 */
	public ArrayList<Contenido> getContenidoDeUnTipo (String nombreUsuario,String tipoContenido,short tipo) throws IOException{
		ArrayList<Contenido> contenido = null;
		switch(tipo){
			case 1:
				DBObjectsManager dbManager = new DBObjectsManager();
				contenido=dbManager.getContenidoDeUnTipo(nombreUsuario,tipoContenido);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
		return contenido;
	}
	/**
	 * M�todo que llama al m�todo borrarContenidoSeleccionado del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la eliminaci�n
	 * @param nombreContenido nombre del contenido que se borrar�
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
	 */
	public void borrarContenidoSeleccionado (String nombreUsuario,String nombreContenido,short tipo) throws IOException {
		switch(tipo){
		case 1:
			DBObjectsManager dbManager = new DBObjectsManager();
			dbManager.borrarContenidoSeleccionado(nombreUsuario,nombreContenido);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	/**
	 * M�todo que llama al m�todo recomendarContenidoSeleccionado del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la recomendaci�n
	 * @param nombreContenido nombre del contenido que se recomendar�
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
	 */
	public void recomendarContenidoSeleccionado (String nombreUsuario,String nombreContenido,short tipo) throws IOException {
		switch(tipo){
		case 1:
			DBObjectsManager dbManager = new DBObjectsManager();
			dbManager.recomendarContenidoSeleccionado(nombreUsuario,nombreContenido);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	/**
	 *  M�todo que llama al m�todo anadirNuevoContenido del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param contenido contenido que se a�adira a la lista del usuario
 @param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Pel�cula", "M�sica", "Libro"
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
	 */
	public void anadirNuevoContenido (String nombreUsuario,Contenido contenido,String tipoContenido,short tipo) throws IOException {
		switch(tipo){
		case 1:
			DBObjectsManager dbManager = new DBObjectsManager();
			dbManager.anadirNuevoContenido(nombreUsuario,contenido,tipoContenido);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	/**
	 *  M�todo que llama al m�todo modificarContenido del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param contenido contenido modificado para sustituir al viejo
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
	 */
	public void modificarContenido (String nombreUsuario,Contenido contenido,short tipo) throws IOException {
		switch(tipo){
		case 1:
			DBObjectsManager dbManager = new DBObjectsManager();
			dbManager.modificarContenido(nombreUsuario,contenido);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	//CALENDARIO--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo getCalendarios del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param calendario calendario especifico que se desea 0 es todos, 1 es lunes,2 es martes....7 es domingo
	 * @param tipo que base de datos se utiliza
	 * @return devuelve los calendarios que tiene el usuario
	 * @throws IOException 
	 */
	public Calendario[] getCalendarios (String nombreUsuario,short calendario,short tipo) throws IOException {
		Calendario [] calendarios=new Calendario[7];
		switch(tipo){
			case 1:
				DBObjectsManager dbManager = new DBObjectsManager();
				calendarios=dbManager.getCalendarios(nombreUsuario,calendario);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
		return calendarios; 
	}
	/**
	 *  M�todo que llama al m�todo borrarRegistroCalendario del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param registro nombre del registro a eliminar
	 * @param dia que dia se modificar�, para saber que calendario modificar
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
	 */
	public void borrarRegistroCalendario (String nombreUsuario,String registro,String dia,short tipo) throws IOException {
		switch(tipo){
		case 1:
			DBObjectsManager dbManager = new DBObjectsManager();
			dbManager.borrarRegistroCalendario(nombreUsuario,dia,registro);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	/**
	 * M�todo que llama al m�todo nuevoRegistroCalendario del Manager correspondiente
	 * @param nombreUsuario  nombre del usuario que efectua la acci�n
	 * @param registro nombre del registro que se a�adir� al calendario
	 * @param dia que dia se modificar�, para saber que calendario modificar
	 * @param tipo base de datos que se utiliza
	 * @throws IOException 
	 */
	public void nuevoRegistroCalendario (String nombreUsuario,String registro,String dia,short tipo) throws IOException {
		switch(tipo){
			case 1:
				DBObjectsManager dbManager = new DBObjectsManager();
				dbManager.nuevoRegistroCalendario(nombreUsuario,dia,registro);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
	}
	//RECOMENDACIONES------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo getRecomendacionesAmigos del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que est� utilizando la aplicaci�n
	 * @param tipo base de datos que se utiliza
	 * @return devuelve una lista de series
	 * @throws IOException
	 */
	public ArrayList<Serie> getRecomendacionesAmigos (String nombreUsuario,short tipo) throws IOException {
		ArrayList<Serie> series=new ArrayList<Serie>();
		switch(tipo){
			case 1:
				DBObjectsManager dbManager = new DBObjectsManager();
				series=dbManager.getRecomendacionesAmigos(nombreUsuario);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
		return series;
	}
	
}
