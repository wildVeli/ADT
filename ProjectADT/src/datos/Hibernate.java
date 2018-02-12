package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clases.Calendario;
import clases.Contenido;
import clases.Libro;
import clases.Pelicula;
import clases.Serie;
import clasesHibernate.Calendarios;
import clasesHibernate.CalendariosId;
import clasesHibernate.HibernateUtil;
import clasesHibernate.Libros;
import clasesHibernate.LibrosId;
import clasesHibernate.Peliculas;
import clasesHibernate.PeliculasId;
import clasesHibernate.Musica;
import clasesHibernate.MusicaId;
import clasesHibernate.Series;
import clasesHibernate.SeriesId;
import clasesHibernate.Usuarios;

public class Hibernate {

	private static final Logger LOGGER = Logger.getLogger("datos");
	//obtener la sesión actual
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Transaction tx;
	private Session session;
	
	public boolean validarUsuario(String nombreUsuario, String password, String tipoAccion) {
		boolean existe = false;
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		Usuarios usuario = new Usuarios();
		usuario.setId(nombreUsuario);
		usuario.setPassword(password);
		
		String hqlQuery="";
		Query query=null;
		
		if(tipoAccion.equals("registro")){

			hqlQuery = "from Usuarios usu where usu.id = :usuario";
			query = session.createQuery(hqlQuery);
			
			query.setParameter("usuario",nombreUsuario);

		}else{
			
			hqlQuery = "from Usuarios usu where usu.id = :usuario and usu.password = :pass";
			query = session.createQuery(hqlQuery);
			
			query.setParameter("usuario",nombreUsuario);
			query.setParameter("pass",password);
		}
		
		List<Usuarios> lista = query.list();
		if (!lista.isEmpty()) {
			existe=true;
		}
		
		tx.commit();
		session.close();
		return existe;
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


	public ArrayList<Object> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {

		Contenido contenido = new Contenido();
		ArrayList<Object> contenidos = new ArrayList<Object>();
		
		
		String hqlQuery="";
		Query query=null;
		
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		
		
		switch (tipoContenido) {
			
			case "Serie":
				contenido = new Serie();
				
				hqlQuery = "from Series s where s.id.propietario = :usuario";
				System.out.println(hqlQuery);
				query = session.createQuery(hqlQuery);
				
				if (query != null) {
					System.out.println("not null");
				}else {
					System.out.println("null");
				}
				
				query.setParameter("usuario",nombreUsuario);
				//Metemos el resultado en una lista
				List<Series> listas = query.list();
				//Iteramos la lista
				Iterator <Series> ite =listas.iterator();
				while(ite.hasNext()) {
					Series series = (Series) ite.next();
					((Serie)contenido).setNombre(series.getId().getNombre());
					((Serie)contenido).setGenero(series.getGenero());
					((Serie)contenido).setPuntucacion((short) series.getPuntuacion());
					if(series.getRecomendado()==1) {
						((Serie)contenido).setRecomendado(true);
					}else {
						((Serie)contenido).setRecomendado(false);
					}
					((Serie)contenido).setTemporadas(series.getTemporadas());
					contenidos.add(contenido);
					contenido = new Serie();
				}

				break;
			case "Película":
				contenido= new Pelicula();
				
				hqlQuery = "from Peliculas s where s.id.propietario = :usuario";
				query = session.createQuery(hqlQuery);
				
				query.setParameter("usuario",nombreUsuario);
				//Metemos el resultado en una lista
				List <Peliculas >listap = query.list();
				//Iteramos la lista
				Iterator <Peliculas> itep =listap.iterator();
				while(itep.hasNext()) {
					Peliculas peliculas = (Peliculas) itep.next();
					((Pelicula)contenido).setNombre(peliculas.getId().getNombre());
					((Pelicula)contenido).setGenero(peliculas.getGenero());
					((Pelicula)contenido).setPuntucacion((short) peliculas.getPuntuacion());
					if(peliculas.getRecomendado()==1) {
						((Pelicula)contenido).setRecomendado(true);
					}else {
						((Pelicula)contenido).setRecomendado(false);
					}
					((Pelicula)contenido).setDirector(peliculas.getDirector());
					contenidos.add(contenido);
					contenido = new Pelicula();
				}
				break;
			case "Música":
				contenido= new clases.Musica();
				
				hqlQuery = "from Musica s where s.id.propietario = :usuario";
				query = session.createQuery(hqlQuery);
				
				query.setParameter("usuario",nombreUsuario);
				//Metemos el resultado en una lista
				List <Musica>listam = query.list();
				//Iteramos la lista
				Iterator <Musica> item =listam.iterator();
				while(item.hasNext()) {
					Musica musica= (Musica) item.next();
					((clases.Musica)contenido).setNombre(musica.getId().getNombre());
					((clases.Musica)contenido).setGenero(musica.getGenero());
					((clases.Musica)contenido).setPuntucacion((short) musica.getPuntuacion());
					if(musica.getRecomendado()==1) {
						((clases.Musica)contenido).setRecomendado(true);
					}else {
						((clases.Musica)contenido).setRecomendado(false);
					}
					
					((clases.Musica)contenido).setCantante(musica.getCantante());
					((clases.Musica)contenido).setTipo(musica.getTipo());
					
					contenidos.add(contenido);
					contenido = new clases.Musica();
				}
				break;
			case "Libro":
				contenido= new Libro();
				
				hqlQuery = "from Libros s where s.id.propietario = :usuario";
				query = session.createQuery(hqlQuery);
				
				query.setParameter("usuario",nombreUsuario);
				//Metemos el resultado en una lista
				List <Libros >listal = query.list();
				//Iteramos la lista
				Iterator <Libros> itel =listal.iterator();
				while(itel.hasNext()) {
					Libros libros = (Libros) itel.next();
					((Libro)contenido).setNombre(libros.getId().getNombre());
					((Libro)contenido).setGenero(libros.getGenero());
					((Libro)contenido).setPuntucacion((short) libros.getPuntuacion());
					if(libros.getRecomendado()==1) {
						((Libro)contenido).setRecomendado(true);
					}else {
						((Libro)contenido).setRecomendado(false);
					}
					((Libro)contenido).setAutor(libros.getAutor());
					contenidos.add(contenido);
					contenido = new Libro();
				}

				break;
			}
		
		tx.commit();
		session.close();
		return contenidos;
	}

	


	public void borrarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		switch (tipoContenido) {	
			case "Serie":
				Series serie = new Series();
				SeriesId seriesid= new SeriesId();
				seriesid.setNombre(nombreContenido);
				seriesid.setPropietario(nombreUsuario);
				serie = session.get(Series.class, seriesid);
				session.delete(serie);
				break;
			case "Película":
				Peliculas pelicula = new Peliculas();
				PeliculasId peliculasid= new PeliculasId();
				peliculasid.setNombre(nombreContenido);
				peliculasid.setPropietario(nombreUsuario);
				pelicula = session.get(Peliculas.class, peliculasid);
				session.delete(pelicula);
				break;
			case "Música":
				Musica musica = new Musica();
				MusicaId musicaid= new MusicaId();
				musicaid.setNombre(nombreContenido);
				musicaid.setPropietario(nombreUsuario);
				musica = session.get(Musica.class, musicaid);
				session.delete(musica);
				break;
			case "Libro":
				Libros libros = new Libros();
				LibrosId librosid= new LibrosId();
				librosid.setNombre(nombreContenido);
				librosid.setPropietario(nombreUsuario);
				libros = session.get(Libros.class, librosid);
				session.delete(libros);
				break;		
		}

		tx.commit();
		session.close();

	}
	
	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		
		String hqlModif="";
		Query query=null;
		switch (tipoContenido) {
			
			case "Serie":
				hqlModif= "update Series e set recomendado = :nuevoEstado where e.id.propietario = :usuario and e.id.nombre =:nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("nuevoEstado",1);
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",nombreContenido);

				break;
			case "Película":
				hqlModif= "update Peliculas e set recomendado = :nuevoEstado where e.id.propietario = :usuario and e.id.nombre =:nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("nuevoEstado",1);
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",nombreContenido);
				
				break;
			case "Música":
				hqlModif= "update Musica e set recomendado = :nuevoEstado where e.id.propietario = :usuario and e.id.nombre =:nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("nuevoEstado",1);
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",nombreContenido);
				
				break;
			case "Libro":
				hqlModif= "update Libros e set recomendado = :nuevoEstado where e.id.propietario = :usuario and e.id.nombre =:nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("nuevoEstado",1);
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",nombreContenido);
				break;		
		}
		int filasAfectadas = query.executeUpdate();
		System.out.println(filasAfectadas);
		
