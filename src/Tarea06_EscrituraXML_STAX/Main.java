package Tarea06_EscrituraXML_STAX;

public class Main {

	public static void gestionPrograma() {
		String[] opciones = { " - Menu Principal -", " 1. Añadir un objeto al XML", " 2. Salir" };
		switch (Utilidades.generarMenu(opciones)) {
		case 1: {
			Utilidades.escribirXMLSTAX();
			gestionPrograma();
			break;
		}
		case 2: {
			System.out.println("¡Saludos!");
			break;
		}

		default:
			break;
		}

	}

	public static void main(String[] args) {
		System.out.println("-Escritura de XML con STAX");
		gestionPrograma();
	}

}
