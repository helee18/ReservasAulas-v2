package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {
	MANANA("Mañana"),TARDE("Tarde"); //opciones
	
	private String cadenaAMostrar; 
	
	private Tramo (String cadenaAMostrar) {
		this.cadenaAMostrar = cadenaAMostrar; //Referencia a Mañana o Tarde ()
    }
	
	public String toString() {
        return cadenaAMostrar; // Devuelve Mañana o Tarde ()
    }
}
