package org.iesalandalus.programacion.reservasaulas.mvc.controlador;

import java.util.List;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public interface IControlador {

	void comenzar();

	void terminar();

	void insertarAula(Aula aula);

	void insertarProfesor(Profesor profesor);

	void borrarAula(Aula aula);

	void borrarProfesor(Profesor profesor);

	Aula buscarAula(Aula aula);

	Profesor buscarProfesor(Profesor profesor);

	List<String> representarAulas();

	List<String> representarProfesores();

	List<String> representarReservas();

	void realizarReserva(Reserva reserva);

	void anularReserva(Reserva reserva);

	List<Reserva> getReservasAula(Aula aula);

	List<Reserva> getReservasProfesor(Profesor profesor);

	List<Reserva> getReservasPremanencia(Permanencia permanencia);

	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia);

}