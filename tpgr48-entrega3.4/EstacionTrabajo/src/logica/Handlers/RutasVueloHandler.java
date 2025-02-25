package logica.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.DataCategoria;
import logica.clases.Categoria;
import logica.clases.Ciudad;
import logica.clases.Reserva;
import logica.clases.RutaVuelo;
import logica.clases.Vuelo;

public class RutasVueloHandler {
	private Map<String, RutaVuelo> rutasVuelo;
	private Set<Ciudad> ciudades;
	private Map<String, Categoria> categorias;
	private static RutasVueloHandler instancia = null;

	private RutasVueloHandler() {
		rutasVuelo = new HashMap<String, RutaVuelo>();
		ciudades = new HashSet<Ciudad>();
		categorias = new HashMap<String, Categoria>();
	}

	public static RutasVueloHandler getInstance() {
		if (instancia == null) {
			instancia = new RutasVueloHandler();
		}
		return instancia;
	}

	public void agregarRutaVuelo(RutaVuelo rutaVuelo) {
		if (!existeRutaVuelo(rutaVuelo.getNombre())) {
			rutasVuelo.put(rutaVuelo.getNombre(), rutaVuelo);
		}
	}

	public void addCiudad(Ciudad ciudad) {
	    if (!existeParPC(ciudad.getPais(), ciudad.getNombre())) {
	        System.out.println("Agregando ciudad: " + ciudad.getNombre() + ", " + ciudad.getPais()); // Depuración
	        ciudades.add(ciudad);
	    }
	}

	public void agregarCategoria(Categoria categoria) {
		if (!existeCategoria(categoria.getNombre())) {
			categorias.put(categoria.getNombre(), categoria);
		}
	}

