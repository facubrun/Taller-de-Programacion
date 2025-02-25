package logica.clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import logica.Datatypes.estadoRutaVuelo;

public class RutaVuelo {

	private String nombre;
	private String descripcion;
	private LocalTime hora;
	private LocalDate fechaAlta;
	private String ciudadOrigen;
	private String ciudadDestino;
	private float costoEjecutivo;
	private float costoTurista;
	private float costoExtra;
	private ArrayList<Categoria> categorias;
	private ArrayList<Vuelo> vuelos;
	private Aerolinea aerolinea;
	private String imagen;
	private String descripcionCorta;
	private estadoRutaVuelo estado;
	private Integer visitas;
	
	private String video;

	public RutaVuelo(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta, String ciudadOrigen,
			String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<Categoria> categorias, Aerolinea aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video) {
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
		this.aerolinea = aerolinea;
		this.imagen = imagen;
		this.descripcionCorta = descripcionCorta;
		this.vuelos = new ArrayList<Vuelo>();
		this.estado = estado;
		this.video = video;
		this.visitas = 0;
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

	public LocalDate getFechaAlta() {
		return this.fechaAlta;
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

	public ArrayList<Categoria> getCategorias() {
		return this.categorias;
	}

	public ArrayList<Vuelo> getVuelos() {
		return this.vuelos;
	}

	public Aerolinea getAerolinea() {
		return this.aerolinea;
	}
	
	public String getImagen() {
		return this.imagen;
	}
	
	public String getDescCorta() {
		return this.descripcionCorta;
	}
	
	public estadoRutaVuelo getEstado() {
		return this.estado;
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

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setVuelos(ArrayList<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public void setDescCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	public void setEstado(estadoRutaVuelo estado) {
		this.estado = estado;
	}

	public void addVuelo(Vuelo v) {
		vuelos.add(v);
	}
	
	
	public String getVideo() {
		return this.video;
	}
	
	public void setVideo(String video) {
		this.video = video;
	}

	public void setVisitas(Integer visitas) {
		this.visitas = visitas;
	}

	public void addVisita() {
		this.visitas++;
	}
}
