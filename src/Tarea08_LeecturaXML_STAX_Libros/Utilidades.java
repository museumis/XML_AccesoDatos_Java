package Tarea08_LeecturaXML_STAX_Libros;

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
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
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

public class Utilidades {

	private static final String urlObj = "src\\Tarea07_EscrituraXML_STAX_DesdeFicheroSerializado\\Agenda.obj";
	private static final String urlXML = "src\\Tarea07_EscrituraXML_STAX_DesdeFicheroSerializado\\Agenda.xml";

	/**
	 * Leer fichero XML mediate STAX
	 */

	public static void leerXML_Stax() {
		
	}// Fin de leer xmlStax

	/**
	 * Metodo que escribe un fichero XML mediante STAX desde un fichero serializado
	 */
	public static void escribirXMLSTAX_DesdeFicheroSerializado(ArrayList<Object> libros) {

		try {
			// Preparativo
			FileOutputStream fos = new FileOutputStream(new File(urlXML));
			XMLOutputFactory f = XMLOutputFactory.newInstance();
			XMLStreamWriter bufferEscritura = f.createXMLStreamWriter(fos, "UTF-8");
			// Escritura fichero

			// <Agenda>
			bufferEscritura.writeStartDocument();
			bufferEscritura.writeStartElement("Agenda");

			for (int i = 0; i < libros.size(); i++) {
				// <persona>
				bufferEscritura.writeStartElement("persona");
				// bufferEscritura.writeAttribute("id", "0001");
				// <nombre>
				bufferEscritura.writeStartElement("nombre");
				// bufferEscritura.writeCharacters(contactos.get(i).getNombre());
				bufferEscritura.writeEndElement();
				// </nombre>
				// <apellido>
				bufferEscritura.writeStartElement("apellido");
				// bufferEscritura.writeCharacters(contactos.get(i).getApellido());
				bufferEscritura.writeEndElement();
				// </autor>
				// <email>
				bufferEscritura.writeStartElement("email");
				// bufferEscritura.writeCharacters(contactos.get(i).getEmail());
				bufferEscritura.writeEndElement();
				// </email>
				// <telefono>
				bufferEscritura.writeStartElement("teléfono");
				// bufferEscritura.writeCharacters(contactos.get(i).getTelefono());
				bufferEscritura.writeEndElement();
				// </telefono>

				// </persona>
				bufferEscritura.writeEndElement();

			}

			// </Agenda>
			bufferEscritura.writeEndDocument();

			bufferEscritura.flush();
			bufferEscritura.close();

			System.out.println("Fichero escrito con éxito.");

		} catch (FileNotFoundException e) {
			System.out.println("El ficero XML no se encontró");// e.printStackTrace();
		} catch (XMLStreamException e) {
			System.out.println("Error en el flujo de escritura");// e.printStackTrace();
		}

	}// Fin de escritura STAX

	/**
	 * Metodo que escribe un fichero XML mediante STAX
	 */
	public static void escribirXMLSTAX() {

		try {
			// Preparativo
			FileOutputStream fos = new FileOutputStream(new File(urlXML));
			XMLOutputFactory f = XMLOutputFactory.newInstance();
			XMLStreamWriter bufferEscritura = f.createXMLStreamWriter(fos, "UTF-8");
			// Escritura fichero

			// <Catalogo>
			bufferEscritura.writeStartDocument();
			bufferEscritura.writeStartElement("Catálogo");
			// <libro>
			bufferEscritura.writeStartElement("libro");
			bufferEscritura.writeAttribute("isbn", "0001");
			// <titulo>
			bufferEscritura.writeStartElement("titulo");
			bufferEscritura.writeCharacters("Acceso a Datos");
			bufferEscritura.writeEndElement();
			// </titulo>
			// <autor>
			System.out.println(" - Introduce los autores de la celestina.\n");
			do {
				bufferEscritura.writeStartElement("autor");
				bufferEscritura.writeCharacters(pedirTexto("Autor: "));
				bufferEscritura.writeEndElement();
			} while (pedirTexto("\n - ¿Desea añadir más autores?[si/intro]: ").equals("si"));
			// </autor>
			// <Editorial>
			bufferEscritura.writeStartElement("editorial");
			bufferEscritura.writeCharacters("tragicomedia");
			bufferEscritura.writeEndElement();
			// </editorial>
			// </libro>
			bufferEscritura.writeEndElement();
			// </Catalogo>
			bufferEscritura.writeEndDocument();

			bufferEscritura.flush();
			bufferEscritura.close();

			System.out.println("Fichero escrito con éxito.");

		} catch (FileNotFoundException e) {
			System.out.println("El ficero XML no se encontró");// e.printStackTrace();
		} catch (XMLStreamException e) {
			System.out.println("Error en el flujo de escritura");// e.printStackTrace();
		}

	}// Fin de escritura STAX

