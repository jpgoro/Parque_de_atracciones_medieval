package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.Sugerencia;
import model.TipoAtraccion;

import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios(dni, nombre, presupuesto, tiempo_disponible,id_Atraccion_preferida) VALUES(?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getDni());
			statement.setString(2, usuario.getNombreDeUsuario());
			statement.setDouble(3, usuario.getPresupuesto());
			statement.setDouble(4, usuario.getTiempoDisponible());
			statement.setInt(5, usuario.idAtraccionPreferida());
			int rows = statement.executeUpdate();

			return rows; // devuelve las filas que cambiaron
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM Usuarios WHERE dni = ? ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getDni());
			int rows = statement.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updatePresupuestoYTiempoDisponible(Usuario usuario) {
		try {
			String sql = "UPDATE usuarios SET tiempo_disponible = ?, presupuesto = ? WHERE dni = ? ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, usuario.getTiempoDisponible());
			statement.setDouble(2, usuario.getPresupuesto());
			statement.setInt(3, usuario.getDni());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public LinkedList<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM Usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet resultado = statement.executeQuery();

			LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultado.next()) {
				usuarios.add(toUsuario(resultado));
				
			}
			
			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByDni(int dni) {
		try {
			String sql = "SELECT * FROM usuarios WHERE dni = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, dni);
			ResultSet resultado = statement.executeQuery();

			Usuario usuario = null;

			if (resultado.next()) {
				usuario = toUsuario(resultado);
			}
			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM usuarios";
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

	private Usuario toUsuario(ResultSet resultados) {
		try {
			
			int tipoAtracciones = resultados.getInt(5);
			TipoAtraccion tipoAtraccion = null;
	
			switch (tipoAtracciones) {
			case 1:{
				tipoAtraccion = TipoAtraccion.AVENTURA;}
			case 2:{
				tipoAtraccion = TipoAtraccion.DEGUSTACION;}
			case 3:{
				tipoAtraccion = TipoAtraccion.PAISAJE;}
			}
			// parametros: dni, Nombre, Presupuesto, tiempoDisponible, tipo de atraccion
			return new Usuario(resultados.getInt(2), resultados.getString(3), resultados.getDouble(4),
					resultados.getDouble(5), tipoAtraccion);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	public boolean aceptarSugerencia(Usuario usuario, Sugerencia nueva) {
		boolean agregada = false;
		if(puedoAceptar(usuario, nueva)) {			
			usuario.setPresupuesto(usuario.getPresupuesto()-nueva.getCosto());
			usuario.setTiempoDisponible(usuario.getTiempoDisponible()-nueva.getTiempoRequerido());
			this.updatePresupuestoYTiempoDisponible(usuario);
	
			//hacer metodo para disminuir el cupo de la atraccin
			agregada = true;
			
			this.agregarAlItinerario(usuario, nueva);
	//		agregada = itinerarioPersonal.aceptarSugerencia(nueva);// hay que hacer este metodo para la tabla itinerario
		}		
		return agregada;
	}

		private int agregarAlItinerario(Usuario usuario, Sugerencia nueva) {
		try {
			String sql = "INSERT INTO itinerario (id_usuario, id_atraccion) VALUES (?,?)"; 
			//String sql = "UPDATE ATRACCIONES SET CUPO = ? WHERE ID = ? ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, this.obtenerIdUsuario(usuario)); 
			statement.setInt(2, this.obtenerIdAtraccion(nueva));

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}
		
		private int obtenerIdAtraccion(Sugerencia sugerencia) {
			try {
				String sql = "SELECT id FROM atracciones WHERE nombre = ?";
				Connection conn = ConnectionProvider.getConnection();

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, sugerencia.getNombre());
				System.out.println("nombre de la sugerencia ::::::::::::::::::::: " + sugerencia.getNombre() );
				ResultSet resultado = statement.executeQuery();	
				
				int id = -1;
				
				if(resultado.next()) {
					id = resultado.getInt("id");
				}

				return id;
				 
			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
		
		
		private int obtenerIdUsuario(Usuario usuario) {
			try {
				String sql = "SELECT id FROM usuarios WHERE nombre = ?";
				Connection conn = ConnectionProvider.getConnection();

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, usuario.getNombreDeUsuario());
				ResultSet resultado = statement.executeQuery();	
				
				int id = -1;
				
				if(resultado.next()) {
					id = resultado.getInt("id");
				}

				return id;
				 
			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}

	private boolean puedoAceptar(Usuario usuario, Sugerencia sugerencia) {
		Usuario unUsuario = null;
		unUsuario = this.findByDni(usuario.getDni());
		return unUsuario.getTiempoDisponible() >= sugerencia.getTiempoRequerido()
				&& unUsuario.getPresupuesto() >= sugerencia.getCosto();
	}
	
		
	@Override
	public int update(Usuario usuario) {
		return 0;
/*		try {
			String sql = "UPDATE USUARIOS SET (PRESUPUESTO,TIEMPO_DISPONIBLE) = (?,?) WHERE DNI = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, usuario.getPresupuesto());
			statement.setDouble(2, usuario.getTiempoDisponible());
			statement.setInt(3, usuario.getDni());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}*/
	}

	@Override
	public Usuario findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
