package Tarea08_LecturaXML_STAX_Libros;

import Tarea08_LecturaXML_STAX_Libros.Utilidades;

/**
 * 
 * @author Ismael Martin Lectura de ficheros XML con STAX
 *
 */
public class Main {
	/**
	 * Programa gestionar el programa
	 */
	public static void gestionPrograma() {
		String[] opciones = { "\n- Menú Principal", " 1-Crear fichero XML", " 2-Leer Fichero XML", " 3-Salir" };
		switch (Utilidades.generarMenu(opciones)) {
		case 1: {
			Utilidades.escribirXMLSTAX();
			gestionPrograma();
			break;
		}
		case 2: {
			Utilidades.leerXML_Stax();
			gestionPrograma();
			break;
		}
		case 3: {
			System.out.println("¡Saludos!");
			break;
		}
		}

	}// Fin de gestion del programa

	/**
	 * Metodo para iniciar el programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		gestionPrograma();
	}// Fin de main

}
