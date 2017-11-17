package datos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import clases.Calendario;
import clases.Contenido;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;

public class DBmongo {




	public boolean validarUsuario(String nombreUsuario, String password, String tipoAccion) {
		boolean existe = false;
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		DBCollection collection = database.getCollection("usuarios");
		
		if(tipoAccion.equals("registro")) {
			DBObject query = new BasicDBObject("_id",nombreUsuario);
			DBCursor cursor = collection.find(query);
			int x = cursor.count();
			if(cursor.count()!=0) {
				existe = true;
			}
		}else {
			DBObject query = new BasicDBObject("_id",nombreUsuario);
			DBCursor cursor = collection.find(query);
			int x = cursor.count();
			if(cursor.count()!=0) {
				if(cursor.one().get("password").equals(password)) {
					existe = true;
				}
			}
		}
		
		
		mongoClient.close();
		return existe;
	}

	public void registrarUsuario(String nombreUsuario, String password) {
		// db.usuarios.insert({_id:"juan",password:"1234",amigos:["Pedro","Isma"],series:['ser1','ser2'],musica:['mus1','mus2'],libros:['libro1','libro2'],peliculas:['peli1','peli2']})
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		DBCollection collection = database.getCollection("usuarios");
		
		List<String> amigos=Arrays.asList("EjemploAmigo");
		DBObject user = new BasicDBObject("_id", nombreUsuario)
		                            .append("password", password)
		                            .append("amigos",amigos);
		collection.insert(user);
		
		collection = database.getCollection("calendarios");
		String [] dias = {"lunes","martes","mi�rcoles","jueves","viernes","s�bado","domingo"};
		for (String dia:dias) {
			List<String> series=Arrays.asList("prueba1","prueba2");
			DBObject calendario = new BasicDBObject("propietario", nombreUsuario)
			                            .append("dia", dia)
			                            .append("series",series);
			collection.insert(calendario);
		}

		mongoClient.close();
		
	}

	public ArrayList<Contenido> getContenidoDeUnTipo(String nombreUsuario, String tipoContenido) {
		// TODO Auto-generated method stub
		return null;
	}

	public void borrarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		String collec = null;
		DBObject query = null;
		switch (tipoContenido) {
		
		case "Serie":
			collec="series";
			break;
		case "Pel�cula":
			System.out.println("entra en pelicula");
			collec="peliculas";
			break;
		case "M�sica":
			collec="musica";
			break;
		case "Libro":
			collec="libros";
			break;
		}
		DBCollection collection = database.getCollection(collec);
		query = new BasicDBObject("propietario",nombreUsuario).append("nombre",nombreContenido);
		collection.remove(query);
		
