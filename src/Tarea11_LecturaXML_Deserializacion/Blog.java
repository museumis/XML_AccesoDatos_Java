package Tarea11_LecturaXML_Deserializacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Blog implements Serializable {

	private String autor;
	private String titulo;
	private ArrayList<Entrada> entradas;

	public Blog() {
		entradas = new ArrayList<>();
	}

	@Override
	public String toString() {
		String cadena = "\n";
		cadena += this.titulo +" by " + this.autor;
		for (int i = 0; i < entradas.size(); i++) {
			cadena += "\n\t" + this.entradas.get(i);
		}
		return cadena;
	}

	// Get and Set
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(ArrayList<Entrada> entradas) {
		this.entradas = entradas;
	}

	

	

}
