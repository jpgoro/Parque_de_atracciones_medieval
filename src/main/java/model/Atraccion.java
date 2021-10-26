package model;

public class Atraccion extends Sugerencia {

	private int cupoMaximo;
	//private int cupoActual = 0;

	public Atraccion(TipoAtraccion tipoDeAtraccion, String nombreAtraccion, 
			double costoAtraccion, double tiempoRequerido, int cupoMaximo) {
		super(tipoDeAtraccion, nombreAtraccion, costoAtraccion, tiempoRequerido, false);
		this.cupoMaximo = cupoMaximo;
//		cupoActual++; 
//		
//		if(cupoActual > cupoMaximo) {
//			throw new Error("No hay suficientes cupos");
//		}
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
		return "Atraccion ( " + super.getNombre() + ", Precio : " + super.getCosto() + ", Duracion : " + super.getTiempoRequerido()+ ", Cupos Restantes: " + cupoActual
				+ ", Tipo : " + super.getTipo() + ") ";
	}	

}
