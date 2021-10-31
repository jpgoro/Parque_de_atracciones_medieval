package testModelo;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.Itinerario;
import model.TipoAtraccion;

public class ItinerarioTest {

	Itinerario itinerarioTest;
	
	@Before
	public void setUp() {
		itinerarioTest = new Itinerario();
	}
	
	@After
	public void tearDown() {
		itinerarioTest = null;
	}
	
	@Test
	public void noAceptaCuandoNoHayCupo() {
		Atraccion atraccionTest = new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 0);
		boolean aceptado = itinerarioTest.aceptarSugerencia(atraccionTest);
		
		assertFalse(aceptado);
	}

}