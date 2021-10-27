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

	private Atraccion toAtraccion(ResultSet resultados) {
		try {
			int tipoAtracciones = resultados.getInt(2);
			Atraccion aux= null;
			TipoAtraccion tipoAtraccion = null;
	
			switch (tipoAtracciones) {
			case 1:{
				tipoAtraccion = TipoAtraccion.AVENTURA;
				System.out.println("tipo de atraccion es:::::"+tipoAtraccion);
				System.out.println("Id "+ resultados.getInt(1));
				System.out.println("Nombre Atraccion "+ resultados.getString(3));
				System.out.println("Costo "+ resultados.getDouble(4));
				System.out.println("Tiempo Requerido "+ resultados.getDouble(5));
				System.out.println("Cupo Atraccion: "+ resultados.getInt(7));}
			case 2:{
				tipoAtraccion = TipoAtraccion.DEGUSTACION;
				System.out.println("tipo de atraccion es:::::"+tipoAtraccion);
				System.out.println("Id "+ resultados.getInt(1));
				System.out.println("Nombre Atraccion "+ resultados.getString(3));
				System.out.println("Costo "+ resultados.getDouble(4));
				System.out.println("Tiempo Requerido "+ resultados.getDouble(5));
				System.out.println("Cupo Atraccion: "+ resultados.getInt(7));}
			case 3:{
				tipoAtraccion = TipoAtraccion.PAISAJE;
				System.out.println("tipo de atraccion es:::::"+tipoAtraccion);
				System.out.println("Id "+ resultados.getInt(1));
				System.out.println("Nombre Atraccion "+ resultados.getString(3));
				System.out.println("Costo "+ resultados.getDouble(4));
				System.out.println("Tiempo Requerido "+ resultados.getDouble(5));
				System.out.println("Cupo Atraccion:"+ resultados.getInt(7));
				}
			}
			aux = new Atraccion( tipoAtraccion, resultados.getString(3), resultados.getDouble(4), resultados.getDouble(5),
								resultados.getInt(7));
			
			return aux;
	//		return new Atraccion( tipoAtraccion, resultados.getString(3), resultados.getDouble(4), resultados.getDouble(5),
	//				resultados.getInt(7));
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
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO Atracciones(id_tipo, nombre, costo, tiempo, promocion, cupo) VALUES(?,?,?,?,?,?)";
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
