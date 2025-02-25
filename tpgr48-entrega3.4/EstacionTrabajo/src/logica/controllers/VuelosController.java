package logica.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import exceptions.ClientePasajeroRepetidoExce;
import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.VueloNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataPasajes;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Handlers.RutasVueloHandler;
import logica.Handlers.UsuariosHandler;
import logica.Handlers.VuelosHandler;
import logica.Interfaces.IVuelosController;
import logica.clases.Cliente;
import logica.clases.Pasaje;
import logica.clases.Reserva;
import logica.clases.RutaVuelo;
import logica.clases.Vuelo;

public class VuelosController implements IVuelosController {

	public void ingresarDatosVuelo(String ruta, String nombre, LocalDate fecha, LocalTime duracion, Integer maxTurista,
			Integer maxEjecutivo, LocalDate fechaAlta) throws NombreVRepetidoExce, RVNoExisteExce {

		VuelosHandler vuelo = VuelosHandler.getinstance();
		if (vuelo.existeVueloNombre(nombre)) {
			throw new NombreVRepetidoExce("El nombre " + nombre + " ya está en uso");
		} else {
			RutasVueloHandler rutaVueloHandler = RutasVueloHandler.getInstance();
			RutaVuelo rv = rutaVueloHandler.obtenerRutaVuelo(ruta);
			if (rv == null) {
				throw new RVNoExisteExce("La ruta de vuelo " + ruta + " no existe");
			} else {
				Vuelo v = new Vuelo(nombre, fecha, duracion, maxTurista, maxEjecutivo, fechaAlta, rv);
				rv.addVuelo(v);
				vuelo.agregarVuelo(v, ruta);
			}

		}
	}

	public DataVuelo verInfoVuelo(String nombre) throws VueloNoExisteExce {
		VuelosHandler vuelo = VuelosHandler.getinstance();
		if (vuelo.existeVueloNombre(nombre)) {
			Vuelo v = vuelo.obtenerVuelo(nombre);

			ArrayList<DataReserva> reservas = new ArrayList<DataReserva>();
			for (Reserva r : v.getReservas()) {

				ArrayList<DataPasajes> pasajes = new ArrayList<DataPasajes>();
				for (Pasaje p : r.getPasajes()) {
					DataPasajes dp = new DataPasajes(p.getNombre(), p.getApellido());
					pasajes.add(dp);
				}

				DataReserva dr = new DataReserva(r.getCliente().getNickname(), r.getVuelo().getNombre(),
						r.getFechaReserva(), r.getAsientoSeleccionado(), r.getCantidadPasajes(),
						r.getCantidadExtraEquipaje(), r.getCostoTotal(), pasajes);
				reservas.add(dr);
			}

			return new DataVuelo(v.getNombre(), v.getFecha(), v.getDuracion(), v.getMaxTurista(), v.getMaxEjecutivo(),
					v.getFechaAlta(), reservas);
		} else {
			throw new VueloNoExisteExce("El vuelo " + nombre + " no existe");
		}
	}

	public boolean confirmarAltaVuelo(String nomV) {
		VuelosHandler vuelo = VuelosHandler.getinstance();
		if (vuelo.existeVueloNombre(nomV)) {
			return true;
		}
		return false;
	}

