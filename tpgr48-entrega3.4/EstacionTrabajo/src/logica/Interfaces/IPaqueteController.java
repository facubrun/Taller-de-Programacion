package logica.Interfaces;

import exceptions.PaqueteExisteExce;

import exceptions.RVNoExisteExce;
import exceptions.VueloNoExisteExce;
import logica.Datatypes.DataPaquete;
import logica.Datatypes.DataRutaVueloPaquete;
import logica.Datatypes.tipoAsiento;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IPaqueteController {
	public abstract void crearPaquete(String nombre, String descripcion, int validezDias, float descuento,
			float costo, LocalDate fechaAlta, ArrayList<DataRutaVueloPaquete> rutasPaquete) throws PaqueteExisteExce;

	public abstract ArrayList<DataPaquete> listarPaquetesDisponibles();
	
	public void agregarRVaPaquete(String nomP,String rutaP, tipoAsiento asientoSelec, Integer cantRutas) throws RVNoExisteExce;
	
	public DataPaquete verInfoPaquete(String nomP) throws VueloNoExisteExce;
	
	public void comprarPaquete(String nickC, String nombrePaq, LocalDate fechaCompra, LocalDate fechaVencimiento) throws PaqueteExisteExce;
}