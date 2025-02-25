package logica.Datatypes;

import java.time.LocalDate;
import java.util.ArrayList;

import logica.clases.Cliente;
import logica.clases.RutaVuelo;

public class DataPaquete {

	private String nombre;
	private String descripcion;
	private int validezDias;
	private float descuento;
	private float costo;
	private LocalDate fechaAlta;
	private ArrayList<DataRutaVueloPaquete> rutasPaquete;
	private ArrayList<Cliente> compraClientes;
	
	public DataPaquete(String nombre, String descripcion, int validezDias, float descuento, float costo, LocalDate fechaAlta, ArrayList<DataRutaVueloPaquete> rutasPaquete) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validezDias = validezDias;
		this.descuento = descuento;
		this.costo = costo;
		this.fechaAlta = fechaAlta;
		this.rutasPaquete = rutasPaquete;
		this.compraClientes = new ArrayList<>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public int getValidezDias() {
		return this.validezDias;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public float getCosto() {
		return this.costo;
	}
	
	public LocalDate getFechaAlta() {
		return this.fechaAlta;
	}
	
	public ArrayList<DataRutaVueloPaquete> getRutasPaquete() {
		return this.rutasPaquete;
	}
	
	public ArrayList<Cliente> getCompraClientes(){
		return this.compraClientes;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setValidezDias(int validezDias) {
		this.validezDias = validezDias;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void addRutaVuelo(DataRutaVueloPaquete ruta, Integer cantRuta) {
		this.rutasPaquete.add(ruta);
		ruta.setCantidad(cantRuta);
		
	}
	
	public void addCompraClientes(Cliente cli) {
		this.compraClientes.add(cli);
	}
}
