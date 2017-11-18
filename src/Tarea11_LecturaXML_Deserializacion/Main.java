package Tarea11_LecturaXML_Deserializacion;
/**
 * 
 * @author Ismael Mart�n
 *
 */
public class Main {
	/**
	 * M�todo para gestionar el programa
	 */
	public static void gestionPrograma() {
		String[] opciones = { " - Menu Principal - ", " 1. Escribir Fichero de objetos"," 5. Salir", };

		switch (XmlFull.generarMenu(opciones)) {
		case 1: {
			XmlFull.leerXML_Deserializacion();
			gestionPrograma();
			break;
		}
		
		case 5: {
			System.out.println("!Saludos!");
			break;
		}

		default:
			System.out.println("Opci�n inexistente");
			gestionPrograma();
			break;
		}
	}// Fin de festion del programa

	public static void main(String[] args) {
		gestionPrograma();
	}

}
