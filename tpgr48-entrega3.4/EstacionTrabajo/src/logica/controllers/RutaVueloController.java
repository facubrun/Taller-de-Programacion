package logica.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import exceptions.AerolineaNoExisteExce;
import exceptions.CategoriaRepetidaExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.NicknameRepetidoExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.estadoRutaVuelo;
import logica.Datatypes.DataCategoria;
import logica.Handlers.RutasVueloHandler;
import logica.Handlers.UsuariosHandler;
import logica.Interfaces.IRutaVueloController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import logica.clases.Ciudad;
import logica.clases.Reserva;
import logica.clases.RutaVuelo;
import logica.clases.Vuelo;

public class RutaVueloController implements IRutaVueloController {

	public void registrarRutaDeVuelo(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta,
			String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<Categoria> categorias, Aerolinea aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video) throws NombreRVRepetidoExce {

		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		UsuariosHandler usuarios = UsuariosHandler.getinstance();

		if (rutasVuelo.existeRutaVuelo(nombre)) {
			throw new NombreRVRepetidoExce("El nombre " + nombre + " de la ruta de vuelo ya está en uso");
		} else {
			RutaVuelo rv = new RutaVuelo(nombre, descripcion, hora, fechaAlta, ciudadOrigen, ciudadDestino, costoEjecutivo,
					costoTurista, costoExtra, categorias, aerolinea, imagen, descripcionCorta, estado, video);
			rutasVuelo.agregarRutaVuelo(rv);
			usuarios.agregarRutaVueloAerolinea(aerolinea.getNickname(), rv);
		}
	}
	
	public void registrarRutaDeVueloWeb(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta,
			String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<String> categorias, String aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video)
			throws NombreRVRepetidoExce {

		Aerolinea aer = UsuariosHandler.getinstance().obtenerAerolinea(aerolinea);

		ArrayList<Categoria> cats = new ArrayList<Categoria>();
		for (String cat : categorias) {
			Categoria c = RutasVueloHandler.getInstance().obtenerCategoria(cat);
			cats.add(c);
		}

		this.registrarRutaDeVuelo(nombre, descripcion, hora, fechaAlta, ciudadOrigen, ciudadDestino, costoEjecutivo,
				costoTurista, costoExtra, cats, aer, imagen, descripcionCorta, estado, video);
	}

	public RutaVuelo obtenerRutaVuelo(String nomRV) {
		RutasVueloHandler rutaVuelo = RutasVueloHandler.getInstance();
		return rutaVuelo.obtenerRutaVuelo(nomRV);
	}
	
	public void seleccionarAerolinea(String nickA) throws AerolineaNoExisteExce {
		UsuariosHandler usuarios = UsuariosHandler.getinstance();
		if (usuarios.existeAerolineaNick(nickA)) {
			String nombreAerolinea = usuarios.getAerolinea().get(nickA).getNombre();
		}
	}

	public void seleccionarRutaVuelo(String nombreRV) throws RVNoExisteExce {
		RutasVueloHandler rutaVuelo = RutasVueloHandler.getInstance();
		String nombreRutaVuelo = rutaVuelo.obtenerDatosRutaVuelo(nombreRV).getNombre();
	}

	public void registrarCiudad(String nombre, String pais, String aeropuerto, String descripcion, String sitioWeb,
			LocalDate fechaAlta) throws NicknameRepetidoExce {

		RutasVueloHandler user = RutasVueloHandler.getInstance();
		if (user.existeParPC(pais, nombre)) {
			throw new NicknameRepetidoExce("El par " + pais + "-" + nombre + " ya esta registrado");
		} else {
			Ciudad ciudad = new Ciudad(nombre, pais, aeropuerto, descripcion, sitioWeb, fechaAlta);
			user.addCiudad(ciudad);
		}
	}
	

	public void ingresarCategoria(String nombreCat, String descripcion) throws CategoriaRepetidaExce {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();

		if (rutasVuelo.existeCategoria(nombreCat)) {
			throw new CategoriaRepetidaExce("La categoria " + nombreCat + " ya está en uso");
		} else {
			Categoria cat = new Categoria(nombreCat, descripcion);
			rutasVuelo.agregarCategoria(cat);
		}
	}

	public DataCategoria getCategoria(String nombre) {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		DataCategoria categoria = rutasVuelo.getCategoria(nombre);

		return categoria;

	}

