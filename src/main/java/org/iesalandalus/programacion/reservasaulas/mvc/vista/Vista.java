package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista implements IVista {
	private static final String ERROR="ERROR", NOMBRE_VALIDO="Helena Gutierrez", CORREO_VALIDO="helena@gutierrez.com";
	
	private IControlador controlador;
	
	public Vista() {
		Opcion.setVista(this);
	}
	
	@Override
	public void setControlador(IControlador controlador) {
		this.controlador = controlador;
		
		controlador.comenzar();
	}
	
	@Override
	public void comenzar()
	{
		int ordinalOpcion;
		do{
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	@Override
	public void salir() {
		controlador.terminar();
		
	}
	
	public void insertarAula() {
		Aula aula;
		
		aula = Consola.leerAula();
		
		controlador.insertarAula(aula);
		
	}
	
	public void borrarAula() {
		Aula aula;
		
		aula = Consola.leerAula();
		
		controlador.borrarAula(aula);
		
	}
	
	public void buscarAula() {
		Aula aula;
		
		aula = Consola.leerAula();
				
		controlador.buscarAula(aula);
		
	}
	
	public void listarAulas() {
		
		controlador.representarAulas();
		
	}
	
	public void insertarProfesor() {
		Profesor profesor;
		
		profesor = Consola.leerProfesor();
		
		controlador.insertarProfesor(profesor);
		
	}
	
	public void borrarProfesor() {
		Profesor profesor;
		
		profesor = Consola.leerProfesor();
		
		controlador.borrarProfesor(profesor);
	}
	
	public void buscarProfesor() {
		Profesor profesor;
	
		profesor = Consola.leerProfesor();
		
		controlador.buscarProfesor(profesor);
		
	}
	
	public void listarProfesores() {
		
		controlador.representarProfesores();
	}
	
	public void realizarReserva() {
		Reserva reserva = Consola.leerReserva();
		
		if (controlador.buscarAula(reserva.getAula()) == null) { 
			throw new IllegalArgumentException("ERROR: No se puede realizar una reserva si el aula aun no existe.");
		} else if (controlador.buscarProfesor(reserva.getProfesor()) == null) {
			throw new IllegalArgumentException("ERROR: No se puede realizar una reserva si el profesor aun no existe.");
		} else {
			controlador.realizarReserva(reserva);
		}
		
	}
	public void anularReserva() {
		
		controlador.anularReserva(Consola.leerReservaFicticia());
		
	}
	
	public void listarReservas() {
		controlador.representarReservas();
		
	}
	
	public void listarReservasAula() {
		controlador.getReservasAula(Consola.leerAula());
	}
	
	public void listarReservasProfesor() {
		controlador.getReservasProfesor(Consola.leerProfesor());
		
	}
	
	public void consultarDisponibilidad() {
		Aula aula;
		
		aula = Consola.leerAula();
		
		if (controlador.buscarAula(aula) != null) { 
			throw new IllegalArgumentException("ERROR: No se puede realizar una reserva si el aula un no existe.");
		} else {
			controlador.consultarDisponibilidad(aula, Consola.leerPermanencia());
		}
		
		
	}
}
