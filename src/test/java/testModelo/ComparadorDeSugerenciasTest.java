package testModelo;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.ComparadorDeSugerencias;
import model.Promocion;
import model.PromocionAbsoluta;
import model.TipoAtraccion;

public class ComparadorDeSugerenciasTest {
	ComparadorDeSugerencias comparadorTest;
	
	@Before
	public void setUp() {
		comparadorTest = new ComparadorDeSugerencias(TipoAtraccion.AVENTURA);
	}
	
	@After
	public void tearDown() {
		comparadorTest = null;
	}
	
	@Test
	public void priorizaPreferencia() {
		Atraccion atraccionTestUno = new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 5);
		Atraccion atraccionTestDos = new Atraccion(TipoAtraccion.DEGUSTACION, "Lothlorien", 35, 1, 30);
		
		assertEquals(-1, comparadorTest.compare(atraccionTestUno, atraccionTestDos));
		assertEquals(1, comparadorTest.compare(atraccionTestDos, atraccionTestUno));

	}
	
	@Test
	public void priorizaPromocion() {
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 6));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4));

		Promocion promocionTest = new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Pack Aventura", atraccionesContenidas,36);
		
		Atraccion atraccionTestUno = new Atraccion(TipoAtraccion.AVENTURA, "Bosque Negro", 3, 4, 12);
		
		assertEquals(-1, comparadorTest.compare(promocionTest, atraccionTestUno));
		assertEquals(1, comparadorTest.compare(atraccionTestUno, promocionTest));
	}
	
	@Test
	public void priorizaDinero() {
		Atraccion atraccionTestUno = new Atraccion(TipoAtraccion.AVENTURA, "Bosque Negro", 3, 4, 12);
		Atraccion atraccionTestDos = new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 25, 3, 4);

		assertEquals(-1, comparadorTest.compare(atraccionTestDos, atraccionTestUno));
		assertEquals(1, comparadorTest.compare(atraccionTestUno, atraccionTestDos));
	}
	
	@Test
	public void priorizaTiempo() {
		Atraccion atraccionTestUno = new Atraccion(TipoAtraccion.AVENTURA, "Bosque Negro", 3, 4, 12);
		Atraccion atraccionTestDos = new Atraccion(TipoAtraccion.AVENTURA, "Mordor", 3, 10, 4);

		assertEquals(-1, comparadorTest.compare(atraccionTestDos, atraccionTestUno));
		assertEquals(1, comparadorTest.compare(atraccionTestUno, atraccionTestDos));
	}

}