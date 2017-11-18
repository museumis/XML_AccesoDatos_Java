package XML_FULL;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author Ismael Martín Ramírez
 * https://museumis.github.io/Si/
 *
 * Clase que lee y escribe ficheros XML mediante DOM, STAX y serialización de
 * objetos.
 * 
 * Nota: Esta clase esta realizada apartir de varios ejemplos,retócala a tu
 * gusto. Nota: Los comentario que inician con *** deberán ser implementados
 * para utilizar clase. 
 * Nota: Se indicará como 'Object' los atributos que
 * necesitan de un objeto para ejecutar,cambialo al necesario. Nota: El objeto
 * que implemente esta clase debe tener el método toString() implementado.
 * Nota:Para utilizar los métodos de serialización y deserialización debe
 * importarse un jar Project/properties/Java Build Path/add External
 * Jar/xstream-1.4.8(XML_FULL.Librerias)(https://x-stream.github.io/download.html
 * ("Binary distribution"))
 * 
 */
public class XmlFull {

	// Ruta de los ficheros
	// private static final String urlObj =
	// "src\\Tarea09_LecturaXML_STAX_Empleados\\empleados.dat";
	// private static final String urlXML =
	// "src\\Tarea09_LecturaXML_STAX_Empleados\\empleados.xml";
	private static final String urlObj = "";
	private static final String urlXML = "";

	public XmlFull() {
		// vacío
	}

	/**
	 * Método para crear un XML mediate un objeto
	 */
	public static void escribirXML_Serializacion() {
		// Preparativos

		File fichero = new File(urlObj);
		ListadoObjetos listado;
		// Lectura del fichero .dat
		// **try {
		// ** FileInputStream fis = new FileInputStream(fichero);
		// **ObjectInputStream ois = new ObjectInputStream(fis);
		// Listado de objetos
		listado = new ListadoObjetos();
		// **try {
		// ***while (true) {
		// *** Libro l = (Libro) ois.readObject();
		// *** listado.addLibro(l);
		// *** }
		// *** } catch (ClassNotFoundException e) {
		// *** ois.close();// e.printStackTrace();
		// ***} catch (EOFException eoe) {eoe.printStackTrace();
		// *** }
		System.out.println("Pensando. . .");
		// Preparativos para la escritura
		XStream xs = new XStream(new DomDriver("UTF-8"));
		// Raiz <biblioteca>
		xs.alias("Biblioteca", ListadoObjetos.class);
		// Nodos<libro> atributos... </libro>
		// *** xs.alias("libro", Libro.class);
		xs.addImplicitCollection(ListadoObjetos.class, "listado");
		// Omitir el nombr
		// *** xs.omitField(Libro.class, "libro");
		// escribir xml
		// ** xs.toXML(listado, new FileOutputStream(urlXML));
		// Excepciones
		// **} catch (IOException e) {e.printStackTrace();
		// **}
		// ***System.out.println("Fichero XML creado con éxito");

	}// Fin de escribir XML Serialización

	/**
	 * Método para deseliarizar un XML y crear un objeto
	 */
	public static void leerXML_Deserializacion() {
		// Preparativos para la lectura
		XStream xs = new XStream(new DomDriver("UTF-8"));
		// Raiz
		xs.alias("Biblioteca", ListadoObjetos.class);
		// Nodos
		// ***xs.alias("libro", Libro.class);
		xs.addImplicitCollection(ListadoObjetos.class, "listado");
		ListadoObjetos libros = null;
		// Comienzo de la lectura
		try {
			libros = (ListadoObjetos) xs.fromXML(new FileInputStream(new File(urlXML)));
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el fichero");
			e.printStackTrace();
		}
		// Obtener listado de los objetos del xml
		// ***ArrayList<Libro>listado = libros.getListado();
		// Mostrar
		// ***Iterator<Libro>i=listado.listIterator();
		// ***while(i.hasNext()) {
		// *** Libro l = (Libro)i.next();
		// *** System.out.println("\tLibro: "+l.getTitulo() + " \t Autor:
		// "+l.getAutor());
		// *** }

	}// Fin de deserializar

	/**
	 * Leer fichero XML mediate STAX
	 */

