package model;

import java.util.Comparator;

public class ComparadorDeSugerencias implements Comparator<Sugerencia> {
	private TipoAtraccion atraccionFavorita;

	public ComparadorDeSugerencias(TipoAtraccion atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}

	@Override
	public int compare(Sugerencia o1, Sugerencia o2) {
		// Prioridad preferencia
		if (this.atraccionFavorita == o2.getTipo() && this.atraccionFavorita != o1.getTipo()) {
			return 1;
		}
		if (this.atraccionFavorita == o1.getTipo() && this.atraccionFavorita != o2.getTipo()) {
			return -1;
		}
		
		// Prioridad promocion
		if (o2.esPromocion() && !o1.esPromocion()) {
			return 1;
		}
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		}

		// Prioridad por dinero
		double diferenciaCosto = o2.getCosto() - o1.getCosto();
		if (diferenciaCosto != 0) {
			return diferenciaCosto > 0 ? 1 : (diferenciaCosto < 0 ? -1 : 0);
		}

		// Prioridad por tiempo
		double diferenciaTiempo = o2.getTiempoRequerido() - o1.getTiempoRequerido();
		return diferenciaTiempo > 0 ? 1 : (diferenciaTiempo < 0 ? -1 : 0);
	}

}
