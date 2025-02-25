package servidor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import exceptions.AerolineaNoExisteExce;
import exceptions.CategoriaNoExisteExce;
import exceptions.CategoriaRepetidaExce;
import exceptions.NicknameRepetidoExce;
import exceptions.NombreRVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Datatypes.DataCategoria;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.estadoRutaVuelo;
import logica.clases.Categoria;
import logica.controllers.RutaVueloController;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorRutasVuelo {

    private Endpoint endpoint = null;
    private RutaVueloController rutaVueloController = new RutaVueloController();

    @WebMethod(exclude = true)
    public void publicar(String host, String port) {
        endpoint = Endpoint.publish("http://" + host + ":" + port + "/PublicadorRutasVuelo", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void registrarRutaDeVuelo(String nombre, String descripcion, String hora, String fechaAlta,
                                     String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista,
                                     float costoExtra, String[] categorias, String aerolinea, String imagen,
                                     String descripcionCorta, String estado, String video) throws NombreRVRepetidoExce {
        LocalTime horaParsed = LocalTime.parse(hora);
        LocalDate fechaAltaParsed = LocalDate.parse(fechaAlta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        estadoRutaVuelo estadoEnum = estadoRutaVuelo.valueOf(estado);
        ArrayList<String> categories = new ArrayList<String>();
        for (String cat : categorias) {
        	categories.add(cat);
        }
        
        rutaVueloController.registrarRutaDeVueloWeb(nombre, descripcion, horaParsed, fechaAltaParsed,
                                                    ciudadOrigen, ciudadDestino, costoEjecutivo, costoTurista, costoExtra,
                                                    categories, aerolinea, imagen, descripcionCorta, estadoEnum, video);
    }


    @WebMethod
    public void seleccionarAerolinea(String nicknameAerolinea) throws AerolineaNoExisteExce {
        rutaVueloController.seleccionarAerolinea(nicknameAerolinea);
    }

    @WebMethod
    public void seleccionarRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
        rutaVueloController.seleccionarRutaVuelo(nombreRutaVuelo);
    }

    @WebMethod
    public void registrarCiudad(String nombre, String pais, String aeropuerto, String descripcion, String sitioWeb,
			String fechaAlta) throws NicknameRepetidoExce {
        LocalDate fechaAltaParsed = LocalDate.parse(fechaAlta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        rutaVueloController.registrarCiudad(nombre, pais, aeropuerto, descripcion, sitioWeb, fechaAltaParsed);
    }

    @WebMethod
    public void ingresarCategoria(String nombre, String descripcion) throws CategoriaRepetidaExce {
        rutaVueloController.ingresarCategoria(nombre, descripcion);
    }

    @WebMethod
    public DataCategoria getCategoria(String nombreCategoria) throws CategoriaNoExisteExce {
        return rutaVueloController.getCategoria(nombreCategoria);
    }

    @WebMethod
    public DataRutaVuelo verInfoRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
        return rutaVueloController.verInfoRutaVuelo(nombreRutaVuelo);
    }

    @WebMethod
    public DataRutaVuelo[] listarRutasVueloPorAerolinea(String nombreAerolinea) throws UsuarioNoExisteExce {
        return rutaVueloController.listarRutasVueloPorAerolinea(nombreAerolinea).toArray(new DataRutaVuelo[rutaVueloController.listarRutasVueloPorAerolinea(nombreAerolinea).size()]);

    }

    @WebMethod
    public ArrayList<DataRutaVuelo> listarRutasVueloIngresadasPorAerolinea(String nombreAerolinea) throws UsuarioNoExisteExce {
        return rutaVueloController.listarRutasVueloIngresadasPorAerolinea(nombreAerolinea);
    }

    @WebMethod
    public DataRutaVuelo[] listarRutasVuelo() throws RVNoExisteExce {
        return rutaVueloController.listarRutasVuelo();
    }

    @WebMethod
    public String[] listarCiudadesDisponibles() {
        return rutaVueloController.listarCiudadesDisponibles().toArray(new String[rutaVueloController.listarCiudadesDisponibles().size()]);
    }

    @WebMethod
    public Categoria[] listarCategorias() {
        ArrayList<Categoria> categorias = rutaVueloController.obtenerCategorias();
        return categorias.toArray(new Categoria[categorias.size()]);
    }

    @WebMethod
    public void modificarDatosRutaVuelo(String nombreRV, String descripcion, String hora, String fechaAlta,
                                        String ciudadOrigen, String ciudadDestino, float costoEjecutivo, float costoTurista, float costoExtra,
                                        ArrayList<String> categorias, String imagen) throws RVNoExisteExce {
        LocalTime horaParsed = LocalTime.parse(hora);
        LocalDate fechaAltaParsed = LocalDate.parse(fechaAlta, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        ArrayList<Categoria> categoriasClass = new ArrayList<>();
        for (String nombreCategoria : categorias) {
            categoriasClass.add(rutaVueloController.obtenerCategoria(nombreCategoria));
        }
        
        rutaVueloController.modificarDatosRutaVuelo(nombreRV, descripcion, horaParsed, fechaAltaParsed, ciudadOrigen, ciudadDestino, costoEjecutivo, costoTurista, costoExtra, categoriasClass, imagen);
    }
    
    @WebMethod
    public void finalizarRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
        rutaVueloController.finalizarRutaVuelo(nombreRutaVuelo);
    }

    @WebMethod
    public void agregarVisitaRutaVuelo(String nombreRutaVuelo) throws RVNoExisteExce {
        rutaVueloController.agregarVisitaRutaVuelo(nombreRutaVuelo);
    }
}
