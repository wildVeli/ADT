package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import clases.Calendario;
import clases.Contenido;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;

public class DBObjectsManager {
	
	private Connection con;
	private PreparedStatement stat;
	private String dbHost;
	private String dbName;
	private String dbUserName;
	private String dbPassword;
	private String dbPort;
	private Statement stmt;
	
	/**
	 * Abre un flujo para recoger del archivo config varios parametros y pasarlos a atributos que luego se utilizarán para conectarse a la base de datos
	 * @throws IOException
	 */
	public DBObjectsManager() throws IOException{
		if(dbHost == null || dbName == null || dbUserName == null || dbPassword == null){
			FileInputStream input = null;
			try{
				input = new FileInputStream("./documents/db.properties");
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
	 * Método para conectarse a la base de datos de SQL
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void connect() throws SQLException, ClassNotFoundException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@" + dbHost + ":"+dbPort+":"+ dbName;
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
	 * Método que registra a un usuario en la base de datos
	 * @param nombreUsuario nombre del usuario que se registra
	 * @param password contraseña del usuario que se registra
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
	 * Método que obtiene los contenidos de un usuario
	 * @param nombreUsuario nombre del usuario que se buscarán los contenidos
	 * @param tipoContenido tipo de contenido que se busca
	 * @return devuelve los contenidos de un usuario del tipo especificado
	 */
	public ArrayList<Object> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {
		try {
			this.connect();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Contenido contenido = new Contenido();
		ArrayList<Object> contenidos = new ArrayList<Object>();
		String tipo = null;
		String query;
		ResultSet rs;
		try {
			
			switch (tipoContenido) {
	
			case "Serie":
				contenido = new Serie();
				tipo = "seriesAni";
				query = "select a.* from usuarios u, table("+tipo+") a where lower(u.id) like lower('"+nombreUsuario+"')";
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);
					//stat = con.prepareStatement(query);
					//rs = stat.executeQuery();
					while (rs.next()) {
						fillCommonContent(contenido,rs);
						((Serie)contenido).setTemporadas(rs.getInt("temporadas"));
						contenidos.add(contenido);
						contenido = new Serie();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				break;
			case "Película":
				contenido = new Pelicula();
				tipo = "peliculasAni";
				query = "select a.* from usuarios u, table("+tipo+") a where lower(u.id) like lower('"+nombreUsuario+"')";
				try {
					stat = con.prepareStatement(query);
					rs = stat.executeQuery();
					while (rs.next()) {
						fillCommonContent(contenido,rs);
						((Pelicula)contenido).setDirector(rs.getString("director"));
						contenidos.add(contenido);
						
						contenido=new Pelicula();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Música":
				contenido = new Musica();
				tipo = "musicaAni";
				query = "select a.* from usuarios u, table("+tipo+") a where lower(u.id) like lower('"+nombreUsuario+"')";
				try {
					stat = con.prepareStatement(query);
					rs = stat.executeQuery();
					while (rs.next()) {
						fillCommonContent(contenido,rs);
						((Musica)contenido).setTipo(rs.getString("tipo"));
						((Musica)contenido).setCantante(rs.getString("cantante"));
						contenidos.add(contenido);
						contenido= new Musica();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Libro":
				contenido = new Libro();
				tipo = "librosAni";
				query = "select a.* from usuarios u, table("+tipo+") a where lower(u.id) like lower('"+nombreUsuario+"')";
				try {
					stat = con.prepareStatement(query);
					rs = stat.executeQuery();
					while (rs.next()) {
						
						fillCommonContent(contenido,rs);
						((Libro)contenido).setAutor(rs.getString("autor"));
						contenidos.add(contenido);
						contenido= new Libro();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			break;
			}

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			this.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return contenidos;
	}
	private void fillCommonContent(Contenido contenido, ResultSet rs) {
		try {
			contenido.setNombre(rs.getString("nombre"));
			contenido.setGenero(rs.getString("genero"));
			System.out.println(rs.getInt("recomendado"));
			if(rs.getInt("recomendado")==1) {
				contenido.setRecomendado(true);
			}else {
				contenido.setRecomendado(false);
			}
			contenido.setPuntucacion((short)rs.getInt("puntuacion"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	/**
	 * Método que borra un contenido de la base de datos
	 * @param nombreUsuario nombre del usuario al que pertenece dicho contenido
	 * @param nombreContenido nombre del contenido que se borrará
	 */
	public void borrarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {
		try {
			this.connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tipo = null;
		String query = null;
		switch (tipoContenido) {	
			case "Serie":
				tipo = "seriesAni";
				break;
			case "Película":
				tipo = "peliculasAni";
				break;
			case "Música":
				tipo = "musicaAni";
				break;
			case "Libro":
				tipo = "librosAni";
				break;		
		}
		
			query= "DELETE from table (select "+tipo+" from usuarios where lower(id) like lower('"+nombreUsuario+"')) contenido where lower(contenido.nombre) like lower('"+nombreContenido+"')";
		try {
			stat = con.prepareStatement(query);
			stat.executeUpdate();
			this.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Método que establece como recomendado un contenido
	 * @param nombreUsuario	nombre del usuario al que pertenece dicho contenido
	 * @param nombreContenido nombre del contenido que se establecera como recomendado
	 */
	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido,String tipoContenido) {
		try {
			this.connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tipo = null;
		String query = null;
		switch (tipoContenido) {	
			case "Serie":
				tipo = "seriesAni";
				break;
			case "Película":
				tipo = "peliculasAni";
				break;
			case "Música":
				tipo = "musicaAni";
				break;
			case "Libro":
				tipo = "librosAni";
				break;		
		}
		
			query= "UPDATE table (select "+tipo+" from usuarios where lower(id) like lower('"+nombreUsuario+"')) contenido set contenido.recomendado = 0 where lower(contenido.nombre) like lower('"+nombreContenido+"')";
		try {
			stat = con.prepareStatement(query);
			stat.executeUpdate();
			this.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * Método que añadira un nuevo contenido a un usuario
	 * @param nombreUsuario nombre del usuario al cual se le añadira el nuevo contenido
	 * @param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Película", "Música", "Libro"
	 * @param contenido contenido que se añadira al usuario
	 */
	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {

		
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
	 * @return devuelve los calendarios del usuario
	 */
	public ArrayList<Calendario> getCalendarios(String nombreUsuario) {
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
