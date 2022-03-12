package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Aula {
	private static final float PUNTOS_POR_PUESTO = 0.5f;
	private static final int MAX_PUESTOS = 100, MIN_PUESTOS = 10;
	
	private String nombre;
	private int puestos;
	
	public Aula(String nombre, int puestos) {
		setNombre(nombre);
		setPuestos(puestos);
	}
	
	public Aula(Aula aulaOriginal) {
		if (aulaOriginal == null)
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		
		setNombre(aulaOriginal.getNombre());
		setPuestos(aulaOriginal.getPuestos());
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		
		// Quitar espacios principio y final (trim) y los que sobre en medio
		nombre = nombre.trim().replaceAll("\\s{2,}"," ");
 		
		// Mayusculas y minusculas
		String nombreNuevo = "";
		String[] palabrasString = nombre.split(" "); 
		
		List<String> palabrasList = new ArrayList<String>();
		palabrasList = Arrays.asList(palabrasString);
		
		for(String i: palabrasList){
			i = i.toUpperCase().charAt(0) + i.substring(1).toLowerCase();
			
			nombreNuevo = nombreNuevo + i + " ";
		}
		
		this.nombre = nombreNuevo.trim();
	}

	public int getPuestos() {
		return puestos;
	}

	private void setPuestos(int puestos) {
		if (puestos < MIN_PUESTOS || puestos > MAX_PUESTOS)
			throw new IllegalArgumentException("ERROR: El número de puestos no es correcto.");
		
		this.puestos = puestos;
	}
	
	public static Aula getAulaFicticia(String nombre) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		
		return new Aula(nombre, MIN_PUESTOS);
	}
	
	public float getPuntos() {
		return PUNTOS_POR_PUESTO*getPuestos();
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre=" + getNombre() + ", puestos=" + getPuestos();
	}
	
	
}
