package testModelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.Itinerario;
import model.TipoAtraccion;

public class AtraccionTest {

	Atraccion atraccionTest;
	
	@Before
	public void setUp() {
		atraccionTest = new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 5);
	}
	
	@After
	public void tearDown() {
		atraccionTest = null;
	}
	
	@Test
	public void seAumentaElCupoActualMedianteItinerario() {
		Itinerario itinerarioTest = new Itinerario();
		
		// Agrego 3 veces
		itinerarioTest.aceptarSugerencia(atraccionTest);
		itinerarioTest.aceptarSugerencia(atraccionTest);
		itinerarioTest.aceptarSugerencia(atraccionTest);

		assertEquals(3, atraccionTest.getCupoActual());
	}
	
	@Test
	public void seAumentaElCupoActualMedianteAtraccion() throws Exception {		
		// Agrego 3 veces
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();

		assertEquals(3, atraccionTest.getCupoActual());
	}
	
	@Test
	public void hayCupo() throws Exception {		
		atraccionTest.ocuparLugar();
		
		assertTrue(atraccionTest.hayCupo());
	}
	
	@Test
	public void noHayCupo() throws Exception {		
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();

		assertFalse(atraccionTest.hayCupo());
	}
	
	@Test(expected = Exception.class)
	public void noPermiteOcuparLugar() throws Exception {		
		// Ocupo 7 veces (el limite es 5)
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
		atraccionTest.ocuparLugar();
	}
}