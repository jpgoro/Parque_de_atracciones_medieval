package dao;

import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion>{
	
	public abstract Atraccion findByNombre(String nombre);
	
}
