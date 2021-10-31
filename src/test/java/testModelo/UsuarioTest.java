package testModelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.Sugerencia;
import model.TipoAtraccion;
import model.Usuario;


public class UsuarioTest {
	Usuario usuarioTest;
	
	@Before
	public void setUp() {
		usuarioTest = new Usuario(42359363,"Dante Mogni", 5000, 90, TipoAtraccion.AVENTURA);
	}
	
	@After
	public void tearDown() {
		usuarioTest = null;
	}
	
	@Test
	public void aceptaSugerencia() {
		Sugerencia sugerenciaTest = new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 2, 5);
		assertTrue(usuarioTest.aceptarSugerencia(sugerenciaTest));
	}
	
	@Test
	public void noAceptaSugerenciaPorFaltaDeMonedas() {
		Sugerencia sugerenciaTest = new Atraccion(TipoAtraccion.AVENTURA, "Moria", 50001, 2, 5);
		assertFalse(usuarioTest.aceptarSugerencia(sugerenciaTest));
	}
	
	@Test
	public void noAceptaSugerenciaPorFaltaDeTiempo() {
		Sugerencia sugerenciaTest = new Atraccion(TipoAtraccion.AVENTURA, "Moria", 10, 91, 5);
		assertFalse(usuarioTest.aceptarSugerencia(sugerenciaTest));
	}


}