	public static void leerXML_Stax() {
		int event = 0;
		try {
			// preparativos
			ArrayList<Object> listado = new ArrayList<>();
			// ***Libro libro = null;
			XMLInputFactory f = XMLInputFactory.newInstance();
			XMLStreamReader r = f.createXMLStreamReader(new FileReader(urlXML));

			// Lectura
			while (r.hasNext()) {
				event = r.next();

				if (event == XMLStreamConstants.START_ELEMENT) {
					String nodo = r.getLocalName();
					switch (nodo) {
					case "libro": {
						// *** libro = new Libro();
						// ***libro.setIsbn(r.getAttributeLocalName(0));
						break;
					}
					case "titulo": {
						// ***libro.setTitulo(r.getElementText());
						break;
					}

					case "autor": {
						// ***libro.anadirAutor(r.getElementText());
						break;
					}
					case "editorial": {
						// ***libro.setEditorial(r.getElementText());
						break;
					}
					}

				}
				// *** if ((event == XMLStreamConstants.END_ELEMENT) && (r.getLocalName() ==
				// "libro")) {
				// *** listado.add(libro);
				// ***}
			} // Fin while

			// Mostrar
			for (int i = 0; i < listado.size(); i++) {
				System.out.println(listado.get(i));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// Fin de leer xmlStax

	// ********************************
	// Principales
	// *******************************
	/**
	 * Metodo que escribe un fichero XML mediante STAX desde un fichero serializado
	 */
	public static void escribirXML_STAX_DesdeFicheroSerializado(ArrayList<Object> libros) {

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
	public static void escribirXML_STAX() {

		try {
			// Preparativo
			FileOutputStream fos = new FileOutputStream(new File(urlXML));
			XMLOutputFactory f = XMLOutputFactory.newInstance();
			XMLStreamWriter bufferEscritura = f.createXMLStreamWriter(fos, "UTF-8");
			// Escritura fichero

			// <Catalogo>
			bufferEscritura.writeStartDocument();
			bufferEscritura.writeStartElement("catalogo");
			// <libro>
			bufferEscritura.writeStartElement("libro");
			bufferEscritura.writeAttribute("isbn", "0001");
			// <titulo>
			bufferEscritura.writeStartElement("titulo");
			bufferEscritura.writeCharacters("Acceso a Datos");
			bufferEscritura.writeEndElement();
			// </titulo>
			// <autores>
			bufferEscritura.writeStartElement("autores");
			// <autor>
			bufferEscritura.writeStartElement("autor");
			bufferEscritura.writeCharacters("Anónimo");
			bufferEscritura.writeEndElement();
			// </autor>

			// <autor>
			bufferEscritura.writeStartElement("autor");
			bufferEscritura.writeCharacters("Bufer Printer");
			bufferEscritura.writeEndElement();
			// </autor>
			bufferEscritura.writeEndElement();
			// </autores>
			// <editorial>
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
	 * @param litado -> listado de libros
	 */
	public static void escribirXML_DOM(ArrayList<Object> litado) {
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
				// ***nodo.setAttribute("isbn", (litado.get(j)).getIsbn());
				documento.getDocumentElement().appendChild(nodo);

				// <nombreHijo>contenidoHijo
				// *** crearHijosNodo("titulo", litado.get(j).getTitulo(), nodo, documento);
				// crearHijosNodo("autor", litado.get(j).getAutor(), nodo, documento);
				// *** crearHijosNodo("editorial", litado.get(j).getEditorial(), nodo,
				// documento);
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
	public static void leerXML_DOM() {
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
	public static ArrayList<Object> listadoObjetosFichero() {
		ArrayList<Object> objetos = new ArrayList<>();

		// ***Libro c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);
			while (true) {
				// *** c = (Libro) ois.readObject();
				// Añadir objeto a la lista
				// *** objetos.add(c);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("-- Listado de contactos creado con éxito --\n");
			// ***} catch (ClassNotFoundException e) {
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
	public static void leerObjetoFichero() {

		// *** Libro c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream(urlObj);
			ois = new ObjectInputStream(fis);

			System.out.println("\n--Catalogo --");
			while (true) {
				// *** c = (Libro) ois.readObject();
				// *** System.out.println(c);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("\n --- Fin de lectura ---\n");
			// ***} catch (ClassNotFoundException e) {
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
	public static void escribirObjetoEnFichero(Object c) {
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

	// ********************************
	// Derivados
	// *******************************

	/**
	 * Lee fichero XML mediate STAX y muestra los empleados ordenados por sueldo
	 */

	public static void leerXML_Stax_OrdenarPorSueldo() {
		int event = 0;
		try {
			// preparativos
			ArrayList<Object> listado = new ArrayList<>();
			// ***Empleado empleado = null;
			XMLInputFactory f = XMLInputFactory.newInstance();
			XMLStreamReader r = f.createXMLStreamReader(new FileReader(urlXML));

			// Lectura
			while (r.hasNext()) {
				event = r.next();

				if (event == XMLStreamConstants.START_ELEMENT) {
					String nodo = r.getLocalName();
					switch (nodo) {
					case "empleado": {
						break;
					}
					case "persona": {
						// *** empleado = new Empleado();
						break;
					}
					case "nombre": {
						// ***empleado.setNombre(r.getElementText());
						break;
					}
					case "sueldo": {
						// ***empleado.setSalario(Integer.parseInt(r.getElementText()));
						break;
					}

					}

				}
				// ***if ((event == XMLStreamConstants.END_ELEMENT) && (r.getLocalName() ==
				// "persona")) {
				// ***listado.add(empleado);
				// ***}
			} // Fin while

			// Ordenar listado
			// ***Collections.sort(listado);
			for (int i = 0; i < listado.size(); i++) {
				System.out.println(" - " + listado.get(i).toString());

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// Fin de leer xmlStax
		// ********************************
		// Utilidades
		// ********************************

	/**
	 * Metodo para generar menus
	 * 
	 * @param opciones del menu
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
