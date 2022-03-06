package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;

public class Controlador implements IControlador {
	
	private IModelo modelo;
	private IVista vista;

	public Controlador(IModelo modelo, IVista vista) {
		try{
			this.modelo = modelo;
			this.vista = vista;
		} catch (NullPointerException e) {
			System.out.println("ERROR: La vista o el controlador no pueden ser nulos.");
		}
		
		this.vista.setControlador(this);
	}
	
	@Override
	public void comenzar() {
		vista.comenzar();
	}
	
	@Override
	public void terminar() {
		System.out.println("Fin del programa.");
	}
	
	@Override
	public void insertarAula(Aula aula) {
		try {
			modelo.insertarAula(aula);
			System.out.println("Se ha insertado el aula.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void insertarProfesor(Profesor profesor) {
		try {
			modelo.insertarProfesor(profesor);
			System.out.println("Se ha insertado el profesor.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void borrarAula(Aula aula) {
		try {
			modelo.borrarAula(aula);
			System.out.println("Se ha elimiado el aula.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void borrarProfesor(Profesor profesor) {
		try {
			modelo.borrarProfesor(profesor);
			System.out.println("Se ha elimiado el profesor.");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public Aula buscarAula(Aula aula) {
		try {
			modelo.buscarAula(aula);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.buscarAula(aula);
	}
	
	@Override
	public Profesor buscarProfesor(Profesor profesor) {
		try {
			modelo.buscarProfesor(profesor);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.buscarProfesor(profesor);
	}
	
	@Override
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}
	
	@Override
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
	
	@Override
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}
	
	@Override
	public void realizarReserva(Reserva reserva) {
		try {
			modelo.realizarReserva(reserva);
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void anularReserva(Reserva reserva) {
		try {
			modelo.anularReserva(reserva);
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Reserva> getReservasAula(Aula aula) {
		try {
			modelo.getReservasAula(aula);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.getReservasAula(aula);
		
	}
	
	@Override
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		try {
			modelo.getReservasProfesor(profesor);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.getReservasProfesor(profesor);
		
	}
	
	@Override
	public List<Reserva> getReservasPremanencia(Permanencia permanencia) {
		try {
			modelo.getReservasPermanencia(permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.getReservasPermanencia(permanencia);
	}
	
	@Override
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		try {
			modelo.consultarDisponibilidad(aula, permanencia);
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		return modelo.consultarDisponibilidad(aula, permanencia);
		
	}
	
	
}
