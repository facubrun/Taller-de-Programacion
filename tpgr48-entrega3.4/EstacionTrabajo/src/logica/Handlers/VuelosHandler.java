package logica.Handlers;


import java.util.Map;

import exceptions.RVNoExisteExce;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

import logica.Fabrica;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.clases.Vuelo;

public final class VuelosHandler {
	private static final tipoAsiento Turista = null;
	private Map<String, Vuelo> vuelos;
	private static VuelosHandler instancia = null;

	private VuelosHandler() {
		vuelos = new HashMap<String, Vuelo>();
	}

	public static VuelosHandler getinstance() {
		if (instancia == null) {
			instancia = new VuelosHandler();
		}
		return instancia;
	}

	public Vuelo obtenerVuelo(String nombre) {
		if (vuelos.get(nombre.toLowerCase()) != null) {
			return vuelos.get(nombre.toLowerCase());
		}
		return null;

	}

	public void agregarVuelo(Vuelo vuelo, String ruta) {
		if (!existeVueloNombre(vuelo.getNombre().toLowerCase())) {
			vuelos.put(vuelo.getNombre().toLowerCase(), vuelo);
			RutasVueloHandler user = RutasVueloHandler.getInstance();
			// user.agregarLink(vuelo, ruta); se esta agregando dos veces el vuelo a la misma ruta
		}
	}

	public boolean existeVueloNombre(String nombre) {
		for (Vuelo v : vuelos.values()) {
			if (v.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("null")
	public ArrayList<DataVuelo> obtenerVuelosDeRutaVuelo(String nomRV) throws RVNoExisteExce {
		DataRutaVuelo rutaVuelo = Fabrica.getInstance().getIControladorRutaVuelo().verInfoRutaVuelo(nomRV);
		ArrayList<DataVuelo> vuelosRV = rutaVuelo.getVuelos();
		ArrayList<DataVuelo> res = null;
		for (DataVuelo vue : vuelosRV) {
			DataVuelo v = new DataVuelo(vue.getNombre(), vue.getFecha(), vue.getDuracion(), vue.getMaxTurista(),
					vue.getMaxEjecutivo(), vue.getFechaAlta(), vue.getReservas());
			res.add(v);
		}
		return res;
	}

	public boolean tieneAsientosDisponibles(Integer max_e, Integer max_t, Integer cantPasajes, tipoAsiento tipoAsiento) {
		if (tipoAsiento == tipoAsiento.Turista) {
			if (cantPasajes <= max_t) {
				max_t = max_t - cantPasajes;
				return true;
			} else {
				return false;
			}
		} else // tipo Ejecutivo
		if (cantPasajes <= max_e) {
			max_e = max_e - cantPasajes;
			return true;
		} else {
			return false;
		}
	}

	public void reservarAsientos(Integer max_e, Integer max_t, tipoAsiento tipoAsiento, Integer cantPasajes) {
		if (tipoAsiento == tipoAsiento.Turista) {
			if (cantPasajes <= max_t) {
				max_t = max_t - cantPasajes;
			}
		} else // tipo Ejecutivo
		if (cantPasajes <= max_e) {
			max_e = max_e - cantPasajes;
		}
	}

	public ArrayList<Integer> asignarAsientos(tipoAsiento tipoAsiento, Integer cantPasajes, String vuelo) {
		Vuelo vueloInstancia = VuelosHandler.getinstance().obtenerVuelo(vuelo);
		boolean[] asignacionTurista = vueloInstancia.getAsignacionTurista();
		boolean[] asignacionEjecutivo = vueloInstancia.getAsignacionEjecutivo();
		ArrayList<Integer> asientos = new ArrayList<>(cantPasajes);
		
		if (tipoAsiento == tipoAsiento.Turista) {
			for (Integer i = 0; i < asignacionTurista.length; i++) {
				if (!asignacionTurista[i]) {
					vueloInstancia.setAsignacionTurista(i, true);
					asientos.add(i + asignacionEjecutivo.length + 1);
					cantPasajes--;
				}
				if (cantPasajes < 0) {
					break;
				}
			}
		} 
		// tipo Ejecutivo
		else {
			for (Integer i = 0; i < asignacionEjecutivo.length; i++) {
				if (!asignacionEjecutivo[i]) {
					vueloInstancia.setAsignacionEjecutivo(i, true);
					asientos.add(i + 1);
					cantPasajes--;
				}
				if (cantPasajes < 0) {
					break;
				}
			}
		}

		return asientos;
	}
}