		mongoClient.close();	
	}

	public void recomendarContenidoSeleccionado(String nombreUsuario, String nombreContenido, String tipoContenido) {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		String collec = null;
		switch (tipoContenido) {
		
		case "Serie":
			collec="series";
			break;
		case "Pel�cula":
			System.out.println("entra en pelicula");
			collec="peliculas";
			break;
		case "M�sica":
			collec="musica";
			break;
		case "Libro":
			collec="libros";
			break;
		}
		DBCollection collection = database.getCollection(collec);
		BasicDBObject query = new BasicDBObject("propietario",nombreUsuario).append("nombre",nombreContenido);
		BasicDBObject set = new BasicDBObject("recomendado",true);
		collection.update(query,new BasicDBObject("$set",set) );
		mongoClient.close();
		
	}
	/**
	 * M�todo que a�adira un nuevo contenido a un usuario
	 * @param nombreUsuario nombre del usuario al cual se le a�adira el nuevo contenido
	 * @param tipoContenido tipo de contenido que se va a guardar, puede contener los valores "Serie", "Pel�cula", "M�sica", "Libro"
	 * @param contenido contenido que se a�adira al usuario
	 */
	public void anadirNuevoContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		String collec = null;
		DBObject query = null;
		switch (tipoContenido) {
		
		case "Serie":
			Serie serie= (Serie) contenido;
			collec="series";
			query = new BasicDBObject("propietario",nombreUsuario).append("nombre",serie.getNombre()).append("genero",serie.getGenero()).
					append("recomendado",serie.getRecomendado()).append("puntuacion", serie.getPuntucacion()).append("temporadas",serie.getTemporadas());
			break;
		case "Pel�cula":
			Pelicula peli= (Pelicula) contenido;
			collec="peliculas";
			query = new BasicDBObject("propietario",nombreUsuario).append("nombre",peli.getNombre()).append("genero",peli.getGenero()).
					append("recomendado",peli.getRecomendado()).append("puntuacion", peli.getPuntucacion()).append("director",peli.getDirector());		
			break;
		case "M�sica":
			Musica musica= (Musica) contenido;
			collec="musica";
			query = new BasicDBObject("propietario",nombreUsuario).append("nombre",musica.getNombre()).append("genero",musica.getGenero()).
					append("recomendado",musica.getRecomendado()).append("puntuacion", musica.getPuntucacion()).append("tipo",musica.getTipo())
					.append("cantante",musica.getCantante());			
			break;
		case "Libro":
			Libro libro= (Libro) contenido;
			collec="libros";
			query = new BasicDBObject("propietario",nombreUsuario).append("nombre",libro.getNombre()).append("genero",libro.getGenero()).
					append("recomendado",libro.getRecomendado()).append("puntuacion", libro.getPuntucacion()).append("autor",libro.getAutor());
			break;
		}
		DBCollection collection = database.getCollection(collec);
		
		DBObject content = query;
		collection.insert(content);
		
		mongoClient.close();
	}

	public void modificarContenido(String nombreUsuario, Contenido contenido, String tipoContenido) {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		String collec = null;
		BasicDBObject set = new BasicDBObject("propietario",nombreUsuario).append("nombre",contenido.getNombre()).append("genero",contenido.getGenero()).
				append("recomendado",contenido.getRecomendado()).append("puntuacion", contenido.getPuntucacion());
		switch (tipoContenido) {
		
		case "Serie":
			Serie serie= (Serie) contenido;
			collec="series";
			set.append("temporadas",serie.getTemporadas());
			break;
		case "Pel�cula":
			Pelicula peli= (Pelicula) contenido;
			collec="peliculas";
			set.append("director",peli.getDirector());		
			break;
		case "M�sica":
			Musica musica= (Musica) contenido;
			collec="musica";
			set.append("tipo",musica.getTipo()).append("cantante",musica.getCantante());			
			break;
		case "Libro":
			Libro libro= (Libro) contenido;
			collec="libros";
			set.append("autor",libro.getAutor());
			break;
		}
		DBCollection collection = database.getCollection(collec);
		BasicDBObject query = new BasicDBObject("propietario",nombreUsuario).append("nombre",contenido.getNombre());
		collection.update(query,new BasicDBObject("$set",set) );
		mongoClient.close();
		
	}

	public Calendario[] getCalendarios(String nombreUsuario, short calendario) {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		DBCollection collection = database.getCollection("calendarios");
		
		DBCursor results = collection.find(new BasicDBObject("name", nombreUsuario), 
                new BasicDBObject("series", 1));
		System.out.println(results.size());
		for (DBObject result : results) {
		    System.out.println(result);
		}
		mongoClient.close();
		return null;
	}

	public void borrarRegistroCalendario(String nombreUsuario, String dia, String registro) {
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		DBCollection collection = database.getCollection("calendarios");
		
		BasicDBObject query = new BasicDBObject("propietario",nombreUsuario).append("dia", dia);
		BasicDBObject update = new BasicDBObject("series","prueba1");
		
		collection.update(query, new BasicDBObject("$pull", update));
		mongoClient.close();
		
	}

	public void nuevoRegistroCalendario(String nombreUsuario, String dia, String registro) {
		
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("contenidos");
		DBCollection collection = database.getCollection("calendarios");
		

		BasicDBObject query = new BasicDBObject("propietario",nombreUsuario).append("dia", dia);
		
		BasicDBObject push = new BasicDBObject();
		push.put("$push",
		  new BasicDBObject("series",registro));
		collection.update(query, push);
		mongoClient.close();
		
		
	}

	public ArrayList<Serie> getRecomendacionesAmigos(String nombreUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
}
