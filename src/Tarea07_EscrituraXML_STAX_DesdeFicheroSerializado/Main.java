package Tarea07_EscrituraXML_STAX_DesdeFicheroSerializado;

import java.util.ArrayList;


/**
 * Lee un fichero serializado y escribe un fichero XML
 * 
 * @author Ismael Martin
 *
 */
public class Main {
	
	/**
	 * Metodo para crear contactos
	 */
	public static void crearFicheroContactos() {
		String[] nombres = { "Julio", "Freddy", "Rosa", "Paco" },
				apellidos = { "Iglesias", "Mercury", "de España", "de Lucía" },
				emails = { "torero@gmail.com", "queen@god.com", "ot@hotmail.com", "maestro@god.es" },
				telefonos = { "1111111", "2222222", "3333333", "4444444" };
		// Contactos
		for (int i = 0; i < nombres.length; i++) {
			Contacto contacto = new Contacto(nombres[i], apellidos[i], emails[i], telefonos[i]);		
			Utilidades.escribirEnFichero(contacto);
		}	
	}

	/**
	 * Metodo para gestionar el programa
	 */
	public static void gestionPrograma() {
		String[] menuPrincipal = { " - Menú Principal - ", " 1. Añadir contactos automaticamente", " 2. Leer Contactos",
				" 3. Escribir XML_STAX", " 4.Salir." };

		switch (Utilidades.generarMenu(menuPrincipal)) {
		case 1: {
			crearFicheroContactos();
			gestionPrograma();
			break;
		}
		case 2: {
			Utilidades.leerFichero();
			gestionPrograma();
			break;
		}
		case 3: {
			ArrayList<Contacto> contactos = new ArrayList<>();
			contactos = Utilidades.listadoObjetosFichero();

			Utilidades.escribirXMLSTAX_DesdeFicheroSerializado(contactos);
			gestionPrograma();
			break;
		}
		case 4: {
			System.out.println("Saludos.");
			break;
		}
		}

	}// Fin de gestion

	public static void main(String[] args) {
		System.out.println(" - Crear  y Leer ficheros en XML con STAX - ");
		gestionPrograma();
	}

}
