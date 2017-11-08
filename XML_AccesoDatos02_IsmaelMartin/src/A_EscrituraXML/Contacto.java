package A_EscrituraXML;

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

	/**
	 * Metodo que escribe un fichero XML con los objetos del fichero
	 * 
	 * @param contacto
	 *            -> listado de contactos
	 */
	public void escribirXML(ArrayList<Contacto> contactos) {
		try {
			// Preparativos
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			
			DOMImplementation i = b.getDOMImplementation();
			//Raiz <Contactos> 
			Document documento = i.createDocument(null,"Contactos", null) ;
			//<xml version="1.0">
			documento.setXmlVersion("1.0");	
			// Por cada contacto
			for (int j = 0; j <= contactos.size(); j++) {
				//<persona>  
				Element nodo = documento.createElement("persona");
				documento.getDocumentElement().appendChild(nodo);
				//<nombreHijo>contenidoHijo
				crearHijosNodo("nombre",contactos.get(j).getNombre(), nodo, documento);
				crearHijosNodo("apellido",contactos.get(j).getApellido(), nodo, documento);
				crearHijosNodo("email",contactos.get(j).getEmail(), nodo, documento);
				crearHijosNodo("teléfono",contactos.get(j).getTelefono(), nodo, documento);
				//</nombreHijo>
				//</persona>
			}
			//</Contactos>
		
			//Creacion del fichero
			Source s =new DOMSource(documento);
			Result result = new StreamResult(new java.io.File("src\\A_EscrituraXML\\Contactos.xml"));
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.transform(s,result);
			
			
		} catch (Exception e) {
			System.out.println("Error en la escritura del fichero XML");
		}
		System.out.println("Fichero XML creado con éxito.");

	
	}// Fin de escribir XML
	
	/**
	 * Metodo que crea los atributos de los nodos que cuelgan de la raiz
	 * @param nombreHijo
	 * @param contenidoHijo
	 * @param nodo
	 * @param documento
	 */
	public void crearHijosNodo(String nombreHijo,String contenidoHijo,Element nodo,Document documento) {
		//<atributo> contacto.atributo </atributo>
		Element e = documento.createElement(nombreHijo);
		Text t = documento.createTextNode(contenidoHijo);
		e.appendChild(t);
		nodo.appendChild(e);
	}

	/**
	 * Metodo para obtener una lista con todos los contacto del fichero
	 */
	public ArrayList<Contacto> listadoContactos() {
		ArrayList<Contacto> contactos = new ArrayList<>();

		Contacto c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream("src\\A_EscrituraXML\\Agenda.obj");
			ois = new ObjectInputStream(fis);

			System.out.println("\n--Contactos --");
			while (true) {
				c = (Contacto) ois.readObject();
				// Añadir contacto a la lista
				contactos.add(c);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("-- Listado de contactos creado con éxito --\n");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer la clase del fichero");
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
		}
		return contactos;
	}// Fin del metodo que devuelve un listado de los empleados del fichero.

	/**
	 * Metodo para leer el fichero serializado donde se encuentra el objeto
	 */
	public void leerFichero() {

		Contacto c = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			// Abrir flujos
			fis = new FileInputStream("src\\A_EscrituraXML\\Agenda.obj");
			ois = new ObjectInputStream(fis);

			System.out.println("\n--Contactos --");
			while (true) {
				c = (Contacto) ois.readObject();
				System.out.println(c);
			}

		} catch (

		FileNotFoundException e) {
			System.out.println("Error al encontrar el fichero.");
		} catch (IOException e) {
			System.out.println("\n --- Fin de lectura ---\n");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer la clase del fichero");
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					e.printStackTrace();
				}
			}
		}
	}// Fin de leer fichero

	/**
	 * Escribir fichero serializado con los datos del contacto
	 */
	public void escribirEnFichero() {
		File fichero = new File("src\\A_EscrituraXML\\Agenda.obj");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		Contacto c = null;

		try {
			// Abrir flujos
			if (fichero.exists()) {
				fos = new FileOutputStream(fichero, true);
				oos = new MyObjectOutPutStream(fos);
			} else {
				fos = new FileOutputStream(fichero);
				oos = new ObjectOutputStream(fos);
			}
			// Crear objeto
			c = new Contacto(this.nombre, this.apellido, this.email, this.telefono);
			// Escribir en el fichero
			oos.writeObject(c);

		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("No se encontró el fichero");
			e.printStackTrace();
		}
	}// Fin de escribir fichero

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
