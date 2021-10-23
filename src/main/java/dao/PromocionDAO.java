package dao;


import model.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion>{
	public abstract Promocion findByNombre(String nombre);
}
