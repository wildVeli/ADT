package clases;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Node;

public class Main {

	public static void main(String [] args) {

		
		File carpeta=new File("./ficherosPrueba");
		if(carpeta.mkdir()) {
			System.out.println("Carpeta creada con exito");
		}
		File fichero=new File("ficherosPrueba/FicheroUsuario");
		Scanner scan=new Scanner(System.in);

		Short option;
		
		do {
			System.out.println("Selecciona opción"
					+ "\n 1-Grabar usuario"
					+ "\n 2-leer Fichero"
					+ "\n 3-modificar fichero"
					+ "\n 4-borrar contenido"
					+ "\n 5-Grabar XML"
					+ "\n 6-leer XML"
					+ "\n 7-Generar html"
					+ "\n 8-Salir");
			option=(short) Utilidades.leerInt(1, 8);
			
			switch(option) {	
				
				case 1:
					grabarUsuarios(fichero,scan);
					break;
				case 2:
					leerFichero(fichero);
					break;
				case 3:
					modificar(fichero,scan);
					break;
				case 4:
					borrar(fichero,scan);
					break;
				case 5:
					grabarXML();
					break;
				case 6:
					leerXML();
					break;
				case 7:
					try {
						generarHTML();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
			}
		}while(option!=8);	
		scan.close();
		
	}


	private static void grabarUsuarios(File fichero, Scanner scan) {
		

		if (fichero.exists()){
			short mas = 1;
			Usuario usuario;
			FileWriter oos;
			
			try {
				oos=new FileWriter(fichero,true);
				while(mas==1) {
					usuario=new Usuario();
					System.out.println("Escribe el usuario");
					usuario.setId(scan.next());
					
					System.out.println("Escribe la contraseña");
					usuario.setPassword(scan.next());
					oos.writeObject(usuario);
					
					System.out.println("¿Desea más?1-Si 2-No");
					mas=scan.nextShort();
				}

				oos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			short mas = 1;
			Usuario usuario;
			FileOutputStream fos;
			ObjectOutputStream oos;
			
			try {
				
				
				fos=new FileOutputStream(fichero);
				oos=new ObjectOutputStream(fos);
				scan=new Scanner(System.in);
				while(mas==1) {
					usuario=new Usuario();
					System.out.println("Escribe el usuario");
					usuario.setId(scan.next());
					
					System.out.println("Escribe la contraseña");
					usuario.setPassword(scan.next());
					oos.writeObject(usuario);
					
					System.out.println("¿Desea más?1-Si 2-No");
					mas=scan.nextShort();
				}

				oos.close();
				fos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		
	}

	private static void leerFichero(File fichero) {
		

		Usuario usuario;
		FileInputStream fis;
		ObjectInputStream ois;
		
		try {
			

			fis=new FileInputStream(fichero);
			ois=new ObjectInputStream(fis);
			
			
			while(true){
				usuario=new Usuario();
				
				try {
					usuario=(Usuario)ois.readObject();
				}catch(EOFException a) {
					break;
				}catch(Exception e) {
					
					e.printStackTrace();
					break;
				}
						
				System.out.println("Usuario: "+usuario.getId());
				System.out.println("Constraseña: "+usuario.getPassword());
			}
			
			ois.close();
			fis.close();
 
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void modificar(File fichero, Scanner scan) {
		

		
		if(fichero.exists()) {


			String usuarioBuscado;
			Short cambiar;
			

			System.out.println("Usuario que desea modificar");
			usuarioBuscado= scan.next();
			
			String nombreF="ficherosPrueba/nuevo";
			File nuevoFichero=new File(nombreF);
			
			FileOutputStream nuevoFos;
			ObjectOutputStream nuevoOos;
			
			Usuario usuario;
			FileInputStream fis;
			ObjectInputStream ois;
			

			
			try {
				
				nuevoFos=new FileOutputStream(nuevoFichero);
				nuevoOos=new ObjectOutputStream(nuevoFos);
				fis=new FileInputStream(fichero);
				ois=new ObjectInputStream(fis);
				
				
				while(true){
					usuario=new Usuario();
					
					try {
						usuario=(Usuario)ois.readObject();
						if(usuario.getId().equals(usuarioBuscado)) {
							System.out.println("Encontrado");
							System.out.println("ID:"+usuario.getId());
							System.out.println("Password:"+usuario.getPassword());
							
							System.out.println("Desea cambiarlo? \n1-Si 2-No");
							cambiar= scan.nextShort();
							if(cambiar==1) {
								System.out.println("Introduzca el nuevo ID");
								usuario.setId(scan.next());
								System.out.println("Introduzca la nueva contraseña");
								usuario.setPassword(scan.next());
							}
							
						}
					}catch(EOFException e1) {

						break;
					}catch(Exception e) {
						break;
					}
						nuevoOos.writeObject(usuario);	

				}

				ois.close();
				nuevoOos.close();
				nuevoFos.close();
				fis.close();
	 
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.gc();
			if(fichero.delete()) {
				nuevoFichero.renameTo(fichero);
			}
			else {
				System.out.println("error al borrar el fichero");
			}
		}else {
			System.out.println("Error, No existe el fichero");
		}
		
	}


	private static void borrar(File fichero, Scanner scan) {

		if(fichero.exists()) {


			Short guardar;
			
			String nombreF="ficherosPrueba/nuevo";
			File nuevoFichero=new File(nombreF);
			
			FileOutputStream nuevoFos;
			ObjectOutputStream nuevoOos;
			
			Usuario usuario;
			FileInputStream fis;
			ObjectInputStream ois;
			
			try {
				
				nuevoFos=new FileOutputStream(nuevoFichero);
				nuevoOos=new ObjectOutputStream(nuevoFos);
				fis=new FileInputStream(fichero);
				ois=new ObjectInputStream(fis);
				
				
				while(true){
					usuario=new Usuario();
					
					try {
						usuario=(Usuario)ois.readObject();
						 
						System.out.println("Encontrado");
						System.out.println("ID:"+usuario.getId());
						System.out.println("Password:"+usuario.getPassword());
							
						System.out.println("Desea conservarlo? \n1-Si 2-No");
						guardar=scan.nextShort();
						if(guardar==1) {						
							nuevoOos.writeObject(usuario);	
						}
					}catch(EOFException e1) {
						
						break;
					}catch(Exception e) {
						break;
					}
						

				}
				ois.close();
				nuevoOos.close();
				nuevoFos.close();
				fis.close();
	 
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.gc();
			fichero.delete();
			nuevoFichero.renameTo(fichero);
		}else {
			System.out.println("Error, No existe el fichero");
		}
		
		
	}


	private static void grabarXML() {
		
		File fichero=new File("ficherosPrueba/FicheroUsuario");
		Usuario usuario = new Usuario();
		FileInputStream fis;
		ObjectInputStream ois;
		
		try {
			

			fis=new FileInputStream(fichero);
			ois=new ObjectInputStream(fis);
			
			DocumentBuilderFactory dBF=DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder dB=dBF.newDocumentBuilder();
					DOMImplementation dI=dB.getDOMImplementation();
					Document doc=dI.createDocument(null,"ADT",null);
					doc.setXmlVersion("1.0");
					try {
						while (true) {
							usuario=new Usuario();
							usuario=(Usuario)ois.readObject();
							Element raiz=doc.createElement("usuario");
							doc.getDocumentElement().appendChild(raiz);
							crearElemento("id",usuario.getId(),raiz,doc);
							crearElemento("password",usuario.getPassword(),raiz,doc);
						}
					}catch(EOFException e) {
						Source source=new DOMSource(doc);
						Result result=new StreamResult(new java.io.File("usuarios.xml"));
						Transformer transformer=TransformerFactory.newInstance().newTransformer();
						transformer.transform(source,result);
					}
					
				}catch(Exception e) {
					System.out.println("Error");
					e.printStackTrace();
				}
				ois.close();
				fis.close();

			}catch(Exception e) {
				e.printStackTrace();
			}
			
	
		
	}

	private static void leerXML() {
		DocumentBuilderFactory dBF=DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder dB=dBF.newDocumentBuilder();
			Document doc=dB.parse(new File("usuarios.xml"));
			doc.getDocumentElement().normalize();
			System.out.println("Usuarios de "+ doc.getDocumentElement().getNodeName());
			NodeList usuarios=doc.getElementsByTagName("usuario");
			for(int i=0;i<usuarios.getLength();i++){
				Node usu=usuarios.item(i);
				if(usu.getNodeType()==Node.ELEMENT_NODE) {
					Element elem=(Element)usu;
					System.out.println("ID:"+getNodo("id",elem)+"");
					System.out.println("PASSWORD:"+getNodo("password",elem)+"\n");
				}
			}
		}catch(Exception e) {
			System.out.println("Error"+e);
			e.printStackTrace();
		}
	}
	
	private static String getNodo (String etiqueta,Element elem) {
		NodeList nodo=elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
		Node valor = (Node)nodo.item(0);
		return valor.getNodeValue();
	}
	private static void crearElemento(String etiqueta,String valor, Element raiz,Document doc) {
		Element element=doc.createElement(etiqueta);
		Text text= doc.createTextNode(valor);
		raiz.appendChild(element);
		element.appendChild(text);
	}
	

	private static void generarHTML() throws IOException {
		String hojaEstilo= "plantilla.xsl";
		String datosAlumnos= "usuarios.xml";
		File pagHTML=new File("mipagina.html");
		
		FileOutputStream os= new FileOutputStream(pagHTML);
		Source estilos =new StreamSource(hojaEstilo);
		Source datos = new StreamSource(datosAlumnos);
		
		Result result = new StreamResult(os);
		
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer(estilos);
				transformer.transform(datos,result);
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		
		os.close();

	}
	
}
