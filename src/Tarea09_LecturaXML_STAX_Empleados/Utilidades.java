package Tarea09_LecturaXML_STAX_Empleados;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class Utilidades {

	private static final String urlXML = "src\\Tarea09_LecturaXML_STAX_Empleados\\empleados.xml";

	/**
	 * Lee fichero XML mediate STAX y muestra los empleados ordenados por sueldo
	 */

	public static void leerXML_Stax_OrdenarPorSueldo() {
		int event = 0;
		try {
			// preparativos
			ArrayList<Empleado> listado = new ArrayList<>();
			Empleado empleado = null;
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
						empleado = new Empleado();
						break;
					}
					case "nombre": {
						empleado.setNombre(r.getElementText());
						break;
					}
					case "sueldo": {
						empleado.setSalario(Integer.parseInt(r.getElementText()));
						break;
					}

					}

				}
				if ((event == XMLStreamConstants.END_ELEMENT) && (r.getLocalName() == "persona")) {
					listado.add(empleado);
				}
			} // Fin while

				//Ordenar listado
			Collections.sort(listado);
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

	/**
	 * Leer fichero XML mediate STAX
	 */

	public static void leerXML_Stax() {
		int event = 0;
		try {
			// preparativos
			ArrayList<Empleado> listado = new ArrayList<>();
			Empleado empleado = null;
			XMLInputFactory f = XMLInputFactory.newInstance();
			XMLStreamReader r = f.createXMLStreamReader(new FileReader(urlXML));

			// Lectura
			while (r.hasNext()) {
				event = r.next();

				if (event == XMLStreamConstants.START_ELEMENT) {
					String nodo = r.getLocalName();
					switch (nodo) {
					case "empleado": {
						// empleado = new Empleado();
						break;
					}
					case "persona": {
						empleado = new Empleado();
						break;
					}
					case "nombre": {
						empleado.setNombre(r.getElementText());
						break;
					}
					case "sueldo": {
						empleado.setSalario(Integer.parseInt(r.getElementText()));
						break;
					}

					}

				}
				if ((event == XMLStreamConstants.END_ELEMENT) && (r.getLocalName() == "persona")) {
					listado.add(empleado);
				}
			} // Fin while

			// Mostrar
			for (int i = 0; i < listado.size(); i++) {
				System.out.println(listado.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// Fin de leer xmlStax

	public static void escribirXMLSTAX() {

		try {
			// Preparativo
			FileOutputStream fos = new FileOutputStream(new File(urlXML));
			XMLOutputFactory f = XMLOutputFactory.newInstance();
			XMLStreamWriter bufferEscritura = f.createXMLStreamWriter(fos, "UTF-8");
			// Escritura fichero

			// <Empleado>
			bufferEscritura.writeStartDocument();
			bufferEscritura.writeStartElement("empleado");

			// <persona>
			bufferEscritura.writeStartElement("persona");
			// <nombre>
			bufferEscritura.writeStartElement("nombre");
			bufferEscritura.writeCharacters("Torrente");
			bufferEscritura.writeEndElement();
			// </nombre>// <sueldo>
			bufferEscritura.writeStartElement("sueldo");
			bufferEscritura.writeCharacters("2000");
			bufferEscritura.writeEndElement();
			// </sueldo>
			bufferEscritura.writeEndElement();
			// </persona>

			// <persona>
			bufferEscritura.writeStartElement("persona");
			// <nombre>
			bufferEscritura.writeStartElement("nombre");
			bufferEscritura.writeCharacters("Lara Croft");
			bufferEscritura.writeEndElement();
			// </nombre>// <sueldo>
			bufferEscritura.writeStartElement("sueldo");
			bufferEscritura.writeCharacters("1500");
			bufferEscritura.writeEndElement();
			// </sueldo>
			bufferEscritura.writeEndElement();
			// </persona>

			// <persona>
			bufferEscritura.writeStartElement("persona");
			// <nombre>
			bufferEscritura.writeStartElement("nombre");
			bufferEscritura.writeCharacters("SonGoanda");
			bufferEscritura.writeEndElement();
			// </nombre>// <sueldo>
			bufferEscritura.writeStartElement("sueldo");
			bufferEscritura.writeCharacters("3100");
			bufferEscritura.writeEndElement();
			// </sueldo>
			bufferEscritura.writeEndElement();
			// </persona>

			// </Empleado>
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
