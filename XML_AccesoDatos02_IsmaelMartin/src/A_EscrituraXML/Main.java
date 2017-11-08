package A_EscrituraXML;

import java.awt.List;
import java.util.ArrayList;

/**
 * Lee un fichero serializado y escribe un fichero XML
 * 
 * @author Ismael Martin
 *
 */
public class Main {
	public static void crearFicheroContactos() {
		String[] nombres = { "Julio", "Freddy", "Rosa", "Paco" },
				apellidos = { "Iglesias", "Mercury", "de Espa�a", "de Luc�a" },
				emails = { "torero@gmail.com", "queen@god.com", "ot@hotmail.com", "maestro@god.es" },
				telefonos = { "1111111", "2222222", "3333333", "4444444" };

		// Contacto 1
		Contacto contactosUno = new Contacto(nombres[0], apellidos[0], emails[0], telefonos[0]);
		contactosUno.escribirEnFichero();
		// Contacto 2
		Contacto contactosDos = new Contacto(nombres[1], apellidos[1], emails[1], telefonos[1]);
		contactosDos.escribirEnFichero();
		// Contacto 4
		Contacto contactosTres = new Contacto(nombres[2], apellidos[2], emails[2], telefonos[2]);
		contactosTres.escribirEnFichero();
		// Contacto 4
		Contacto contactosCuatro = new Contacto(nombres[3], apellidos[3], emails[3], telefonos[3]);
		contactosCuatro.escribirEnFichero();

		// Depurar contactos
		contactosCuatro.leerFichero();

	}

	public static void main(String[] args) {
		// Crear fichero serializado, comentado para no escribir mas contactos
		// crearFicheroContactos();
		//Objeto que nos permitira jugar con los metodos de la clase
		Contacto apoyo = new Contacto();
		//Obtener listado de los contactos
		ArrayList<Contacto> contactos = new ArrayList<>();
		contactos = apoyo.listadoContactos();
		//Escribir fichero XML
		apoyo.escribirXML(contactos);
		
		
	}

}
