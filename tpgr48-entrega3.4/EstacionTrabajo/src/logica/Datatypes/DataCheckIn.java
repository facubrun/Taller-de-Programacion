package logica.Datatypes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import logica.clases.Cliente;
import logica.clases.Reserva;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

public class DataCheckIn {

	private DataReserva reserva;
	private ArrayList<Integer> asientosAsignados;
	private LocalTime horaEmbarque;
	private LocalDate fechaActual;
	private String cliente;
	
	public DataCheckIn(DataReserva reserva, ArrayList<Integer> asientosAsignados, LocalTime horaEmbarque, LocalDate fechaActual, String cliente) {
		this.reserva = reserva;
		this.asientosAsignados = new ArrayList<Integer>();
		this.horaEmbarque = horaEmbarque;
		this.fechaActual = fechaActual;
		this.cliente = cliente;
	}

	public DataReserva getReserva() {
		return this.reserva;
	}

	public ArrayList<Integer> getAsientosAsignados() {
		return this.asientosAsignados;
	}

	public LocalTime getHoraEmbarque() {
		return this.horaEmbarque;
	}

	@XmlElement
	public String getHoraEmbarqueFormatted() {
		return horaEmbarque != null ? horaEmbarque.format(DateTimeFormatter.ofPattern("HH:mm")) : "Fecha no disponible";
	}

	public LocalDate getFechaActual() {
		return this.fechaActual;
	}

	@XmlElement
	public String getFechaActualFormatted() {
		return fechaActual != null ? fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Fecha no disponible";
	}
	
	public String getCliente() {
		return this.cliente;
	}

	public void setReserva(DataReserva reserva) {
		this.reserva = reserva;
	}

	public void setAsientosAsignados(ArrayList<Integer> asientosAsignados) {
		this.asientosAsignados = asientosAsignados;
	}

	public void setHoraEmbarque(LocalTime horaEmbarque) {
		this.horaEmbarque = horaEmbarque;
	}

	public void setFechaActual(LocalDate fechaActual) {
		this.fechaActual = fechaActual;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
