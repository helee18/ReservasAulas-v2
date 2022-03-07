package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

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
		
		try {
			aula = Consola.leerAula();
			controlador.insertarAula(aula);
			System.out.println("Se ha insertado el aula.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void borrarAula() {
		Aula aula;
		
		try {
			aula = Consola.leerAula();
			controlador.borrarAula(aula);
			System.out.println("Se ha elimiado el aula.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void buscarAula() {
		Aula aula;
		
		try {
			aula = Consola.leerAula();
			
			if (controlador.buscarAula(aula) == null)
				System.out.println("No existe.");
			else
				System.out.println(controlador.buscarAula(aula));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void listarAulas() {
		try {
			System.out.println(controlador.representarAulas());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void insertarProfesor() {
		Profesor profesor;
		
		try {
			profesor = Consola.leerProfesor();
			controlador.insertarProfesor(profesor);
			System.out.println("Se ha insertado el profesor.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void borrarProfesor() {
		Profesor profesor;
		
		try {
			profesor = Consola.leerProfesor();
			controlador.borrarProfesor(profesor);
			System.out.println("Se ha elimiado el profesor.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Profesor profesor;
	
		try {
			profesor = Consola.leerProfesor();
			if (controlador.buscarProfesor(profesor) == null)
				System.out.println("No existe.");
			else
				System.out.println(controlador.buscarProfesor(profesor));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public void listarProfesores() {
		
		try {
			System.out.println(controlador.representarProfesores());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void realizarReserva(){
		
		try {
			Reserva reserva = Consola.leerReserva();
			if (controlador.buscarAula(reserva.getAula()) == null)
				System.out.println("ERROR: No se puede realizar una reserva si el aula aun no existe.");
			else if (controlador.buscarProfesor(reserva.getProfesor()) == null)
				System.out.println("ERROR: No se puede realizar una reserva si el profesor aun no existe.");
			else {
				controlador.realizarReserva(reserva);
				System.out.println("Se ha realizado al reserva");}
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void anularReserva() {
		
		try {
			controlador.anularReserva(Consola.leerReservaFicticia());
			System.out.println("Se ha anulado la reserva.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarReservas() {
		try {
			System.out.println(controlador.representarReservas());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void listarReservasAula() {
		
		try {
			System.out.println(controlador.getReservasAula(Consola.leerAula()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void listarReservasProfesor() {
		
		try {
			System.out.println(controlador.getReservasProfesor(Consola.leerProfesor()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public void consultarDisponibilidad() {
		Aula aula;
		
		try {
			aula = Consola.leerAula();
			boolean disponible = controlador.consultarDisponibilidad(aula, Consola.leerPermanencia());
			
			if (disponible == true)
				System.out.println("Sí está disponible.");
			else 
				System.out.println("No está disponible.");
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
