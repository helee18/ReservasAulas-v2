package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PermanenciaPorHora extends Permanencia{

	private static final int PUNTOS=3;
	private static final LocalTime HORA_INICIO=LocalTime.of(8, 00), HORA_FIN=LocalTime.of(22, 00);
	protected static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm"); 
	private LocalTime hora;
	
	public PermanenciaPorHora(LocalDate dia, LocalTime hora) {
		super(dia);
		// TODO Auto-generated constructor stub
		if (hora == null)
			throw new NullPointerException("ERROR: La hora de una permanencia no puede ser nula.");
		
		setHora(hora);
	}
	
	public PermanenciaPorHora(PermanenciaPorHora permanenciaOriginal) {  
        super(permanenciaOriginal);
        setHora(permanenciaOriginal.getHora());
	}

	public LocalTime getHora() {
		return hora;
	}

	private void setHora(LocalTime hora) {
		if (hora.compareTo(HORA_INICIO) <= 0 || hora.compareTo(HORA_FIN) >= 0)
			throw new IllegalArgumentException("ERROR: La hora de una permanencia no es válida.");
		
		if (hora.getMinute() != 0)
			throw new IllegalArgumentException("ERROR: La hora de una permanencia debe ser una hora en punto.");

		this.hora = hora;
	}

	@Override
	public int getPuntos() {
		// TODO Auto-generated method stub
		return PUNTOS;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(getDia());
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
		return super.toString() + ", hora=" + FORMATO_HORA.format(getHora());
	}

}
