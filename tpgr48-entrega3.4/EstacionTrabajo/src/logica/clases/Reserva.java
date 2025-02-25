package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import logica.Datatypes.tipoAsiento;

public class Reserva {
	private Cliente cliente;
	private Vuelo vuelo;
	private LocalDate fechaReserva;
	private tipoAsiento asientoSeleccionado;
	private int cantidadPasajes;
	private int cantidadExtraEquipaje;
	private float costoTotal;
	private ArrayList<Pasaje> pasajes;
	private boolean checkIn;

	public Reserva() {
	}

	public Reserva(Cliente cliente, Vuelo vuelo, LocalDate fechaReserva, tipoAsiento asientoSeleccionado,
			int cantidadPasajes, int cantidadExtraEquipaje, float costoTotal) {
		this.cliente = cliente;
		this.vuelo = vuelo;
		this.fechaReserva = fechaReserva;
		this.asientoSeleccionado = asientoSeleccionado;
		this.cantidadPasajes = cantidadPasajes;
		this.cantidadExtraEquipaje = cantidadExtraEquipaje;
		this.costoTotal = costoTotal;
		this.pasajes = new ArrayList<Pasaje>();
		this.checkIn = false;

	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public Vuelo getVuelo() {
		return this.vuelo;
	}

	public LocalDate getFechaReserva() {
		return this.fechaReserva;
	}

	public tipoAsiento getAsientoSeleccionado() {
		return this.asientoSeleccionado;
	}

	public int getCantidadPasajes() {
		return this.cantidadPasajes;
	}

	public int getCantidadExtraEquipaje() {
		return this.cantidadExtraEquipaje;
	}

	public float getCostoTotal() {
		return this.costoTotal;
	}

	public ArrayList<Pasaje> getPasajes() {
		return this.pasajes;
	}

	public boolean getCheckIn() {
		return this.checkIn;
	}	

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public void setAsientoSeleccionado(tipoAsiento asientoSeleccionado) {
		this.asientoSeleccionado = asientoSeleccionado;
	}

	public void setCantidadPasajes(int cantidadPasajes) {
		this.cantidadPasajes = cantidadPasajes;
	}

	public void setCantidadExtraEquipaje(int cantidadExtraEquipaje) {
		this.cantidadExtraEquipaje = cantidadExtraEquipaje;
	}

	public void setCostoTotal(float costoTotal) {
		this.costoTotal = costoTotal;
	}

	public void setPasajes(ArrayList<Pasaje> pasajes) {
		this.pasajes = pasajes;
	}

	public void agregrPasaje(Pasaje pasaje) {
		this.pasajes.add(pasaje);
	}

	public void setCheckIn() {
		this.checkIn = true;
	}
}
