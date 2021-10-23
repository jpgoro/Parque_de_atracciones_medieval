package dao;

import java.sql.SQLException;

import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {
	
	public abstract Usuario findByDni(int dni) throws SQLException;
	
	public abstract Usuario findByNombre(String nombre) throws SQLException;
	
}
