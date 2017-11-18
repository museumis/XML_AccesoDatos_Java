package Tarea11_LecturaXML_Deserializacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Blog implements Serializable {

	private String nombre;
	private ArrayList<Entrada>entradas;
	
	public Blog() {
		// vacío
	}

	//Get and Set
		public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}
	
	
}
