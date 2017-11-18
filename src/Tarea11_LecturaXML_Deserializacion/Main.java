package Tarea11_LecturaXML_Deserializacion;
/**
 * 
 * @author Ismael Mart�n Ram�rez
 * https://museumis.github.io/Si/
 *
 */
public class Main {
	/**
	 * M�todo para gestionar el programa
	 */
	public static void gestionPrograma() {
		String[] opciones = { " - Menu Principal - ", " 1. Leer XML"," 2. Salir", };
		String urlObj ="src\\Tarea11_LecturaXML_Deserializacion\\Blog.xml";
		switch (XmlFull.generarMenu(opciones)) {
		case 1: {
			XmlFull.leerXML_Deserializacion(urlObj);
			gestionPrograma();
			break;
		}
		
		case 2: {
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
