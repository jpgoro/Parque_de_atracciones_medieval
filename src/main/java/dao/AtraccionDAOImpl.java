package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.TipoAtraccion;


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

	private Atraccion toAtraccion(ResultSet resultado) {
		try {
			TipoAtraccion tipo = TipoAtraccion.valueOf(resultado.getString(1));
			return new Atraccion( tipo, resultado.getString(2), resultado.getDouble(3), resultado.getDouble(4),
					resultado.getInt(5));
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

}
