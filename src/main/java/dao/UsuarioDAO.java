package dao;

import model.Sugerencia;
import model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByDni(int dni);

	public abstract Usuario findByNombre(String nombre);
	
	public boolean aceptarSugerencia(Usuario usuario, Sugerencia nueva);


}
