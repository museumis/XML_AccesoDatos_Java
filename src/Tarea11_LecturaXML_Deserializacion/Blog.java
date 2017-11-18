package Tarea11_LecturaXML_Deserializacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Blog implements Serializable {

	private String nombre;
	private ArrayList<Entrada>entradas;
	
	public Blog() {
		// vacío
	}
	
}
