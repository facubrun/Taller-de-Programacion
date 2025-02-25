package logica.Datatypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataVuelo", propOrder = {
	    "nombre", "fecha", "duracion", "maxTurista", "maxEjecutivo", "fechaAlta", 
	    "reservas", "duracionFormatted", "fechaAltaFormatted", "fechaFormatted"
	})
@XmlRootElement(name = "dataVuelo")
public class DataVuelo {

	@XmlElement
	private String nombre;
	@XmlElement
	private LocalDate fecha;
	@XmlElement
	private LocalTime duracion;
	@XmlElement
	private Integer maxTurista;
	@XmlElement
	private Integer maxEjecutivo;
	@XmlElement
	private LocalDate fechaAlta;
	@XmlElement
	private ArrayList<DataReserva> reservas;

	public DataVuelo() {
	}

	public DataVuelo(String nombre, LocalDate fecha, LocalTime duracion, Integer maxTurista, Integer maxEjecutivo,
			LocalDate fechaAlta, ArrayList<DataReserva> reservas) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.duracion = duracion;
		this.maxTurista = maxTurista;
		this.maxEjecutivo = maxEjecutivo;
		this.fechaAlta = fechaAlta;
		this.reservas = reservas;

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
	
	@XmlElement
	public String getDuracionFormatted() {
	    return duracion != null ? duracion.format(DateTimeFormatter.ofPattern("HH:mm")) : "Fecha no disponible";
	}

	public Integer getMaxTurista() {
		return this.maxTurista;
	}

	public Integer getMaxEjecutivo() {
		return this.maxEjecutivo;
	}

	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}
	
	@XmlElement
	public String getFechaAltaFormatted() {
	    return fechaAlta != null ? fechaAlta.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Fecha no disponible";
	}

	public ArrayList<DataReserva> getReservas() {
		return this.reservas;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	@XmlElement
	public String getFechaFormatted() {
	    return fecha != null ? fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Fecha no disponible";
	}

	protected void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	protected void setMaxTurista(Integer maxTurista) {
		this.maxTurista = maxTurista;
	}

	protected void setMaxEjecutivo(Integer maxEjecutivo) {
		this.maxEjecutivo = maxEjecutivo;
	}

	protected void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	protected void setReservas(ArrayList<DataReserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		
		return this.nombre + " , " + this.fecha + ", " + this.duracion + this.maxTurista + ", " + this.maxEjecutivo
				+ ", " + this.fechaAlta;
	}

}