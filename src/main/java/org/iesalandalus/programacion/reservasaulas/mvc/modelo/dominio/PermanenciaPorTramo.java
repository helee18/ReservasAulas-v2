package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

public class PermanenciaPorTramo extends Permanencia{
	
	private static final int PUNTOS=10;
	
	private Tramo tramo; 

	public PermanenciaPorTramo(LocalDate dia, Tramo tramo) {
		super(dia);
		// TODO Auto-generated constructor stub
		setTramo(tramo);
	}
	
	public PermanenciaPorTramo(PermanenciaPorTramo permanenciaOriginal) {  
        super(permanenciaOriginal);
        setTramo(permanenciaOriginal.getTramo());
	}
	
	public Tramo getTramo() {
		return tramo;
	}

	private void setTramo(Tramo tramo) {
		if (tramo == null)
			throw new NullPointerException("ERROR: El tramo de una permanencia no puede ser nulo.");
		
		this.tramo = tramo;
	}

	@Override
	public int getPuntos() {
		// TODO Auto-generated method stub
		return PUNTOS;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(getDia(), getTramo());
	}

	@Override
	public boolean equals(Object permanencia) {
		// TODO Auto-generated method stub
		if (this == permanencia)
			return true;
		if (permanencia == null)
			return false;
		if (getClass() != permanencia.getClass())
			return false;
		Permanencia other = (Permanencia) permanencia;
		return Objects.equals(getDia(), other.getDia());
	}
	
	public String toString() {
		return super.toString() + ", tramo=" + getTramo();
	}

}
