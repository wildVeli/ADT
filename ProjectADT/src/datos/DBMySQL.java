package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.Connection;

import clases.Calendario;
import clases.Contenido;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;

public class DBMySQL {

	private java.sql.Connection con;
	private PreparedStatement stat;
	private String dbHost;
	private String dbName;
	private String dbUserName;
	private String dbPassword;

	
		/**
	 * Abre un flujo para recoger del archivo config varios parametros y pasarlos a atributos que luego se utilizarï¿½n para conectarse a la base de datos
	 * @throws IOException
	 */
	public DBMySQL() throws IOException{
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
			} finally {
				if(input != null)
					input.close();
			}
		}
	}
	
		/**
	 * Mï¿½todo para conectarse a la base de datos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void connect() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://" + dbHost + "/" + dbName;
		con = DriverManager.getConnection(url, dbUserName, dbPassword);
	}
	/*
	private void connect() {
		
		String url = "jdbc:mysql://" + dbHost + ":"+dbPort+"/"+ dbName;
				
		//String url = "jdbc:mysql://localhost:3306/javabase";

		System.out.println("Connecting database...");

		try  {
			con = DriverManager.getConnection(url, dbUserName, dbPassword);
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	*/
	/**
	 * Mï¿½todo para conectarse a la base de datos
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
	
	public boolean validarUsuario(String nombreUsuario, String password, String tipoAccion) {
		boolean existe = false;
		ResultSet rs = null;

		try {
			this.connect();
			if(tipoAccion.equals("registro")){
				String sql = "SELECT count(*) FROM usuarios WHERE lower(id) like ?";
				stat = con.prepareStatement(sql);
				stat.setString(1, nombreUsuario);
				rs = stat.executeQuery();

			}else{
				String sql = "SELECT count(*) as total FROM usuarios WHERE lower(id) like ? and lower(password) like ?";
				stat = con.prepareStatement(sql);
				stat.setString(1, nombreUsuario);
				stat.setString(2, password);
				rs = stat.executeQuery();
			}
			
			if (rs.next()) {
				if(rs.getInt(1)>=1){
					System.out.println(rs.getInt(1));
					existe = true;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return existe;
	}
	
	public void registrarUsuario(String nombreUsuario, String password) {
		try {
			this.connect();
			String sql = "INSERT INTO usuarios values (?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1,nombreUsuario);
			stat.setString(2,password);
			stat.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	public ArrayList<Object> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {
		
		Serie serie;
		Pelicula pelicula;
		Musica musica;
		Libro libro;
		Contenido contenido = new Contenido();
		ArrayList<Object> contenidos = new ArrayList<Object>();
		ResultSet rs = null;
		String sql;
		
		try {
			this.connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		switch (tipoContenido) {
			
			case "Serie":
				contenido = new Serie();
				sql = "SELECT * FROM series WHERE lower(propietario) like ?";
				try {
					stat = con.prepareStatement(sql);
					stat.setString(1, nombreUsuario);
					rs = stat.executeQuery();										
					while (rs.next()) {
						fillCommonContent(contenido,rs);
						((Serie)contenido).setTemporadas(rs.getInt("temporadas"));
						contenidos.add(contenido);
						contenido = new Serie();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "Película":
				contenido= new Pelicula();
				sql = "SELECT * FROM peliculas WHERE lower(propietario) like ?";
				try {
					stat = con.prepareStatement(sql);
					stat.setString(1, nombreUsuario);
					rs = stat.executeQuery();	
					while (rs.next()) {
						fillCommonContent(contenido,rs);
						((Pelicula)contenido).setDirector(rs.getString("director"));
						contenidos.add(contenido);
						
						contenido=new Pelicula();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "Música":
				contenido= new Musica();
				sql = "SELECT * FROM musica WHERE lower(propietario) like ?";
				try {
					stat = con.prepareStatement(sql);
					stat.setString(1, nombreUsuario);
					rs = stat.executeQuery();	
					while (rs.next()) {
						fillCommonContent(contenido,rs);
						((Musica)contenido).setTipo(rs.getString("tipo"));
						((Musica)contenido).setCantante(rs.getString("cantante"));
						contenidos.add(contenido);
						contenido= new Musica();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "Libro":
				contenido= new Libro();
				sql = "SELECT * FROM libros WHERE lower(propietario) like ?";
				try {
					stat = con.prepareStatement(sql);
					stat.setString(1, nombreUsuario);
					rs = stat.executeQuery();	
					while (rs.next()) {
						
						fillCommonContent(contenido,rs);
						((Libro)contenido).setAutor(rs.getString("autor"));
						contenidos.add(contenido);
						contenido= new Libro();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
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
		String sql = null;
		switch (tipoContenido) {	
			case "Serie":
				sql = "DELETE FROM series WHERE lower(propietario) like lower(?) and lower(nombre) like lower(?)";
				break;
			case "Película":
				sql = "DELETE FROM peliculas WHERE lower(propietario) like lower(?) and lower(nombre) like  lower(?)";
				break;
			case "Música":
				sql = "DELETE FROM musica WHERE lower(propietario) like lower(?)  and lower(nombre) like  lower(?)";
				break;
			case "Libro":
				sql = "DELETE FROM libros WHERE lower(propietario) like lower(?)  and lower(nombre) like  lower(?)";
				break;		
		}
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1, nombreUsuario);
			stat.setString(2, nombreContenido);	
			stat.executeUpdate();
			this.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {
		try{
			this.connect();
			String sql = null;
			switch (tipoContenido) {
			
				case "Serie":
					sql = "UPDATE series set recomendado = 1 where lower(propietario) like ? and lower(nombre) like ?";
					break;
				case "Película":
					sql = "UPDATE peliculas set recomendado = 1 where lower(propietario) like ? and lower(nombre) like ?";
					break;
				case "Música":
					sql = "UPDATE musica set recomendado = 1 where lower(propietario) like ? and lower(nombre) like ?";
					break;
				case "Libro":
					sql = "UPDATE libros set recomendado = 1 where lower(propietario) like ? and lower(nombre) like ?";
					break;		
			}
			stat = con.prepareStatement(sql);
			stat.setString(1, nombreUsuario);
			stat.setString(2, nombreContenido);
			stat.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {
		try {
			this.connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch (tipoContenido) {
		
		case "Serie":
			Serie serie= (Serie) contenido;	
			try {
				String sql = "INSERT INTO series values (?,?,?,?,?,?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, nombreUsuario);
				stat.setString(2, serie.getNombre());
				stat.setString(3, serie.getGenero());
				stat.setBoolean(4, serie.getRecomendado());
				stat.setInt(5, serie.getPuntucacion());
				stat.setInt(6, serie.getTemporadas());
				stat.executeUpdate();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					this.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		case "Película":
			Pelicula peli= (Pelicula) contenido;
			try {
				String sql = "INSERT INTO peliculas values (?,?,?,?,?,?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, nombreUsuario);
				stat.setString(2, peli.getNombre());
				stat.setString(3, peli.getGenero());
				stat.setBoolean(4, peli.getRecomendado());
				stat.setInt(5, peli.getPuntucacion());
				stat.setString(6, peli.getDirector());
				stat.executeUpdate();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					this.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}					
			break;
		case "Música":
			Musica musica= (Musica) contenido;
			try {
				String sql = "INSERT INTO musica values (?,?,?,?,?,?,?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, nombreUsuario);
				stat.setString(2, musica.getNombre());
				stat.setString(3, musica.getGenero());
				stat.setBoolean(4, musica.getRecomendado());
				stat.setInt(5, musica.getPuntucacion());
				stat.setString(6, musica.getTipo());
				stat.setString(7, musica.getCantante());
				stat.executeUpdate();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					this.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
			break;
		case "Libro":
			Libro libro= (Libro) contenido;
			try {
				String sql = "INSERT INTO libros values (?,?,?,?,?,?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, nombreUsuario);
				stat.setString(2, libro.getNombre());
				stat.setString(3, libro.getGenero());
				stat.setBoolean(4, libro.getRecomendado());
				stat.setInt(5, libro.getPuntucacion());
				stat.setString(6, libro.getAutor());
				stat.executeUpdate();
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					this.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
			break;
		}

	}
	public void modificarContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {
		String sql = null;
		try {
			this.connect();
			switch (tipoContenido) {
			
			case "Serie":
				Serie serie= (Serie) contenido;
				sql ="UPDATE series SET genero=?,recomendado=?,puntuacion=?,temporadas=? WHERE lower(propietario) like lower(?) and lower(nombre) like lower(?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, serie.getGenero());
				stat.setBoolean(2, serie.getRecomendado());
				stat.setInt(3, serie.getPuntucacion());
				stat.setInt(4,serie.getTemporadas());
				stat.setString(5, nombreUsuario);
				stat.setString(6, serie.getNombre());
				System.out.println(serie.getGenero());
				break;
			case "Película":
				Pelicula peli= (Pelicula) contenido;
				sql ="UPDATE peliculas SET genero=?,recomendado=?,puntuacion=?,director=? WHERE lower(propietario) like lower(?) and lower(nombre) like lower(?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, peli.getGenero());
				stat.setBoolean(2, peli.getRecomendado());
				stat.setInt(3, peli.getPuntucacion());
				stat.setString(4,peli.getDirector());
				stat.setString(5, nombreUsuario);
				stat.setString(6, peli.getNombre());
				break;
			case "Música":
				Musica musica= (Musica) contenido;
				sql ="UPDATE musica SET genero=?,recomendado=?,puntuacion=?,tipo=?,cantante=? WHERE lower(propietario) like lower(?) and lower(nombre) like lower(?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, musica.getGenero());
				stat.setBoolean(2, musica.getRecomendado());
				stat.setInt(3, musica.getPuntucacion());
				stat.setString(4,musica.getTipo());
				stat.setString(5, musica.getCantante());
				stat.setString(6, nombreUsuario);
				stat.setString(7, musica.getNombre());
				break;
			case "Libro":
				Libro libro= (Libro) contenido;
				sql ="UPDATE libros SET genero=?,recomendado=?,puntuacion=?,autor=? WHERE lower(propietario) like lower(?) and lower(nombre) like lower(?)";
				stat = con.prepareStatement(sql);
				stat.setString(1, libro.getGenero());
				stat.setBoolean(2, libro.getRecomendado());
				stat.setInt(3, libro.getPuntucacion());
				stat.setString(4,libro.getAutor());
				stat.setString(5, nombreUsuario);
				stat.setString(6, libro.getNombre());
				break;
			}
			stat.executeUpdate();
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
	public ArrayList<Calendario> getCalendarios(String nombreUsuario) {
		Serie serie=new Serie();
		ArrayList<Serie> series=new ArrayList<Serie>();
		
		Calendario calendario=new Calendario();
		ArrayList<Calendario> calendarios = new ArrayList<Calendario>();
		ResultSet rs = null;

		try{
			this.connect();
			String sql = "select dia,serie from calendarios where lower(id) like lower(?) order by dia";
			stat = con.prepareStatement(sql);
			stat.setString(1, nombreUsuario);
			rs = stat.executeQuery();
			boolean primero=true;
			String anteriorCalendario="";
			
			while(rs.next()) {
				if(primero) {
					anteriorCalendario=rs.getString("dia");
					
					primero = false;
				}
				System.out.println("Anterior "+anteriorCalendario);
				String actualCalendario=rs.getString("dia");
				//Comprobar valores int o string
				serie.setNombre(rs.getString("serie"));
				
				if(anteriorCalendario.equals(actualCalendario)) {
					series.add(serie);
					serie = new Serie();
				}else {
					calendario.setSeries(series);
					System.out.println(anteriorCalendario);
					calendario.setDia(anteriorCalendario);
					calendarios.add(calendario);
					calendario = new Calendario();
					series = new ArrayList<Serie>();
					series.add(serie);
					serie = new Serie();
					
				}
				anteriorCalendario=actualCalendario;
			}
			calendario.setSeries(series);
			calendario.setDia(anteriorCalendario);
			calendarios.add(calendario);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				this.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("size in mysql"+calendarios.size());
		return calendarios;
	}
	

	public void borrarRegistroCalendario(String nombreUsuario, String dia, String registro) {
		try {
			this.connect();
			String sql = "DELETE FROM calendarios where lower(id) like lower(?) and lower(dia) like lower(?) and lower(serie) like lower(?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, nombreUsuario);
			stat.setString(2, dia);
			stat.setString(3, registro);
			
			stat.executeUpdate();
			this.disconnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
		

	public void nuevoRegistroCalendario(String nombreUsuario, String dia, String registro) {
		try {
			this.connect();
			String sql = "insert into calendarios values (?,?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, nombreUsuario);
			stat.setString(2, dia);
			stat.setString(3, registro);
			stat.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}

	public ArrayList<Serie> getRecomendacionesAmigos(String nombreUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
