package Tarea09_LecturaXML_STAX_Empleados;

import java.util.Collection;

public class Empleado implements Comparable<Empleado>{

	String nombre;
	int salario;

	public Empleado() {
		// constructor vacio
	}

	public Empleado(String n, int s) {
		this.nombre = n;
		this.salario = s;
	}
	
	

	@Override
	public String toString() {
		return this.nombre + " tiene un salario de " + this.salario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	@Override
	public int compareTo(Empleado o) {
		if(o.getSalario()==this.salario) {
			return 0;
		}
		if(o.getSalario()<this.salario) {
			return 1;
		}else {
			return -1;
		}
		
	}
	
	//Get and Set
	
}
