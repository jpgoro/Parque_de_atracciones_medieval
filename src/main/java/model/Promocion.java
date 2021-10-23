package model;

import java.util.List;
import java.util.Objects;

public abstract class Promocion extends Sugerencia {

	protected double montoDescuento; // Cantidad de dinero descontado
	protected double montoTotalConDto; // Total a pagar con el descuento
	protected double montoTotalSinDto; // Total a pagar sin el descuento. Coresponde al total de atracciones del pack
								// sin la promo

	protected List<Atraccion> atraccionesContenidas;

	public Promocion(TipoAtraccion tipoDePromocion, String nombrePromocion,
			List<Atraccion> atraccionesContenidas) {
		super(tipoDePromocion, nombrePromocion, true);
		this.atraccionesContenidas = atraccionesContenidas;
		calcularTiempoRequeridoPromocion();
		calulcarCostoTotalSinPromo();
		}
	

	public abstract void calcularCostoPromocion();

	protected void calulcarCostoTotalSinPromo() {
		// Obtiene el costo de todas las atracciones contenidas dentro del pack sin
		// aplicar promo
		for (Atraccion atraccion : atraccionesContenidas) {
			montoTotalSinDto += atraccion.getCosto();
		}
	}
	
	private void calcularTiempoRequeridoPromocion() {
		double suma = 0;
		for(Atraccion atraccion : atraccionesContenidas) {
			suma += atraccion.getTiempoRequerido();
		}
		
		super.setTiempoRequerido(suma);
	}
	
	public double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public double getMontoTotalConDto() {
		return montoTotalConDto;
	}

	public void setMontoTotalConDto(double montoTotalConDto) {
		this.montoTotalConDto = montoTotalConDto;
		super.setCosto(montoTotalConDto);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionesContenidas);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesContenidas, other.atraccionesContenidas);
	}

}
