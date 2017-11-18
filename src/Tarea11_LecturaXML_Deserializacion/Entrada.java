package Tarea11_LecturaXML_Deserializacion;

import java.io.Serializable;

public class Entrada implements Serializable {

	private String titulo;
	private String descripcion;

	public Entrada() {
		// vacío
	}

	public Entrada(String t, String d) {
		this.titulo = t;
		this.descripcion = d;
	}

	@Override
	public String toString() {
		return this.titulo + " -> " + this.descripcion;
	}

}
