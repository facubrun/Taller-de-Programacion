package logica.Interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.VueloNoExisteExce;
import exceptions.ClientePasajeroRepetidoExce;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;

public interface IVuelosController {

	void ingresarDatosVuelo(String ruta, String nombre, LocalDate fecha, LocalTime duracion, Integer maxTurista,
			Integer maxEjecutivo, LocalDate fechaAlta) throws NombreVRepetidoExce, RVNoExisteExce;

	public abstract DataVuelo verInfoVuelo(String nombre) throws VueloNoExisteExce;

	public boolean confirmarAltaVuelo(String nomV);

	public void reservarVuelo(String nomV, String nickC, tipoAsiento tipoAsientoSeleccionado,
			Integer cantPasajes, Integer cantExtra, LocalDate fecha_r, ArrayList<String> pasajeros) throws ClientePasajeroRepetidoExce;

	public ArrayList<DataRutaVuelo> listarRutasVuelosPorAerolinea(String nickA) throws UsuarioNoExisteExce;

	public ArrayList<DataVuelo> listarVuelosPorRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce;

	public ArrayList<DataReserva> listarReservasDeVuelo(String nombreVuelo) throws VueloNoExisteExce;
}