	public DataRutaVuelo verInfoRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		RutaVuelo rv = rutasVuelo.obtenerRutaVuelo(nombreRutaVuelo);
		if (rv == null) {
			throw new RVNoExisteExce("La ruta de vuelo " + nombreRutaVuelo + " no existe");
		} else {
			ArrayList<String> categorias = new ArrayList<String>();
			for (Categoria cat : rv.getCategorias()) {
				categorias.add(cat.getNombre());
			}

			ArrayList<DataVuelo> vuelos = new ArrayList<DataVuelo>();
			for (Vuelo v : rv.getVuelos()) {
				ArrayList<DataReserva> reservas = new ArrayList<DataReserva>();
				for (Reserva r : v.getReservas()) {
					DataReserva dr = new DataReserva(r.getCliente().getNickname(), r.getVuelo().getNombre(),
							r.getFechaReserva(), r.getAsientoSeleccionado(), r.getCantidadPasajes(),
							r.getCantidadExtraEquipaje(), r.getCostoTotal());
					reservas.add(dr);
				}
				DataVuelo dv = new DataVuelo(v.getNombre(), v.getFecha(), v.getDuracion(), v.getMaxTurista(),
						v.getMaxEjecutivo(), v.getFechaAlta(), reservas);
				vuelos.add(dv);
			}

			return new DataRutaVuelo(rv.getNombre(), rv.getDescripcion(), rv.getHora(), rv.getFechaAlta(),
					rv.getCiudadOrigen(), rv.getCiudadDestino(), rv.getCostoEjecutivo(), rv.getCostoTurista(),
					rv.getCostoExtra(), categorias, vuelos, rv.getAerolinea().getNickname(), rv.getImagen(), rv.getDescCorta(), rv.getEstado(), rv.getVideo(), rv.getVisitas());
		}
	}

	public ArrayList<DataRutaVuelo> listarRutasVueloPorAerolinea(String nombreAerolinea) throws UsuarioNoExisteExce {
		ArrayList<DataRutaVuelo> rutas = new ArrayList<DataRutaVuelo>();

		try {
			DataAerolinea aerolinea = Fabrica.getInstance().getIControladorUsuario().verInfoAerolinea(nombreAerolinea);
			rutas = aerolinea.getRutasVuelo();
		} catch (UsuarioNoExisteExce e) {
			throw new UsuarioNoExisteExce("La aerolinea " + nombreAerolinea + " no existe");
		}

		return rutas;
	}
	
	public ArrayList<DataRutaVuelo> listarRutasVueloIngresadasPorAerolinea(String nombreAerolinea) throws UsuarioNoExisteExce {
		ArrayList<DataRutaVuelo> rutas = new ArrayList<DataRutaVuelo>();
		ArrayList<DataRutaVuelo> rutas_ingresadas = new ArrayList<DataRutaVuelo>();
		try {
			DataAerolinea aerolinea = Fabrica.getInstance().getIControladorUsuario().verInfoAerolinea(nombreAerolinea);
			rutas = aerolinea.getRutasVuelo();
			 for (DataRutaVuelo dataRV : rutas) {
				if (dataRV.getEstado() == estadoRutaVuelo.Ingresada) {
					rutas_ingresadas.add(dataRV);
				}
			}
		} catch (UsuarioNoExisteExce e) {
			throw new UsuarioNoExisteExce("La aerolinea " + nombreAerolinea + " no existe");
		}

		return rutas_ingresadas;
	}

	
	public Ciudad obtenerCiudad(String nombre, String pais) {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		return rutasVuelo.getCiudad(nombre, pais);
	}
	
	public ArrayList<String> listarCiudadesDisponibles() {
	    RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
	    return rutasVuelo.obtenerCiudades();
	}

	public ArrayList<Categoria> obtenerCategorias() {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		return rutasVuelo.getCategorias();
	}

	public Categoria obtenerCategoria(String nombre) {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		return rutasVuelo.obtenerCategoria(nombre);
	}
	
	public DataRutaVuelo[] listarRutasVuelo() throws RVNoExisteExce {
		RutasVueloHandler rutasVuelo = RutasVueloHandler.getInstance();
		RutaVuelo[] rutas = rutasVuelo.listarRutasVuelo();
		DataRutaVuelo[] dataRutas = new DataRutaVuelo[rutas.length];

		for (int i = 0; i < rutas.length; i++) {
			dataRutas[i] = verInfoRutaVuelo(rutas[i].getNombre());
		}

		return dataRutas;
	}
	
	public void modificarDatosRutaVuelo(String nombreRV, String descripcion, LocalTime hora, LocalDate fechaAlta,
			String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<Categoria> categorias, String imagen) throws RVNoExisteExce {
		RutasVueloHandler rv = RutasVueloHandler.getInstance();
		RutaVuelo rutaVuelo = rv.obtenerRutaVuelo(nombreRV);
		if (rutaVuelo == null) {
			throw new RVNoExisteExce("La ruta de vuelo" + nombreRV + " no existe");
		} else {
			rutaVuelo.setDescripcion(descripcion);
			rutaVuelo.setHora(hora);
			rutaVuelo.setFechaAlta(fechaAlta);
			rutaVuelo.setCiudadOrigen(ciudadOrigen);
			rutaVuelo.setCiudadDestino(ciudadDestino);
			rutaVuelo.setCostoEjecutivo(costoEjecutivo);
			rutaVuelo.setCostoTurista(costoTurista);
			rutaVuelo.setCostoExtra(costoExtra);
			rutaVuelo.setCategorias(categorias);
			rutaVuelo.setImagen(imagen);
		}
	}
	
	@Override
	public void finalizarRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
        RutasVueloHandler rutasVueloHandler = RutasVueloHandler.getInstance();

        RutaVuelo ruta = rutasVueloHandler.obtenerRutaVuelo(nombreRutaVuelo);

        if (ruta == null) {
            throw new RVNoExisteExce("La ruta de vuelo " + nombreRutaVuelo + " no existe");
        }

        ruta.setEstado(estadoRutaVuelo.Finalizada);

    }

    @Override
	public void agregarVisitaRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
		RutasVueloHandler rutasVueloHandler = RutasVueloHandler.getInstance();
		RutaVuelo ruta = rutasVueloHandler.obtenerRutaVuelo(nombreRutaVuelo);

		if (ruta == null) {
			throw new RVNoExisteExce("La ruta de vuelo " + nombreRutaVuelo + " no existe");
		}

		ruta.addVisita();
	}
}
