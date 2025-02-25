package testing;

import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.VueloNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataPasajes;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.estadoRutaVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Handlers.UsuariosHandler;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import logica.clases.Cliente;
import logica.clases.Pasaje;

import org.w3c.dom.UserDataHandler;

class VuelosControllerTest {

	private static IVuelosController cVuelos;
	private static IUsuariosController cUsuarios;
	private static IRutaVueloController cRutas;

	private static UsuariosHandler userHandler;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		cVuelos = fabrica.getIControladorVuelos();
		cUsuarios = fabrica.getIControladorUsuario();
		cRutas = fabrica.getIControladorRutaVuelo();

		userHandler = UsuariosHandler.getinstance();
	}

	@Test
	void testAltaVueloOK() {

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
		try {
			cUsuarios.crearAerolinea("aero_1111", "aero1", "aero1111@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = cUsuarios.verInfoAerolinea("aero_1");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		
		String nombreV = "vueloOK12";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);

		try {
			cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
			DataVuelo dv = cVuelos.verInfoVuelo(nombreV);
			assertEquals(dv.getNombre(), nombreV);
			assertEquals(dv.getFecha(), fecha);
			assertEquals(dv.getDuracion(), duracion);
			assertEquals(dv.getMaxTurista(), max_t);
			assertEquals(dv.getMaxEjecutivo(), max_e);
			assertEquals(dv.getFechaAlta(), fecha_a);

			cVuelos.confirmarAltaVuelo(nombreV);

		} catch (NombreVRepetidoExce | RVNoExisteExce e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (VueloNoExisteExce e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	};

	@Test
	void testAltaVueloNombreRepetido() {

		String nombreRV = "nombreRV2";
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
		try {
			cUsuarios.crearAerolinea("aero_102", "aero1", "aero102@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = cUsuarios.verInfoAerolinea("aero_1");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		String nombreV = "vueloOK";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);
		try {
			cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
		} catch (NombreVRepetidoExce e) {
			e.printStackTrace();
		} catch (RVNoExisteExce e) {
			e.printStackTrace();
		}
		assertThrows(NombreVRepetidoExce.class, () -> {
			cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
		}, "Se da una excepcion al intentar crear un vuelo con mismo nombre que otro existente");
	};

	@Test
	void testVueloNoExiste() {
		assertThrows(VueloNoExisteExce.class, () -> {
			cVuelos.verInfoVuelo("vuelo_inexistente");
		}, "Se da una excepcion al intentar consultar un vuelo que no existe");
	
	};

	@Test
	void testVerInfoVuelo() {

		String nombreRV = "nombreRV111";
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
		try {
			cUsuarios.crearAerolinea("aero_55", "aero1", "aero55@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = cUsuarios.verInfoAerolinea("aero_1");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		String nombreV = "vueloOK2";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);
		try {
			cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
			DataVuelo dv = cVuelos.verInfoVuelo(nombreV);
			assertEquals(dv.getNombre(), nombreV);
			assertEquals(dv.getFecha(), fecha);
			assertEquals(dv.getDuracion(), duracion);
			assertEquals(dv.getMaxTurista(), max_t);
			assertEquals(dv.getMaxEjecutivo(), max_e);
			assertEquals(dv.getFechaAlta(), fecha_a);

		} catch (NombreVRepetidoExce | RVNoExisteExce e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (VueloNoExisteExce e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	};

	@Test
	void testConsultaVueloOk() {
		try {
			String aerolineaNickname = "aeroNew";
			String aerolineaNombre = "aerolinea";
			String aerolineaEmail = "aeroNew@gmail.com";
			String aerolineaDesc = "descripcion";
			String aerolineaWeb = "www.aeroNew.com";
			cUsuarios.crearAerolinea(aerolineaNickname, aerolineaNombre, aerolineaEmail, aerolineaDesc, aerolineaWeb, "123", "", null, null);

			String ciudadNombre = "Montevideo";
			String ciudadPais = "Uruguay";
			String ciudadAeropuerto = "Carrasco";
			String ciudadDesc = "descripcion";
			String ciudadWeb = "www.montevideo.com";
			LocalDate ciudadFechaAlta = LocalDate.of(2024, 9, 30);
			cRutas.registrarCiudad(ciudadNombre, ciudadPais, ciudadAeropuerto, ciudadDesc, ciudadWeb, ciudadFechaAlta);

			String rutaNombre = "rutaNew";
			String rutaDesc = "rutaDesc";
			LocalTime rutaHora = LocalTime.of(12, 0);
			LocalDate rutaFechaAlta = LocalDate.of(2024, 9, 30);
			String ciudadOrigen = "Montevideo";
			String ciudadDestino = "Buenos Aires";
			float costoEjecutivo = 100;
			float costoTurista = 50;
			float costoExtra = 10;
			ArrayList<Categoria> listaNombresCategorias = new ArrayList<Categoria>();
			Aerolinea aerolinea = userHandler.obtenerAerolinea(aerolineaNickname);
			cRutas.registrarRutaDeVuelo(rutaNombre, rutaDesc, rutaHora, rutaFechaAlta, ciudadOrigen, ciudadDestino, costoEjecutivo, costoTurista, costoExtra, listaNombresCategorias, aerolinea, "", rutaDesc, estadoRutaVuelo.Ingresada, "");

			String vueloNombre = "vueloNew";
			LocalDate vueloFecha = LocalDate.of(2024, 10, 30);
			LocalTime vueloDuracion = LocalTime.of(2, 0);
			Integer vueloMaxTurista = 10;
			Integer vueloMaxEjecutivo = 12;
			LocalDate vueloFechaAlta = LocalDate.of(2024, 9, 30);
			cVuelos.ingresarDatosVuelo(rutaNombre, vueloNombre, vueloFecha, vueloDuracion, vueloMaxTurista, vueloMaxEjecutivo, vueloFechaAlta);

			DataVuelo dv = cVuelos.verInfoVuelo(vueloNombre);
			assertEquals(dv.getNombre(), vueloNombre);
			assertEquals(dv.getFecha(), vueloFecha);
			assertEquals(dv.getDuracion(), vueloDuracion);
			assertEquals(dv.getMaxTurista(), vueloMaxTurista);
			assertEquals(dv.getMaxEjecutivo(), vueloMaxEjecutivo);
			assertEquals(dv.getFechaAlta(), vueloFechaAlta);
		} catch (EmailRepetidoExce | NicknameRepetidoExce | NombreRVRepetidoExce | RVNoExisteExce | NombreVRepetidoExce | VueloNoExisteExce e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testConfirmarAltaVuelo() {
		String nombreRV = "nombreRV22";
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
		try {
			cUsuarios.crearAerolinea("aero_10", "aero1", "aero10@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = cUsuarios.verInfoAerolinea("aero_1");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		String nombreV = "vuelon";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);
		try {
			cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
	        boolean confirmado = cVuelos.confirmarAltaVuelo(nombreV);
	        assertTrue(confirmado, "Se esperaba que el vuelo se confirmara correctamente.");
	    } catch (NombreVRepetidoExce | RVNoExisteExce e) {
	        fail("No se esperaba una excepción al registrar el vuelo: " + e.getMessage());
	    }
	}

	@Test
	void testReservarVuelo() {
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
		DataCliente dataCliente;
		Cliente cliente = null;
		try {
			cUsuarios.crearAerolinea("aero_1", "aero1", "aero1@gmail.com", "desc aero1", " ", "123", " ", null, null);
			cUsuarios.crearCliente("cliNick", "nombre", "cli@gmail.com", "apellido", LocalDate.of(2000, 10, 15),
					"Paraguay", "C.I", "52651762", "password", " ", null, null);
			dataCliente = cUsuarios.verInfoCliente("cliNick");
			cliente = new Cliente(dataCliente.getNickname(), dataCliente.getNombre(), dataCliente.getEmail(), dataCliente.getApellido(), dataCliente.getNacimiento(),
					dataCliente.getTipoNacionalidad(), dataCliente.getTipoDocumento(), dataCliente.getDocumento(), dataCliente.getPassword(), null, "", null, null);
			dataAero = cUsuarios.verInfoAerolinea("aero_1");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		String nombreV = "vueloOK3";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);

	    ArrayList<String> pasajeros = new ArrayList<>();
	    pasajeros.add("John Doe");

	    try {
	        // Registrar el vuelo
	        cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);

	        // Reservar vuelo
	        cVuelos.reservarVuelo(nombreV, cliente.getNickname(), tipoAsiento.Turista, 2, 0, LocalDate.of(2024, 10, 10), pasajeros);

	        // Verificar que el vuelo se reservó
	        DataVuelo dv = cVuelos.verInfoVuelo(nombreV);
	        assertEquals(10, dv.getMaxTurista(), "Se esperaba que el número de asientos turista disminuyera después de la reserva.");

	    } catch (Exception e) {
	        fail("No se esperaba una excepción al reservar el vuelo: " + e.getMessage());
	    }
	}
	
	
	@Test
	void testListarVuelosPorRutaVuelo() {
		String nombreRV = "nombreRV10";
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
		try {
			cUsuarios.crearAerolinea("aero_4", "aero1", "aero4@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero = cUsuarios.verInfoAerolinea("aero_1");
			Aerolinea aero = new Aerolinea(dataAero.getNickname(), dataAero.getNombre(), 
					dataAero.getEmail(), dataAero.getDescripcion(), dataAero.getWeb(), dataAero.getPassword(), dataAero.getImagen(), null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		String nombreV = "vueloOK4";
		String nombreV2 = "vueloOK5";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);
	    try {
	        // Registrar dos vuelos
	        cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
	        cVuelos.ingresarDatosVuelo(nombreRV, nombreV2, fecha, duracion, max_t, max_e, fecha_a);

	        // Listar vuelos por ruta
	        ArrayList<DataVuelo> vuelos = cVuelos.listarVuelosPorRutaVuelo(nombreRV);
	        Assertions.assertNotNull(vuelos, "Deberian listarse vuelos.");
	    } catch (Exception e) {
	        fail("No se esperaba una excepción al listar los vuelos de la ruta: " + e.getMessage());
	    }
	}

	@Test
	void testListarReservasDeVuelo() {
		String nombreRV = "nombreRV500";
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
		DataAerolinea dataAero2;
		DataCliente dataCliente2;
		Cliente cliente2 = null;
		ArrayList<String> pasajeros = new ArrayList<>();
	    pasajeros.add("Jhon Doe");
		try {
			cUsuarios.crearAerolinea("aero_2", "aero1", "aero2@gmail.com", "desc aero1", " ", "123", " ", null, null);
			dataAero2 = cUsuarios.verInfoAerolinea("aero_2");
			Aerolinea aero2 = new Aerolinea(dataAero2.getNickname(), dataAero2.getNombre(), 
					dataAero2.getEmail(), dataAero2.getDescripcion(), dataAero2.getWeb(), dataAero2.getPassword(), dataAero2.getImagen(), null, null);
			cUsuarios.crearCliente("cliNick2", "nombre", "cli2@gmail.com", "apellido", LocalDate.of(2000, 10, 15),
					"Paraguay", "C.I", "52651762", "password", " ", null, null);
			dataCliente2 = cUsuarios.verInfoCliente("cliNick2");
			cliente2 = new Cliente(dataCliente2.getNickname(), dataCliente2.getNombre(), dataCliente2.getEmail(), dataCliente2.getApellido(), dataCliente2.getNacimiento(),
					dataCliente2.getTipoNacionalidad(), dataCliente2.getTipoDocumento(), dataCliente2.getDocumento(), dataCliente2.getPassword(), null, "", null, null);
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aero2, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (UsuarioNoExisteExce | NombreRVRepetidoExce | NicknameRepetidoExce  | EmailRepetidoExce e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		String nombreV = "vueloOK6";
		LocalDate fecha = LocalDate.of(2024, 10, 21);
		LocalTime duracion = LocalTime.of(14, 0); // horas
		Integer max_t = 12;
		Integer max_e = 11;
		LocalDate fecha_a = LocalDate.of(2024, 7, 30);
	    try {
	        // Registrar dos vuelos
	        cVuelos.ingresarDatosVuelo(nombreRV, nombreV, fecha, duracion, max_t, max_e, fecha_a);
	        cVuelos.reservarVuelo(nombreV, cliente2.getNickname(), tipoAsiento.Turista, 2, 0, LocalDate.of(2024, 10, 10), pasajeros);

	        // Listar reservas
	        ArrayList<DataReserva> reservas = cVuelos.listarReservasDeVuelo(nombreV);
	        ArrayList<DataRutaVuelo> datosRutaVuelo = cVuelos.listarRutasVuelosPorAerolinea("aero_2");
	        Assertions.assertEquals(1, reservas.size(), "Se esperaba una reserva en el vuelo.");
	        Assertions.assertNotNull(reservas, "La reserva deberia existir");
	        Assertions.assertNotNull(datosRutaVuelo, "La reserva deberia existir");
	    } catch (Exception e) {
	        fail("No se esperaba una excepción al listar las reservas del vuelo: " + e.getMessage());
	    }
	}

	
}	
