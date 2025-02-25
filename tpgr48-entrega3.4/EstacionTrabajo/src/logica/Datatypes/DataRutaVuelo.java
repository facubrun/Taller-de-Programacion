
package logica.Datatypes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;

public class DataRutaVuelo {

	private String nombre;
	private String descripcion;
	private LocalTime hora;
	private LocalDate fechaAlta;
	private String ciudadOrigen;
	private String ciudadDestino;
	private float costoEjecutivo;
	private float costoTurista;
	private float costoExtra;
	private ArrayList<String> categorias;
	private ArrayList<DataVuelo> vuelos;
	private String aerolinea;
	private String imagen;
	private String descripcionCorta;
	private estadoRutaVuelo estado;
	
	private String video;
	private Integer visitas;

	public DataRutaVuelo(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta, String ciudadOrigen,
			String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<String> categorias, ArrayList<DataVuelo> vuelos, String aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hora = hora;
		this.fechaAlta = fechaAlta;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.costoEjecutivo = costoEjecutivo;
		this.costoTurista = costoTurista;
		this.costoExtra = costoExtra;
		this.categorias = categorias;
		this.vuelos = vuelos;
		this.aerolinea = aerolinea;
		this.imagen = imagen;
		this.descripcionCorta = descripcionCorta;
		this.estado = estado;
		
		this.video = video;
	}

	public DataRutaVuelo(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta, String ciudadOrigen,
			String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<String> categorias, ArrayList<DataVuelo> vuelos, String aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video, Integer visitas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.hora = hora;
		this.fechaAlta = fechaAlta;
		this.ciudadOrigen = ciudadOrigen;
		this.ciudadDestino = ciudadDestino;
		this.costoEjecutivo = costoEjecutivo;
		this.costoTurista = costoTurista;
		this.costoExtra = costoExtra;
		this.categorias = categorias;
		this.vuelos = vuelos;
		this.aerolinea = aerolinea;
		this.imagen = imagen;
		this.descripcionCorta = descripcionCorta;
		this.estado = estado;
		
		this.video = video;
		this.visitas = visitas;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public LocalTime getHora() {
		return this.hora;
	}

	@XmlElement
	public String getHoraFormatted() {
	    return hora != null ? hora.format(DateTimeFormatter.ofPattern("HH:mm")) : "Fecha no disponible";
	}

	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}

	@XmlElement
	public String getFechaAltaFormatted() {
	    return fechaAlta != null ? fechaAlta.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Fecha no disponible";
	}

	public String getCiudadOrigen() {
		return this.ciudadOrigen;
	}

	public String getCiudadDestino() {
		return this.ciudadDestino;
	}

	public float getCostoEjecutivo() {
		return this.costoEjecutivo;
	}

	public float getCostoTurista() {
		return this.costoTurista;
	}

	public float getCostoExtra() {
		return this.costoExtra;
	}
	
	public String getDescCorta() {
		return this.descripcionCorta;
	}
	
	public String getImagen() {
		return this.imagen;
	}
	
	public estadoRutaVuelo getEstado() {
		return this.estado;
	}

	public ArrayList<String> getCategorias() {
		return this.categorias;
	}

	public ArrayList<DataVuelo> getVuelos() {
		return this.vuelos;
	}

	public String getAerolinea() {
		return this.aerolinea;
	}

	public Integer getVisitas() {
		return this.visitas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public void setCostoEjecutivo(float costoEjecutivo) {
		this.costoEjecutivo = costoEjecutivo;
	}

	public void setCostoTurista(float costoTurista) {
		this.costoTurista = costoTurista;
	}

	public void setCostoExtra(float costoExtra) {
		this.costoExtra = costoExtra;
	}
	
	public void setCategorias(ArrayList<String> categorias) {
		this.categorias = categorias;
	}

	public void setVuelos(ArrayList<DataVuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public void setDescCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public void setEstado(estadoRutaVuelo estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return this.nombre + " - " + this.descripcion + ", " + this.hora + ", " + this.fechaAlta + ", "
				+ this.costoTurista + ", " + this.costoEjecutivo + ", " + this.costoExtra + ", " + this.categorias;
	}
	
	public String getVideo() {
		return this.video;
	}
	
	public void setVideo(String video) {
		this.video = video;
	}

}
