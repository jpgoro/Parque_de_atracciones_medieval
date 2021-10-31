package testDAO;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.UsuarioDAO;
import dao.UsuarioDAOImpl;
import model.TipoAtraccion;
import model.Usuario;


public class UsuarioDAOTest {

	UsuarioDAO usuarioDAO;
	Usuario userTest;
	
	@Before
	public void setUp() {
		usuarioDAO = DAOFactory.getUsuarioDAO();
		userTest = new Usuario(42359363,"Dante Mogni", 5000, 90, TipoAtraccion.AVENTURA);
	}
	
	@After
	public void tearDown() {
		usuarioDAO.delete(userTest); // La borro para no modificar la tabla
	}
	
	@Test
	public void insertaUnUsuario() {
		usuarioDAO.insert(userTest);
		
		// Si devuelve -1, significa que no se agregó.
		assertNotEquals(-1, UsuarioDAOImpl.obtenerIdUsuario(userTest));
	}
	
	@Test
	public void eliminaUnUsuario() {
		usuarioDAO.insert(userTest);
		
		// Verifica que se haya agregado.
		assertNotEquals(-1, UsuarioDAOImpl.obtenerIdUsuario(userTest));
		
		// La borro
		usuarioDAO.delete(userTest); 
		
		// Verifica que ya no exista mas
		assertEquals(-1, UsuarioDAOImpl.obtenerIdUsuario(userTest));

	}
	
	@Test
	public void encuentraPorNombre() {
		usuarioDAO.insert(userTest);

		Usuario testUsuario = usuarioDAO.findByNombre("Dante Mogni");
		
		assertEquals("Dante Mogni", testUsuario.getNombreDeUsuario());
	}
	
	@Test
	public void encuentraPorDNI() {
		usuarioDAO.insert(userTest);

		Usuario testUsuario = usuarioDAO.findByDni(42359363);
		
		assertEquals("Dante Mogni", testUsuario.getNombreDeUsuario());
	}
}
