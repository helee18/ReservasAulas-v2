package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Profesor {
	private static final String ER_TELEFONO="[69][0-9]{8}", ER_CORREO="\\w+[\\.\\w]*@\\w+[\\.\\w]*\\.\\w{2,5}\\b\\s?";
	private String nombre, correo, telefono;
	
	
	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(null);
	}
	
	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}
	
	public Profesor(Profesor profesorOriginal) {
		if (profesorOriginal == null)
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		
		setNombre(profesorOriginal.getNombre());
		setCorreo(profesorOriginal.getCorreo());
		setTelefono(profesorOriginal.getTelefono());
	}
	
	public String getNombre() {
		return nombre;
	}
	
	private void setNombre(String nombre) {
		if (nombre == null)
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		
		String nombreNuevo = formateaNombre(nombre);
		
		this.nombre = nombreNuevo.trim();
	}

	/**
	 * @param nombre
	 * @return
	 */
	private String formateaNombre(String nombre) {
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
		
		return nombreNuevo;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		if (correo == null)
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		
		if (correo.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		
		if (!correo.matches(ER_CORREO))
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		
		this.correo = correo;
	}
	
	public static Profesor getProfesorFicticio(String correo) {
		if (correo == null)
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		
		if (correo.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		
		if (!correo.matches(ER_CORREO) )
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		
		String nombreFicticio = "Profesor Ficticio";
		return new Profesor(nombreFicticio, correo);
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		if (telefono == null)
			telefono = null;
		
		if (telefono != null)
			if (!telefono.matches(ER_TELEFONO))
				throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		
		this.telefono = telefono;	
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		if (getTelefono() == null)
			return "nombre=" + getNombre() + ", correo=" + getCorreo();
		else
			return "nombre=" + getNombre() + ", correo=" + getCorreo() + ", teléfono=" + getTelefono() ;
	}
	
	
	
	
}
