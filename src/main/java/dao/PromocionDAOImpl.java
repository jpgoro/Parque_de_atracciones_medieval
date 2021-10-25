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
import model.SecretariaTurismo;
import model.TipoAtraccion;
import model.Usuario;

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
		List<Promocion> promociones = null;

		try {
			promociones = new LinkedList<Promocion>();
			int tipoPromocion = resultados.getInt(2);// absoluta, AxB o Porcentual
			int tipoAtraccionDeLaPromo = resultados.getInt(4);
			TipoAtraccion tipoAtraccion = null;
			switch (tipoAtraccionDeLaPromo) {
			case 1: {
				tipoAtraccion = TipoAtraccion.AVENTURA;
			}
			case 2:
				tipoAtraccion = TipoAtraccion.DEGUSTACION;
			case 3:
				tipoAtraccion = TipoAtraccion.PAISAJE;
			}

			switch (tipoPromocion) {
			case 1:
				double dtoTotal = resultados.getDouble(5);
				promociones.add(new PromocionAbsoluta(tipoAtraccion, resultados.getString(3), 
						traerLasAtracciones(resultados.getInt(2)), dtoTotal));
				break;
	// hasta aca llegue!!!!!!
			case 2:
				String[] atraccionesGratis = datos[4].split("-");
				promociones.add(
						new PromocionAxB(tipo, nombrePromo, SecretariaTurismo.traerAtracciones(atraccionesContenidas),
								SecretariaTurismo.traerAtracciones(atraccionesGratis)));
				break;

			case 3:
				double porcentajeDto = Double.parseDouble(datos[datos.length - 1]);
				promociones.add(new PromocionPorcentual(tipo, nombrePromo,
						SecretariaTurismo.traerAtracciones(atraccionesContenidas), porcentajeDto));
				break;
			}
						
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	
	private List<Atraccion> traerLasAtracciones(int idTipoPromo) {
		try {
			String sql = "select Atracciones.id, Atracciones.id_tipo, Atracciones.nombre, Atracciones.costo, Atracciones.tiempo, Atracciones.promocion, Atracciones.cupo\r\n"
					+ "from Promociones\r\n"
					+ "inner JOIN atracciones_en_promo on atracciones_en_promo.id_promocion = Promociones.id\r\n"
					+ "inner JOIN Atracciones on Atracciones.id = atracciones_en_promo.id_atraccion\r\n"
					+ "where Promociones.tipo_promocion values ?\r\n" + "";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idTipoPromo);
			ResultSet resultado = statement.executeQuery();

			LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultado.next()) {
				atracciones.add(toAtraccion(resultado));
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
		
		return new Atraccion( tipoAtraccion, resultados.getString(3), resultados.getDouble(4), resultados.getDouble(5),
				resultados.getInt(6));
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Promocion findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}