package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;

	public Controlador(Modelo modelo, Vista vista) {
		try{
			this.modelo = modelo;
			this.vista = vista;
		} catch (NullPointerException e) {
			System.out.println("ERROR: La vista o el controlador no pueden ser nulos.");
		}
		
		this.vista.setControlador(this);
	}
	
	public void comenzar() {
		vista.comenzar();
	}
	
	public void terminar() {
		System.out.println("Fin del programa.");
	}
	
	public void insertarAula(Aula aula) {
		try {
			modelo.insertarAula(aula);
			System.out.println("Se ha insertado el aula.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertarProfesor(Profesor profesor) {
		try {
			modelo.insertarProfesor(profesor);
			System.out.println("Se ha insertado el profesor.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarAula(Aula aula) {
		try {
			modelo.borrarAula(aula);
			System.out.println("Se ha elimiado el aula.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void borrarProfesor(Profesor profesor) {
		try {
			modelo.borrarProfesor(profesor);
			System.out.println("Se ha elimiado el profesor.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Aula buscarAula(Aula aula) {
		try {
			modelo.buscarAula(aula);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.buscarAula(aula);
	}
	
	public Profesor buscarProfesor(Profesor profesor) {
		try {
			modelo.buscarProfesor(profesor);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.buscarProfesor(profesor);
	}
	
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}
	
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
	
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}
	
	public void realizarReserva(Reserva reserva) {
		try {
			modelo.realizarReserva(reserva);
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void anularReserva(Reserva reserva) {
		try {
			modelo.anularReserva(reserva);
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Reserva> getReservasAula(Aula aula) {
		try {
			modelo.getReservasAula(aula);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.getReservasAula(aula);
		
	}
	
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		try {
			modelo.getReservasProfesor(profesor);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.getReservasProfesor(profesor);
		
	}
	
	public List<Reserva> getReservasPremanencia(Permanencia permanencia) {
		try {
			modelo.getReservasPermanencia(permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.getReservasPermanencia(permanencia);
	}
	
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		try {
			modelo.consultarDisponibilidad(aula, permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.consultarDisponibilidad(aula, permanencia);
		
	}
	
	
}
