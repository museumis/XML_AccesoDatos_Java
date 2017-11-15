package Tarea08_LeecturaXML_STAX_Libros;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Ismael M
 *
 */
public class Libro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4302725085116527753L;
	private String isbn;
	private String titulo;
	private ArrayList<String> autores;
	private String editorial;

	// Contructor
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	// Constructor completo
	public Libro(String isbn, String titulo,ArrayList<String> autor, String editorial) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autores = autor;
		this.editorial = editorial;
	}
	
	/*
	 * Informacion de la clase
	 */
	@Override
	public String toString() {
		String cadena = "\n-----------\n";
		cadena += "Nombre: " + this.isbn;
		cadena += "\nApellido: " + this.titulo;
		cadena += "\nEmail: " + this.autores;
		cadena += "\nTelefono: " + this.editorial;

		return cadena;
	}

	//Get and Set
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutor(ArrayList<String> autores) {
		this.autores = autores;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
}

