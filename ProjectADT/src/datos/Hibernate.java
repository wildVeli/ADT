package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clases.Calendario;
import clases.Contenido;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;
import clasesHibernate.Calendarios;
import clasesHibernate.CalendariosId;
import clasesHibernate.HibernateUtil;
import clasesHibernate.Usuarios;

public class Hibernate {

	private static final Logger LOGGER = Logger.getLogger("datos");
	//obtener la sesión actual
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Transaction tx;
	private Session session;
	
	public boolean validarUsuario(String nombreUsuario, String password, String tipoAccion) {
		//crear la sesión
		Session session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		tx = session.beginTransaction();
		
		

		return true;
	}
	
	public void registrarUsuario(String nombreUsuario, String password) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		Usuarios usuario = new Usuarios();
		usuario.setId(nombreUsuario);
		usuario.setPassword(password);
		
		session.save(usuario);
		tx.commit();
		session.close();

		
	}
/*

	public ArrayList<Object> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {
		
	
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


	}
	
	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {

		
	}
	
	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {


	}
	public void modificarContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {

		

		
	}
	
	public ArrayList<Calendario> getCalendarios(String nombreUsuario) {

	}
	
	*/
//TODO PAGINA LIBRO 165-166 EN ADELANTE
	public void borrarRegistroCalendario(String nombreUsuario, String dia, String registro) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		Usuarios usuario = new Usuarios();

		CalendariosId calendariosid= new CalendariosId();
		calendariosid.setId(nombreUsuario);
		calendariosid.setDia(dia);
		calendariosid.setSerie(registro);
		Calendarios calendario = new Calendarios();
		calendario.setId(calendariosid);
		
		session.delete(calendario);
		tx.commit();
		session.close();


	}
		

	public void nuevoRegistroCalendario(String nombreUsuario, String dia, String registro) {

	}

	public ArrayList<Serie> getRecomendacionesAmigos(String nombreUsuario) {
		return null;
	}
	
	
}
