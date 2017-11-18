package Tarea11_LecturaXML_Deserializacion;

import java.util.ArrayList;

/**
 * 
 * @author Ismael Mart�n
 *
 * Clase para la utilizaci�n de los m�todos de Serializacion y Desserializaci�n
 */
public class ListadoObjetos {

	ArrayList<Object> listado;

	public ListadoObjetos() {
		listado = new ArrayList<>();
	}

	public void addLibro(Object l) {
		listado.add(l);
	}

	public ArrayList<Object> getListado() {
		return listado;
	}
}
