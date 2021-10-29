package app;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UsuarioDAO;
import model.AdministradorArchivos;
import model.Atraccion;
import model.ComparadorDeSugerencias;
import model.Promocion;
import model.PromocionAbsoluta;
import model.Sugerencia;
import model.TipoAtraccion;
import model.Usuario;

public class App {
	private static List<Usuario>    usuarios 	= new LinkedList<Usuario>();
	private static List<Atraccion>  atracciones = new LinkedList<Atraccion>(); 
	private static List<Promocion>  promociones = new LinkedList<Promocion>();
	private static List<Sugerencia> sugerencias = new LinkedList<Sugerencia>();
	
	public static void main(String[] args) throws Exception {
		
		// Levanto los datos desde los archivos
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionesDAO();
		PromocionDAO promocionDAO = DAOFactory.getPromocionesDAO();
		usuarios = usuarioDAO.findAll();// devuelve una linkedlist de Usuario
		atracciones= atraccionDAO.findAll();// devuelve una linkedlist de Atraccion
		
		promociones= promocionDAO.findAll();

		
		List<Atraccion> atraccionesContenidas = new LinkedList<Atraccion>();
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "Moria", 999, 999, 999));
		atraccionesContenidas.add(new Atraccion(TipoAtraccion.AVENTURA, "ATRACCION EN PROMO PRUEBA 2", 999, 999, 999));
		
		promocionDAO.insert(new PromocionAbsoluta(TipoAtraccion.AVENTURA, "Pack PRUEBA 2", atraccionesContenidas,-1));
		//	usuarios    = AdministradorArchivos.leerUsuarios();
	//	atracciones = AdministradorArchivos.leerAtracciones();
	//	promociones = AdministradorArchivos.leerPromociones();

		// Preparo para leer desde la consola 
		Scanner in = new Scanner(System.in);
		String opcion = null;
		String mensaje = null;
		System.out.println("##################################################################################\n"
						 + "##                             BIENVENIDO A TIERRA MEDIA                        ##\n"
						 + "##################################################################################");
		
		for(Usuario usuario : usuarios) {
			// Ordeno segun las preferencias del usuario
			Collections.sort(atracciones, new ComparadorDeSugerencias(usuario.getPreferenciaDelUsuario()));
			Collections.sort(promociones, new ComparadorDeSugerencias(usuario.getPreferenciaDelUsuario()));

			// Añado todo a las sugerencias
			sugerencias.addAll(promociones);
			sugerencias.addAll(atracciones);
			
			// Muestro los datos del usuario y las sugerencias que el sistema encontró
			System.out.println("\nSESION INICIADA POR: \n" + usuario);
			System.out.println("-----------------------------------------------------------------------------------");
			usuario.imprimirSugerencias(sugerencias);
			System.out.println("-----------------------------------------------------------------------------------");

			Iterator<Sugerencia> itr = sugerencias.iterator();
			do {
				Sugerencia sugerenciaCursor = itr.next();
				System.out.println("[ Dinero en la cuenta: " + usuario.getPresupuesto() + ", Tiempo Max. Programado: (min) " + usuario.getTiempoDisponible() + " ]\n");
				System.out.println("¿Desea comprar " + sugerenciaCursor.getNombre() + "?\n"
						+ "[s] - si \t [n] - no \t [q] - salir");
				opcion = in.next();
				switch(opcion) {
					case "s":
						if (usuarioDAO.aceptarSugerencia(usuario, sugerenciaCursor)) 
							mensaje = "##################################################################################\n"
									 + "##                         ¡Compra realizada con exito!                        ##\n"
									 + "##################################################################################";
						else 
							 mensaje = "##################################################################################\n"
									 + "##                Lo sentimos, no se pudo efectual la compra                    ##\n"
									 + "##################################################################################";
						
						break;
						
					case "n":
						mensaje =  "##################################################################################\n"
								 + "##            Sugerencia no aceptada. Pasando a la siguiente opcion...          ##\n"
								 + "##################################################################################";
						break;
					
					case "q":
						mensaje =  "##################################################################################\n"
								 + "##                         Usted ha salido. Hasta luego!                        ##\n"
								 + "##################################################################################";
						break;
				}
				
				System.out.println(mensaje);
 				TimeUnit.SECONDS.sleep(2); // Pausa para que se lean bien los mensajes del programa
				System.out.println("-----------------------------------------------------------------------------------");
			} while(itr.hasNext() && !opcion.equalsIgnoreCase("q"));
			AdministradorArchivos.escribirUsuario(usuario);
		}
		System.out.println("------------------ Atencion: No hay mas usuarios en el sistema. Fin del proceso ------------------");
		in.close();
	}
	
	public static List<Atraccion> traerAtracciones(String[] nombres){
		/*
		 * Dado un array de nombres, devuelve una lista con las atracciones y todos sus atributos
		 */
		List<Atraccion> atrac =  new LinkedList<Atraccion>();
		
		for (int i = 0; i < nombres.length; i++) {
			for(int j = 0; j < atracciones.size(); j++) {
				if(atracciones.get(j).getNombre().contains(nombres[i])) {
					atrac.add(atracciones.get(j));
				}
			}
		}
		
		return atrac;
	}
}
