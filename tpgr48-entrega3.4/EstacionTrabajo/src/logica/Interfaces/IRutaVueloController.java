package logica.Interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import exceptions.AerolineaNoExisteExce;
import exceptions.CategoriaNoExisteExce;
import exceptions.CategoriaRepetidaExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import logica.Datatypes.DataCategoria;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.estadoRutaVuelo;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import logica.clases.Ciudad;
import logica.clases.RutaVuelo;

public interface IRutaVueloController {
	public abstract void registrarRutaDeVuelo(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta,
			String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<Categoria> categorias, Aerolinea aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video) throws NombreRVRepetidoExce;
	
	public abstract void registrarRutaDeVueloWeb(String nombre, String descripcion, LocalTime hora, LocalDate fechaAlta,
			String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<String> categorias, String aerolinea, String imagen, String descripcionCorta, estadoRutaVuelo estado, String video) throws NombreRVRepetidoExce;

	public RutaVuelo obtenerRutaVuelo(String nomRV);

	public abstract void seleccionarAerolinea(String nickA) throws AerolineaNoExisteExce;

	public abstract void seleccionarRutaVuelo(String nombreRV) throws RVNoExisteExce;

	public abstract void registrarCiudad(String nombre, String pais, String aeropuerto, String descripcion, String sitioWeb,
			LocalDate fechaAlta) throws NicknameRepetidoExce;

	public abstract void ingresarCategoria(String nombreCat, String descripcion) throws CategoriaRepetidaExce;

	public abstract DataCategoria getCategoria(String nombreCat) throws CategoriaNoExisteExce;

	public abstract DataRutaVuelo verInfoRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce;

	public abstract ArrayList<DataRutaVuelo> listarRutasVueloPorAerolinea(String nombreAerolinea)
			throws UsuarioNoExisteExce;
	
	public abstract ArrayList<DataRutaVuelo> listarRutasVueloIngresadasPorAerolinea(String nombreAerolinea) throws UsuarioNoExisteExce;

    public abstract Ciudad obtenerCiudad(String nombre, String pais);
    
    public abstract ArrayList<Categoria> obtenerCategorias();
    
    public abstract Categoria obtenerCategoria(String nombre);

	public abstract DataRutaVuelo[] listarRutasVuelo() throws RVNoExisteExce;
	
	public abstract void modificarDatosRutaVuelo(String nombreRV, String descripcion, LocalTime hora, LocalDate fechaAlta,
			String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
			ArrayList<Categoria> categorias, String imagen) throws RVNoExisteExce;

	public void finalizarRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce;

	public void agregarVisitaRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce;
}
