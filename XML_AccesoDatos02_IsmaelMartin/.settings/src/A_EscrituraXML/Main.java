package A_EscrituraXML;

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

		// Contacto 1
		Contacto contactosUno = new Contacto(nombres[0], apellidos[0], emails[0], telefonos[0]);		
		Contacto contactosDos = new Contacto(nombres[1], apellidos[1], emails[1], telefonos[1]);		
		Contacto contactosTres = new Contacto(nombres[2], apellidos[2], emails[2], telefonos[2]);	
		Contacto contactosCuatro = new Contacto(nombres[3], apellidos[3], emails[3], telefonos[3]);
		Utilidades.escribirEnFichero(contactosUno);
		Utilidades.escribirEnFichero(contactosDos);
		Utilidades.escribirEnFichero(contactosTres);
		Utilidades.escribirEnFichero(contactosCuatro);

	}

	/**
	 * Metodo para gestionar el programa
	 */
	public static void gestion() {
		String[] menuPrincipal = { " - Menu Principal - ", " 1. Añadir contactos automaticamente", " 2.Leer Contactos",
				" 3.Escribir XML", " 4.Salir." };

		switch (Utilidades.generarMenu(menuPrincipal)) {
		case 1: {
			crearFicheroContactos();
			System.out.println("Contactos creados en un fichero");
			gestion();
			break;
		}
		case 2: {
			Utilidades.leerFichero();
			gestion();
			break;
		}
		case 3: {
			ArrayList<Contacto> contactos = new ArrayList<>();
			contactos = Utilidades.listadoContactos();

			Utilidades.escribirXML(contactos);
			gestion();
			break;
		}
		case 4: {
			System.out.println("Saudos.");
		}
		}

	}// Fin de gestion

	public static void main(String[] args) {
		System.out.println(" - Crear ficheros en XML - ");
		gestion();
	}

}
