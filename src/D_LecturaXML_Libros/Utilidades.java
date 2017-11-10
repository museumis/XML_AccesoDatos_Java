package D_LecturaXML_Libros;

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
import javax.xml.parsers.ParserConfigurationException;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import A_EscrituraXML.MyObjectOutPutStream;

public class Utilidades {

	private static final String urlObj = "src\\D_LecturaXml_Libros\\Libros.obj";
	private static final String urlXML = "src\\D_LecturaXml_Libros\\LibrosXML.xml";

	/**
	 * Metodo que escribe un fichero XML con los objetos del fichero
	 * 
	 * @param libros
	 *            -> listado de libros
	 */
	public static void escribirXML(ArrayList<Libro> libros) {
		try {
			// Preparativos
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();

			DOMImplementation i = b.getDOMImplementation();
			// Raiz <Catalogo>
			Document documento = i.createDocument(null, "Catalogo", null);
			// <xml version="1.0">
			documento.setXmlVersion("1.0");
			// Por cada contacto
			for (int j = 0; j < libros.size(); j++) {
				// <libro>
				Element nodo = documento.createElement("libro");
				nodo.setAttribute("isbn", libros.get(j).getIsbn());
				documento.getDocumentElement().appendChild(nodo);

				// <nombreHijo>contenidoHijo
				crearHijosNodo("titulo", libros.get(j).getTitulo(), nodo, documento);
				crearHijosNodo("autor", libros.get(j).getAutor(), nodo, documento);
				crearHijosNodo("editorial", libros.get(j).getEditorial(), nodo, documento);
				// </nombreHijo>
				// </libro>
			}
			// </Catalogo>

			// Creacion del fichero
			Source s = new DOMSource(documento);
			Result result = new StreamResult(new java.io.File(urlXML));
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.transform(s, result);

		} catch (Exception e) {
			System.out.println("Error en la escritura del fichero XML");
			e.printStackTrace();
		}
		System.out.println("Fichero XML creado con éxito.");

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
	 * Metodo para la lectura de ficheros XML
	 */
	public static void leerXML() {
		try {
			// Preparativos
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document documento = b.parse(new File(urlXML));
			documento.getDocumentElement().normalize();
			// Lectura e impresion por pantalla
			System.out.println("<" + documento.getDocumentElement().getNodeName() +">");
			NodeList listado = documento.getElementsByTagName("libro");
			for (int i = 0; i < listado.getLength(); i++) {
				Node libro = listado.item(i);
				Element valor = (Element) libro;
				System.out.println("\n<"+valor.getNodeName()+" isbn="+valor.getAttribute("isbn") +">");
				System.out.println("<titulo>" + verNodo("titulo", valor) + "</titulo>");
				System.out.println("<autor>" + verNodo("autor", valor) + "</autor>");
				System.out.println("<titulo>" + verNodo("titulo", valor) + "</titulo>");
				System.out.println("</"+valor.getNodeName()+">");
			}

			System.out.println("</" + documento.getDocumentElement().getNodeName() + ">");

		} catch (ParserConfigurationException e) {
			System.out.println("Error al configurar la lectura del fichero");// e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("El fichero XML esta mal construido.");// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// Fin de leer fichero XML

	/**
	 * Metodo para ver los atributos de un nodo
	 */
	public static String verNodo(String atributo, Element valor) {
		Node nodo = valor.getElementsByTagName(atributo).item(0).getFirstChild();
		return nodo.getTextContent();
	}// Fin de ver nodo

	/**
	 * Metodo para obtener una lista con todos los libros del fichero
	 */
	public static ArrayList<Libro> listadoLibros() {
		ArrayList<Libro> libros = new ArrayList<>();

		Libro l = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);
			while (true) {
				l = (Libro) ois.readObject();
				// Añadir libro a la lista
				libros.add(l);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("-- Listado de contactos creado con éxito --\n");
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
		return libros;
	}// Fin del metodo que devuelve un listado de los libros del fichero.

	/**
	 * Metodo para leer el fichero serializado donde se encuentra el objeto
	 */
	public static void leerFichero() {

		Libro l = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);

			System.out.println("\n--Catalogo --");
			while (true) {
				l = (Libro) ois.readObject();
				System.out.println(l);
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
	 * Escribir fichero serializado con los datos de os libros
	 */
	public static void escribirEnFichero(Libro c) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File fichero = new File(urlObj);

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
			System.out.println("No se encontró el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se encontró el fichero");
			e.printStackTrace();
		}
		System.out.println("Fichero serializado creado con éxito");
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
			System.out.println("Opción incorrecta.");
			generarMenu(opciones);
		}

		if ((respuesta < 1) || (respuesta > opciones.length)) {
			System.out.println("Opción incorrecta.");
			generarMenu(opciones);
		}
		return respuesta;

	}// Fin de generar Menu

}
