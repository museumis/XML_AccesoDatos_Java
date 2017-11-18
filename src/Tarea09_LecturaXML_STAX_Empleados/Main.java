package Tarea09_LecturaXML_STAX_Empleados;

/**
 * 
 * @author Ismael Martin
 *
 */
public class Main {

	/**
	 * Metodo para gestionar el programa
	 */
	public static void gestionPrograma() {
		String[] opciones = { "\n -Menú inicial- ", " 1. Escribir xml", " 2. Leer xml",
				" 3. Ver empleado con sueldo superior a 30000", " 4. Salir" };
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
			Utilidades.leerXML_Stax_OrdenarPorSueldo();
			gestionPrograma();
			break;
		}
		case 4: {
			System.out.println("¡Saludos!");
			break;
		}

		default:
			break;
		}
	}// Fin de gestionProgram

	public static void main(String[] args) {
		System.out.println(" - Lectura de Fichero XML");
		gestionPrograma();
	}

}
