package model;

import java.util.Objects;

public abstract class Sugerencia {

	protected TipoAtraccion tipo;
	protected String nombre;
	protected double costo;
	protected double tiempoRequerido;
	protected boolean promocion;
	protected int cupoActual = 0;
	
	public int getCupoActual() {
		return cupoActual;
	}

	public Sugerencia(TipoAtraccion tipo, String nombre, Double costo, 
			Double tiempoRequerido, boolean promocion) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempoRequerido = tiempoRequerido;
		this.promocion = promocion;
	}
	
	public Sugerencia(TipoAtraccion tipo, String nombre, boolean promocion) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.promocion = promocion;
	}

	public abstract void ocuparLugar() throws Exception;
	public abstract boolean hayCupo();
	
	
	public int getIdTipo() {
		switch(this.getTipo()) {
		case AVENTURA:
			return 1;
		case DEGUSTACION:
			return 2;
		case PAISAJE:
			return 3;
		default:
			return -1;
		}
	}
	
	public TipoAtraccion getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public double getCosto() {
		return costo;
	}

	public double getTiempoRequerido() {
		return tiempoRequerido;
	}

	public void setTiempoRequerido(double tiempoRequerido) {
		this.tiempoRequerido = tiempoRequerido;
	}

	public boolean esPromocion() {
		return promocion;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, promocion, tiempoRequerido, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sugerencia other = (Sugerencia) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(nombre, other.nombre) && promocion == other.promocion
				&& Double.doubleToLongBits(tiempoRequerido) == Double.doubleToLongBits(other.tiempoRequerido)
				&& tipo == other.tipo;
	}
	


}
