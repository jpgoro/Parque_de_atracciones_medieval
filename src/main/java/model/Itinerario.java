package model;

import java.util.LinkedList;
import java.util.List;

public class Itinerario {
	
	List <Sugerencia> listadoSugerenciasAceptadas = new LinkedList<Sugerencia>();

	public boolean aceptarSugerencia(Sugerencia nueva) {
		boolean agregada = false;
		try {
			nueva.ocuparLugar();
			listadoSugerenciasAceptadas.add(nueva);
			agregada = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agregada; 
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
