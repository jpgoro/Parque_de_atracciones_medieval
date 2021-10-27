package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.TipoAtraccion;


public class AtraccionDAOImpl implements AtraccionDAO {

	
	@Override
	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM atracciones";
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
	
	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		
		TipoAtraccion tipo = null;
		
		switch(resultados.getInt(2)) {
		case 1:
			tipo = TipoAtraccion.AVENTURA;
			break;
		case 2:
			tipo = TipoAtraccion.DEGUSTACION;
			break;
		case 3:
			tipo = TipoAtraccion.PAISAJE;
			break;
		}
		return new Atraccion(tipo, resultados.getString(3), resultados.getDouble(4), resultados.getDouble(5),
								resultados.getInt(7));
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
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO atracciones(id_tipo, nombre, costo, tiempo, promocion, cupo) VALUES(?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, atraccion.getIdTipo());
			statement.setString(2, atraccion.getNombre());
			statement.setDouble(3, atraccion.getCosto());
			statement.setDouble(4, atraccion.getTiempoRequerido());
			statement.setBoolean(5, atraccion.esPromocion());
			statement.setInt(6, atraccion.getCupoActual());

			int rows = statement.executeUpdate();

			return rows; // devuelve las filas que cambiaron
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE atracciones SET costo = ? WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, atraccion.getCosto());
			statement.setString(2, atraccion.getNombre());
			
			int rows = statement.executeUpdate();

			return rows; // devuelve las filas que cambiaron
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM atracciones WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			
			int rows = statement.executeUpdate();

			return rows; // devuelve las filas que cambiaron
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM atracciones WHERE nombre = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
