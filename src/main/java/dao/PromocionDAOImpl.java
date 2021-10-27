package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.TipoAtraccion;


public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM Promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			LinkedList<Promocion> promociones = new LinkedList<Promocion>();
			while (resultado.next()) {
				promociones.add(toPromocion(resultado));
			}
			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromocion(ResultSet resultados) {
		Promocion promo = null;
		// List<Promocion> promociones = null;
		try {
			// promociones = new LinkedList<Promocion>();
			int tipoPromocion = resultados.getInt(2);// absoluta, AxB o Porcentual
			int tipoAtraccionDeLaPromo = resultados.getInt(4);
			TipoAtraccion tipoAtraccion = null;
			
			switch (tipoAtraccionDeLaPromo) {
			case 1:  
				tipoAtraccion = TipoAtraccion.AVENTURA;
			case 2:
				tipoAtraccion = TipoAtraccion.DEGUSTACION;
			case 3:
				tipoAtraccion = TipoAtraccion.PAISAJE;
			}

			switch (tipoPromocion) {
			case 1: {// Absuluta
				double dtoTotal = resultados.getDouble(5);
				promo= new PromocionAbsoluta(tipoAtraccion, resultados.getString(3),
						traerLasAtracciones(resultados.getInt(1)), dtoTotal);// 1 es el id de la promo
			}
			case 2: {// AxB
				promo = new PromocionAxB(tipoAtraccion, resultados.getString(3),
						traerLasAtracciones(resultados.getInt(1)), traerLasAtraccionesGratis(resultados.getInt(1)));
			}
			case 3: {// Porcentual
				double porcentajeDto = resultados.getDouble(5);
				promo = new PromocionPorcentual(tipoAtraccion, resultados.getString(3),
						traerLasAtracciones(resultados.getInt(1)), porcentajeDto);
			}
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return promo;
	}

	private List<Atraccion> traerLasAtraccionesGratis(int idPromo) {
		try {
			String sql = "select Atracciones.id, Atracciones.id_tipo, Atracciones.nombre, Atracciones.costo, Atracciones.tiempo, Atracciones.promocion, Atracciones.cupo\r\n"
					+ "from Promociones\r\n"
					+ "inner JOIN atracciones_gratis_en_promo on atracciones_gratis_en_promo.id_promocion = Promociones.id\r\n"
					+ "inner JOIN Atracciones on Atracciones.id = atracciones_gratis_en_promo.id_atraccion\r\n"
					+ "where Promociones.id = 2\r\n" + "";// tipo promocion 2 son las AxB
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			//statement.setInt(1, idPromo);
			ResultSet resultado = statement.executeQuery();
			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultado.next()) {
				atracciones.add(this.toAtraccion(resultado));
			}
			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private List<Atraccion> traerLasAtracciones(int idPromo) {
		try {
			String sql = "select Atracciones.id, Atracciones.id_tipo, Atracciones.nombre, Atracciones.costo, Atracciones.tiempo, Atracciones.promocion, Atracciones.cupo\r\n"
					+ "from Promociones\r\n"
					+ "inner JOIN atracciones_en_promo on atracciones_en_promo.id_promocion = Promociones.id\r\n"
					+ "inner JOIN Atracciones on Atracciones.id = atracciones_en_promo.id_atraccion\r\n"
					+ "where Promociones.id = ?\r\n" + "";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromo);
			ResultSet resultado = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultado.next()) {
				atracciones.add(this.toAtraccion(resultado));
			}
			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) {
		try {
			int tipoAtracciones = resultados.getInt(2);
			TipoAtraccion tipoAtraccion = null;

			switch (tipoAtracciones) {
			case 1:
				tipoAtraccion = TipoAtraccion.AVENTURA;
			case 2:
				tipoAtraccion = TipoAtraccion.DEGUSTACION;
			case 3:
				tipoAtraccion = TipoAtraccion.PAISAJE;
			}
			return new Atraccion(tipoAtraccion, resultados.getString(3), resultados.getDouble(4),
					resultados.getDouble(5), resultados.getInt(7));
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			resultado.next();
			int total = resultado.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Promocion promocion) {
//		try {
//			String sql = "INSERT INTO promociones(tipo_promocion, nombre_promo, id_tipo_atraccion, descuento) VALUES(?,?,?,?)";
//			Connection conn = ConnectionProvider.getConnection();
//
//			PreparedStatement statement = conn.prepareStatement(sql);
//			
//			// Esta es una forma de hacerlo. Otra seria crear tablas en la base de datos para cada tipo de promo
//			if(promocion instanceof PromocionAbsoluta) {
//				statement.setInt(1, 1);
//			} else if (promocion instanceof PromocionAxB) {
//				statement.setInt(1, 2);
//			} else if (promocion instanceof PromocionPorcentual) {
//				statement.setInt(1, 3);
//			}
//			
//			statement.setString(2, promocion.getNombre());
//			statement.setDouble(3, promocion.getIdTipo());
//			statement.setDouble(4, promocion.getMontoDescuento());
//
//			int rows = statement.executeUpdate();
//
//			return rows; // devuelve las filas que cambiaron
//		} catch (Exception e) {
//			throw new MissingDataException(e);
//		}
	}

	@Override
	public int update(Promocion promocion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion promocion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Promocion findByNombre(String nombre) {
		try {
			String sql = "SELECT * FROM promociones WHERE nombre_promo = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();	
			
			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

}
