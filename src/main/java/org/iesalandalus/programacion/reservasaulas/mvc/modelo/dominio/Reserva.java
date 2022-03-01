package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {
	private Permanencia permanencia;
	private Aula aula;
	private Profesor profesor;
	
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}
	
	public Reserva(Reserva reservaOriginal) {
		if (reservaOriginal == null)
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		
		setProfesor(reservaOriginal.getProfesor());
		setAula(reservaOriginal.getAula());
		setPermanencia(reservaOriginal.getPermanencia());
	}
	
	public Permanencia getPermanencia() {
		
		Permanencia permanenciaGet = null;
		
		if(permanencia instanceof PermanenciaPorTramo)
			permanenciaGet = new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
	    
		else if (permanencia instanceof PermanenciaPorHora)
			permanenciaGet = new PermanenciaPorHora((PermanenciaPorHora) permanencia);
		
		return permanenciaGet;
	}
	
	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null)
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		
		if(permanencia instanceof PermanenciaPorTramo)
			this.permanencia=new PermanenciaPorTramo(((PermanenciaPorTramo) permanencia).getDia(),((PermanenciaPorTramo) permanencia).getTramo());
	    
		if(permanencia instanceof PermanenciaPorHora)
			this.permanencia=new PermanenciaPorHora(((PermanenciaPorHora) permanencia).getDia(),((PermanenciaPorHora) permanencia).getHora());
	}
	
	public Aula getAula() {
		return new Aula(aula);
	}
	
	private void setAula(Aula aula) {
		if (aula == null)
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		
		this.aula = new Aula(aula);
	}
	
	public Profesor getProfesor() {
		return new Profesor(profesor);
	}
	
	private void setProfesor(Profesor profesor) {
		if (profesor == null)
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		
		this.profesor = new Profesor(profesor);
	}
	
	public static Reserva getReservaFicticia(Aula aula, Permanencia permanencia) {
		Profesor profesor = new Profesor("Pepe Gutierrez", "pepe.gutierrez@gmail.com");
		
		return new Reserva(profesor, aula, permanencia);
	}
	
	public float getPuntos() {
		return aula.getPuntos()+permanencia.getPuntos();
	}

	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia, profesor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia);
	}

	@Override
	public String toString() {
		return getProfesor() + ", " + getAula() + ", " + getPermanencia() + ", puntos=" + getPuntos();
	}
	
	
	
	
}
