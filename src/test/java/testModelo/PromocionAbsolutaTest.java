package testModelo;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.TipoAtraccion;

public class PromocionAbsolutaTest {

	@Test
	public void calculaCostoRequeridoSinPromoAbsolutas() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		// Moria: 10 costo
		// Mordor: 25 costo
		// costo esperado: 35
		
		Promocion promocionTest = new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Pack Aventura", atraccionesContenidas,36);
		
		assertEquals(35, promocionTest.getMontoTotalSinDto(), 0.01);
	}
	
	@Test
	public void calcularTiempoRequeridoPromocionAbsolutas() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		// Moria: 2 tiempo
		// Mordor: 3 tiempo
		// tiempo esperado: 5
		
		Promocion promocionTest = new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Pack Aventura", atraccionesContenidas,36);
		
		assertEquals(5, promocionTest.getTiempoRequerido(), 0.01);
	}
	
	@Test
	public void calcularCostoPromocionAbsolutas() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));
		
		Promocion promocionTest = new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Pack Aventura", atraccionesContenidas,5);
		// Costo total sin promo: 35 
		// Descuento absoluto de 5
		// Nuevo monto: 30
		
		assertEquals(30, promocionTest.getMontoTotalConDto(), 0.01);
	}

}