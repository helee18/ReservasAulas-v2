package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public class Reservas implements IReservas {
	
	private static final float MAX_PUNTOS_PROFESOR_MES = 200.0f;

	private List<Reserva> coleccionReservas;
	
	public Reservas () {
		coleccionReservas = new ArrayList<Reserva>();
	}
	
	public Reservas(IReservas reservasOriginal) {
		if (reservasOriginal == null)
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		
		if (reservasOriginal.getNumReservas() == 0)
			coleccionReservas = new ArrayList<Reserva>();
		else 
			setReservas(reservasOriginal);
	}
	
	private void setReservas(IReservas reservas) {

		coleccionReservas = copiaProfundaReservas(reservas.getReservas());
	}
	
	@Override
	public List<Reserva> getReservas() {
		if (getNumReservas() == 0)
			throw new NullPointerException("ERROR: No se ha realizado ninguna reserva aun.");
		
		Comparator<Reserva> comparator = Comparator.comparing(Reserva::getPuntos);
		
		List<Reserva> copiaReservas = copiaProfundaReservas(coleccionReservas);
		
		Collections.sort(copiaReservas, comparator);
		
		return copiaReservas;
	}
	
	private List<Reserva> copiaProfundaReservas(List<Reserva> coleccionReservasOriginal) {
		
		List<Reserva> coleccionCopiaReservas;
		
		coleccionCopiaReservas = new ArrayList<Reserva>();
		
		// recorremos todas las reservas comparando
		Iterator<Reserva> it = coleccionReservasOriginal.iterator();
		while(it.hasNext()) {
			Reserva reservaCopia = new Reserva(it.next());
			coleccionCopiaReservas.add(reservaCopia);
		}
		return coleccionCopiaReservas;
		
	}
	
	private float getPuntosGastadosReserva(Reserva reserva) {
		if (reserva == null) 
			throw new NullPointerException("ERROR: No se pueden obtener puntos de una reserva nula."); 
		
		return reserva.getPuntos();

	}
	
	private Reserva getReservaAulaDia(Aula aula, LocalDate dia) {

		Iterator<Reserva> it = coleccionReservas.iterator();

		while (it.hasNext()) {
			Reserva reserva = it.next();
			if (reserva.getAula().equals(aula) && reserva.getPermanencia().getDia().equals(dia))
				return new Reserva(reserva);
		}
		return null;
	}
	
	private List<Reserva> getReservaProfesorMes(Profesor profesor, LocalDate dia){
		List<Reserva> reservasProfesor = new ArrayList<Reserva>();
		
		Iterator<Reserva> it = coleccionReservas.iterator();

		while (it.hasNext()) {
			Reserva reserva = it.next();
			if (reserva.getProfesor().equals(profesor) && (reserva.getPermanencia().getDia().getMonthValue() == dia.getMonthValue()))
				reservasProfesor.add(new Reserva(reserva));
		}
		
		return reservasProfesor;
	}
	
	@Override
	public void insertar (Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) 
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		
		if (buscar(reserva) != null)
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
		
		if (!esMesSiguienteOPosterior(reserva))
			throw new OperationNotSupportedException("ERROR: Sólo se pueden hacer reservas para el mes que viene o posteriores.");
		
		Reserva aulaDia = getReservaAulaDia(reserva.getAula(), reserva.getPermanencia().getDia());
		if (aulaDia != null) {
			if (aulaDia.getPermanencia().getPuntos() != reserva.getPermanencia().getPuntos()) {
				throw new OperationNotSupportedException(
						"ERROR: Ya se ha realizado una reserva de otro tipo de permanencia para este día.");
			}
		}
		
		//para cada reserva vamos viendo los puntos y los sumamos
		List<Reserva> reservasProfesor = new ArrayList<Reserva>();
		reservasProfesor = getReservaProfesorMes(reserva.getProfesor(), reserva.getPermanencia().getDia());
		
		float puntosProfesorMes=reserva.getPuntos();
		
		for (Reserva r : reservasProfesor) {
			puntosProfesorMes = puntosProfesorMes + getPuntosGastadosReserva(r);
		}
		
		if ((puntosProfesorMes) >= MAX_PUNTOS_PROFESOR_MES)
			throw new OperationNotSupportedException("ERROR: Esta reserva excede los puntos máximos por mes para dicho profesor.");

		
		//if (!reserva.getPermanencia().getDia().equals(coleccionReservas.get(0).getPermanencia().getDia()))
		if (getReservaAulaDia(reserva.getAula(), reserva.getPermanencia().getDia()) != null)
			throw new OperationNotSupportedException("ERROR: Ya se ha realizado una reserva de otro tipo de permanencia para este día.");

		coleccionReservas.add(new Reserva(reserva));
	}
	
	@Override
	public Reserva buscar (Reserva reserva) {
		if (reserva == null)
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		
		if (coleccionReservas.contains(reserva))
			return new Reserva(reserva); 
		else
			return null;
	}
	
	@Override
	public void borrar (Reserva reserva) throws OperationNotSupportedException {
		if (reserva == null) 
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		
		if (!esMesSiguienteOPosterior(reserva))
			throw new OperationNotSupportedException("ERROR: Sólo se pueden anular reservas para el mes que viene o posteriores.");
		
		if (buscar(reserva) == null) 	
			throw new OperationNotSupportedException("ERROR: No existe ninguna reserva igual.");
		
		coleccionReservas.remove(reserva);
	}
	
	@Override
	public List<String> representar() throws OperationNotSupportedException {
		if (getNumReservas() == 0)
			throw new OperationNotSupportedException("ERROR: La lista de reservas está vacia.");
		
		List<String> representacion = new ArrayList<String>();
		
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			Reserva reservaCopia = new Reserva(it.next());
			representacion.add(reservaCopia.toString());
		}
	
		return representacion;
	}

	@Override
	public int getNumReservas() {
		return coleccionReservas.size();
	}
	
	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		boolean mesSiguientePosterior = false;
		
		if (reserva.getPermanencia().getDia().getMonth().compareTo(LocalDate.now().getMonth()) > 0)
			mesSiguientePosterior = true;
		
		return mesSiguientePosterior;
			
	}
	
	@Override
	public List<Reserva> getReservasProfesor (Profesor profesor) {
		if (profesor ==  null)
			throw new NullPointerException ("ERROR: El profesor no puede ser nulo.");
		
		List<Reserva> coleccionReservasProfesor;
		
		coleccionReservasProfesor = new ArrayList<Reserva>();
		
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			Reserva reserva = new Reserva(it.next());
			
			if (profesor.equals(reserva.getProfesor())) {
				coleccionReservasProfesor.add(reserva);
			}
		}
		
		return coleccionReservasProfesor;
	}
	
	@Override
	public List<Reserva> getReservasAula (Aula aula) {
		if (aula ==  null)
			throw new NullPointerException ("ERROR: El aula no puede ser nula.");
		
		List<Reserva> coleccionReservasAula;
		
		coleccionReservasAula = new ArrayList<Reserva>();
		
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			Reserva reserva = new Reserva(it.next());
			
			if (aula.equals(reserva.getAula())) {
				coleccionReservasAula.add(reserva);
			}
		}
		
		return coleccionReservasAula;
	}
	
	@Override
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if (permanencia ==  null)
			throw new NullPointerException ("ERROR: No se puede buscar una reserva con permanencia nula.");
		
		List<Reserva> coleccionReservasPermanencia;
		
		coleccionReservasPermanencia = new ArrayList<Reserva>();
		
		Iterator<Reserva> it = coleccionReservas.iterator();
		while(it.hasNext()) {
			Reserva reserva = new Reserva(it.next());
			
			if (permanencia.equals(reserva.getPermanencia())) {
				coleccionReservasPermanencia.add(reserva);
			}
		}
		
		return coleccionReservasPermanencia; 
	}
	
	@Override
	public boolean consultarDisponibilidad (Aula aula, Permanencia permanencia) {
		boolean disponible = true;
		
		if (aula == null)
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");

		if (permanencia == null)
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		
		if (coleccionReservas.size() == 0)
			disponible = true;
		else {
		
			for(Reserva i: coleccionReservas) {
				
				if(i.getAula().equals(aula) && i.getPermanencia().equals(permanencia))
					disponible = false;
			}
		}
		
		return disponible;
			
	}
}
