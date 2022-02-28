package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Permanencia {
	private LocalDate dia;
	protected static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //despues usar constante directamente, no funcion para formatear
	
	public Permanencia(LocalDate dia) {
		setDia(dia);
	}
	
	public Permanencia(Permanencia permanenciaOriginal) {
		//Not Null
		if (permanenciaOriginal == null)
			throw new NullPointerException ("ERROR: No se puede copiar una permanencia nula.");
		
		setDia(permanenciaOriginal.getDia());
	}

	public LocalDate getDia() {
		return dia;
	}

	private void setDia(LocalDate dia) {
		if (dia == null)
			throw new NullPointerException ("ERROR: El día de una permanencia no puede ser nulo.");
			
		this.dia = dia;
	}

	public abstract int getPuntos();
    public abstract int hashCode();
    public abstract boolean equals(Object permanencia);
 
	@Override
	public String toString() {
		return "día=" + FORMATO_DIA.format(dia);
	}
}
