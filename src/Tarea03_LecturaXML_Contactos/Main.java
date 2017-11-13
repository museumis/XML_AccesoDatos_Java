package Tarea03_LecturaXML_Contactos;

import java.util.ArrayList;

import Tarea07_EscrituraXML_STAX_DesdeFicheroSerializado.Contacto;
import Tarea07_EscrituraXML_STAX_DesdeFicheroSerializado.Utilidades;

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
				" 3. Escribir XML", " 4. Leer Fichero XML", " 5.Salir." };

		switch (Utilidades.generarMenu(menuPrincipal)) {
		case 1: {
			crearFicheroContactos();
			System.out.println("Contactos creados en un fichero");
			gestionPrograma();
			break;
		}
		case 2: {
			Utilidades03.leerFichero();
			gestionPrograma();
			break;
		}
		case 3: {
			//LA CLASE UTILIDADES SE HA MEZCLADO CON OTRO PAQUETE,creo. El programa funciona.
			ArrayList<Tarea03_LecturaXML_Contactos.Contacto> contactos = new ArrayList<>();
			contactos = Utilidades03.listadoContactos();

			Utilidades03.escribirXML(contactos);
			gestionPrograma();
			break;
		}
		case 4: {
			Utilidades03.leerXML();
			gestionPrograma();
			break;
		}
		case 5: {
			System.out.println("Saludos.");
			break;
		}
		}

	}// Fin de gestion

	public static void main(String[] args) {
		System.out.println(" - Crear  y Leer ficheros en XML - ");
		gestionPrograma();
	}

}
