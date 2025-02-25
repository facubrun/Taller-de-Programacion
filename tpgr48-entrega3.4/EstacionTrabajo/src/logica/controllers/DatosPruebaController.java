package logica.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import logica.Datatypes.DataRutaVueloPaquete;
import logica.Datatypes.estadoRutaVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Fabrica;
import logica.Handlers.RutasVueloHandler;
import logica.Handlers.UsuariosHandler;
import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;

public class DatosPruebaController {
	

	public DatosPruebaController() {}

	public void cargarDatosBasicos() throws Exception {
		cargarUsuarios();
		cargarCategorias();
		cargarCiudades();
		cargarRutasDeVuelo();
		cargarVuelos();
		cargarPaquetes();
		cargarReservas();
	}

	public void cargarUsuarios() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IUsuariosController ctrlUsers = fabrica.getIControladorUsuario();

		ctrlUsers.crearAerolinea("aerolineas", "Aerolíneas Argentinas", "servicioalcliente@aerolineas.com.uy", "Aerolinea nacional de Argentina que ofrece vuelos directos entre multiples destinos", "https://www.aerolineas.com.ar", "zaq1xsw2", "US01.png", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearAerolinea("aireuropa", "Air Europa", "reservas@aireuropa.com.uy", "Aerolinea espanola que ofrece vuelos varios destinos en Europa y America", "https://www.aireuropa.com", "cde3vfr4", "US02.png", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("anarod87", "Ana", "arodriguez87@netuy.com", "Rodríguez", LocalDate.of(1987, 02, 18), "Uruguaya", "Pasaporte", "B2345678", "bgt5nhy6", "US03.jpg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("claire93d", "Claire", "claire.db@frmail.fr", "Rinaldi", LocalDate.of(1993, 8, 22), "Italiana", "Pasaporte", "20VF756483", "mju76yhn", "US04.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearAerolinea("copaair", "Copa Airlines", "contacto@copaair.com.uy", "Aerolinea panamena con conexiones a varios destinos en America y el Caribe", "https://www.copaair.com", "2wsx3edc", "US05.png", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("csexto", "Cristian", "csexto@adinet.com.uy", "Sexto", LocalDate.of(1987, 3, 26), "Uruguaya", "CI", "45871265", "4rfvbgt5", "US06.jpeg",new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("ejstar", "Emily", "emily.j@hotmail.com", "Johnson", LocalDate.of(1985, 6, 24), "Estadounidense", "Pasaporte", "485719842", "lkjoiu987", "US07.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("hernacar", "Carlos", "carlosh89@mxmail.com", "Hernández", LocalDate.of(1988, 9, 15), "Mexicano", "Pasaporte", "GZ1234567", "poi098lkj", "US08.jpg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearAerolinea("iberia", "Iberia", "atencionclientes@iberia.com.uy", "Aerolinea espanola que te conecta con Europa y otros destinos internacionales", "https://www.iberia.com", "qwer1234", "US09.png", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("jackwil", "Jack", "jack.w.90@mail.br", "Oliveira", LocalDate.of(1990, 12, 10), "Brasilera", "Pasaporte", "N98123456", "asdfzxcv", "US10.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("juanitop", "Juan", "juanito.uy@correo.com", "Pérez", LocalDate.of(1990, 3, 12), "Uruguaya", "CI", "39142842", "cde34rfv", "US11.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearAerolinea("latam", "LATAM Airlines", "info@latam.com.uy", "Ofrecemos vuelos nacionales e internacionales", "https://www.latam.com", "mki8nju7", "US12.png", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("liamth", "Liam", "liam.t.ca@mailbox.com", "Thompson", LocalDate.of(1992, 11, 30), "Canadiense", "Pasaporte", "AJ7684359", "bhu7vgy7", "US13.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("marsil14", "Martín", "m.silva94@webmail.uy", "Silva", LocalDate.of(1994, 1, 14), "Uruguaya", "Pasaporte", "C3456789", "vgy6cft5", "US14.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("martig", "Marta", "marta.garcia95@webmail.es", "García", LocalDate.of(1995, 7, 5), "Española", "Pasaporte", "X1245786L", "cft5xdr4", "US15.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearCliente("sofi89", "Sofía", "sofia.lopez@correouruguay.com", "López", LocalDate.of(1989, 4, 25), "Uruguaya", "Pasaporte", "A9876543", "xde2zsw1", "US16.jpeg", new ArrayList<>(), new ArrayList<>());
		ctrlUsers.crearAerolinea("zfly", "ZuluFly", "info@zfly.com", "Viajes exclusivos entre los destinos mas solicitados", "https://www.zfly.com", "r45tgvcf", "US17.png", new ArrayList<>(), new ArrayList<>());
	}

	public void cargarCategorias() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IRutaVueloController ctrlRutas = fabrica.getIControladorRutaVuelo();
		ctrlRutas.ingresarCategoria("Nacionales", "");
		ctrlRutas.ingresarCategoria("Internacionales", "");
		ctrlRutas.ingresarCategoria("Europa", "");
		ctrlRutas.ingresarCategoria("America", "");
		ctrlRutas.ingresarCategoria("Exclusivos", "");
		ctrlRutas.ingresarCategoria("Temporada", "");
		ctrlRutas.ingresarCategoria("Cortos", "");
	}

	public void cargarCiudades() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IRutaVueloController ctrlRutas = fabrica.getIControladorRutaVuelo();
		ctrlRutas.registrarCiudad("Montevideo", "Uruguay", "Carrasco", "Capital uruguaya, conocida por su Rambla, arquitectura colonial y vibrante vida cultural.", "https://montevideo.gub.uy", LocalDate.of(2024, 4, 1));
		ctrlRutas.registrarCiudad("Múnich", "Alemania", "Aeropuerto de Múnich", "Ciudad alemana con rica historia, arquitectura barroca y vibrante vida cultural", "https://www.munich.travel/es", LocalDate.of(2024, 6, 23));
		ctrlRutas.registrarCiudad("Ciudad de Panamá", "Panamá", "Tocumen", "Moderno centro urbano con rascacielos, el Canal de Panamá y vibrante vida cultural.", "https://www.atp.gob.pa/", LocalDate.of(2024, 6, 25));
		ctrlRutas.registrarCiudad("Buenos Aires", "Argentina", "Aeroparque Jorge Newbery", "Vibrante capital argentina, conocida por su arquitectura, tango y vida cultural.", "https://turismo.buenosaires.gob.ar/es", LocalDate.of(2024, 7, 5));
		ctrlRutas.registrarCiudad("Barcelona", "España", "Josep Tarradellas Barcelona–El Prat", "Ciudad catalana con arquitectura modernista, playas y vibrante vida cultural.", "https://ajuntament.barcelona.cat", LocalDate.of(2024, 7, 5));
		ctrlRutas.registrarCiudad("Santiago de Chile", "Chile", "Arturo Merino Benítez", "Capital chilena con moderna arquitectura, cerros y rica vida cultural", "https://disfrutasantiago.cl", LocalDate.of(2024, 7, 6));
		ctrlRutas.registrarCiudad("Punta del Este", "Uruguay", "Laguna del Sauce", "Famoso balneario uruguayo, con playas, vida nocturna y lujosos resorts", "https://www.maldonado.gub.uy", LocalDate.of(2024, 7, 15));
		ctrlRutas.registrarCiudad("Madrid", "España", "Adolfo Suárez Madrid-Barajas", "Vibrante capital española con rica historia, cultura y arquitectura impresionante.", "https://www.turismomadrid.es/es/", LocalDate.of(2024, 8, 12));
		ctrlRutas.registrarCiudad("Nueva York", "Estado Unidos", "John F. Kennedy", "Ciudad icónica con rascacielos, cultura diversa y atracciones famosas", "https://www.nyc.gov/", LocalDate.of(2024, 8, 25));
		ctrlRutas.registrarCiudad("Río de Janeiro", "Brasil", "Galeão Antonio Carlos Jobim", "Ciudad costera de Brasil, famosa por sus playas y la estatua del Cristo Redentor.", "https://riotur.rio/es/bienvenido/", LocalDate.of(2024, 7, 1));
		ctrlRutas.registrarCiudad("Sevilla", "España", "Sevilla-San Pablo", "Sevilla, es un destino turístico fascinante que ofrece una rica mezcla de historia, cultura y belleza.", "https://visitasevilla.es/", LocalDate.of(2024, 2, 29));
	}

	public void cargarRutasDeVuelo() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IRutaVueloController ctrlRutas = fabrica.getIControladorRutaVuelo();
		UsuariosHandler usrH = UsuariosHandler.getinstance();
		RutasVueloHandler rvH = RutasVueloHandler.getInstance();

		ArrayList<Categoria> categorias = new ArrayList<>();
		Aerolinea aerolinea = null;

		categorias.add(new Categoria("Internacionales", ""));
		categorias.add(new Categoria("Europa", ""));
		aerolinea = usrH.obtenerAerolinea("aireuropa");
		ctrlRutas.registrarRutaDeVuelo("UX46", "Tiempo de vuelo aproximado de 12 horas con comidas incluidas", LocalTime.of(12, 20), LocalDate.of(2024, 8, 19), "Montevideo", "Madrid", 950f, 450f, 50f, categorias, aerolinea, "RV01.jpeg", "Montevideo - Madrid por Air Europa (UX46)", estadoRutaVuelo.Ingresada, "");
        rvH.obtenerRutaVuelo("UX46").setVisitas(2);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Internacionales", ""));
		categorias.add(new Categoria("Europa", ""));
		categorias.add(new Categoria("Temporada", ""));
		aerolinea = usrH.obtenerAerolinea("iberia");
		ctrlRutas.registrarRutaDeVuelo("IB6012", "Tiempo de vuelo: cerca de 12 horas. Incluye comidas, bebidas y entretenimiento en vuelo", LocalTime.of(13, 0), LocalDate.of(2024, 8, 22), "Montevideo", "Madrid", 1800f, 350f, 60f, categorias, aerolinea, "RV02.jpeg", "Montevideo - Madrid por Iberia (IB6012)", estadoRutaVuelo.Confirmada, "https://youtu.be/bpeF1YCiUe0?si=E5k3CZCD6YygWtEE");
		rvH.obtenerRutaVuelo("IB6012").setVisitas(1);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("America", ""));
		categorias.add(new Categoria("Temporada", ""));
		aerolinea = usrH.obtenerAerolinea("aerolineas");
		ctrlRutas.registrarRutaDeVuelo("AR1380", "Tiempo de vuelo directo y sin escalas", LocalTime.of(7, 55), LocalDate.of(2024, 8, 9), "Buenos Aires", "Montevideo", 340f, 120f, 30f, categorias, aerolinea, "RV03.jpg", "Buenos Aires - Montevideo por Aerolíneas Argentinas (AR1380)", estadoRutaVuelo.Confirmada, "https://youtu.be/HBY4FSi1RHo?si=OQkyEwb1Dweso2pQ");
        rvH.obtenerRutaVuelo("AR1380").setVisitas(8);
		
		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Cortos", ""));
		categorias.add(new Categoria("America", ""));
		aerolinea = usrH.obtenerAerolinea("aerolineas");
		ctrlRutas.registrarRutaDeVuelo("AR1381", "Tiempo estimado de vuelo 55 minutos", LocalTime.of(9, 35), LocalDate.of(2024, 8, 9), "Montevideo", "Buenos Aires", 400f, 160f, 30f, categorias, aerolinea, "RV04.jpg", "Montevideo - Buenos Aires por Aerolíneas Argentinas (AR1381)", estadoRutaVuelo.Confirmada, "https://youtu.be/HBY4FSi1RHo?si=OQkyEwb1Dweso2pQ");
        rvH.obtenerRutaVuelo("AR1381").setVisitas(3);
		
		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Exclusivos", ""));
		aerolinea = usrH.obtenerAerolinea("zfly");
		ctrlRutas.registrarRutaDeVuelo("ZL1501", "Viaje exclusivo en aviones pequeños, tiempo de vuelo 20 minutos", LocalTime.of(12, 0), LocalDate.of(2024, 8, 11), "Montevideo", "Punta del Este", 200f, 60f, 10f, categorias, aerolinea, "RV05.jpg", "Montevideo - Punta del Este por Zulu Fly (ZL1501)", estadoRutaVuelo.Finalizada, "");
        rvH.obtenerRutaVuelo("ZL1501").setVisitas(1);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Temporada", ""));
		categorias.add(new Categoria("Europa", ""));
		aerolinea = usrH.obtenerAerolinea("iberia");
		ctrlRutas.registrarRutaDeVuelo("IB6011", "Ruta directa con tiempo aproximado de vuelo de 11 horas", LocalTime.of(0, 10), LocalDate.of(2024, 8, 28), "Madrid", "Montevideo", 1000f, 400f, 60f, categorias, aerolinea, "RV06.jpg", "Madrid - Montevideo por Iberia (IB6011)", estadoRutaVuelo.Confirmada, "");
        rvH.obtenerRutaVuelo("IB6011").setVisitas(7);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Europa", ""));
		categorias.add(new Categoria("Internacionales", ""));
		aerolinea = usrH.obtenerAerolinea("aireuropa");
		ctrlRutas.registrarRutaDeVuelo("UX45", "Ideal para quienes buscan una experiencia de vuelo sin complicaciones, con todos los servicios necesarios para un trayecto largo.", LocalTime.of(23, 55), LocalDate.of(2024, 8, 25), "Madrid", "Montevideo", 950f, 450f, 50f, categorias, aerolinea, "RV07.jpg", "Madrid - Montevideo por Air Europa (UX45)", estadoRutaVuelo.Confirmada, "");
        rvH.obtenerRutaVuelo("UX45").setVisitas(4);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("America", ""));
		categorias.add(new Categoria("Temporada", ""));
		aerolinea = usrH.obtenerAerolinea("latam");
		ctrlRutas.registrarRutaDeVuelo("LA406", "Tiempo de vuelo: alrededor de 2 horas y 30 minutos. Ofrece vuelos directos con servicio de bebidas y snacks a bordo Ideal para un viaje rápido y cómodo entre las dos ciudades.", LocalTime.of(12, 31), LocalDate.of(2024, 7, 30), "Santiago de Chile", "Montevideo", 500f, 100f, 50f, categorias, aerolinea, "RV08.jpg", "Santiago de Chile - Montevideo por LATAM Airlines(LA406)", estadoRutaVuelo.Ingresada, "https://youtu.be/OUkl-3PkMw4?si=x-VQ5OxLjwLX0wdb");
        rvH.obtenerRutaVuelo("LA406").setVisitas(1);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Nacionales", ""));
		categorias.add(new Categoria("Cortos", ""));
		aerolinea = usrH.obtenerAerolinea("iberia");
		ctrlRutas.registrarRutaDeVuelo("IB34", "Tiempo de vuelo: aproximadamente 1 hora y 20 minutos. Ofrece vuelos directos con servicio de bebidas y snacks a bordo. Ideal para un viaje rápido y eficiente entre las dos principales ciudades españolas.", LocalTime.of(7, 5), LocalDate.of(2024, 8, 16), "Madrid", "Barcelona", 400f, 170f, 20f, categorias, aerolinea, "RV09.jpeg", "Madrid - Barcelona por Iberia (IB34)", estadoRutaVuelo.Confirmada, "https://youtu.be/nH9xPwfhueo?si=bWc79j1X5jksvUuo");
        rvH.obtenerRutaVuelo("IB34").setVisitas(7);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Europa", ""));
		categorias.add(new Categoria("Cortos", ""));
		aerolinea = usrH.obtenerAerolinea("aireuropa");
		ctrlRutas.registrarRutaDeVuelo("UX1515", "Tiempo de vuelo: aproximadamente 2 horas y 30 minutos. Ofrece vuelos directos con servicio de bebidas y snacks a bordo, además de entretenimiento para hacer tu viaje más placentero. Ideal para un traslado rápido y cómodo entre las dos ciudades europeas", LocalTime.of(7, 10), LocalDate.of(2024, 8, 22), "Madrid", "Múnich", 250f, 50f, 60f, categorias, aerolinea, "RV10.jpg", "Madrid - Barcelona por Iberia (IB34)", estadoRutaVuelo.Rechazada, "");
        rvH.obtenerRutaVuelo("UX1515").setVisitas(2);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("America", ""));
		aerolinea = usrH.obtenerAerolinea("copaair");
		ctrlRutas.registrarRutaDeVuelo("CM284", "Tiempo de vuelo: aproximadamente 7 horas. Copa Airlines ofrece comidas, bebidas y entretenimiento a bordo para un viaje cómodo y agradable. Ideal para quienes buscan una conexión eficiente entre Sudamérica y Centroamérica", LocalTime.of(0, 30), LocalDate.of(2024, 7, 20), "Montevideo", "Ciudad de Panamá", 1800f, 350f, 60f, categorias, aerolinea, "RV11.jpg", "Madrid - Múnich por Air Europa (UX1515)", estadoRutaVuelo.Confirmada, "");
        rvH.obtenerRutaVuelo("CM284").setVisitas(9);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("America", ""));
		categorias.add(new Categoria("Internacionales", ""));
		aerolinea = usrH.obtenerAerolinea("copaair");
		ctrlRutas.registrarRutaDeVuelo("CM804", "Copa Airlines ofrece comidas, bebidas y entretenimiento a bordo, asegurando una experiencia de viaje cómoda y agradable entre Centroamérica y la ciudad de Nueva York.", LocalTime.of(18, 33), LocalDate.of(2024, 8, 27), "Ciudad de Panamá", "Nueva York", 1000f, 400f, 15f, categorias, aerolinea, "RV12.jpg", "Ciudad de Panam´a - Nueva York por Copa Airlines(CM804)", estadoRutaVuelo.Confirmada, "https://youtu.be/-gOq2NNFXbA?si=lZH-dytsLPA5vhVc");
        rvH.obtenerRutaVuelo("CM804").setVisitas(4);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Internacionales", ""));
		categorias.add(new Categoria("Temporada", ""));
		categorias.add(new Categoria("America", ""));
		aerolinea = usrH.obtenerAerolinea("latam");
		ctrlRutas.registrarRutaDeVuelo("LA533", "LATAM ofrece comidas, bebidas y entretenimiento a bordo para un viaje cómodo y placentero entre Estados Unidos y Chile.", LocalTime.of(20, 5), LocalDate.of(2024, 8, 28), "Nueva York", "Santiago de Chile", 1600f, 600f, 40f, categorias, aerolinea, "RV13.jpg", "Nueva York - Santiago de Chile por LATAM Airlines(LA533)", estadoRutaVuelo.Rechazada, "");
        rvH.obtenerRutaVuelo("LA533").setVisitas(10);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Internacionales", ""));
		categorias.add(new Categoria("America", ""));
		aerolinea = usrH.obtenerAerolinea("copaair");
		ctrlRutas.registrarRutaDeVuelo("CM283", "Elvuelo tiene una duración aproximada de 7 horas. Ofrece comidas, bebidas y entretenimiento a bordo para una experiencia de viaje cómoda y agradable", LocalTime.of(15, 43), LocalDate.of(2024, 7, 14), "Ciudad de Panamá", "Montevideo", 1700f, 500f, 60f, categorias, aerolinea, "", "Ciudad de Panamá - Montevideo por Copa Airlines(CM283)", estadoRutaVuelo.Confirmada, "https://youtu.be/6vUHyM26E3A?si=H9D56SyAZIo0EkYR");
        rvH.obtenerRutaVuelo("CM283").setVisitas(0);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Temporada", ""));
		aerolinea = usrH.obtenerAerolinea("latam");
		ctrlRutas.registrarRutaDeVuelo("LA407", "El vuelo incluye bebidas y snacks a bordo, con una duración aproximada de 2 horas y 30 minutos, ofreciendo una experiencia cómoda entre las dos ciudades sudamericanas", LocalTime.of(19, 40), LocalDate.of(2024, 8, 2), "Montevideo", "Santiago de Chile", 500f, 150f, 50f, categorias, aerolinea, "RV15.jpg", "Montevideo - Santiago de Chile por LATAM Airlines(LA407)", estadoRutaVuelo.Ingresada, "https://youtu.be/TzD46EJXgdM?si=eqYxFzqamdU6BcL1 ");
        rvH.obtenerRutaVuelo("LA407").setVisitas(0);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Internacionales", ""));
		categorias.add(new Categoria("America", ""));
		categorias.add(new Categoria("Cortos", ""));
		aerolinea = usrH.obtenerAerolinea("zfly");
		ctrlRutas.registrarRutaDeVuelo("ZL1502", "Tiempo estimado de vuelo 2 horas y 30 minutos, vuelo directo.", LocalTime.of(12, 50), LocalDate.of(2024, 7, 28), "Montevideo", "Río de Janeiro", 190f, 75f, 30f, categorias, aerolinea, "RV16.jpeg", "Montevideo - Río de Janeiro por Zulu Fly (ZL1502)", estadoRutaVuelo.Ingresada, "");
        rvH.obtenerRutaVuelo("ZL1502").setVisitas(6);

		aerolinea = null;
		categorias = new ArrayList<>();
		categorias.add(new Categoria("Nacionales", ""));
		categorias.add(new Categoria("Europa", ""));
		categorias.add(new Categoria("Cortos", ""));
		aerolinea = usrH.obtenerAerolinea("iberia");
		ctrlRutas.registrarRutaDeVuelo("IB3009", "Tiempo estimado de vuelo es de 1 hora y 40 minutos.", LocalTime.of(14, 55), LocalDate.of(2024, 7, 20), "Barcelona", "Sevilla", 250f, 140f, 20f, categorias, aerolinea, "RV17.jpeg", "Barcelona - Sevilla por Iberia (IB3009)", estadoRutaVuelo.Confirmada, "");
		rvH.obtenerRutaVuelo("IB3009").setVisitas(7);
	}

	public void cargarVuelos() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IVuelosController ctrlVuelos = fabrica.getIControladorVuelos();

		ctrlVuelos.ingresarDatosVuelo("IB6012", "IB6012272", LocalDate.of(2024, 11, 18), LocalTime.of(11, 31), 269, 19, LocalDate.of(2024, 8, 24));
		ctrlVuelos.ingresarDatosVuelo("IB6012", "IB6012377", LocalDate.of(2024, 12, 3), LocalTime.of(11, 29), 269, 19, LocalDate.of(2024, 8, 29));
		ctrlVuelos.ingresarDatosVuelo("IB6012", "IB60124102", LocalDate.of(2024, 12, 1), LocalTime.of(11, 46), 269, 19, LocalDate.of(2024, 8, 29));
		ctrlVuelos.ingresarDatosVuelo("IB6012", "IB60125114", LocalDate.of(2024, 11, 30), LocalTime.of(11, 57), 269, 19, LocalDate.of(2024, 8, 28));
		ctrlVuelos.ingresarDatosVuelo("IB6011", "IB6011651", LocalDate.of(2024, 11, 29), LocalTime.of(11, 56), 200, 34, LocalDate.of(2024, 8, 28));
		ctrlVuelos.ingresarDatosVuelo("IB6011", "IB6011769", LocalDate.of(2024, 11, 30), LocalTime.of(12, 4), 200, 34, LocalDate.of(2024, 8, 29));
		ctrlVuelos.ingresarDatosVuelo("UX45", "UX45810", LocalDate.of(2024, 11, 30), LocalTime.of(12, 1), 150, 8, LocalDate.of(2024, 8, 29));
		ctrlVuelos.ingresarDatosVuelo("AR1380", "AR1380939", LocalDate.of(2024, 11, 1), LocalTime.of(0, 26), 153, 16, LocalDate.of(2024, 8, 26));
		ctrlVuelos.ingresarDatosVuelo("AR1380", "AR13801059", LocalDate.of(2024, 11, 1), LocalTime.of(0, 30), 162, 8, LocalDate.of(2024, 8, 27));
		ctrlVuelos.ingresarDatosVuelo("AR1381", "AR138111124", LocalDate.of(2024, 11, 24), LocalTime.of(0, 47), 248, 16, LocalDate.of(2024, 8, 28));
		ctrlVuelos.ingresarDatosVuelo("AR1381", "AR1381124", LocalDate.of(2024, 10, 25), LocalTime.of(0, 28), 162, 8, LocalDate.of(2024, 8, 14));
		ctrlVuelos.ingresarDatosVuelo("ZL1501", "ZL15011350", LocalDate.of(2024, 11, 1), LocalTime.of(0, 15), 2, 2, LocalDate.of(2024, 8, 27));
		ctrlVuelos.ingresarDatosVuelo("ZL1501", "ZL15011419", LocalDate.of(2024, 10, 15), LocalTime.of(0, 24), 2, 2, LocalDate.of(2024, 8, 26));
		ctrlVuelos.ingresarDatosVuelo("ZL1501", "ZL15011527", LocalDate.of(2024, 10, 16), LocalTime.of(0, 25), 0, 4, LocalDate.of(2024, 8, 16));
		ctrlVuelos.ingresarDatosVuelo("CM284", "CM2841635", LocalDate.of(2024, 11, 28), LocalTime.of(6, 55), 159, 20, LocalDate.of(2024, 8, 21));
		ctrlVuelos.ingresarDatosVuelo("CM804", "CM8041764", LocalDate.of(2025, 1, 27), LocalTime.of(4, 37), 160, 16, LocalDate.of(2024, 8, 27));
		ctrlVuelos.ingresarDatosVuelo("IB34", "IB3418130", LocalDate.of(2024, 11, 30), LocalTime.of(0, 54), 171, 16, LocalDate.of(2024, 8, 29));
		ctrlVuelos.ingresarDatosVuelo("CM283", "CM2831967", LocalDate.of(2025, 1, 30), LocalTime.of(6, 27), 146, 20, LocalDate.of(2024, 8, 3));
		ctrlVuelos.ingresarDatosVuelo("CM284", "CM2842032", LocalDate.of(2025, 1, 3), LocalTime.of(6, 47), 147, 20, LocalDate.of(2024, 8, 16));
		ctrlVuelos.ingresarDatosVuelo("CM284", "CM284218", LocalDate.of(2024, 11, 29), LocalTime.of(7, 3), 159, 20, LocalDate.of(2024, 7, 22));
		ctrlVuelos.ingresarDatosVuelo("IB3009", "IB3009689", LocalDate.of(2024, 11, 25), LocalTime.of(1, 40), 120, 45, LocalDate.of(2024, 9, 2));
	}

	public void cargarPaquetes() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IPaqueteController ctrlPaquetes = fabrica.getIControladorPaquete();
		IRutaVueloController ctrlRutas = fabrica.getIControladorRutaVuelo();
		ArrayList<DataRutaVueloPaquete> rutasPaquete = new ArrayList<>();
		
		rutasPaquete = new ArrayList<>();
        rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("IB6012"),1));
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("IB6011"),1));
		ctrlPaquetes.crearPaquete("Madrid ida y vuelta", "Descubre Madrid con este paquete perfecto para una escapada completa. Disfruta de vuelos directos ida y vuelta, con cómodos horarios que te permiten aprovechar al máximo tu tiempo en la vibrante capital española.", 120, 50, 1400, LocalDate.of(2023, 8, 23), rutasPaquete);
		
		rutasPaquete = new ArrayList<>();
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("AR1381"),3));
		ctrlPaquetes.crearPaquete("Cruzar el Charco", "Escápate a Buenos Aires  y sumérgete en la vibrante vida de la capital argentina. Este paquete incluye vuelos directos", 150, 30, 1008, LocalDate.of(2024, 8, 25), rutasPaquete);
		
		rutasPaquete = new ArrayList<>();
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("IB34"),1));
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("IB6012"),1));
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("IB3009"),1));
		ctrlPaquetes.crearPaquete("Recorrer España", "Descubre lo mejor de España en un solo viaje.", 30, 10, 594, LocalDate.of(2024, 8, 29), rutasPaquete);	
		
		rutasPaquete = new ArrayList<>();
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("CM284"),2));
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("CM283"),2));
		ctrlPaquetes.crearPaquete("Descubre la Ciudad de Panama", "La Ciudad de Panama es una vibrante metropoli que combina historia, cultura y modernidad. Conocida como el ”Corazon de las Americas”, esta ciudad es famosa por su impresionante Canal, una obra maestra de la ingenieria que conecta el oceano Atlantico y el Pacifico.", 180, 50, 2050, LocalDate.of(2024, 9, 21), rutasPaquete);
		
		rutasPaquete = new ArrayList<>();
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("IB6012"),4));
		rutasPaquete.add(new DataRutaVueloPaquete(ctrlRutas.verInfoRutaVuelo("CM804"),2));
		ctrlPaquetes.crearPaquete("Destinos Inolvidables: NY - Madrid", "Nueva York, la ciudad que nunca duerme te espera con sus iconicas calles, rascacielos impresionantes y una cultura vibrante. Sumergete a su vez, en la rica historia y el arte de la capital española, Madrid. Pasea por el majestuoso Parque del Retiro, explora el Museo del Prado y disfruta de la autentica gastronomia local en sus tapas y mercados.", 90, 40, 2040, LocalDate.of(2024, 9, 25), rutasPaquete);
	}

	public void cargarReservas() throws Exception {
		Fabrica fabrica = Fabrica.getInstance();
		IVuelosController ctrlVuelos = fabrica.getIControladorVuelos();
		IUsuariosController ctrlUsers = fabrica.getIControladorUsuario();
		ArrayList<String> pasajeros = new ArrayList<>();
		
		ctrlVuelos.reservarVuelo("IB6012272", "hernacar", tipoAsiento.Turista, 1, 2, LocalDate.of(2024, 8, 28), pasajeros);
		ctrlUsers.realizarCheckIn("hernacar", "IB6012272");
		
		pasajeros.add("Jill Perk");
		ctrlVuelos.reservarVuelo("IB6012272", "jackwil", tipoAsiento.Ejecutivo, 2, 4, LocalDate.of(2024, 8, 29), pasajeros);
		ctrlUsers.realizarCheckIn("jackwil", "IB6012272");
		
		pasajeros = new ArrayList<>();
		pasajeros.add("Marta Lopez");
		ctrlVuelos.reservarVuelo("ZL15011350", "csexto", tipoAsiento.Turista, 2, 0, LocalDate.of(2024, 8, 30), pasajeros);
		ctrlUsers.realizarCheckIn("csexto", "ZL15011350");
		
		pasajeros = new ArrayList<>();
		pasajeros.add("Lucas Morales");
		ctrlVuelos.reservarVuelo("ZL15011350", "anarod87", tipoAsiento.Ejecutivo, 2, 2, LocalDate.of(2024, 8, 30), pasajeros);
		ctrlUsers.realizarCheckIn("anarod87", "ZL15011350");
		
		pasajeros = new ArrayList<>();
		pasajeros.add("Franco Gonzalez");
		ctrlVuelos.reservarVuelo("ZL15011419", "juanitop", tipoAsiento.Turista, 2, 1, LocalDate.of(2024, 8, 27), pasajeros);
		ctrlUsers.realizarCheckIn("juanitop", "ZL15011419");
		
		pasajeros = new ArrayList<>();
		pasajeros.add("Jack Jhonson");
		pasajeros.add("Liberty Trent");
		pasajeros.add("Marc Ruffalo");
		pasajeros.add("Jessica Landon");
		pasajeros.add("Robert Shank");
		pasajeros.add("Frank Trent");
		pasajeros.add("Lucy Felton");
		ctrlVuelos.reservarVuelo("AR13801059", "ejstar", tipoAsiento.Ejecutivo, 8, 5, LocalDate.of(2024, 8, 28), pasajeros);
		
		pasajeros = new ArrayList<>();
		pasajeros.add("Jack Jhonson");
		pasajeros.add("Trent Jhonson");
		ctrlVuelos.reservarVuelo("AR1381124", "ejstar", tipoAsiento.Ejecutivo, 3, 3, LocalDate.of(2024, 8, 29), pasajeros);
		ctrlUsers.realizarCheckIn("ejstar", "AR1381124");
		
		pasajeros = new ArrayList<>();
		ctrlVuelos.reservarVuelo("AR138111124", "ejstar", tipoAsiento.Ejecutivo, 1, 1, LocalDate.of(2024, 8, 30), pasajeros);

		ctrlUsers.getReservasSinCheckIn("ejstar");
		pasajeros = new ArrayList<>();
		ctrlVuelos.reservarVuelo("CM2841635", "juanitop", tipoAsiento.Turista, 1, 1, LocalDate.of(2024, 9, 30), pasajeros); 
		
		
		pasajeros = new ArrayList<>();
		pasajeros.add("Marta Lima");
		ctrlVuelos.reservarVuelo("CM2842032", "hernacar", tipoAsiento.Turista, 2, 1, LocalDate.of(2024, 10, 01), pasajeros);

		pasajeros = new ArrayList<>();
		pasajeros.add("Marta Lima");
		ctrlVuelos.reservarVuelo("CM2831967", "hernacar", tipoAsiento.Ejecutivo, 2, 2, LocalDate.of(2024, 10, 01), pasajeros);
	}
}