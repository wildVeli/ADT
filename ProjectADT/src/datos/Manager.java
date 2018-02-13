package datos;

import java.io.IOException;
import java.util.ArrayList;

import clases.Calendario;
import clases.Contenido;
import clases.Serie;

public class Manager {

	
	private DBObjectsManager dbManager;
	private DBmongo dbMongo;
	private DBMySQL dbMysql;
	private Hibernate hibernate = null;
	
	
	public Manager(){
		try {
			dbManager = new DBObjectsManager();
			dbMongo = new DBmongo();
			dbMysql = new DBMySQL();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//GESTI�N DE USUARIO -----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo validarUsuario del manager correspondiente
	 * @param nombreUsuario Nombre del usuario
	 * @param password contrase�a del usuario que se comprueba
	 * @param tipo que base de datos se utilizar�
	 * @param tipoAccion puede ser "registro" comprueba si existe el nombre de usuario o "login" comprueba si existe el nombre y contrase�a del usuario
	 * @return devuelve si existe en la base de datos
	 */
	public boolean validarUsuario(String nombreUsuario,String password,String tipoAccion,short tipo)  {
		boolean existe=true;
		boolean primero=true;
		switch(tipo){
		
			case 1:	
				existe = dbManager.validarUsuario(nombreUsuario,password,tipoAccion);
				break;
			case 2:
				existe = dbMongo.validarUsuario(nombreUsuario,password,tipoAccion);
				break;
			case 3:
				existe = dbMysql.validarUsuario(nombreUsuario,password,tipoAccion);
				break;
			case 4:
				hibernateCreate();
				existe = hibernate.validarUsuario(nombreUsuario,password,tipoAccion);
				break;
		}
		return existe;
	}
	/**
	 * M�todo que llama al m�todo registrarUsuario del manager correspondiente
	 * @param nombreUsuario Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @param tipo que base de datos se utilizar�
	 */
	public void registrarUsuario(String nombreUsuario,String password,short tipo)  {
		switch(tipo){
			case 1:
				dbManager.registrarUsuario(nombreUsuario, password);
				break;
			case 2:
				dbMongo.registrarUsuario(nombreUsuario, password);
				break;
			case 3:
				dbMysql.registrarUsuario(nombreUsuario, password);
				break;
			case 4:
				hibernateCreate();
				hibernate.registrarUsuario(nombreUsuario, password);
				break;
		}
	}
	/**
	 * Hace un new de Hibernate
	 */
	private void hibernateCreate() {
		if(hibernate == null) {
			hibernate = new Hibernate();
		}
		
	}
	//CONTENIDOS-------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo getContenidoDeUnTipo del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la busqueda
	 * @param tipoContenido por que tipo de contenido se filtra
	 * @param tipo que base de datos se utiliza
	 * @return devuelve una lista del contenido del usuario de dicho tipo
	 */
	public ArrayList<Object> getContenidoDeUnTipo (String nombreUsuario,String tipoContenido,short tipo) {
		ArrayList<Object> contenido = null;
		switch(tipo){
			case 1:
				contenido=dbManager.getContenidoDeUnTipo(nombreUsuario,tipoContenido);
				break;
			case 2:
				contenido=dbMongo.getContenidoDeUnTipo(nombreUsuario,tipoContenido);
				break;
			case 3:
				contenido=dbMysql.getContenidoDeUnTipo(nombreUsuario,tipoContenido);
				break;
			case 4:
				hibernateCreate();
				contenido=hibernate.getContenidoDeUnTipo(nombreUsuario,tipoContenido);
				break;
		}
		return contenido;
	}
	/**
	 * M�todo que llama al m�todo borrarContenidoSeleccionado del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la eliminaci�n
	 * @param nombreContenido nombre del contenido que se borrar�
	 * @param tipo que base de datos se utiliza
	 * @param tipoContenido especifica que tipo de contenido es(series,pel�culas,m�sica o libros)
	 */
	public void borrarContenidoSeleccionado (String nombreUsuario,String nombreContenido,String tipoContenido,short tipo){
		switch(tipo){
		case 1:
			dbManager.borrarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		case 2:
			dbMongo.borrarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		case 3:
			dbMysql.borrarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		case 4:
			hibernateCreate();
			hibernate.borrarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		}
	}
	/**
	 * M�todo que llama al m�todo recomendarContenidoSeleccionado del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la recomendaci�n
	 * @param nombreContenido nombre del contenido que se recomendar�
	 * @param tipo que base de datos se utiliza
	 * @param tipoContenido especifica que tipo de contenido es(series,pel�culas,m�sica o libros)
	 */
	public void recomendarContenidoSeleccionado (String nombreUsuario,String nombreContenido,String tipoContenido,short tipo) {
		switch(tipo){
		case 1:
			dbManager.recomendarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		case 2:
			dbMongo.recomendarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		case 3:
			dbMysql.recomendarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		case 4:
			hibernateCreate();
			hibernate.recomendarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		}
	}
	/**
	 *  M�todo que llama al m�todo anadirNuevoContenido del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param contenido contenido que se a�adira a la lista del usuario
 		@param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Pel�cula", "M�sica", "Libro"
	 * @param tipo que base de datos se utiliza
	 */
	public void anadirNuevoContenido (String nombreUsuario,Contenido contenido,String tipoContenido,short tipo) {
		switch(tipo){
		case 1:
			dbManager.anadirNuevoContenido(nombreUsuario,contenido,tipoContenido);
			break;
		case 2:
			dbMongo.anadirNuevoContenido(nombreUsuario,contenido,tipoContenido);
			break;
		case 3:
			dbMysql.anadirNuevoContenido(nombreUsuario,contenido,tipoContenido);
			break;
		case 4:
			hibernateCreate();
			hibernate.anadirNuevoContenido(nombreUsuario,contenido,tipoContenido);
			break;
		}
	}
	/**
	 *  M�todo que llama al m�todo modificarContenido del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param contenido contenido modificado para sustituir al viejo
	 * @param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Pel�cula", "M�sica", "Libro"
	 * @param tipo que base de datos se utiliza
	 */
	public void modificarContenido (String nombreUsuario,Contenido contenido,String tipoContenido,short tipo) {
		switch(tipo){
		case 1:
			dbManager.modificarContenido(nombreUsuario,contenido,tipoContenido);
			break;
		case 2:
			dbMongo.modificarContenido(nombreUsuario,contenido, tipoContenido);
			break;
		case 3:
			dbMysql.modificarContenido(nombreUsuario,contenido, tipoContenido);
			break;
		case 4:
			hibernateCreate();
			hibernate.modificarContenido(nombreUsuario,contenido, tipoContenido);
			break;
		}
	}
	
	//CALENDARIO--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo getCalendarios del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acci�n
	 * @param tipo que base de datos se utiliza
	 * @return devuelve los calendarios que tiene el usuario
	 */
	public ArrayList<Calendario> getCalendarios (String nombreUsuario,short tipo) {
		ArrayList<Calendario> calendarios=new ArrayList<Calendario>();
		switch(tipo){
			case 1:
				calendarios=dbManager.getCalendarios(nombreUsuario);
				break;
			case 2:
				calendarios=dbMongo.getCalendarios(nombreUsuario);
				break;
			case 3:
				calendarios=dbMysql.getCalendarios(nombreUsuario);
				break;
			case 4:
				hibernateCreate();
				calendarios=hibernate.getCalendarios(nombreUsuario);
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
	 */
	public void borrarRegistroCalendario (String nombreUsuario,String registro,String dia,short tipo)  {
		switch(tipo){
		case 1:
			dbManager.borrarRegistroCalendario(nombreUsuario,dia,registro);
			break;
		case 2:
			dbMongo.borrarRegistroCalendario(nombreUsuario,dia,registro);
			break;
		case 3:
			dbMysql.borrarRegistroCalendario(nombreUsuario,dia,registro);
			break;
		case 4:
			hibernateCreate();
			hibernate.borrarRegistroCalendario(nombreUsuario,dia,registro);
			break;
		}
	}
	/**
	 * M�todo que llama al m�todo nuevoRegistroCalendario del Manager correspondiente
	 * @param nombreUsuario  nombre del usuario que efectua la acci�n
	 * @param registro nombre del registro que se a�adir� al calendario
	 * @param dia que dia se modificar�, para saber que calendario modificar
	 * @param tipo base de datos que se utiliza
	 */
	public void nuevoRegistroCalendario (String nombreUsuario,String registro,String dia,short tipo){
		switch(tipo){
			case 1:
				dbManager.nuevoRegistroCalendario(nombreUsuario,dia,registro);
				break;
			case 2:
				dbMongo.nuevoRegistroCalendario(nombreUsuario,dia,registro);
				break;
			case 3:
				dbMysql.nuevoRegistroCalendario(nombreUsuario,dia,registro);
				break;
			case 4:
				hibernateCreate();
				hibernate.nuevoRegistroCalendario(nombreUsuario,dia,registro);
				break;
		}
	}
	//RECOMENDACIONES------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que llama al m�todo getRecomendacionesAmigos del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que est� utilizando la aplicaci�n
	 * @param tipo base de datos que se utiliza
	 * @return devuelve una lista de series
	 */
	public ArrayList<Serie> getRecomendacionesAmigos (String nombreUsuario,short tipo) {
		ArrayList<Serie> series=new ArrayList<Serie>();
		switch(tipo){
			case 1:
				series=dbManager.getRecomendacionesAmigos(nombreUsuario);
				break;
			case 2:
				series=dbMongo.getRecomendacionesAmigos(nombreUsuario);
				break;
			case 3:
				series=dbMysql.getRecomendacionesAmigos(nombreUsuario);
				break;
			case 4:
				hibernateCreate();
				series=hibernate.getRecomendacionesAmigos(nombreUsuario);
				break;
		}
		return series;
	}
	
}
