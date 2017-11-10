package C_LecturaXML_Contactos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//imports para la escritura, mediante DOM, de xml
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class Contacto implements Serializable {

	/**
	 * Serial y variables de la clase
	 */
	private static final long serialVersionUID = 6661969314517933842L;
	private String nombre, apellido, email, telefono;

	// Constructor vacio
	public Contacto() {
	}

	// Contructor completo
	public Contacto(String nombre, String apellido, String email, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}// Fin de constructor completo

	
	/*
	 * Informacion de la clase
	 */
	@Override
	public String toString() {
		String cadena = "\n-----------\n";
		cadena += "Nombre: " + this.nombre;
		cadena += "\nApellido: " + this.apellido;
		cadena += "\nEmail: " + this.email;
		cadena += "\nTelefono: " + this.telefono;

		return cadena;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
