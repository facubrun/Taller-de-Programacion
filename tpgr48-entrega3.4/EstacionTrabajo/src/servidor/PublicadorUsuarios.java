package servidor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exceptions.ClientePasajeroRepetidoExce;
import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import exceptions.UsuarioNoExisteExce;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCheckIn;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataReserva;
import logica.clases.Reserva;
import logica.controllers.UsuarioController;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class PublicadorUsuarios {

    private Endpoint endpoint = null;
    private UsuarioController usuarioController = new UsuarioController();

    @WebMethod(exclude = true)
    public void publicar(String host, String port) {
        endpoint = Endpoint.publish("http://" + host + ":" + port + "/PublicadorUsuario", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    // Método para crear un cliente
    @WebMethod
    public void crearCliente(String nickname, String nombre, String email, String apellido, String nacimiento,
                             String nacionalidad, String tipoDocumento, String documento, String password, String imagen, String[] seguidos, String[] seguidores)
            throws EmailRepetidoExce, NicknameRepetidoExce {
        LocalDate fechaNacimiento = LocalDate.parse(nacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ArrayList<String> seguidos_parced = new ArrayList<String>();
        for(String seg: seguidos) {
        	seguidos_parced.add(seg);
        }
        ArrayList<String> seguidores_parced = new ArrayList<String>();
        for(String seg2: seguidores) {
        	seguidores_parced.add(seg2);
        }
        usuarioController.crearCliente(nickname, nombre, email, apellido, fechaNacimiento, nacionalidad, tipoDocumento,
                documento, password, imagen, seguidos_parced, seguidores_parced);
    }

    // Método para crear una aerolínea
    @WebMethod
    public void crearAerolinea(String nickname, String nombre, String email, String descripcion, String web,
                               String password, String imagen, String[] seguidos, String[] seguidores)
            throws EmailRepetidoExce, NicknameRepetidoExce {
    	ArrayList<String> seguidos_parced = new ArrayList<String>();
        for(String seg: seguidos) {
        	seguidos_parced.add(seg);
        }
        ArrayList<String> seguidores_parced = new ArrayList<String>();
        for(String seg2: seguidores) {
        	seguidores_parced.add(seg2);
        }
        usuarioController.crearAerolinea(nickname, nombre, email, descripcion, web, password, imagen, seguidos_parced, seguidores_parced);
    }

    // Método para ver información de un cliente
    @WebMethod
    public DataCliente verInfoCliente(String nickname) throws UsuarioNoExisteExce {
        return usuarioController.verInfoCliente(nickname);
    }

    // Método para ver información de una aerolínea
    @WebMethod
    public DataAerolinea verInfoAerolinea(String nickname) throws UsuarioNoExisteExce {
        return usuarioController.verInfoAerolinea(nickname);
    }

    // Método para modificar un cliente
    @WebMethod
    public void modificarCliente(String nickname, String nombre, String email, String apellido, String nacimiento,
                                 String nacionalidad, String tipoDocumento, String documento, String password,
                                 String imagen) throws UsuarioNoExisteExce {
        LocalDate fechaNacimiento = LocalDate.parse(nacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        usuarioController.modificarCliente(nickname, nombre, email, apellido, fechaNacimiento, nacionalidad, tipoDocumento,
                documento, password, imagen);
    }

    // Método para modificar una aerolínea
    @WebMethod
    public void modificarAerolinea(String nickname, String nombre, String email, String descripcion, String web,
                                   String password, String imagen) throws UsuarioNoExisteExce {
        usuarioController.modificarAerolinea(nickname, nombre, email, descripcion, web, password, imagen);
    }

    // Obtener todos los clientes
    @WebMethod
    public DataCliente[] getClientes() throws UsuarioNoExisteExce {
        return usuarioController.getClientes();
    }

    // Obtener todas las aerolíneas
    @WebMethod
    public DataAerolinea[] getAerolineas() throws UsuarioNoExisteExce {
        return usuarioController.getAerolineas();
    }

    @WebMethod
    public DataReserva[] getReservasSinCheckIn(String nickCliente) throws UsuarioNoExisteExce {
        return usuarioController.getReservasSinCheckIn(nickCliente).toArray(new DataReserva[usuarioController.getReservasSinCheckIn(nickCliente).size()]);
    }
    
    @WebMethod
    public void realizarCheckIn(String nickC, String nickV) throws ClientePasajeroRepetidoExce{
    	usuarioController.realizarCheckIn(nickC, nickV);
    }
    
    @WebMethod
    public DataCheckIn[] getCheckIns(String nickC) throws UsuarioNoExisteExce {
    	return usuarioController.getCheckIns(nickC);
    }
    
    @WebMethod
    public void seguir(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteExce {
    	usuarioController.seguir(nickSeguidor, nickSeguido);
    }
    
    @WebMethod
    public void dejarSeguir(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteExce {
    	usuarioController.dejarSeguir(nickSeguidor, nickSeguido);
    }
}
