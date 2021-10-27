package model;

import java.util.List;

public class PromocionAbsoluta extends Promocion{

	private double descuentoTotal;

	public PromocionAbsoluta(TipoAtraccion tipoPromocion, String nombrePromocion,
			List<Atraccion> atraccionesContenidas, double descuentoTotal) {
		super(tipoPromocion, nombrePromocion, atraccionesContenidas);
		this.descuentoTotal = descuentoTotal;
		calcularCostoPromocion();
	}

	@Override
	public void calcularCostoPromocion() {
		super.setMontoDescuento(this.descuentoTotal);
		super.setMontoTotalConDto(super.montoTotalSinDto - this.descuentoTotal);
	}
	
	@Override
	public String toString() {
		String mensaje = ">>> Promocion '" + nombre + "'\n";
		
		mensaje += "\tCosto sin descuento: " + super.montoTotalSinDto + '\n';
		mensaje += "\tDescuento total: " + super.montoDescuento + '\n';
		mensaje += "\tNuevo costo (c/dto): " + super.montoTotalConDto + "\n\n";
		
		mensaje += "\tTotal atracciones: \n";
		for(Atraccion atraccion : atraccionesContenidas) {
			mensaje += "\t\t> " + atraccion.getNombre() + '\n';
		}
		mensaje += "\n\tTiempo Requerido (min): " + super.tiempoRequerido + "\n";

		return mensaje;
	}
}
