package Tarea11_LecturaXML_Deserializacion;

import java.io.Serializable;

/**
 * 
 * @author Ismael Martín Ramírez
 * https://museumis.github.io/Si/
 */
public class Entrada implements Serializable {

	//Variables de la clase
	private String titulo;
	private String descripcion;

	//Constructores
	public Entrada() {
		// TODO 
	}

	public Entrada(String t, String d) {
		this.titulo = t;
		this.descripcion = d;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Información de la clase
	 */
	@Override
	public String toString() {
		return this.titulo + " -> " + this.descripcion;
	}

}
