package logica.clases;

import java.time.LocalDate;

public class Ciudad {

	private String nombre;
	private String pais;
	private String aeropuerto;
	private String descripcion;
	private String sitioWeb;
	private LocalDate fechaAlta;

	public Ciudad(String nombre, String pais, String aeropuerto, String descripcion, String sitioWeb,
			LocalDate fechaAlta) {
		this.nombre = nombre;
		this.pais = pais;
		this.aeropuerto = aeropuerto;
		this.descripcion = descripcion;
		this.sitioWeb = sitioWeb;
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getPais() {
		return this.pais;
	}

	public String getAeropuerto() {
		return this.aeropuerto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getSitioWeb() {
		return this.sitioWeb;
	}

	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setAeropuerto(String aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
}