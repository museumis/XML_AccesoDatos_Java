package Tarea10_EscrituraLectura_XML_Serializacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {
	// Variables de la clase
	final static String urlObj = "src\\Tarea10_EscrituraLectura_XML_Serializacion\\Libros.dat";
	final static String urlXML = "src\\Tarea10_EscrituraLectura_XML_Serializacion\\Libro.xml";

	/**
	 * Método para crear un XML mediate un objeto
	 */
	public static void escribirXML_Serializacion() {
		// Preparativos

		File fichero = new File(urlObj);
		ListadoLibros listado;
		// Lectura del fichero .dat
		try {
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Listado de objetos
			listado = new ListadoLibros();
			try {
				while (true) {
					Libro l = (Libro) ois.readObject();
					listado.addLibro(l);
				}
			} catch (ClassNotFoundException e) {
				ois.close();// e.printStackTrace();
			} catch (EOFException eoe) {
				// eoe.printStackTrace();
			}
			System.out.println("Pensando. . .");
			// Preparativos para la escritura
			XStream xs = new XStream(new DomDriver("UTF-8"));
			// Raiz <biblioteca>
			xs.alias("Biblioteca", ListadoLibros.class);
			// Nodos<libro> atributos... </libro>
			xs.alias("libro", Libro.class);
			xs.addImplicitCollection(ListadoLibros.class, "listado");
			// Omitir el nombr
			xs.omitField(Libro.class, "libro");
			// escribir xml
			xs.toXML(listado, new FileOutputStream(urlXML));
			// Excepciones
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fichero XML creado con éxito");

	}// Fin de escribir XML Serialización

	public static void leerXML_Deserializacion() {
		// Preparativos para la lectura
		XStream xs = new XStream(new DomDriver("UTF-8"));
		//Raiz
		xs.alias("Biblioteca", ListadoLibros.class);
		//Nodos
		xs.alias("libro", Libro.class);
		xs.addImplicitCollection(ListadoLibros.class, "listado");
		ListadoLibros libros =null;
		//Comienzo de la lectura
		try {
			libros = (ListadoLibros) xs.fromXML(new FileInputStream(new File(urlXML)));
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el fichero");
			e.printStackTrace();
		}
		//Obtener listado de los objetos del xml
		ArrayList<Libro>listado = libros.getListado();
		//Mostrar
		Iterator<Libro>i=listado.listIterator();
		while(i.hasNext()) {
			Libro l = (Libro)i.next();
			System.out.println("\tLibro: "+l.getTitulo() + " \t Autor: "+l.getAutor());
		}
		
	}

	/**
	 * Método para gestionar el programa
	 */
	public static void gestionPrograma() {
		String[] opciones = { " - Menu Principal - ", " 1. Escribir Fichero de objetos", " 2. Leer fichero de objetos",
				" 3. Escribir XML", " 4. Leer XML", " 5. Salir", };

		switch (XmlFull.generarMenu(opciones)) {
		case 1: {
			Libro l1 = new Libro("Génesis", "Dios");
			Libro l2 = new Libro("Éxodo", "Moises");
			Libro l3 = new Libro("La biblia", "Jesus");
			XmlFull.escribirObjetoEnFichero(l1, urlObj);
			XmlFull.escribirObjetoEnFichero(l2, urlObj);
			XmlFull.escribirObjetoEnFichero(l3, urlObj);

			gestionPrograma();
			break;
		}
		case 2: {
			XmlFull.leerObjetoFichero(urlObj);
			gestionPrograma();
			break;
		}
		case 3: {
			escribirXML_Serializacion();
			gestionPrograma();
			break;
		}
		case 4: {
			leerXML_Deserializacion();
			gestionPrograma();
			break;
		}
		case 5: {
			System.out.println("!Saludos!");
			break;
		}

		default:
			System.out.println("Opción inexistente");
			gestionPrograma();
			break;
		}
	}// Fin de gestion del programa

	public static void main(String[] args) {
		gestionPrograma();
	}

}
