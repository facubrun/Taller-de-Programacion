package servidor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import exceptions.PaqueteExisteExce;
import exceptions.RVNoExisteExce;
import exceptions.VueloNoExisteExce;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.xml.ws.Endpoint;
import logica.Datatypes.DataPaquete;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.tipoAsiento;
import logica.controllers.PaqueteController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorPaquete {

    private Endpoint endpoint = null;
    private PaqueteController paqueteController = new PaqueteController();

    @WebMethod(exclude = true)
    public void publicar(String host, String port) {
        endpoint = Endpoint.publish("http://" + host + ":" + port + "/PublicadorPaquete", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

//    // Método para crear un paquete
//    @WebMethod
//    public void crearPaquete(String nombre, String descripcion, int validezDias,  float descuento, float costo,
//                             String alta, DataRutaVuelo[] rutasPaquete)
//            throws PaqueteExisteExce {
//        LocalDate fechaAlta = LocalDate.parse(alta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        ArrayList<DataRutaVuelo> rutasVuelo = new	 ArrayList<DataRutaVuelo>();
//        for (DataRutaVuelo ruta : rutasVuelo) {
//        	rutasVuelo.add(ruta);
//        }
//        paqueteController.crearPaquete(nombre, descripcion, validezDias, descuento, costo, 
//                                        fechaAlta,rutasVuelo);
//    }

    // Método para ver información de un cliente
    @WebMethod
    public DataPaquete[] listarPaquetesDisponibles() {
        return paqueteController.listarPaquetesDisponibles().toArray(new DataPaquete[paqueteController.listarPaquetesDisponibles().size()]);
    }

    // Método para ver información de una aerolínea
    @WebMethod
    public void agregarRVaPaquete(String nomP, String rutaP, tipoAsiento asientoSelec, Integer cantRutas) throws RVNoExisteExce{
        paqueteController.agregarRVaPaquete(nomP, rutaP, asientoSelec, cantRutas);
    }

    // Método para modificar un cliente
    @WebMethod
    public DataPaquete verInfoPaquete(String nombrePaq) throws VueloNoExisteExce {
        return paqueteController.verInfoPaquete(nombrePaq);
    }
    
    public void comprarPaquete(String nickC, String nombrePaq, LocalDate fechaCompra, LocalDate fechaVencimiento) throws PaqueteExisteExce{
        paqueteController.comprarPaquete(nickC, nombrePaq,fechaCompra,fechaVencimiento);
    }
}