	public boolean existeRutaVuelo(String nombreRV) {
		for (RutaVuelo rv : rutasVuelo.values()) {
			if (rv.getNombre().toLowerCase().equals(nombreRV.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeParPC(String pais, String nombre) {
		for (Ciudad ciu : ciudades) {
			if (ciu.getNombre().toLowerCase().equals(nombre.toLowerCase())
					&& ciu.getPais().toLowerCase().equals(pais.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeCategoria(String categoria) {
		for (Categoria cat : categorias.values()) {
			if (cat.getNombre().toLowerCase().equals(categoria.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public RutaVuelo obtenerRutaVuelo(String nombreRV) {
		for (RutaVuelo rv : rutasVuelo.values()) {
			if (rv.getNombre().toLowerCase().equals(nombreRV.toLowerCase())) {
				return rv;
			}
		}
		return null;
	}

	public ArrayList<RutaVuelo> obtenerRutasVuelo(String nicknameAero) {
		ArrayList<RutaVuelo> rutasVueloAero = new ArrayList<>();
		for (RutaVuelo rv : rutasVuelo.values()) {
			if (rv.getAerolinea().getNombre().toLowerCase().equals(nicknameAero.toLowerCase())) {
				rutasVueloAero.add(rv);
			}
		}
		return rutasVueloAero;
	}

	public DataRutaVuelo obtenerDatosRutaVuelo(String nombreRV) {
		if (rutasVuelo.get(nombreRV) != null) {
			RutaVuelo rv = rutasVuelo.get(nombreRV);
			ArrayList<String> listaNombresCategorias = new ArrayList<>();
			for (Categoria cat : rv.getCategorias()) {
				listaNombresCategorias.add(cat.getNombre());
			}

			ArrayList<DataVuelo> listaDataVuelo = new ArrayList<>();
			for (Vuelo vuelo : rv.getVuelos()) {

				ArrayList<DataReserva> listaDataReserva = new ArrayList<>();
				for (Reserva res : vuelo.getReservas()) {
					DataReserva dtReserva = new DataReserva(res.getCliente().getNickname(), vuelo.getNombre(),
							res.getFechaReserva(), res.getAsientoSeleccionado(), res.getCantidadPasajes(),
							res.getCantidadExtraEquipaje(), res.getCostoTotal());
					listaDataReserva.add(dtReserva);
				}

				DataVuelo dtvuelo = new DataVuelo(vuelo.getNombre(), vuelo.getFecha(), vuelo.getDuracion(),
						vuelo.getMaxTurista(), vuelo.getMaxEjecutivo(), vuelo.getFechaAlta(), listaDataReserva);
				listaDataVuelo.add(dtvuelo);
				listaDataReserva.clear();
			}

			DataRutaVuelo dtRutaVuelo = new DataRutaVuelo(rv.getNombre(), rv.getDescripcion(), rv.getHora(),
					rv.getFechaAlta(), rv.getCiudadOrigen(), rv.getCiudadDestino(), rv.getCostoEjecutivo(),
					rv.getCostoTurista(), rv.getCostoExtra(), listaNombresCategorias, listaDataVuelo,
					rv.getAerolinea().getNickname(), rv.getDescCorta(), rv.getImagen(), rv.getEstado(), rv.getVideo());
			return dtRutaVuelo;
		}
		return null;
	}

	public ArrayList<String> obtenerCiudades() {
		ArrayList<String> listaCiudades = new ArrayList<>();
			/*for (RutaVuelo rv : rutasVuelo.values()) {
				if (!listaCiudades.contains(rv.getCiudadOrigen().toLowerCase()))
					listaCiudades.add(rv.getCiudadOrigen().toLowerCase());
				if (!listaCiudades.contains(rv.getCiudadDestino().toLowerCase()))
					listaCiudades.add(rv.getCiudadDestino().toLowerCase());
			}*/
		 for (Ciudad ciudad : ciudades) {
			if (!listaCiudades.contains(ciudad.getNombre().toLowerCase())) {
				listaCiudades.add(ciudad.getNombre().toLowerCase());
			}
		}
		return listaCiudades;
	}

	public Ciudad getCiudad(String ciudad, String pais) {
		if (existeParPC(pais, ciudad)) {
			for (Ciudad ciu : ciudades) {
				if (ciu.getNombre().toLowerCase().equals(ciudad.toLowerCase()) && ciu.getPais().toLowerCase().equals(pais)) {
					return ciu;
				}
			}
		}
		return null;
	}

	public ArrayList<String> obtenerCategorias() {
		ArrayList<String> listaCategorias = new ArrayList<>();
		for (RutaVuelo rv : rutasVuelo.values()) {
			for (Categoria cat : rv.getCategorias()) {
				if (!listaCategorias.contains(cat.getNombre())) {
					listaCategorias.add(cat.getNombre());
				}
			}

		}
		return listaCategorias;
	}

	public ArrayList<Categoria> getCategorias() {
		ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
		for (Categoria cat : categorias.values()) {
			listaCategorias.add(cat);
		}
		return listaCategorias;
	}

	public DataCategoria getCategoria(String nombre) {
		if (categorias.get(nombre) != null) {
			Categoria cat = categorias.get(nombre);
			DataCategoria dtCat = new DataCategoria(cat.getNombre(), cat.getDescripcion());
			return dtCat;
		}
		return null;
	}
	
	public Categoria obtenerCategoria(String nombre) {
		if (categorias.get(nombre) != null) {
			return categorias.get(nombre);
		}
		return null;
	}

	public void agregarLink(Vuelo v, String ruta) {
		if (rutasVuelo.get(ruta.toLowerCase()) != null) {
			RutaVuelo rv = rutasVuelo.get(ruta.toLowerCase());
			if (rv != null) {
				rv.addVuelo(v);
			}
		}
	}

	public RutaVuelo[] listarRutasVuelo() {
		RutaVuelo[] rutas = new RutaVuelo[rutasVuelo.size()];
		int i = 0;
		for (RutaVuelo rv : rutasVuelo.values()) {
			rutas[i] = rv;
			i++;
		}
		return rutas;
	}
}
