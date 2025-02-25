/**
 * 
 */
package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import logica.Fabrica;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import exceptions.ClientePasajeroRepetidoExce;
import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;
import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.VueloNoExisteExce;
import logica.Datatypes.DataCliente;
import logica.Datatypes.estadoRutaVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Handlers.UsuariosHandler;
import logica.Datatypes.DataAerolinea;

class ControladorUsuariosTest {

	private static IUsuariosController controladorUsuario;
	private static IVuelosController controladorVuelo;
	private static IRutaVueloController controladorRutaVuelo;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		controladorUsuario = fabrica.getIControladorUsuario();
		controladorVuelo = fabrica.getIControladorVuelos();
		controladorRutaVuelo = fabrica.getIControladorRutaVuelo();
	}

	@BeforeEach
	public void limpiarSistema() {
		UsuariosHandler handler = UsuariosHandler.getinstance();
		handler.limpiarClientes();
		handler.limpiarAerolineas();
	}

	@Test
	void testCrearClienteNickRepetido() {
	    try {
	        controladorUsuario.crearCliente("nick_cliente", "nombre_cliente", "cliente@mail.com", 
	                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
	                "12345678", "password", null, null, null);
	    } catch (EmailRepetidoExce | NicknameRepetidoExce e) {
	        fail("No se esperaba una excepción al crear el primer cliente: " + e.getMessage());
	    }

	    Assertions.assertThrows(NicknameRepetidoExce.class, () -> {
	        controladorUsuario.crearCliente("nick_cliente", "nombre_cliente2", "cliente2@mail.com", 
	                "apellido_cliente2", LocalDate.of(1991, 2, 2), "nacionalidad", "CI", 
	                "87654321", "password2", null, null, null);
	    }, "Se esperaba una NicknameRepetidoExce al intentar crear un cliente con un nickname repetido");
	}


	@Test
	void testCrearAerolineaEmailRepetido() {
		String nick1 = "nick_aero1";
		String nick2 = "nick_aero2";
		String email = "email_aero@example.com";
		String nombre = "NombreAerolinea";
		String descripcion = "Descripcion Aerolinea";
		String web = "www.ejemplo.com";

		try {
			controladorUsuario.crearAerolinea(nick1, nombre, email, descripcion, web, "124", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e) {
			Assertions.fail("No se esperaba una excepción al crear la primera aerolínea: " + e.getMessage());
		}

		Assertions.assertThrows(EmailRepetidoExce.class, () -> {
			controladorUsuario.crearAerolinea(nick2, nombre, email, descripcion, web, "124", "", null, null);
		}, "Se esperaba una EmailRepetidoExce al intentar crear una aerolínea con un email repetido");
	}

	@Test
	void testGetClientesSinClientes() {
		UsuariosHandler handler = UsuariosHandler.getinstance();
		handler.limpiarClientes();

		Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
			controladorUsuario.getClientes();
		}, "Se esperaba una UsuarioNoExisteExce al intentar obtener clientes cuando no hay ninguno en el sistema");

	}

	@Test
	void testGetAerolineasSinAerolineas() {
		UsuariosHandler handler = UsuariosHandler.getinstance();
		handler.limpiarAerolineas();

		Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
			controladorUsuario.getAerolineas();
		}, "Se esperaba una UsuarioNoExisteExce al intentar obtener aerolíneas cuando no hay ninguna en el sistema");
	}

	@Test
	void testGetClientes() {
	    try {
	    	controladorUsuario.crearCliente("nick_cliente3", "nombre_cliente", "cliente@mail.com", 
	                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
	                "12345678", "password", null, null, null);
	    } catch (EmailRepetidoExce | NicknameRepetidoExce e) {
	        fail("No se esperaba una excepción al crear el cliente: " + e.getMessage());
	    }

	    try {
	        DataCliente[] clientes = controladorUsuario.getClientes();
	        Assertions.assertNotNull(clientes, "Se esperaba que getClientes devolviera un arreglo no nulo");
	        Assertions.assertTrue(clientes.length > 0, "Se esperaba que existieran clientes en el sistema");
	    } catch (UsuarioNoExisteExce e) {
	        fail("No se esperaba una excepción al obtener los clientes: " + e.getMessage());
	    }
	}

	@Test
	void testGetAerolineas() {
		
		String nombreRV = "nombreRV334";
		String descRV = "descripcion";
		LocalTime horaRV = LocalTime.now();
		LocalDate fechaAltaRV = LocalDate.now();
		String ciudadO = "ciudad origen";
		String ciudadD = "ciudad destino";
		float costoEjec = 20.0f;
		float costoT = 10.0f;
		float costoExt = 2.0f;
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(new Categoria("cat1", "categoria 1"));
		categorias.add(new Categoria("cat2", "categoria 2"));
		ArrayList<String> pasajeros = new ArrayList<>();
	    pasajeros.add("John Doe");

		try {
			controladorUsuario.crearAerolinea("aero_112", "aero1", "aero112@gmail.com", "desc aero1", " ", "123", " ", null, null);
			DataAerolinea dataAero1 = controladorUsuario.verInfoAerolinea("aero_112");
			Aerolinea aero = new Aerolinea(dataAero1.getNickname(), dataAero1.getNombre(), 
					dataAero1.getEmail(), dataAero1.getDescripcion(), dataAero1.getWeb(), dataAero1.getPassword(), dataAero1.getImagen(), null, null);
			controladorRutaVuelo.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
			controladorVuelo.ingresarDatosVuelo(nombreRV, "nombreV32", LocalDate.of(2024, 12, 10), LocalTime.of(2, 0), 10, 11, LocalDate.now());
			controladorUsuario.crearCliente("nick_cliente3", "nombre_cliente", "cliente@mail.com", 
	                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
	                "12345678", "password", null, null, null);
				controladorVuelo.reservarVuelo("nombreV", "nick_cliente3", tipoAsiento.Ejecutivo, 2, 0, LocalDate.of(2024, 12, 10), pasajeros);
    } catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | NombreVRepetidoExce | RVNoExisteExce | EmailRepetidoExce | ClientePasajeroRepetidoExce e4) {}
		try {
			DataAerolinea[] aerolineas = controladorUsuario.getAerolineas();
			Assertions.assertNotNull(aerolineas, "La lista de aerolíneas no debería ser nula");
			Assertions.assertTrue(aerolineas.length > 0, "Debería haber al menos una aerolínea en el sistema");
			Assertions.assertEquals("aero_112", aerolineas[0].getNickname(), "El nickname de la aerolínea debería coincidir");
			Assertions.assertTrue(controladorVuelo.listarReservasDeVuelo("nombreV32").size() > 0, "Deberia haber una reserva para el vuelo");
		} catch (UsuarioNoExisteExce | VueloNoExisteExce e) {
			Assertions.fail("No se esperaba una excepción al obtener las aerolíneas: " + e.getMessage());
		}
	}

	@Test
	void testVerInfo() {
	    	String nombreRV = "nombreRV33";
			String descRV = "descripcion";
			LocalTime horaRV = LocalTime.now();
			LocalDate fechaAltaRV = LocalDate.now();
			String ciudadO = "ciudad origen";
			String ciudadD = "ciudad destino";
			float costoEjec = 20.0f;
			float costoT = 10.0f;
			float costoExt = 2.0f;
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			categorias.add(new Categoria("cat1", "categoria 1"));
			categorias.add(new Categoria("cat2", "categoria 2"));
			DataAerolinea dataAero;
			ArrayList<String> pasajeros = new ArrayList<>();
		    pasajeros.add("John Doe");
			try {
				controladorUsuario.crearAerolinea("aero_1111", "aero1", "aero1111@gmail.com", "desc aero1", " ", "123", " ", null, null);
				dataAero = controladorUsuario.verInfoAerolinea("aero_1111");
				Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
						dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
				controladorRutaVuelo.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
						categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
				controladorVuelo.ingresarDatosVuelo(nombreRV, "nombreV", LocalDate.of(2024, 12, 10), LocalTime.of(2, 0), 10, 11, LocalDate.now());
				controladorUsuario.crearCliente("nick_cliente2", "nombre_cliente", "cliente@mail.com", 
		                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
		                "12345678", "password", null, null, null);
					controladorVuelo.reservarVuelo("nombreV", "nick_cliente2", tipoAsiento.Ejecutivo, 2, 0, LocalDate.of(2024, 12, 10), pasajeros);
	    } catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | NombreVRepetidoExce | RVNoExisteExce | EmailRepetidoExce | ClientePasajeroRepetidoExce e4) {}
	    // Ahora obtener la información del cliente
	    try {
	        DataCliente cliente = controladorUsuario.verInfoCliente("nick_cliente2");
	        DataAerolinea aerol = controladorUsuario.verInfoAerolinea("aero_1111");
	        Assertions.assertNotNull(cliente, "Se esperaba que verInfoCliente devolviera un cliente válido");
	        Assertions.assertNotNull(cliente, "Se esperaba que verInfoCliente devolviera un cliente válido");
	        Assertions.assertTrue(aerol.getRutasVuelo().size() > 0 ,"Se esperaba que hayan rutas de vuelo en la aerolinea");
	    } catch (UsuarioNoExisteExce e) {
	        fail("No se esperaba una excepción al consultar la información del cliente: " + e.getMessage());
	    }
}


	@Test
	void testVerInfoClienteInexistente() {
		Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
			controladorUsuario.verInfoCliente("cliente_inexistente");
		}, "Se esperaba una UsuarioNoExisteExce al intentar consultar un cliente inexistente");
	}

	@Test
	void testModificarClienteInexistente() {
		Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
			controladorUsuario.modificarCliente("cliente_inexistente", "NuevoNombre", "nuevo_email@example.com",
					"NuevoApellido", LocalDate.now(), "Uruguaya", "CI", "12345678", "contra_distinta", "");
		}, "Se esperaba una UsuarioNoExisteExce al intentar modificar un cliente inexistente");
	}

	@Test
	void testVerInfoAerolineaInexistente() {
		Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
			controladorUsuario.verInfoAerolinea("aerolinea_inexistente");
		}, "Se esperaba una UsuarioNoExisteExce al intentar consultar una aerolínea inexistente");
	}

	@Test
	void testModificarAerolineaInexistente() {
		Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
			controladorUsuario.modificarAerolinea("aerolinea_inexistente", "NuevoNombre", "nuevo_email@example.com",
					"NuevaDescripcion", "www.nuevaweb.com", "contra_distinta2", "");
		}, "Se esperaba una UsuarioNoExisteExce al intentar modificar una aerolínea inexistente");
	}

	@Test
	void testVerInfoAerolinea() {
		String nick = "nick_aero";
		String email = "aero@example.com";
		String nombre = "NombreAerolinea";
		String descripcion = "Descripcion Aerolinea";
		String web = "www.aero.com";

		try {
			controladorUsuario.crearAerolinea(nick, nombre, email, descripcion, web, "123", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e) {
			Assertions.fail("No se esperaba una excepción al crear la aerolínea: " + e.getMessage());
		}

		try {
			DataAerolinea aerolinea = controladorUsuario.verInfoAerolinea(nick);
			Assertions.assertNotNull(aerolinea, "La aerolínea debería existir");
			Assertions.assertEquals(nick, aerolinea.getNickname(), "El nickname de la aerolínea debería coincidir");
			Assertions.assertEquals(email, aerolinea.getEmail(), "El email de la aerolínea debería coincidir");
		} catch (UsuarioNoExisteExce e) {
			Assertions.fail(
					"No se esperaba una excepción al consultar la información de la aerolínea: " + e.getMessage());
		}
	}

	@Test
	void testModificarCliente() {
		String nick = "nick_mod";
		String email = "mod@example.com";
		String nombre = "NombreMod";
		String apellido = "ApellidoMod";
		LocalDate fechaNac = LocalDate.of(1990, 1, 1);
		String nacionalidad = "Uruguaya";
		String tipoDocumento = "CI";
		String documento = "12345678";

		try {
			controladorUsuario.crearCliente(nick, nombre, email, apellido, fechaNac, nacionalidad, tipoDocumento,
					documento, "123", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e) {
			Assertions.fail("No se esperaba una excepción al crear el cliente: " + e.getMessage());
		}

		String nuevoNombre = "NuevoNombre";
		String nuevoApellido = "NuevoApellido";

		try {
			controladorUsuario.modificarCliente(nick, nuevoNombre, email, nuevoApellido, fechaNac, nacionalidad,
					tipoDocumento, documento, "contra_distinta3", "");
			DataCliente cliente = controladorUsuario.verInfoCliente(nick);
			Assertions.assertEquals(nuevoNombre, cliente.getNombre(),
					"El nombre del cliente debería haberse modificado");
			Assertions.assertEquals(nuevoApellido, cliente.getApellido(),
					"El apellido del cliente debería haberse modificado");
		} catch (UsuarioNoExisteExce e) {
			Assertions.fail("No se esperaba una excepción al modificar el cliente: " + e.getMessage());
		}
	}

	@Test
	void testModificarAerolinea() {
		String nick = "nick_mod_aero";
		String email = "mod_aero@example.com";
		String nombre = "NombreModAero";
		String descripcion = "Descripcion Mod";
		String web = "www.modaero.com";

		try {
			controladorUsuario.crearAerolinea(nick, nombre, email, descripcion, web, "123", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e) {
			Assertions.fail("No se esperaba una excepción al crear la aerolínea: " + e.getMessage());
		}

		String nuevoNombre = "NuevoNombreAero";
		String nuevaDescripcion = "NuevaDescripcion";

		try {
			controladorUsuario.modificarAerolinea(nick, nuevoNombre, email, nuevaDescripcion, web, "contra_distinta4", "");
			DataAerolinea aerolinea = controladorUsuario.verInfoAerolinea(nick);
			Assertions.assertEquals(nuevoNombre, aerolinea.getNombre(),
					"El nombre de la aerolínea debería haberse modificado");
			Assertions.assertEquals(nuevaDescripcion, aerolinea.getDescripcion(),
					"La descripción de la aerolínea debería haberse modificado");
		} catch (UsuarioNoExisteExce e) {
			Assertions.fail("No se esperaba una excepción al modificar la aerolínea: " + e.getMessage());
		}
	}
	
	@Test
	void testRealizarCheckIn() {
		String nombreRV = "nombreRV12";
		String descRV = "descripcion";
		LocalTime horaRV = LocalTime.now();
		LocalDate fechaAltaRV = LocalDate.now();
		String ciudadO = "ciudad origen";
		String ciudadD = "ciudad destino";
		float costoEjec = 20.0f;
		float costoT = 10.0f;
		float costoExt = 2.0f;
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(new Categoria("cat1", "categoria 1"));
		categorias.add(new Categoria("cat2", "categoria 2"));
		DataAerolinea dataAero;
		ArrayList<String> pasajeros = new ArrayList<>();
	    pasajeros.add("John Doe");
		try {
			controladorUsuario.crearAerolinea("aero_13", "aero1", "aero1111@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = controladorUsuario.verInfoAerolinea("aero_13");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			controladorRutaVuelo.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
			controladorVuelo.ingresarDatosVuelo(nombreRV, "nombreV23", LocalDate.of(2024, 12, 10), LocalTime.of(2, 0), 10, 11, LocalDate.now());
			controladorUsuario.crearCliente("nick_cliente4", "nombre_cliente", "cliente@mail.com", 
	                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
	                "12345678", "password", null, null, null);
				controladorVuelo.reservarVuelo("nombreV23", "nick_cliente4", tipoAsiento.Ejecutivo, 2, 0, LocalDate.of(2024, 12, 10), pasajeros);
				controladorUsuario.realizarCheckIn("nick_cliente4", "nombreV23");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | NombreVRepetidoExce | RVNoExisteExce | EmailRepetidoExce | ClientePasajeroRepetidoExce e4) {}
	}
	
	@Test
	void testGetCheckIns() {
		String nombreRV = "nombreRV1";
		String descRV = "descripcion";
		LocalTime horaRV = LocalTime.now();
		LocalDate fechaAltaRV = LocalDate.now();
		String ciudadO = "ciudad origen";
		String ciudadD = "ciudad destino";
		float costoEjec = 20.0f;
		float costoT = 10.0f;
		float costoExt = 2.0f;
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(new Categoria("cat1", "categoria 1"));
		categorias.add(new Categoria("cat2", "categoria 2"));
		DataAerolinea dataAero;
		ArrayList<String> pasajeros = new ArrayList<>();
	    pasajeros.add("John Doe");
		try {
			controladorUsuario.crearAerolinea("aero_13", "aero1", "aero1111@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = controladorUsuario.verInfoAerolinea("aero_13");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			controladorRutaVuelo.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
			
			controladorUsuario.crearCliente("nick_cliente77", "nombre_cliente", "cliente@mail.com", 
	                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
	                "12345678", "password", null, null, null);
			
			controladorVuelo.ingresarDatosVuelo(nombreRV, "nombreV28", LocalDate.of(2024, 12, 10), LocalTime.of(2, 0), 10, 11, LocalDate.now());
			controladorVuelo.ingresarDatosVuelo(nombreRV, "nombreV26", LocalDate.of(2024, 12, 10), LocalTime.of(2, 0), 10, 11, LocalDate.now());
			
			controladorVuelo.reservarVuelo("nombreV28", "nick_cliente77", tipoAsiento.Ejecutivo, 2, 0, LocalDate.of(2024, 12, 10), pasajeros);
			controladorUsuario.realizarCheckIn("nick_cliente77", "nombreV28");
			controladorVuelo.reservarVuelo("nombreV26", "nick_cliente77", tipoAsiento.Ejecutivo, 2, 0, LocalDate.of(2024, 12, 10), pasajeros);
			controladorUsuario.realizarCheckIn("nick_cliente77", "nombreV26");
			
			controladorUsuario.getCheckIns("nick_cliente77");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | NombreVRepetidoExce | RVNoExisteExce | EmailRepetidoExce | ClientePasajeroRepetidoExce e4) {}
	}
	
	@Test
	void testSeguiryDejarDeSeguir() {
		ArrayList<String> seguidores_cli = new ArrayList<>();
		ArrayList<String> seguidos_aero = new ArrayList<>();
		ArrayList<String> seguidores_aero = new ArrayList<>();
		ArrayList<String> seguidos_cli = new ArrayList<>();		
		seguidores_cli.add("aero_13");
		seguidos_aero.add("nick_cliente79");
		try {
			controladorUsuario.crearAerolinea("aero_13", "aero1", "aero1111@gmail.com", "desc aero1", " ", "123", " ", seguidos_aero, seguidores_aero);
			DataAerolinea dataAero = controladorUsuario.verInfoAerolinea("aero_13");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), seguidos_aero, seguidores_aero);
			
			controladorUsuario.crearCliente("nick_cliente79", "nombre_cliente", "cliente@mail.com", 
	                "apellido_cliente", LocalDate.of(1990, 1, 1), "nacionalidad", "CI", 
	                "12345678", "password", null, seguidos_cli, seguidores_cli);
			
			controladorUsuario.seguir("aero_13", "nick_cliente79");
			controladorUsuario.seguir("nick_cliente79", "aero_13");
			controladorUsuario.dejarSeguir("nick_cliente79", "aero_13");
			Assertions.assertNotNull(seguidos_aero);
			Assertions.assertNotNull(aero);
			Assertions.assertNotNull(seguidores_cli);
			
		} catch (UsuarioNoExisteExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {}
	}
	
}
