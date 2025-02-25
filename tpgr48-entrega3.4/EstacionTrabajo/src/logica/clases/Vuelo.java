package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Vuelo {
	private String nombre;
	private LocalDate fecha;
	private LocalTime duracion;
	private Integer maxTurista;
	private Integer maxEjecutivo;
	private LocalDate fechaAlta;
	private RutaVuelo rutaVuelo;
	private ArrayList<Reserva> reservas;
	private boolean[] asignacionTurista;
	private boolean[] asignacionEjecutivo;

	public Vuelo() {
	}

	public Vuelo(String nombre, LocalDate fecha, LocalTime duracion, Integer maxTurista, Integer maxEjecutivo,
			LocalDate fechaAlta, RutaVuelo rutaVuelo) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.duracion = duracion;
		this.maxTurista = maxTurista;
		this.maxEjecutivo = maxEjecutivo;
		this.asignacionTurista = new boolean[maxTurista];
		this.asignacionEjecutivo = new boolean[maxEjecutivo];
		this.fechaAlta = fechaAlta;
		this.rutaVuelo = rutaVuelo;
		this.reservas = new ArrayList<Reserva>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public LocalTime getDuracion() {
		return this.duracion;
	}

	public Integer getMaxTurista() {
		return this.maxTurista;
	}

	public Integer getMaxEjecutivo() {
		return this.maxEjecutivo;
	}

	public boolean[] getAsignacionTurista() {
		return this.asignacionTurista;
	}

	public boolean[] getAsignacionEjecutivo() {
		return this.asignacionEjecutivo;
	}

	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}

	public RutaVuelo getRutaVuelo() {
		return this.rutaVuelo;
	}

	public ArrayList<Reserva> getReservas() {
		return this.reservas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public void setMaxTurista(Integer maxTurista) {
		this.maxTurista = maxTurista;
	}

	public void setMaxEjecutivo(Integer maxEjecutivo) {
		this.maxEjecutivo = maxEjecutivo;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setRutaVuelo(RutaVuelo rv) {
		this.rutaVuelo = rv;
	}

	public void setAsignacionTurista(int posicion, boolean valor) {
		this.asignacionTurista[posicion] = valor;
	}

	public void setAsignacionEjecutivo(int posicion, boolean valor) {
		this.asignacionEjecutivo[posicion] = valor;
	}

	public void setReserva(Reserva r) {
		this.reservas.add(r);
	}

}
