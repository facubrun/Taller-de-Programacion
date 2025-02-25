package logica.Handlers;

import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import logica.clases.Cliente;
import logica.clases.Pasaje;
import logica.clases.Reserva;
import logica.Datatypes.DataPasajes;
import logica.clases.Aerolinea;
import logica.clases.CheckIn;
import logica.clases.RutaVuelo;

import java.time.LocalTime;
import java.time.LocalDate;

import logica.clases.Vuelo;

public final class UsuariosHandler {
	private Map<String, Cliente> clienteNickname;
	private Map<String, Aerolinea> aerolineaNickname;
	private static UsuariosHandler instancia = null;

	private UsuariosHandler() {
		clienteNickname = new HashMap<String, Cliente>();
		aerolineaNickname = new HashMap<String, Aerolinea>();
	}

	public static UsuariosHandler getinstance() {
		if (instancia == null) {
			instancia = new UsuariosHandler();
		}
		return instancia;
	}

	public void addCliente(Cliente cliente) {
		if (!existeClienteNick(cliente.getNombre())) {
			clienteNickname.put(cliente.getNickname(), cliente);
		}
	}

	public void addAerolinea(Aerolinea aerolinea) {
		if (!existeAerolineaNick(aerolinea.getNombre())) {
			aerolineaNickname.put(aerolinea.getNickname(), aerolinea);
		}
	}

	public Cliente obtenerCliente(String nickname) {
		if (clienteNickname.get(nickname) != null) {
			return clienteNickname.get(nickname);
		}
		return null;

	}

	public Aerolinea obtenerAerolinea(String nickname) {
		if (aerolineaNickname.get(nickname) != null) {
			return aerolineaNickname.get(nickname);
		}
		return null;

	}

	public Map<String, Cliente> getCliente() {
		return clienteNickname;
	}

	public Map<String, Aerolinea> getAerolinea() {
		return aerolineaNickname;
	}

	public boolean existeClienteNick(String nickname) {
		for (Cliente cli : clienteNickname.values()) {
			if (cli.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeAerolineaNick(String nickname) {
		for (Aerolinea aero : aerolineaNickname.values()) {
			if (aero.getNickname().toLowerCase().equals(nickname.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeClienteEmail(String email) {
		for (Cliente cli : clienteNickname.values()) {
			if (cli.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeAerolineaEmail(String email) {
		for (Aerolinea aero : aerolineaNickname.values()) {
			if (aero.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void removerTurista(String nickname) {
		this.clienteNickname.remove(nickname);
	}

	public void removerProveedor(String nickname) {
		this.aerolineaNickname.remove(nickname);
	}

	public void limpiarClientes() {
		this.clienteNickname.clear();
	}

	public void limpiarAerolineas() {
		this.aerolineaNickname.clear();
	}

	public ArrayList<DataPasajes> obtenerDatosPasajes(String nickC) {
		return clienteNickname.get(nickC).getDataPasajes();
	}

	public void agregarRutaVueloAerolinea(String nickA, RutaVuelo vuelo) {
		aerolineaNickname.get(nickA).addRutaVuelo(vuelo);
	}
	
	public void CheckInCliente(Cliente cliente,Reserva reserva) {
		ArrayList<Pasaje> pasajes = reserva.getPasajes();
//		ArrayList<Integer> asientosAsig = new ArrayList<>();
		
		ArrayList<Integer> asientos = VuelosHandler.getinstance().asignarAsientos(reserva.getAsientoSeleccionado(), reserva.getCantidadPasajes(), reserva.getVuelo().getNombre());
		
		Integer i = 0;
		for(Pasaje pasaje: pasajes) {
			pasaje.setAsientoAsig(asientos.get(i));
			i++;
		}

		reserva.setPasajes(pasajes);

		CheckIn check = new CheckIn(reserva, asientos, reserva.getVuelo().getRutaVuelo().getHora().minusMinutes(30), LocalDate.now(), cliente);
		cliente.agregarCheckIn(check);
	}
	
	
	public boolean existeNickname(String nickname) {
		if (existeClienteNick(nickname) || existeAerolineaNick(nickname)) {
			return true;
		}
		return false;
	}

}
