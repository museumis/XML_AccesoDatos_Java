package Tarea02_EscrituraXml_Libros;

import java.io.Serializable;

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
	private String autor;
	private String editorial;

	// Contructor
	public Libro() {
		// TODO Auto-generated constructor stub
	}

	// Constructor completo
	public Libro(String isbn, String titulo, String autor, String editorial) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
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
		cadena += "\nEmail: " + this.autor;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
}
