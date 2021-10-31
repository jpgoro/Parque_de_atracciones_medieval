package testDAO;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.AtraccionDAO;
import dao.AtraccionDAOImpl;
import dao.DAOFactory;
import model.Atraccion;
import model.TipoAtraccion;

public class AtraccionDAOTest {
	AtraccionDAO atraccionDAO;
	Atraccion test;
	
	@Before
	public void setUp() {
		atraccionDAO = DAOFactory.getAtraccionesDAO();
		test = new Atraccion(TipoAtraccion.AVENTURA, "Atraccion Test", 10, 2, 5);
	}
	
	@Test
	public void insertaUnaAtraccion() {
		atraccionDAO.insert(test);
		
		// Si devuelve -1, significa que no se agregó.
		assertNotEquals(-1, AtraccionDAOImpl.obtenerIdAtraccion(test));
		
		atraccionDAO.delete(test); // La borro para no modificar la tabla
	}
	
	@Test
	public void eliminaUnaAtraccion() {
		atraccionDAO.insert(test);
		
		// Verifica que se haya agregado.
		assertNotEquals(-1, AtraccionDAOImpl.obtenerIdAtraccion(test));
		
		// La borro
		atraccionDAO.delete(test); 
		
		// Verifica que ya no exista mas
		assertEquals(-1, AtraccionDAOImpl.obtenerIdAtraccion(test));

	}

}
