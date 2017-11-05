package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import clases.Calendario;
import clases.Contenido;
import clases.Serie;

public class DBObjectsManager {
	
	private Connection con;
	private PreparedStatement stat;
	private String dbHost;
	private String dbName;
	private String dbUserName;
	private String dbPassword;
	
	/**
	 * Abre un flujo para recoger del archivo config varios parametros y pasarlos a atributos que luego se utilizarán para conectarse a la base de datos
	 * @throws IOException
	 */
	public DBObjectsManager() throws IOException{
		if(dbHost == null || dbName == null || dbUserName == null || dbPassword == null){
			FileInputStream input = null;
			try{
				input = new FileInputStream("./db.properties");
				Properties config = new Properties();
				config.load(input);
				dbHost = config.getProperty("ip");
				dbName = config.getProperty("dbname");
				dbUserName = config.getProperty("username");
				dbPassword = config.getProperty("password");
			} finally {
				if(input != null)
					input.close();
			}
		}
	}
	/**
	 * Método para conectarse a la base de datos de SQL
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void connect() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + dbHost + "/" + dbName;
		con = DriverManager.getConnection(url, dbUserName, dbPassword);
	}
	/**
	 * Método para desconectarse de la base de datos
	 * @throws SQLException
	 */
	private void disconnect() throws SQLException {
		if(stat != null){
			stat.close();
		}
		if (con != null){
			con.close();
		}
	}
	
	//ALTAS,BAJAS,MODIFICACIONES Y CONSULTAS-------------------------------------------------------------------------------------------------------
	
	//GESTIÓN DE USUARIO -----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que valida si existe un usuario en la base de datos
	 * @param nombreUsuario nombre del usuario que se comprobara
	 * @param password  contraseña del usuario que se comprobará
	 * @param tipoAccion puede ser "registro" comprueba si existe el nombre de usuario o "login" comprueba si existe el nombre y contraseña del usuario
	 * @return devuelve si existe en la base de datos
	 */
	public boolean validarUsuario(String nombreUsuario, String password, String tipoAccion) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Método que registra a un usuario en la base de datos
	 * @param nombreUsuario nombre del usuario que se registra
	 * @param password contraseña del usuario que se registra
	 */
	public void registrarUsuario(String nombreUsuario, String password) {
		// TODO Auto-generated method stub
		
	}
	//CONTENIDOS-------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que obtiene los contenidos de un usuario
	 * @param nombreUsuario nombre del usuario que se buscarán los contenidos
	 * @param tipoContenido tipo de contenido que se busca
	 * @return devuelve los contenidos de un usuario del tipo especificado
	 */
	public ArrayList<Contenido> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Método que borra un contenido de la base de datos
	 * @param nombreUsuario nombre del usuario al que pertenece dicho contenido
	 * @param nombreContenido nombre del contenido que se borrará
	 */
	public void borrarContenidoSeleccionado(String nombreUsuario, String nombreContenido) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Método que establece como recomendado un contenido
	 * @param nombreUsuario	nombre del usuario al que pertenece dicho contenido
	 * @param nombreContenido nombre del contenido que se establecera como recomendado
	 */
	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Método que añadira un nuevo contenido a un usuario
	 * @param nombreUsuario nombre del usuario al cual se le añadira el nuevo contenido
	 * @param contenido contenido que se añadira al usuario
	 */
	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Método que modificará un contenido existente de un usuario 
	 * @param nombreUsuario nombre del usuario al que se le modificará el contenido
	 * @param contenido contenido modificado que se utilizará para reemplazar los datos del viejo
	 */
	public void modificarContenido(String nombreUsuario, Contenido contenido) {
		// TODO Auto-generated method stub
		
	}
	
	//CALENDARIO--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 *Método que recogerá los calendarios de un usuario concreto
	 * @param nombreUsuario usuario del cual se buscarán los calendarios
	 * @param calendario calendario especifico que se desea 0 es todos, 1 es lunes,2 es martes....7 es domingo
	 * @return devuelve los calendarios del usuario
	 */
	public Calendario[] getCalendarios(String nombreUsuario, short calendario) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Método que borra un registro de un calendario en concreto
	 * @param nombreUsuario nombre del usuario al que pertenece el calendario 
	 * @param dia nombre del calendario al cual se le borrara el registro
	 * @param registro que dato se borrará de el calendario
	 */
	public void borrarRegistroCalendario(String nombreUsuario, String dia, String registro) {
		// TODO Auto-generated method stub
	}
	/**
	 * Método que añade un registro a un calendario de un usuario
	 * @param nombreUsuario nombre del usuario al que pertenece el calendario
	 * @param dia nombre del calendario al cual se le añadirá el nuevo registro
	 * @param registro el nuevo dato que se le añadirá al calendario
	 */
	public void nuevoRegistroCalendario(String nombreUsuario, String dia, String registro) {
		// TODO Auto-generated method stub
	}
	//RECOMENDACIONES------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * Método que devuelve una lista de las series en recomendaciones que tienen los amigos del usuario 
	 * @param nombreUsuario nombre del usuario que está conectado
	 * @return  devuelve una lista de las series recomendadas por los amigos
	 */
	public ArrayList<Serie> getRecomendacionesAmigos(String nombreUsuario) {
		return null;
		// TODO Auto-generated method stub
		
	}
	
}
