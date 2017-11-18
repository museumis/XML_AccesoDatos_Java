package Tarea10_EscrituraLectura_XML_Serializacion;

import java.util.ArrayList;

public class ListadoLibros {

	ArrayList<Libro> listado;

	public ListadoLibros() {
		listado = new ArrayList<>();
	}

	public void addLibro(Libro l) {
		listado.add(l);
	}

	public ArrayList<Libro> getListado() {
		return listado;
	}
}