		tx.commit();
		session.close();

		
	}

	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {

		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		switch (tipoContenido) {
		
		case "Serie":
			SeriesId seriesid = new SeriesId();
			Series series = new Series();
			Serie serie= (Serie) contenido;	
			
			System.out.println(serie.getNombre());
			seriesid.setNombre(serie.getNombre());
			seriesid.setPropietario(nombreUsuario);
			series.setId(seriesid);
			series.setGenero(serie.getGenero());
			series.setPuntuacion(serie.getPuntucacion());
			if(serie.getRecomendado()) {
				series.setRecomendado(1);
			}else {
				series.setRecomendado(0);
			}
			series.setTemporadas(serie.getTemporadas());
			
			session.save(series);

			break;
		case "Película":
			Pelicula peli= (Pelicula) contenido;
			
			PeliculasId peliculasid = new PeliculasId();
			Peliculas peliculas = new Peliculas();
			
			peliculasid.setNombre(peli.getNombre());
			peliculasid.setPropietario(nombreUsuario);
			peliculas.setId(peliculasid);
			peliculas.setGenero(peli.getGenero());
			peliculas.setPuntuacion(peli.getPuntucacion());
			if(peli.getRecomendado()) {
				peliculas.setRecomendado(1);
			}else {
				peliculas.setRecomendado(0);
			}
			peliculas.setDirector((peli.getDirector()));
			
			session.save(peliculas);
				
			break;
		case "Música":
			clases.Musica musica= (clases.Musica) contenido;
			
			MusicaId musicaid = new MusicaId();
			Musica musicas = new Musica();
			
			musicaid.setNombre(musica.getNombre());
			musicaid.setPropietario(nombreUsuario);
			musicas.setId(musicaid);
			musicas.setGenero(musica.getGenero());
			musicas.setPuntuacion(musica.getPuntucacion());
			if(musica.getRecomendado()) {
				musicas.setRecomendado(1);
			}else {
				musicas.setRecomendado(0);
			}
			musicas.setCantante(musica.getCantante());
			musicas.setTipo(musica.getTipo());
			
			session.save(musicas);

			break;
		case "Libro":
			Libro libro= (Libro) contenido;
			
			LibrosId librosid = new LibrosId();
			Libros libros = new Libros();
			
			librosid.setNombre(libro.getNombre());
			librosid.setPropietario(nombreUsuario);
			libros.setId(librosid);
			libros.setGenero(libro.getGenero());
			libros.setPuntuacion(libro.getPuntucacion());
			if(libro.getRecomendado()) {
				libros.setRecomendado(1);
			}else {
				libros.setRecomendado(0);
			}
			libros.setAutor(libro.getAutor());
			
			session.save(libros);

			break;
		}
		
		tx.commit();
		session.close();

	}
	public void modificarContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();
		
		String hqlModif="";
		Query query=null;
		switch (tipoContenido) {
			
			case "Serie":
				hqlModif= "update Series e set e.genero = :gene, e.recomendado = :recomen, e.puntuacion = :puntu,"
						+ " e.temporadas = :tempo where e.id.propietario = :usuario and e.id.nombre =:nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("gene",contenido.getGenero());
				if(contenido.getRecomendado()) {
					query.setParameter("recomen",1);
				}else {
					query.setParameter("recomen",0);
				}
				query.setParameter("puntu",(int)contenido.getPuntucacion());
				query.setParameter("tempo",((Serie)contenido).getTemporadas());
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",contenido.getNombre());
				

				break;
			case "Película":
				hqlModif= "update Peliculas e set e.genero = :gene, e.recomendado = :recomen,  e.puntuacion = :puntu, "
						+ "e.director = :direc where e.id.propietario = :usuario and e.id.nombre =:nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("gene",contenido.getGenero());
				if(contenido.getRecomendado()) {
					query.setParameter("recomen",1);
				}else {
					query.setParameter("recomen",0);
				}
				query.setParameter("puntu",(int)contenido.getPuntucacion());
				query.setParameter("direc",((Pelicula)contenido).getDirector());
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",contenido.getNombre());
				
				break;
			case "Música":
				hqlModif= "update Musica e set e.genero = :gene, e.recomendado = :recomen, e.puntuacion = :puntu, "
						+ "e.cantante = :cant, tipo = :tip where e.id.propietario = :usuario and e.id.nombre =nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("gene",contenido.getGenero());
				if(contenido.getRecomendado()) {
					query.setParameter("recomen",1);
				}else {
					query.setParameter("recomen",0);
				}
				query.setParameter("puntu",(int)contenido.getPuntucacion());
				query.setParameter("cant",((clases.Musica)contenido).getCantante());
				query.setParameter("tip",((clases.Musica)contenido).getTipo());
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",contenido.getNombre());
				break;
			case "Libro":
				hqlModif= "update Libros e set e.genero = :gene, e.recomendado = :recomen, e.puntuacion = :puntu, "
						+ "e.autor = :aut where e.id.propietario = :usuario and e.id.nombre =nombreContenido ";
				query = session.createQuery(hqlModif);
				
				query.setParameter("gene",contenido.getGenero());
				if(contenido.getRecomendado()) {
					query.setParameter("recomen",1);
				}else {
					query.setParameter("recomen",0);
				}
				query.setParameter("puntu",(int)contenido.getPuntucacion());
				query.setParameter("aut",((Libro)contenido).getAutor());
				query.setParameter("usuario",nombreUsuario);
				query.setParameter("nombreContenido",contenido.getNombre());
				break;		
		}
		query.executeUpdate();
		//System.out.println(filasAfectadas);
		
		tx.commit();
		session.close();
		
	}
	public ArrayList<Calendario> getCalendarios(String nombreUsuario) {
		boolean existe = false;
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();

		
		String hqlQuery="";
		Query query=null;
		
		hqlQuery = "from Calendarios cal where cal.id.id = :usuario order by cal.id.dia ";
		query = session.createQuery(hqlQuery);
			
		query.setParameter("usuario",nombreUsuario);

		boolean primero=true;
		String anteriorCalendario="";
		Serie serie=new Serie();
		ArrayList<Serie> series=new ArrayList<Serie>();
		
		Calendario calendario=new Calendario();
		ArrayList<Calendario> calendarios = new ArrayList<Calendario>();
		
		List<Calendarios> lista = query.list();
		Iterator<Calendarios> ite =lista.iterator();
		while(ite.hasNext()) {
			Calendarios calendarioHibernate = (Calendarios) ite.next();
			if(primero) {
				anteriorCalendario=calendarioHibernate.getId().getDia();
				
				primero = false;
			}
			System.out.println("Anterior "+anteriorCalendario);
			String actualCalendario=calendarioHibernate.getId().getDia();
			//Comprobar valores int o string
			serie.setNombre(calendarioHibernate.getId().getSerie());
			
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
		
		tx.commit();
		session.close();
		System.out.println("size in mysql"+calendarios.size());
		return calendarios;
	}
	
	

	public void borrarRegistroCalendario(String nombreUsuario, String dia, String registro) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();


		CalendariosId calendariosid= new CalendariosId();
		calendariosid.setId(nombreUsuario);
		calendariosid.setDia(dia);
		calendariosid.setSerie(registro);
		Calendarios calendario = new Calendarios();
		calendario.setId(calendariosid);
		
		calendario = session.get(Calendarios.class,calendariosid);
		session.delete(calendario);
		tx.commit();
		session.close();


	}
		

	public void nuevoRegistroCalendario(String nombreUsuario, String dia, String registro) {
		//crear la sesión
		session = sessionFactory.openSession();
		//Crear una transacción de la sesión
		Transaction tx = session.beginTransaction();


		CalendariosId calendariosid= new CalendariosId();
		calendariosid.setId(nombreUsuario);
		calendariosid.setDia(dia);
		calendariosid.setSerie(registro);
		Calendarios calendario = new Calendarios();
		calendario.setId(calendariosid);
		
		session.save(calendario);
		tx.commit();
		session.close();
		
	}

	public ArrayList<Serie> getRecomendacionesAmigos(String nombreUsuario) {
		return null;
	}
	
	
}
