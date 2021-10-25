package dao;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}
	
	public static AtraccionDAO getAtraccionesDAO() {
		return new AtraccionDAOImpl();
	}
	
	public static PromocionDAO getPromodionesDAO() {
		return new PromocionDAOImpl();
	}
}
