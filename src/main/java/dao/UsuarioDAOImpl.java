package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public int insert(Usuario usuario) {
		try {
			String sql = "INSERT INTO Usuarios(dni, nombre, presupuesto, tiempo_disponible,id_Atraccion_preferida) VALUES('?,?,?,?,?')";
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
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int updatePresupuestoYTiempoDisponible(Usuario usuario) {
		try {
		String sql = "UPDATE USUARIOS SET tiempo_dispobile = ?, presupuesto = ? WHERE dni = ? ";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setDouble(1, usuario.getTiempoDisponible());
		statement.setDouble(2, usuario.getPresupuesto());
		statement.setInt(3, usuario.getDni());

		int rows = statement.executeUpdate();

		return rows;
		}catch (Exception e) {
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
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario findByDni(int dni)  {
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
		}catch (Exception e) {
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
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuario(ResultSet resultados) {
		try {
		return new Usuario(resultados.getInt(1), resultados.getString(2), resultados.getDouble(3),
				resultados.getDouble(4));
		}catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Usuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario findByNombre(String nombre)  {
		// TODO Auto-generated method stub
		return null;
	}

}
