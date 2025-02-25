package testing;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;
import exceptions.PaqueteExisteExce;
import exceptions.RVNoExisteExce;
import exceptions.VueloNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataPaquete;
import logica.Datatypes.DataRutaVueloPaquete;
import logica.Datatypes.estadoRutaVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;

class ControladorPaqueteTest {
		private static IUsuariosController cUsuarios;
	private static IRutaVueloController cRutas;
	private static IPaqueteController cPaquete;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		cUsuarios = fabrica.getIControladorUsuario();
		cRutas = fabrica.getIControladorRutaVuelo();
		cPaquete = fabrica.getIControladorPaquete();	}
	
	@Test
	void testCrearPaqueteNombreRepetido() {
		String nombre = "nom_repetido";
		String desc = "paquete repetido";
		int validez = 10;
		float descuento  = 20;
		float costo = 120;
		LocalDate fechaAlta = LocalDate.of(2024, 5, 2);
		ArrayList<DataRutaVueloPaquete> rutas = new ArrayList<>();

		Assertions.assertThrows(PaqueteExisteExce.class, () -> {
			cPaquete.crearPaquete(nombre, desc, validez, descuento, costo, fechaAlta, rutas);
		}, "Se esperaba una excepcion al intentar crear un paquete con repetido");
	}
	
	@Test
	void testVerInfoPaqueteInexistente() {
		Assertions.assertThrows(VueloNoExisteExce.class, () -> {
			cPaquete.verInfoPaquete("paquete_inex");
		}, "Se esperaba una UsuarioNoExisteExce al intentar consultar un paquete inexistente");
	}
	
	@Test
	void testAgregarRVInexistente() {
		
		//RUTA DE VUELO
		String nombreRV = "nombreRVinex";
		tipoAsiento tipoAs = tipoAsiento.Ejecutivo;
		Integer cantRutas = 10;
		//PAQUETE
		String nombre = "nom_paquete2";
		String desc = "paquete ruta";
		int validez = 10;
		float descuento  = 20;
		float costo = 120;
		LocalDate fechaAlta = LocalDate.of(2024, 5, 2);
		ArrayList<DataRutaVueloPaquete> rutas = new ArrayList<>();
		try {
			cPaquete.crearPaquete(nombre, desc, validez, descuento, costo, fechaAlta, rutas);
		} catch (PaqueteExisteExce e) {}
		
		Assertions.assertThrows(RVNoExisteExce.class, () -> {
			cPaquete.agregarRVaPaquete(nombre, nombreRV, tipoAs, cantRutas);
		}, "Se esperaba una RVNoExisteExce al intentar agregar una ruta de vuelo inexistente");
	}
	
	@Test
	void testAgregarRV() {
		
		//RUTA DE VUELO
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
		try {
			cUsuarios.crearAerolinea("aero1", "Aerolinea 1", "aero1@aero1.com", "Aerolinea 1", "aero1.com", "123", " ", null, null);
			cUsuarios.crearCliente("nick_cli", "nom_cli", "emailcli@gmail.com", "apellido_cli", LocalDate.of(2000, 10, 20), "Chileno" , "CI", "50201023", "1111", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e) {}
		Aerolinea aerolinea = new Aerolinea("aero1", "Aerolinea 1", "aero1@aero1.com", "Aerolinea 1", "aero1.com", "123", " ", null, null);
		tipoAsiento tipoAs = tipoAsiento.Ejecutivo;
		Integer cantRutas = 10;
		try {
			cRutas.registrarRutaDeVuelo(nombreRV, descRV, horaRV, fechaAltaRV, ciudadO, ciudadD, costoEjec, costoT, costoExt, 
					categorias, aerolinea, ciudadO, descRV, estadoRutaVuelo.Confirmada, "");
		} catch (NombreRVRepetidoExce e) {}

		String nombre = "nom_paquete3";
		String desc = "paquete ruta";
		int validez = 10;
		float descuento  = 20;
		float costo = 120;
		LocalDate fechaAlta = LocalDate.of(2024, 5, 2);
		ArrayList<DataRutaVueloPaquete> rutas = new ArrayList<>();
		try {
			cPaquete.crearPaquete(nombre, desc, validez, descuento, costo, fechaAlta, rutas);
			cPaquete.agregarRVaPaquete(nombre, nombreRV, tipoAs, cantRutas);
			cPaquete.comprarPaquete("nick_cli", nombre, LocalDate.now(), LocalDate.now().plusDays(validez));
		} catch (RVNoExisteExce | PaqueteExisteExce e) {
			e.printStackTrace();
		}
		Assertions.assertThrows(PaqueteExisteExce.class, () -> {
			cPaquete.agregarRVaPaquete(nombre, nombreRV, tipoAs, cantRutas);
		}, "Se esperaba una excepcion al intentar agregar una ruta de vuelo ya agregada");
	}
	
	@Test
	void testVerInfoPaquete() {
		String nombre = "nom_paquete4";
		String desc = "paquete ruta";
		int validez = 10;
		float descuento  = 20;
		float costo = 120;
		LocalDate fechaAlta = LocalDate.of(2024, 5, 2);
		ArrayList<DataRutaVueloPaquete> rutas = new ArrayList<>();
		try {
			cPaquete.crearPaquete(nombre, desc, validez, descuento, costo, fechaAlta, rutas);
		} catch (PaqueteExisteExce e) {}
		
		DataPaquete paquete = null;
		try {
			paquete = cPaquete.verInfoPaquete(nombre);
			Assertions.assertNotNull(paquete, "El paquete debería existir");
			Assertions.assertEquals(nombre, paquete.getNombre(), "El nombre del paquete debería coincidir");
		} catch (VueloNoExisteExce e) {
			Assertions.fail("No se esperaba una excepción al consultar la información del paquete: " + e.getMessage());
		}
	}
	
	@Test
	void testListarPaquetes() {
		String nombre = "nom_paquete5";
		String desc = "paquete ruta";
		int validez = 10;
		float descuento  = 20;
		float costo = 120;
		LocalDate fechaAlta = LocalDate.of(2024, 5, 2);
		ArrayList<DataRutaVueloPaquete> rutas = new ArrayList<>();

		try {
			cPaquete.crearPaquete(nombre, desc, validez, descuento, costo, fechaAlta, rutas);
		} catch (PaqueteExisteExce e) {
			Assertions.fail("No se esperaba una excepción al crear el paquete: " + e.getMessage());
		}

		ArrayList<DataPaquete> paquetes = cPaquete.listarPaquetesDisponibles();
		Assertions.assertNotNull(paquetes, "La lista de paquetes no debería ser nula");
		Assertions.assertTrue(paquetes.isEmpty(), "Debería haber al menos un paquete en el sistema");
		Assertions.assertEquals(nombre, paquetes.get(0).getNombre(), "El nombre del paquete debería coincidir");
	}
}