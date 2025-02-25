package logica.clases;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CheckIn {
	private Reserva reserva;
	private ArrayList<Integer> asientosAsignados;
	private LocalTime horaEmbarque;
	private LocalDate fechaActual;
	private Cliente cliente;
	public CheckIn() {
	}

	public CheckIn(Reserva reserva, ArrayList<Integer> asientosAsignados,
			LocalTime horaEmbarque, LocalDate fechaActual, Cliente cliente) {
		this.reserva = reserva;
		this.asientosAsignados = asientosAsignados;
		this.horaEmbarque = horaEmbarque;
		this.fechaActual = fechaActual;
		this.cliente = cliente;
	}

	public Reserva getReserva() {
		return this.reserva;
	}

	public ArrayList<Integer> getAsientosAsignados() {
		return this.asientosAsignados;
	}

	public LocalTime getHoraEmbarque() {
		return this.horaEmbarque;
	}

	public LocalDate getFechaActual() {
		return this.fechaActual;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setReserva(Reserva reserva) {
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
