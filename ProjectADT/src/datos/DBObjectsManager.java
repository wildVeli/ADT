package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
	private String dbPort;
	
	/**
	 * Abre un flujo para recoger del archivo config varios parametros y pasarlos a atributos que luego se utilizar�n para conectarse a la base de datos
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
				dbPort=config.getProperty("dbPort");
			} finally {
				if(input != null)
					input.close();
			}
		}
	}
	/**
	 * M�todo para conectarse a la base de datos de SQL
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void connect() throws SQLException, ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@" + dbHost + ":"+dbPort+":"+ dbName;
		con = DriverManager.getConnection(url, dbUserName, dbPassword);
	}
	/**
	 * M�todo para desconectarse de la base de datos
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
	
	//GESTI�N DE USUARIO -----------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que valida si existe un usuario en la base de datos
	 * @param nombreUsuario nombre del usuario que se comprobara
	 * @param password  contrase�a del usuario que se comprobar�
	 * @param tipoAccion puede ser "registro" comprueba si existe el nombre de usuario o "login" comprueba si existe el nombre y contrase�a del usuario
	 * @return devuelve si existe en la base de datos
	 */
	public boolean validarUsuario(String nombreUsuario, String password, String tipoAccion) {
		boolean resultado=true;
		String call;
		CallableStatement cs;
		try {
			this.connect();
			if(tipoAccion.equals("registro")) {
				call = "{ ? = call existeUsuario(?) }";
	            cs = con.prepareCall(call);	            	            
			}else {
				call = "{ ? = call existeUsuarioyPass(?,?) }";
	            cs = con.prepareCall(call);        
	            cs.setString(3,password);
	           
			}
			cs.setQueryTimeout(1800);
			cs.setString(2,nombreUsuario);
            //Utilizar las funciones que devuelvan boolean "java.sql.Types.BOOLEAN " no esta soportado
            //Referencia https://docs.oracle.com/cd/E11882_01/java.112/e16548/apxref.htm#JJDBC28928
            //Debido a eso las funciones retornan integers 1 para true 0 para false
			cs.registerOutParameter(1, java.sql.Types.INTEGER);
			cs.execute();
            
            resultado=cs.getBoolean(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}
	/**
	 * M�todo que registra a un usuario en la base de datos
	 * @param nombreUsuario nombre del usuario que se registra
	 * @param password contrase�a del usuario que se registra
	 */
	public void registrarUsuario(String nombreUsuario, String password) {
		try {
			this.connect();
			String call = "{ call crearUsuario(?,?) }";
			CallableStatement cs = con.prepareCall(call);	            	            
			cs.setQueryTimeout(1800);
			cs.setString(1,nombreUsuario);
			cs.setString(2,password);
			cs.execute();
            
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//CONTENIDOS-------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que obtiene los contenidos de un usuario
	 * @param nombreUsuario nombre del usuario que se buscar�n los contenidos
	 * @param tipoContenido tipo de contenido que se busca
	 * @return devuelve los contenidos de un usuario del tipo especificado
	 */
	public ArrayList<Contenido> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * M�todo que borra un contenido de la base de datos
	 * @param nombreUsuario nombre del usuario al que pertenece dicho contenido
	 * @param nombreContenido nombre del contenido que se borrar�
	 */
	public void borrarContenidoSeleccionado(String nombreUsuario, String nombreContenido) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * M�todo que establece como recomendado un contenido
	 * @param nombreUsuario	nombre del usuario al que pertenece dicho contenido
	 * @param nombreContenido nombre del contenido que se establecera como recomendado
	 */
	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * M�todo que a�adira un nuevo contenido a un usuario
	 * @param nombreUsuario nombre del usuario al cual se le a�adira el nuevo contenido
	 * @param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Pel�cula", "M�sica", "Libro"
	 * @param contenido contenido que se a�adira al usuario
	 */
	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {

		
	}
	/**
	 * M�todo que modificar� un contenido existente de un usuario 
	 * @param nombreUsuario nombre del usuario al que se le modificar� el contenido
	 * @param contenido contenido modificado que se utilizar� para reemplazar los datos del viejo
	 */
	public void modificarContenido(String nombreUsuario, Contenido contenido) {
		// TODO Auto-generated method stub
		
	}
	
	//CALENDARIO--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 *M�todo que recoger� los calendarios de un usuario concreto
	 * @param nombreUsuario usuario del cual se buscar�n los calendarios
	 * @param calendario calendario especifico que se desea 0 es todos, 1 es lunes,2 es martes....7 es domingo
	 * @return devuelve los calendarios del usuario
	 */
	public ArrayList<Calendario> getCalendarios(String nombreUsuario, short calendario) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * M�todo que borra un registro de un calendario en concreto
	 * @param nombreUsuario nombre del usuario al que pertenece el calendario 
	 * @param dia nombre del calendario al cual se le borrara el registro
	 * @param registro que dato se borrar� de el calendario
	 */
	public void borrarRegistroCalendario(String nombreUsuario, String dia, String registro) {
		// TODO Auto-generated method stub
	}
	/**
	 * M�todo que a�ade un registro a un calendario de un usuario
	 * @param nombreUsuario nombre del usuario al que pertenece el calendario
	 * @param dia nombre del calendario al cual se le a�adir� el nuevo registro
	 * @param registro el nuevo dato que se le a�adir� al calendario
	 */
	public void nuevoRegistroCalendario(String nombreUsuario, String dia, String registro) {
		// TODO Auto-generated method stub
	}
	//RECOMENDACIONES------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * M�todo que devuelve una lista de las series en recomendaciones que tienen los amigos del usuario 
	 * @param nombreUsuario nombre del usuario que est� conectado
	 * @return  devuelve una lista de las series recomendadas por los amigos
	 */
	public ArrayList<Serie> getRecomendacionesAmigos(String nombreUsuario) {
		return null;
		// TODO Auto-generated method stub
		
	}
	
}
