package model;

public class Atraccion extends Sugerencia {

	private int cupoMaximo;

	public Atraccion(TipoAtraccion tipoDeAtraccion, String nombreAtraccion, 
			double costoAtraccion, double tiempoRequerido, int cupoMaximo) {
		super(tipoDeAtraccion, nombreAtraccion, costoAtraccion, tiempoRequerido, false);
		this.cupoMaximo = cupoMaximo;
	}
	
	@Override
	public void ocuparLugar() throws Exception {
		if(hayCupo()) {
			super.cupoActual++;
		} else {
			throw new Exception("No se puede comprar la atraccion por falta de cupos.");
		}
	}
	
	@Override
	public boolean hayCupo() {
		return super.cupoActual < this.cupoMaximo;
	}


	@Override
	public String toString() {
		return "Atraccion ( " + super.getNombre() + ", Precio : " + super.getCosto() + ", Duracion : " + super.getTiempoRequerido()+ ", Cupos Restantes: " + (cupoMaximo-cupoActual)
				+ ", Tipo : " + super.getTipo() + ") ";
	}	

}
