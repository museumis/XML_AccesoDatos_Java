package Tarea01_EscrituraXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Utilidades {

	private static final String urlObj = "src\\Tarea01_EscrituraXML\\Contacto.obj";
	private static final String urlXML = "src\\Tarea01_EscrituraXML\\ContactoXML.xml";

	/**
	 * Metodo que escribe un fichero XML con los objetos del fichero
	 * 
	 * @param contacto
	 *            -> listado de contactos
	 */
	public static void escribirXML(ArrayList<Contacto> contactos) {
		try {
			// Preparativos
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();

			DOMImplementation i = b.getDOMImplementation();
			// Raiz <Contactos>
			Document documento = i.createDocument(null, "Contactos", null);
			// <xml version="1.0">
			documento.setXmlVersion("1.0");
			// Por cada contacto
			for (int j = 0; j < contactos.size(); j++) {
				// <persona>
				Element nodo = documento.createElement("persona");
				documento.getDocumentElement().appendChild(nodo);
				// <nombreHijo>contenidoHijo
				crearHijosNodo("nombre", contactos.get(j).getNombre(), nodo, documento);
				crearHijosNodo("apellido", contactos.get(j).getApellido(), nodo, documento);
				crearHijosNodo("email", contactos.get(j).getEmail(), nodo, documento);
				crearHijosNodo("tel�fono", contactos.get(j).getTelefono(), nodo, documento);
				// </nombreHijo>
				// </persona>
			}
			// </Contactos>

			// Creacion del fichero
			Source s = new DOMSource(documento);
			Result result = new StreamResult(new java.io.File(urlXML));
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.transform(s, result);

		} catch (Exception e) {
			System.out.println("Error en la escritura del fichero XML");
			e.printStackTrace();
		}
		System.out.println("Fichero XML creado con �xito.");

	}// Fin de escribir XML

	/**
	 * Metodo que crea los atributos de los nodos que cuelgan de la raiz
	 * 
	 * @param nombreHijo
	 * @param contenidoHijo
	 * @param nodo
	 * @param documento
	 */
	public static void crearHijosNodo(String nombreHijo, String contenidoHijo, Element nodo, Document documento) {
		// <atributo> contacto.atributo </atributo>
		Element e = documento.createElement(nombreHijo);
		Text t = documento.createTextNode(contenidoHijo);
		e.appendChild(t);
		nodo.appendChild(e);
	}

	/**
	 * Metodo para obtener una lista con todos los contacto del fichero
	 */
	public static ArrayList<Contacto> listadoContactos() {
		ArrayList<Contacto> contactos = new ArrayList<>();

		Contacto c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);
			while (true) {
				c = (Contacto) ois.readObject();
				// A�adir contacto a la lista
				contactos.add(c);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("-- Listado de contactos creado con �xito --\n");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer la clase del fichero");
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
		}
		System.out.println("Listado de contactos creado con �xito.");
		return contactos;
	}// Fin del metodo que devuelve un listado de los empleados del fichero.

	/**
	 * Metodo para leer el fichero serializado donde se encuentra el objeto
	 */
	public static void leerFichero() {

		Contacto c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);

			System.out.println("\n--Contactos --");
			while (true) {
				c = (Contacto) ois.readObject();
				System.out.println(c);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("\n --- Fin de lectura ---\n");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer la clase del fichero");
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
		}
	}// Fin de leer fichero

	/**
	 * Escribir fichero serializado con los datos del contacto
	 */
	public static void escribirEnFichero(Contacto c) {
		File fichero = new File(urlObj);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			// Abrir flujos
			if (fichero.exists()) {
				fos = new FileOutputStream(fichero, true);
				oos = new MyObjectOutPutStream(fos);
			} else {
				fos = new FileOutputStream(fichero);
				oos = new ObjectOutputStream(fos);
			}
			
			// Escribir en el fichero
			oos.writeObject(c);

		} catch (FileNotFoundException e) {
			System.out.println("No se encontr� el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se encontr� el fichero");
			e.printStackTrace();
		}
	}// Fin de escribir fichero

	/**
	 * Metodo para generar menus
	 * 
	 * @param opciones
	 *            del menu
	 * @return respuesta
	 */
	public static int generarMenu(String[] opciones) {
		Scanner entrada = new Scanner(System.in);
		int respuesta = -1;
		for (int i = 0; i < opciones.length; i++) {
			System.out.println(opciones[i]);

		}
		System.out.print("Opcion: ");
		try {
			respuesta = entrada.nextInt();
		} catch (Exception e) {
			System.out.println("Opci�n incorrecta.");
			generarMenu(opciones);
		}

		if ((respuesta < 1) || (respuesta > opciones.length)) {
			System.out.println("Opci�n incorrecta.");
			generarMenu(opciones);
		}
		return respuesta;

	}// Fin de generar Menu

}
