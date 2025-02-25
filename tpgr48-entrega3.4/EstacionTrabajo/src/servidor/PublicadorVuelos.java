package servidor;

import exceptions.ClientePasajeroRepetidoExce;
import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.VueloNoExisteExce;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.DataReserva;
import logica.Datatypes.tipoAsiento;
import logica.clases.Categoria;
import logica.controllers.VuelosController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorVuelos {

    private Endpoint endpoint = null;
    private VuelosController vuelosController = new VuelosController();

    @WebMethod(exclude = true)
    public void publicar(String host, String port) {
        endpoint = Endpoint.publish("http://" + host + ":" + port + "/PublicadorVuelos", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void ingresarDatosVuelo(String ruta, String nombre, String fecha, String duracion, Integer maxTurista,
                                   Integer maxEjecutivo, String fechaAlta) throws NombreVRepetidoExce, RVNoExisteExce {
        LocalDate parsedFecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime parsedDuracion = LocalTime.parse(duracion, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDate parsedFechaAlta = LocalDate.parse(fechaAlta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        vuelosController.ingresarDatosVuelo(ruta, nombre, parsedFecha, parsedDuracion, maxTurista, maxEjecutivo, parsedFechaAlta);
    }

    @WebMethod
    public DataVuelo verInfoVuelo(String nombre) throws VueloNoExisteExce {
        return vuelosController.verInfoVuelo(nombre);
    }

    @WebMethod
    public boolean confirmarAltaVuelo(String nomV) {
        return vuelosController.confirmarAltaVuelo(nomV);
    }

    @WebMethod
    public void reservarVuelo(String nomV, String nickC, tipoAsiento tipo, Integer cantPasajes, Integer cantExtra, 
                              String fechaReserva, String[] pasajeros) 
            throws ClientePasajeroRepetidoExce {
    	
    	ArrayList<String> pasajeros_format = new ArrayList<String>();
        for (String pas : pasajeros) {
        	pasajeros_format.add(pas);
        }
        LocalDate parsedFechaReserva = LocalDate.parse(fechaReserva, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        vuelosController.reservarVuelo(nomV, nickC, tipo, cantPasajes, cantExtra, parsedFechaReserva, pasajeros_format);
    }

    @WebMethod
    public DataRutaVuelo[] listarRutasVuelosPorAerolinea(String nickA) throws UsuarioNoExisteExce {
        ArrayList<DataRutaVuelo> rutasVuelo = vuelosController.listarRutasVuelosPorAerolinea(nickA);
        return rutasVuelo.toArray(new DataRutaVuelo[rutasVuelo.size()]);
    }

    @WebMethod
    public DataVuelo[] listarVuelosPorRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
        ArrayList<DataVuelo> vuelo = vuelosController.listarVuelosPorRutaVuelo(nombreRutaVuelo);
        return vuelo.toArray(new DataVuelo[vuelo.size()]);
    }

    @WebMethod
    public DataReserva[] listarReservasDeVuelo(String nombreVuelo) throws VueloNoExisteExce {
        ArrayList<DataReserva> reservas = vuelosController.listarReservasDeVuelo(nombreVuelo);
        return reservas.toArray(new DataReserva[0]);
    }
}
