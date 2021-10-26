package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Sugerencia;
import model.TipoAtraccion;
import model.Usuario;


public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM Atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultado.next()) {
				atracciones.add(toAtraccion(resultado));
			}
			return atracciones;
			}catch (Exception e) {
				throw new MissingDataException(e);
			}
	}

	private Atraccion toAtraccion(ResultSet resultados) {
		try {
			int tipoAtracciones = resultados.getInt(2);
			TipoAtraccion tipoAtraccion = null;
	
			switch (tipoAtracciones) {
			case 1:{
				tipoAtraccion = TipoAtraccion.AVENTURA;
				System.out.println("tipo de atraccion es:::::"+tipoAtraccion);
				System.out.println("Id "+ resultados.getInt(1));
				System.out.println("Nombre Atraccion "+ resultados.getString(3));
				System.out.println("Costo "+ resultados.getDouble(4));
				System.out.println("Tiempo Requerido "+ resultados.getDouble(5));
				System.out.println("Cupo "+ resultados.getInt(7));}
			case 2:{
				tipoAtraccion = TipoAtraccion.DEGUSTACION;
				System.out.println("tipo de atraccion es:::::"+tipoAtraccion);
				System.out.println("Id "+ resultados.getInt(1));
				System.out.println("Nombre Atraccion "+ resultados.getString(3));
				System.out.println("Costo "+ resultados.getDouble(4));
				System.out.println("Tiempo Requerido "+ resultados.getDouble(5));
				System.out.println("Cupo "+ resultados.getInt(7));}
			case 3:{
				tipoAtraccion = TipoAtraccion.PAISAJE;
				System.out.println("tipo de atraccion es:::::"+tipoAtraccion);
				System.out.println("Id "+ resultados.getInt(1));
				System.out.println("Nombre Atraccion "+ resultados.getString(3));
				System.out.println("Costo "+ resultados.getDouble(4));
				System.out.println("Tiempo Requerido "+ resultados.getDouble(5));
				System.out.println("Cupo "+ resultados.getInt(7));
				}
			}
			
			return new Atraccion( tipoAtraccion, resultados.getString(2), resultados.getDouble(3), resultados.getDouble(4),
					resultados.getInt(5));
			}catch (Exception e) {
				throw new MissingDataException(e);
			}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			resultado.next();
			int total = resultado.getInt("TOTAL");

			return total;
			}catch (Exception e) {
				throw new MissingDataException(e);
			}
	}

	@Override
	public int insert(Atraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Atraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Atraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Atraccion findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean aceptarSugerencia(Usuario usuario, Sugerencia nueva) {
		// TODO Auto-generated method stub
		return false;
	}

}
