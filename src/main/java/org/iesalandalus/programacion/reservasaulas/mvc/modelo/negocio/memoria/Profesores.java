package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;

public class Profesores implements IProfesores {
	
	private List<Profesor> coleccionProfesores;
	
	public Profesores () {
		coleccionProfesores = new ArrayList<Profesor>();
	}
	
	public Profesores(IProfesores profesoresOriginal) {
		if (profesoresOriginal == null)
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		
		if (profesoresOriginal.getNumProfesores() == 0)
			this.coleccionProfesores = new ArrayList<Profesor>();
		else 
			this.coleccionProfesores = copiaProfundaProfesores(profesoresOriginal.getProfesores());
	}
	
	@Override
	public List<Profesor> getProfesores() {
		Comparator<Profesor> comparator = Comparator.comparing(Profesor::getCorreo);
		
		List<Profesor> copiaProfesores = copiaProfundaProfesores(coleccionProfesores);
		
		Collections.sort(copiaProfesores, comparator);
		
		return copiaProfesores;
	}
	
	private List<Profesor> copiaProfundaProfesores(List<Profesor> coleccionProfesoresOriginal) {
		
		List<Profesor> coleccionCopiaProfesores;
		
		coleccionCopiaProfesores = new ArrayList<Profesor>(getNumProfesores());
		
		// recorremos todas las profesores comparando
		Iterator<Profesor> it = coleccionProfesoresOriginal.iterator();
		while(it.hasNext()) {
			Profesor profesorCopia = new Profesor(it.next());
			coleccionCopiaProfesores.add(profesorCopia);
		}
		return coleccionCopiaProfesores;
		
	}
	
	@Override
	public void insertar (Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) 
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		
		if (buscar(profesor) != null)
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese correo.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionProfesores.add(new Profesor(profesor));
	}
	
	@Override
	public Profesor buscar (Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (coleccionProfesores.contains(profesor))
			return new Profesor(profesor); 
		else
			return null;
	}
	
	@Override
	public void borrar (Profesor profesor) throws OperationNotSupportedException {
		if (profesor == null) 
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		
		if (buscar(profesor) != null) 		
			coleccionProfesores.remove(profesor);
			
		else
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese correo.");
	}
	
	@Override
	public List<String> representar() {
		if (getNumProfesores() == 0)
			throw new IllegalArgumentException("ERROR: La lista de profesores está vacia.");
		
		List<String> representacion = new ArrayList<String>();
		
		for (Profesor p : coleccionProfesores)
			representacion.add(p.toString());	
	
		return representacion;
	}

	@Override
	public int getNumProfesores() {
		return coleccionProfesores.size();
	}
	

}