	public void reservarVuelo(String nomV, String nickC, tipoAsiento tipoAsiento, Integer cantPasajes, Integer cantExtra, LocalDate fecha_r, ArrayList<String> pasajeros) throws ClientePasajeroRepetidoExce {
		VuelosHandler vueloHandler = VuelosHandler.getinstance();
		

		if (cantPasajes <= 0) {
			throw new ClientePasajeroRepetidoExce("La cantidad de pasajes debe ser mayor a cero.");
		}

		if (cantExtra < 0) {
			throw new ClientePasajeroRepetidoExce("Las unidades de equipaje deben ser 0 o más");
		}

		Vuelo vuelo = vueloHandler.obtenerVuelo(nomV);
		if (vuelo == null) {
			throw new ClientePasajeroRepetidoExce("El vuelo especificado no existe.");
		}
		UsuariosHandler clienteHandler = UsuariosHandler.getinstance();
		Cliente cliente = clienteHandler.obtenerCliente(nickC);
		if (cliente == null) {
			throw new ClientePasajeroRepetidoExce("El cliente especificado no existe.");
		}

		if (!vueloHandler.tieneAsientosDisponibles(vuelo.getMaxEjecutivo(), vuelo.getMaxTurista(), cantPasajes, tipoAsiento)) {
			throw new ClientePasajeroRepetidoExce("No hay suficientes asientos disponibles en este vuelo para el tipo de asiento seleccionado.");
		}

		if (pasajeros.size() != cantPasajes - 1) {
			throw new ClientePasajeroRepetidoExce("La cantidad de pasajeros no coincide con la cantidad de pasajes.");
		}

		if (pasajeros.contains(nickC)) {
			throw new ClientePasajeroRepetidoExce("El cliente no puede ser pasajero en el vuelo.");
		}

		for (Reserva r : vuelo.getReservas()) {
			if (r.getCliente().getNickname().equals(nickC)) {
				throw new ClientePasajeroRepetidoExce("El cliente ya tiene una reserva en este vuelo.");
			}
		}

		vueloHandler.reservarAsientos(vuelo.getMaxEjecutivo(), vuelo.getMaxTurista(), tipoAsiento, cantPasajes);
		
		if (vueloHandler.tieneAsientosDisponibles(vuelo.getMaxEjecutivo(), vuelo.getMaxTurista(), cantPasajes, tipoAsiento)) {
			float costoTotal;

			if (tipoAsiento == tipoAsiento.Turista) {
				costoTotal = (cantPasajes * (vuelo.getRutaVuelo().getCostoTurista()))
						+ (cantExtra * (vuelo.getRutaVuelo().getCostoExtra()));
			} else {
				costoTotal = (cantPasajes * (vuelo.getRutaVuelo().getCostoEjecutivo()))
						+ (cantExtra * (vuelo.getRutaVuelo().getCostoExtra()));
			}

			Reserva reserva = new Reserva(cliente, vuelo, fecha_r, tipoAsiento, cantPasajes, cantExtra, costoTotal);
			Pasaje pasaje = new Pasaje(reserva, cliente.getNombre(), cliente.getApellido());
			reserva.agregrPasaje(pasaje);

			for (String nombrePasajero : pasajeros) {
				
				String[] nombreApellido = nombrePasajero.split(" ");
				if (nombreApellido.length != 2) {
					throw new ClientePasajeroRepetidoExce("El nombre del pasajero debe tener nombre y apellido.");
				}

				String nombre = nombreApellido[0];
				String apellido = nombreApellido[1];
				Pasaje pasajePasajero = new Pasaje(reserva, nombre, apellido);
				reserva.agregrPasaje(pasajePasajero);
			}

			vuelo.setReserva(reserva); // se asocia la reserva al vuelo correspondiente
			cliente.addReserva(reserva);
			int cantidad;
			if (tipoAsiento == tipoAsiento.Turista) {
				cantidad = vuelo.getMaxTurista();
				vuelo.setMaxTurista(cantidad - cantPasajes);
			} else {
				cantidad = vuelo.getMaxEjecutivo();
				vuelo.setMaxEjecutivo(cantidad - cantPasajes);
			}
		}
	}

	public ArrayList<DataRutaVuelo> listarRutasVuelosPorAerolinea(String nickA) throws UsuarioNoExisteExce {
		ArrayList<DataRutaVuelo> datosRutaVuelo = null;

		DataAerolinea aerolinea = Fabrica.getInstance().getIControladorUsuario().verInfoAerolinea(nickA);
		datosRutaVuelo = aerolinea.getRutasVuelo();

		return datosRutaVuelo;
	}

	public ArrayList<DataVuelo> listarVuelosPorRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
		ArrayList<DataVuelo> datosVuelos = null;

		DataRutaVuelo rutaVuelo = Fabrica.getInstance().getIControladorRutaVuelo().verInfoRutaVuelo(nombreRutaVuelo);
		datosVuelos = rutaVuelo.getVuelos();

		return datosVuelos;
	}

	public ArrayList<DataReserva> listarReservasDeVuelo(String nombreVuelo) throws VueloNoExisteExce {
		ArrayList<DataReserva> reservas = new ArrayList<DataReserva>();
		Vuelo vuelo = VuelosHandler.getinstance().obtenerVuelo(nombreVuelo);
		for (Reserva r : vuelo.getReservas()) {
			DataReserva dr = new DataReserva(r.getCliente().getNickname(), r.getVuelo().getNombre(),
					r.getFechaReserva(), r.getAsientoSeleccionado(), r.getCantidadPasajes(),
					r.getCantidadExtraEquipaje(), r.getCostoTotal());
			reservas.add(dr);
		}
		return reservas;
	}

}
