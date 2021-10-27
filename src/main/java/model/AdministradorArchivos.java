package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dao.UsuarioDAO;

public class AdministradorArchivos {
	public static List<Usuario> leerUsuarios() throws SQLException {
		List<Usuario> usuarios = null;

		FileReader fr = null;
		BufferedReader br = null;

	//	UserDAO userDAO = new UserDAO();

		try {
			usuarios = new LinkedList<Usuario>();
			fr = new FileReader("archivos/usuarios.txt");
			br = new BufferedReader(fr);

			// lectura del fichero
			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] datos = linea.split(";"); // Separo los datos

					// Se parsean los datos del archivo
					int dni 			= Integer.parseInt(datos[0]);
					String nombre 		= datos[1];
					double presupuesto 	= Double.parseDouble(datos[2]);
					double tiempo 		= Double.parseDouble(datos[3]);
					TipoAtraccion atrac = TipoAtraccion.valueOf(datos[4]);

					// Se extrae el usuario con dichos datos
					usuarios.add(new Usuario(dni, nombre, presupuesto, tiempo, atrac));
			//		Usuario usuaux = new Usuario(dni, nombre, presupuesto, tiempo, atrac);
			//		userDAO.insert(usuaux);
				} catch (NumberFormatException e) {
					System.err.println("Uno o mas valores no se han podido leer");

				} catch (IllegalArgumentException e) {
					System.err.println("(leerUsuarios) No se ha podido leer la ateccion preferida"); // Cuando falla enum.valueOf()

				} finally {
					linea = br.readLine();// Continua la lectura del archivo
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public static List<Atraccion> leerAtracciones() {
		List<Atraccion> atracciones = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			atracciones = new LinkedList<Atraccion>();
			fr = new FileReader("archivos/atracciones.txt");
			br = new BufferedReader(fr);

			// lectura del fichero
			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] datos = linea.split(";"); // Separo los datos

					// Se parsean los datos del archivo
					String nombre 		= datos[0];
					double costo 		= Double.parseDouble(datos[1]);
					double tiempo 		= Double.parseDouble(datos[2]);
					int cupo 			= Integer.parseInt(datos[3]);
					TipoAtraccion tipo = TipoAtraccion.valueOf(datos[4]);
					// Se agregan las atracciones con dichos datos
					atracciones.add(new Atraccion(tipo, nombre, costo, tiempo, cupo));
					
				} catch (NumberFormatException e) {
					System.err.println("Uno o mas valores no se han podido leer");

				} catch (IllegalArgumentException e) {
					System.err.println("(leerAtracciones) No se ha podido leer el tipo de atraccion preferida"); // Cuando falla
																								// enum.valueOf()

				} finally {
					linea = br.readLine();// Continua la lectura del archivo
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return atracciones;
	}
	
	public static List<Promocion> leerPromociones() throws Exception {

		List<Promocion> promociones = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			promociones = new LinkedList<Promocion>();
			fr = new FileReader("archivos/promociones.txt");
			br = new BufferedReader(fr);

			// lectura del fichero
			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] datos = linea.split(";"); // Separo los datos
					
					String tipoPromo 			   = datos[0]; //obtengo el tipo de promocion
					String nombrePromo			   = datos[1];
					TipoAtraccion tipo 		   	   = TipoAtraccion.valueOf(datos[2]);
					String[] atraccionesContenidas = datos[3].split("-"); 
					
					List<Atraccion> atraccionesPromo = SecretariaTurismo.traerAtracciones(atraccionesContenidas);

					switch(tipoPromo) {
						case "Absoluta":
							double dtoTotal = Double.parseDouble(datos[datos.length - 1]);
							promociones.add(new PromocionAbsoluta(tipo,
											nombrePromo,
											atraccionesPromo, 
											dtoTotal));
							break;
							
						case "AxB":
							String[] atraccionesGratis = datos[4].split("-");
							List<Atraccion> atraccionesGratisAxB = SecretariaTurismo.traerAtracciones(atraccionesGratis);
							atraccionesPromo.addAll(atraccionesGratisAxB); //agrego tambien las atracciones gratis a la lista de promos contenidas
							promociones.add(new PromocionAxB(tipo,
											nombrePromo,
											atraccionesPromo,
											atraccionesGratisAxB));
							break;
							
						case "Porcentual":
							double porcentajeDto = Double.parseDouble(datos[datos.length - 1]);
							promociones.add(new PromocionPorcentual(tipo,
											nombrePromo,
											atraccionesPromo,
											porcentajeDto));
							break;
					}
						
					
				} catch (NumberFormatException e) {
					System.err.println("Uno o mas valores no se han podido leer");

				} catch (IllegalArgumentException e) {
					System.err.println("(leerPromociones) No se ha podido leer el tipo de atraccion preferida"); // Cuando falla
																								// enum.valueOf()

				} finally {
					linea = br.readLine();// Continua la lectura del archivo
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return promociones;
	}
	
	public static void escribirUsuario(Usuario usuario) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("archivos-salida/"+usuario.getNombreDeUsuario()+".txt"));
		salida.println("Archivo de salida.\n"+usuario.toString()+"\n"+usuario.getItinerarioPersonal().toString());
		salida.close();
	}

}
