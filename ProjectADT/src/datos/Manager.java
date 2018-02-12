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
	//GESTIÓN DE USUARIO -----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que llama al método validarUsuario del manager correspondiente
	 * @param nombreUsuario Nombre del usuario
	 * @param password contraseña del usuario que se comprueba
	 * @param tipo que base de datos se utilizará
	 * @param tipoAccion puede ser "registro" comprueba si existe el nombre de usuario o "login" comprueba si existe el nombre y contraseña del usuario
	 * @return devuelve si existe en la base de datos
	 * @throws IOException 
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
				if(hibernate == null) {
					hibernate = new Hibernate();
				}
				existe = hibernate.validarUsuario(nombreUsuario,password,tipoAccion);
				break;
		}
		return existe;
	}
	/**
	 * Método que llama al método registrarUsuario del manager correspondiente
	 * @param nombreUsuario Nombre del usuario
	 * @param password Contraseña del usuario
	 * @param tipo que base de datos se utilizará
	 * @throws IOException 
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
				if(hibernate == null) {
					hibernate = new Hibernate();
				}
				hibernate.registrarUsuario(nombreUsuario, password);
				break;
		}
	}
	
	//CONTENIDOS-------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que llama al método getContenidoDeUnTipo del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la busqueda
	 * @param tipoContenido por que tipo de contenido se filtra
	 * @param tipo que base de datos se utiliza
	 * @return devuelve una lista del contenido del usuario de dicho tipo
	 * @throws IOException 
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
				contenido=hibernate.getContenidoDeUnTipo(nombreUsuario,tipoContenido);
				break;
		}
		return contenido;
	}
	/**
	 * Método que llama al método borrarContenidoSeleccionado del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la eliminación
	 * @param nombreContenido nombre del contenido que se borrará
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
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
			hibernate.borrarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		}
	}
	/**
	 * Método que llama al método recomendarContenidoSeleccionado del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que hace la recomendación
	 * @param nombreContenido nombre del contenido que se recomendará
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
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
			hibernate.recomendarContenidoSeleccionado(nombreUsuario,nombreContenido, tipoContenido);
			break;
		}
	}
	/**
	 *  Método que llama al método anadirNuevoContenido del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acción
	 * @param contenido contenido que se añadira a la lista del usuario
 		@param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Película", "Música", "Libro"
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
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
			hibernate.anadirNuevoContenido(nombreUsuario,contenido,tipoContenido);
			break;
		}
	}
	/**
	 *  Método que llama al método modificarContenido del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acción
	 * @param contenido contenido modificado para sustituir al viejo
	 * @param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Película", "Música", "Libro"
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
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
			hibernate.modificarContenido(nombreUsuario,contenido, tipoContenido);
			break;
		}
	}
	
	//CALENDARIO--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que llama al método getCalendarios del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acción
	 * @param calendario calendario especifico que se desea 0 es todos, 1 es lunes,2 es martes....7 es domingo
	 * @param tipo que base de datos se utiliza
	 * @return devuelve los calendarios que tiene el usuario
	 * @throws IOException 
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
				calendarios=hibernate.getCalendarios(nombreUsuario);
				break;
		}
		return calendarios; 
	}
	/**
	 *  Método que llama al método borrarRegistroCalendario del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que efectua la acción
	 * @param registro nombre del registro a eliminar
	 * @param dia que dia se modificará, para saber que calendario modificar
	 * @param tipo que base de datos se utiliza
	 * @throws IOException 
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
			hibernate.borrarRegistroCalendario(nombreUsuario,dia,registro);
			break;
		}
	}
	/**
	 * Método que llama al método nuevoRegistroCalendario del Manager correspondiente
	 * @param nombreUsuario  nombre del usuario que efectua la acción
	 * @param registro nombre del registro que se añadirá al calendario
	 * @param dia que dia se modificará, para saber que calendario modificar
	 * @param tipo base de datos que se utiliza
	 * @throws IOException 
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
				hibernate.nuevoRegistroCalendario(nombreUsuario,dia,registro);
				break;
		}
	}
	//RECOMENDACIONES------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que llama al método getRecomendacionesAmigos del Manager correspondiente
	 * @param nombreUsuario nombre del usuario que está utilizando la aplicación
	 * @param tipo base de datos que se utiliza
	 * @return devuelve una lista de series
	 * @throws IOException
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
				series=hibernate.getRecomendacionesAmigos(nombreUsuario);
				break;
		}
		return series;
	}
	
}
