package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas {
	
	private List<Aula> coleccionAulas;
	
	public Aulas () {
		coleccionAulas = new ArrayList<Aula>();
	}
	
	public Aulas(IAulas aulasOriginal) {
		if (aulasOriginal == null)
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		
		if (aulasOriginal.getNumAulas() == 0)
			coleccionAulas = new ArrayList<Aula>();
		else 
			setAulas(aulasOriginal);
	}
	
	private void setAulas(IAulas aulas) {

		coleccionAulas = copiaProfundaAulas(aulas.getAulas());
	}
	
	@Override
	public List<Aula> getAulas() {
		if (getNumAulas() == 0)
			throw new NullPointerException("ERROR: No se ha creado ningun aula aun.");
		
		Comparator<Aula> comparator = Comparator.comparing(Aula::getNombre);
		
		List<Aula> copiaAulas = copiaProfundaAulas(coleccionAulas);
		
		Collections.sort(copiaAulas, comparator);
		
		return copiaAulas;
	}
	
	private List<Aula> copiaProfundaAulas(List<Aula> coleccionAulasOriginal) {
		
		List<Aula> coleccionCopiaAulas;
		
		coleccionCopiaAulas = new ArrayList<Aula>(getNumAulas());
		
		// recorremos todas las aulas comparando
		Iterator<Aula> it = coleccionAulasOriginal.iterator();
		while(it.hasNext()) {
			Aula aulaCopia = new Aula(it.next());
			coleccionCopiaAulas.add(aulaCopia);
		}
		return coleccionCopiaAulas;
		
	}
	
	@Override
	public void insertar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		
		if (buscar(aula) != null)
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		
		// insertamos la nueva cita y actualizamos el tamaño
		coleccionAulas.add(new Aula(aula));
	}
	
	@Override
	public Aula buscar (Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		
		// si el indice supera al tamaño, es que no lo ha encontrado y es un objeto nuevo
		if (coleccionAulas.contains(aula))
			return new Aula(aula); 
		else
			return null;
	}
	
	@Override
	public void borrar (Aula aula) throws OperationNotSupportedException {
		if (aula == null) 
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		
		if (buscar(aula) == null) 			
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		
		coleccionAulas.remove(aula);
	}
	
	@Override
	public List<String> representar() {
		if (getNumAulas() == 0)
			throw new IllegalArgumentException("ERROR: La lista de aulas está vacia.");
		
		List<String> representacion = new ArrayList<String>(getNumAulas());
		
		for (Aula a : coleccionAulas)
			representacion.add(a.toString());			
	
		return representacion;
	}

	@Override
	public int getNumAulas() {
		return coleccionAulas.size();
	}
	

}
