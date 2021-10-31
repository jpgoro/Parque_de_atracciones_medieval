package testModelo;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.PromocionAxB;
import model.TipoAtraccion;

public class PromocionAxBTest {

	@Test
	public void calculaCostoRequeridoSinPromoAxB() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		
		List<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();
		atraccionesGratis.add(new Atraccion(TipoAtraccion.AVENTURA, "Bosque Negro", 3, 4, 12));
		
		atraccionesContenidas.addAll(atraccionesGratis);
		
		Promocion promocionTest = new PromocionAxB(TipoAtraccion.AVENTURA,"Pack Aventura",atraccionesContenidas,atraccionesGratis);
		
		// Moria: 10 costo
		// Mordor: 25 costo
		// Bosque Negro: 3 costo
		// costo esperado: 38
		
		assertEquals(38, promocionTest.getMontoTotalSinDto(), 0.01);
	}
	
	@Test
	public void calcularTiempoRequeridoPromocionAxB() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		
		List<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();
		atraccionesGratis.add(new Atraccion(TipoAtraccion.AVENTURA, "Bosque Negro", 3, 4, 12));
		
		atraccionesContenidas.addAll(atraccionesGratis);
		
		Promocion promocionTest = new PromocionAxB(TipoAtraccion.AVENTURA,"Pack Aventura",atraccionesContenidas,atraccionesGratis);
		
		// Moria: 2 tiempo
		// Mordor: 3 tiempo
		// Bosque Negro: 4 tiempo
		// tiempo esperado: 9
		
		assertEquals(9, promocionTest.getTiempoRequerido(), 0.01);
	}
	
	@Test
	public void calcularCostoPromocionAxB() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		
		List<Atraccion> atraccionesGratis = new LinkedList<Atraccion>();
		atraccionesGratis.add(new Atraccion(TipoAtraccion.AVENTURA, "Bosque Negro", 3, 4, 12));
		
		atraccionesContenidas.addAll(atraccionesGratis);
		
		Promocion promocionTest = new PromocionAxB(TipoAtraccion.AVENTURA,"Pack Aventura",atraccionesContenidas,atraccionesGratis);
		
		// Costo total sin promo: 38 
		// Descuento de 3
		// Nuevo monto: 35
		
		assertEquals(35, promocionTest.getMontoTotalConDto(), 0.01);
	}

}