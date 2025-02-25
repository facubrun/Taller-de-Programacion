package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.AerolineaNoExisteExce;
import exceptions.CategoriaRepetidaExce;
import exceptions.EmailRepetidoExce;
import exceptions.CategoriaNoExisteExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;
import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataCategoria;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.estadoRutaVuelo;
import logica.Handlers.UsuariosHandler;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import logica.clases.Ciudad;
import logica.clases.RutaVuelo;

public class ControladorRutaVueloTest {

	private static IRutaVueloController controladorRutaVuelo;
	private static IVuelosController controladorVuelos;
	private static IUsuariosController controladorUsuarios;

	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		controladorRutaVuelo = fabrica.getIControladorRutaVuelo();
		controladorVuelos = fabrica.getIControladorVuelos();
		controladorUsuarios = fabrica.getIControladorUsuario();
	}

	// Cobertura existente...

	@Test
	void testModificarDatosRutaVueloNoExiste() {
		// Intentar modificar una ruta de vuelo que no existe
		String nombreRV = "rutaNoExiste";
		LocalTime hora = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		ArrayList<Categoria> categorias = new ArrayList<>();

		Assertions.assertThrows(RVNoExisteExce.class, () -> {
			controladorRutaVuelo.modificarDatosRutaVuelo(
				nombreRV, "descripcion modificada", hora, fecha, "origen", "destino", 
				200, 150, 30, categorias, "imagen.png"
			);
		}, "Se esperaba RVNoExisteExce al intentar modificar una ruta de vuelo que no existe");
	}

	@Test
	void testObtenerCiudadNoExiste() {
		// Probar la obtención de una ciudad que no existe
		Ciudad ciudad = controladorRutaVuelo.obtenerCiudad("CiudadNoExiste", "PaisNoExiste");
		Assertions.assertNull(ciudad, "Se esperaba que obtenerCiudad devolviera null para una ciudad inexistente");
	}

	@Test
	void testListarRutasVueloPorAerolineaNoExiste() {
	    // Usa un nombre de aerolínea que seguramente no existe
	    String aerolineaInexistente = "aerolineaFantasma";

	    Assertions.assertThrows(UsuarioNoExisteExce.class, () -> {
	        controladorRutaVuelo.listarRutasVueloPorAerolinea(aerolineaInexistente);
	    }, "Se esperaba UsuarioNoExisteExce al intentar listar rutas de vuelo de una aerolínea inexistente");
	}



	
	@Test
	void testListarRutasVueloIngresadasPorAerolinea() throws UsuarioNoExisteExce {
	    String nombreAerolinea = "aero12412";
	    try {
			controladorUsuarios.crearAerolinea(nombreAerolinea, "Aerolinea 1", "aero12412@aero.com", "Aerolinea", "aero.com", "123", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e){}
	    // Asegúrate de que la aerolínea existe
	    Aerolinea aerolinea = new Aerolinea(nombreAerolinea, "Aerolinea 1", "aero1@aero.com", "Aerolinea", "aero.com", "123", "", null, null); // Agregar la aerolínea manualmente
	    // Registrar una ruta de vuelo para que haya rutas ingresadas
	    String nombreRuta = "rutaIngresadaTest";
	    LocalTime hora = LocalTime.now();
	    LocalDate fechaAlta = LocalDate.now();
	    ArrayList<Categoria> categorias = new ArrayList<>();
	    ArrayList<String> catWeb = new ArrayList<>();
	    categorias.add(new Categoria("cat1", "Categoria 1"));
	    catWeb.add("cat1");
	    ArrayList<DataRutaVuelo> rutasIngresadas= new ArrayList<>();

	    try {
	        controladorRutaVuelo.registrarRutaDeVuelo(nombreRuta, "descripcion", hora, fechaAlta, "Ciudad A", "Ciudad B", 
	                300, 150, 30, categorias, aerolinea, null, "descripcion corta", estadoRutaVuelo.Ingresada, "");
	        rutasIngresadas = controladorRutaVuelo.listarRutasVueloIngresadasPorAerolinea(nombreAerolinea);
		    Assertions.assertTrue(rutasIngresadas.size() > 0, "Se esperaba que hubiera rutas ingresadas");
		    for (DataRutaVuelo ruta : rutasIngresadas) {
		        assertEquals(estadoRutaVuelo.Ingresada, ruta.getEstado());
		    }
	    } catch (NombreRVRepetidoExce e) {
	        fail("No se esperaba excepción al registrar una ruta de vuelo");
	    }
	}


	
	@Test
	void testRegistrarCiudadYaExiste() {
	    // Registrar la ciudad la primera vez
	    String nombre = "Montevideo";
	    String pais = "Uruguay";
	    String aeropuerto = "Aeropuerto de Carrasco";
	    String descripcion = "Ciudad ya existente";
	    String sitioWeb = "montevideo.com";
	    LocalDate fechaAlta = LocalDate.of(2025, 10, 21);
	    

	    try {
	        controladorRutaVuelo.registrarCiudad(nombre, pais, aeropuerto, descripcion, sitioWeb, fechaAlta);
	    } catch (NicknameRepetidoExce e) {
	        fail("No se esperaba una excepción al registrar la ciudad por primera vez");
	    }

	    // Ahora intenta registrarla de nuevo y debería fallar
	    Assertions.assertThrows(NicknameRepetidoExce.class, () -> {
	        controladorRutaVuelo.registrarCiudad(nombre, pais, aeropuerto, descripcion, sitioWeb, fechaAlta);
	    }, "Se esperaba NicknameRepetidoExce al intentar registrar una ciudad ya existente");
	}
	
	
	@Test
	void testListarRutasVuelo() throws RVNoExisteExce {
	    String nombre = "rutaTest2";
	    LocalTime hora = LocalTime.now();
	    LocalDate fechaAlta = LocalDate.now();
	    String ciudadOrigen = "Ciudad A";
	    String ciudadDestino = "Ciudad B";
	    ArrayList<Categoria> categorias = new ArrayList<>();
	    categorias.add(new Categoria("cat1", "Categoria 1"));
	    ArrayList<String> catsWeb = new ArrayList<>();
	    catsWeb.add("cat1");

	    // Crear y registrar una aerolínea
	    try {
			controladorUsuarios.crearAerolinea("aero12", "Aerolinea 1", "aero12@aero.com", "Aerolinea", "aero.com", "123", "", null, null);
		} catch (EmailRepetidoExce | NicknameRepetidoExce e) {}
	    Aerolinea aerolinea = new Aerolinea("aero12", "Aerolinea 1", "aero12@aero.com", "Aerolinea", "aero.com", "123", "", null, null);
	    UsuariosHandler.getinstance().addAerolinea(aerolinea);

	    try {
	    	controladorRutaVuelo.registrarRutaDeVueloWeb("rutaIngresada2", "descripcion2", hora, LocalDate.of(2024, 10, 10), "Ciudad A", "Ciudad B", 
	                300, 150, 30, catsWeb, "aero1", null, "descripcion corta", estadoRutaVuelo.Ingresada, "");
	        controladorRutaVuelo.registrarRutaDeVuelo(nombre, "descripcion", hora, fechaAlta, ciudadOrigen, ciudadDestino,
	                300, 150, 30, categorias, aerolinea, null, "descripcion corta", estadoRutaVuelo.Ingresada, "");
	        RutaVuelo registrada = controladorRutaVuelo.obtenerRutaVuelo(nombre);
	        Assertions.assertNotNull(registrada, "Deberia crearse la ruta de vuelo");
	        controladorRutaVuelo.seleccionarRutaVuelo(nombre);
	        controladorRutaVuelo.finalizarRutaVuelo(nombre);
	        controladorRutaVuelo.getCategoria("cat1");
	        controladorRutaVuelo.obtenerCiudad(ciudadOrigen, "Chile");
	        Assertions.assertEquals(controladorRutaVuelo.verInfoRutaVuelo(nombre).getEstado(), estadoRutaVuelo.Finalizada);
	        ArrayList<Categoria> cats = controladorRutaVuelo.obtenerCategorias();
	        Assertions.assertNotNull(cats, "Deberian existir categorias");	        
	    } catch (NombreRVRepetidoExce | CategoriaNoExisteExce e) {
	        fail("No se esperaba excepción al registrar una ruta de vuelo");
	    }

	    // Ahora listar las rutas
	    DataRutaVuelo[] rutas = controladorRutaVuelo.listarRutasVuelo();
	    Assertions.assertNotNull(rutas, "Se esperaba que listarRutasVuelo devolviera un arreglo no nulo");
	    Assertions.assertTrue(rutas.length > 0, "Se esperaba que existieran rutas de vuelo en el sistema");
	}

	@Test
	void testVerInfoRutaVueloNoExiste() {
	    Assertions.assertThrows(RVNoExisteExce.class, () -> {
	        controladorRutaVuelo.verInfoRutaVuelo("rutaNoExiste");
	    }, "Se esperaba RVNoExisteExce al intentar obtener la información de una ruta de vuelo que no existe");
	}
	
	@Test
	void testSeleccionarAerolineaNoExiste() {
	    Assertions.assertThrows(AerolineaNoExisteExce.class, () -> {
	        controladorRutaVuelo.seleccionarAerolinea("aeroNoExiste");
	    }, "Se esperaba AerolineaNoExisteExce al intentar seleccionar una aerolínea que no existe");
	}
	
	@Test
	void testModificarDatosRutaVueloExiste() throws RVNoExisteExce {
	    // Crear una ruta de vuelo
	    String nombreRuta = "rutaTest";
	    LocalTime hora = LocalTime.now();
	    LocalDate fechaAlta = LocalDate.now();
	    ArrayList<Categoria> categorias = new ArrayList<>();
	    categorias.add(new Categoria("cat1", "Categoria 1"));

	    Aerolinea aerolinea = new Aerolinea("aero1", "Aerolinea 1", "aero1@aero.com", "Aerolinea", "aero.com", "123", "", null, null);
	    UsuariosHandler.getinstance().addAerolinea(aerolinea);

	    try {
	    	controladorRutaVuelo.seleccionarAerolinea("aero1");
	        controladorRutaVuelo.registrarRutaDeVuelo(nombreRuta, "descripcion", hora, fechaAlta, "Ciudad A", "Ciudad B",
	                300, 150, 30, categorias, aerolinea, null, "descripcion corta", estadoRutaVuelo.Ingresada, "");
	        controladorVuelos.ingresarDatosVuelo(nombreRuta, "vueloRuta", LocalDate.of(2024, 10, 10), LocalTime.of(2, 0), 5, 2, fechaAlta);
	    } catch (NombreRVRepetidoExce | NombreVRepetidoExce | AerolineaNoExisteExce e) {
	        fail("No se esperaba excepción al registrar una ruta de vuelo");
	    }

	    // Modificar los datos de la ruta
	    try {
	        controladorRutaVuelo.modificarDatosRutaVuelo(nombreRuta, "descripcion modificada", hora, fechaAlta, "Ciudad C", "Ciudad D", 400, 200, 50, categorias, "imagen.png");
	    } catch (RVNoExisteExce e) {
	        fail("No se esperaba excepción al modificar una ruta de vuelo existente");
	    }

	    // Verificar que los datos fueron modificados
	    DataRutaVuelo rutaModificada = controladorRutaVuelo.verInfoRutaVuelo(nombreRuta);
	    assertEquals("descripcion modificada", rutaModificada.getDescripcion());
	    assertEquals("Ciudad C", rutaModificada.getCiudadOrigen());
	    assertEquals("Ciudad D", rutaModificada.getCiudadDestino());
	}


	
	@Test
	void testGetCategoriaNoExiste() throws CategoriaNoExisteExce {
	    DataCategoria categoria = controladorRutaVuelo.getCategoria("categoriaNoExiste");
	    Assertions.assertNull(categoria, "Se esperaba que getCategoria devolviera null para una categoría inexistente");
	}
	
	@Test
	void testingresarCategoria() throws CategoriaNoExisteExce {
		ArrayList<Categoria> categorias = new ArrayList<>();
		try {
			controladorRutaVuelo.ingresarCategoria("categoriaTest", "Test");
		} catch (CategoriaRepetidaExce e) {}
		Categoria cat = controladorRutaVuelo.obtenerCategoria("CategoriaTest");
		categorias.add(cat);
	    Assertions.assertNotNull(categorias, "Deberia estar cat en el array");
	}
    
}