	/**
	 * Metodo que escribe un fichero XML mediante DOM con los objetos del fichero
	 * 
	 * @param litado
	 *            -> listado de libros
	 */
	public static void escribirXML(ArrayList<Object> litado) {
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
			for (int j = 0; j < litado.size(); j++) {
				// <libro>
				Element nodo = documento.createElement("libro");
				// nodo.setAttribute("isbn", ((Object) litado.get(j)).getIsbn());
				documento.getDocumentElement().appendChild(nodo);

				// <nombreHijo>contenidoHijo
				// crearHijosNodo("titulo", litado.get(j).getTitulo(), nodo, documento);
				// crearHijosNodo("autor", litado.get(j).getAutor(), nodo, documento);
				// crearHijosNodo("editorial", litado.get(j).getEditorial(), nodo, documento);
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
	 * Metodo para la lectura de ficheros XML mediante DOM
	 */
	public static void leerXML() {
		try {
			// Preparativos
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document documento = b.parse(new File(urlXML));
			documento.getDocumentElement().normalize();
			// Lectura e impresion por pantalla
			System.out.println("<" + documento.getDocumentElement().getNodeName() + ">");
			NodeList listado = documento.getElementsByTagName("libro");
			for (int i = 0; i < listado.getLength(); i++) {
				Node libro = listado.item(i);
				Element valor = (Element) libro;
				System.out.println("\n<" + valor.getNodeName() + " isbn=" + valor.getAttribute("isbn") + ">");
				System.out.println("<titulo>" + verNodo("titulo", valor) + "</titulo>");
				System.out.println("<autor>" + verNodo("autor", valor) + "</autor>");
				System.out.println("<titulo>" + verNodo("titulo", valor) + "</titulo>");
				System.out.println("</" + valor.getNodeName() + ">");
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
	 * Metodo para obtener una lista con todos los objetos del fichero
	 */
	public static ArrayList<Libro> listadoObjetosFichero() {
		ArrayList<Libro> objetos = new ArrayList<>();

		Libro c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);
			while (true) {
				c = (Libro) ois.readObject();
				// Añadir objeto a la lista
				objetos.add(c);
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
		return objetos;
	}// Fin del metodo que devuelve un listado de los objetos del fichero.

	/**
	 * Metodo para leer el fichero serializado donde se encuentra el objeto
	 */
	public static void leerFichero() {

		Libro c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);

			System.out.println("\n--Catalogo --");
			while (true) {
				c = (Libro) ois.readObject();
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
	 * Escribir fichero serializado con los datos del objeto
	 */
	public static void escribirEnFichero(Object c) {
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
		System.out.println("Fichero serializado creado/modificado con éxito");
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

	/**
	 * Metodo para obtener texto por teclado
	 * 
	 * @param pregunta
	 * @return respuesta
	 */
	public static String pedirTexto(String pregunta) {
		Scanner entrada = new Scanner(System.in);
		System.out.print(pregunta);
		return entrada.nextLine();
	}// Fin de pedir texto

}
