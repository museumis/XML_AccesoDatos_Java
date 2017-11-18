package Tarea11_LecturaXML_Deserializacion;

import java.util.ArrayList;

/**
 * 
 * @author Ismael Martín
 *
 * Clase para la utilización de los métodos de Serializacion y Desserialización
 */
public class ListadoObjetos {

	ArrayList<Blog> listado;

	public ListadoObjetos() {
		listado = new ArrayList<>();
	}

	public void addLibro(Blog l) {
		listado.add(l);
	}

	public ArrayList<Blog> getListado() {
		return listado;
	}
}
