package model;

import java.util.LinkedList;
import java.util.List;

public class Itinerario {
	
	List <Sugerencia> listadoSugerenciasAceptadas = new LinkedList<Sugerencia>();

	public boolean aceptarSugerencia(Sugerencia nueva) {
		return listadoSugerenciasAceptadas.add(nueva); // devuelve true si se agregó
	}

	public List<Sugerencia> getListadoSugerenciasAceptadas() {
		return listadoSugerenciasAceptadas;
	}


	@Override
	public String toString() {
		String mensaje = "Itinerario\n";
		for(Sugerencia sugerencia : listadoSugerenciasAceptadas) {
			mensaje += "\t> Nombre: "   + sugerencia.getNombre()+", "
					   + "\t> Costo: "  + sugerencia.getCosto()+", "
					   + "\t> Tiempo: " + sugerencia.getTiempoRequerido()+", "
					   + "\t> Tipo: "   + sugerencia.getTipo() + "\n";
		}
	
		return mensaje;
	}
	
	
	
}
