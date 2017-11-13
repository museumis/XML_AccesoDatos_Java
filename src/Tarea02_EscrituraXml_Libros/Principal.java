package Tarea02_EscrituraXml_Libros;


import java.util.ArrayList;

/**
 * Lee un fichero serializado y escribe un fichero XML
 * @author Ismael Martin
 *
 */
public class Principal {

	/**
	 * Metodo para gestionar el funcionamiento del programa
	 */
	public static void gestionPrograma() {
		String[] opciones = { "\n- Menú Principal", " 1- Añadir libros a un fichero serializado",
				" 2-Ver fichero serilizado", " 3-Crear fichero XML", " 4-Salir" };
		switch (Utilidades.generarMenu(opciones)) {
		case 1: {
			Libro uno = new Libro("00001", "Lazarillo de tormes", "Anónimo", "NovelaPicaresca");
			Libro dos = new Libro("00002", "El Quijote", "Cervantes", "Planeta");
			Libro tres = new Libro("00003", "La celestina", "F. Rojas", "Tragicomedia");
			Utilidades.escribirEnFichero(uno);
			Utilidades.escribirEnFichero(dos);
			Utilidades.escribirEnFichero(tres);

			gestionPrograma();
			break;
		}
		case 2: {
			Utilidades.leerFichero();
			
			gestionPrograma();
			break;
		}
		case 3: {
			ArrayList<Libro> libros = new ArrayList<>();
			libros = Utilidades.listadoLibros();
			Utilidades.escribirXML(libros);
			
			gestionPrograma();
			break;
		}
		case 4: {
			System.out.println("¡Saludos!");
			break;
		}
		}

	}//Fin de gestion del programa

	public static void main(String[] args) {
		System.out.println(" - Escritura de XML - ");
		gestionPrograma();
	}

}
