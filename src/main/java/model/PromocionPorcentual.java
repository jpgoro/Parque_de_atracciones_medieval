package model;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	private double porcentajeDescuento;

	public PromocionPorcentual(TipoAtraccion tipoPromocion, String nombrePromocion,
			List<Atraccion> atraccionesContenidas, double porcentajeDescuento) {

		super(tipoPromocion, nombrePromocion, atraccionesContenidas);
		this.porcentajeDescuento = porcentajeDescuento;
		calcularCostoPromocion();
	}

	@Override
	public void calcularCostoPromocion() {
		setMontoDescuento((super.montoTotalSinDto / 100) * this.porcentajeDescuento);
		setMontoTotalConDto(super.montoTotalSinDto - super.montoDescuento);
	}

	@Override
	public String toString() {
		String mensaje = ">>> Promocion '" + super.nombre + "'\n";

		mensaje += "\tCosto sin descuento: " + super.montoTotalSinDto + '\n';
		mensaje += "\tDescuento total: " + super.montoDescuento + '\n';
		mensaje += "\tPorcentaje de descunto: -" + this.porcentajeDescuento + "%\n";
		mensaje += "\tNuevo costo (c/dto): " + super.montoTotalConDto + "\n\n";

		mensaje += "\tTotal atracciones: \n";
		for (Atraccion atraccion : super.atraccionesContenidas) {
			mensaje += "\t\t> " + atraccion.getNombre() + '\n';
		}
		mensaje += "\n\tTiempo Requerido (min): " + super.tiempoRequerido + "\n";
		return mensaje;
	}

}
