package testModelo;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.PromocionPorcentual;
import model.TipoAtraccion;

public class PromocionPorcentualTest {

	@Test
	public void calculaCostoRequeridoSinPromoPorcentual() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		Promocion promocionTest = new PromocionPorcentual(TipoAtraccion.AVENTURA,"Pack Aventura",atraccionesContenidas,20);		
		
		// Moria: 10 costo
		// Mordor: 25 costo
		// costo esperado: 35
		
		assertEquals(35, promocionTest.getMontoTotalSinDto(), 0.01);
	}
	
	@Test
	public void calcularTiempoRequeridoPromocionPorcentuales() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		Promocion promocionTest = new PromocionPorcentual(TipoAtraccion.AVENTURA,"Pack Aventura",atraccionesContenidas,20);		
		
		// Moria: 2 tiempo
		// Mordor: 3 tiempo
		// tiempo esperado: 5
		
		assertEquals(5, promocionTest.getTiempoRequerido(), 0.01);
	}
	
	
	@Test
	public void calcularCostoPromocionPorcentual() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		Promocion promocionTest = new PromocionPorcentual(TipoAtraccion.AVENTURA,"Pack Aventura",atraccionesContenidas,20);		
		
		// Costo total sin promo: 35 
		// Descuento del -20%
		// Nuevo monto: 28
		
		assertEquals(28, promocionTest.getMontoTotalConDto(), 0.01);
	}
}