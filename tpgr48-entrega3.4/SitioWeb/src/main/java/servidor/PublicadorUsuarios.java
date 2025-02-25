
package servidor;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import net.java.dev.jaxb.array.StringArray;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "PublicadorUsuarios", targetNamespace = "http://servidor/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    net.java.dev.jaxb.array.ObjectFactory.class,
    servidor.ObjectFactory.class
})
public interface PublicadorUsuarios {


    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @param arg9
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/modificarClienteRequest", output = "http://servidor/PublicadorUsuarios/modificarClienteResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/modificarCliente/Fault/UsuarioNoExisteExce")
    })
    public void modificarCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/modificarAerolineaRequest", output = "http://servidor/PublicadorUsuarios/modificarAerolineaResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/modificarAerolinea/Fault/UsuarioNoExisteExce")
    })
    public void modificarAerolinea(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataReservaArray
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorUsuarios/getReservasSinCheckInRequest", output = "http://servidor/PublicadorUsuarios/getReservasSinCheckInResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/getReservasSinCheckIn/Fault/UsuarioNoExisteExce")
    })
    public DataReservaArray getReservasSinCheckIn(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @throws ClientePasajeroRepetidoExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/realizarCheckInRequest", output = "http://servidor/PublicadorUsuarios/realizarCheckInResponse", fault = {
        @FaultAction(className = ClientePasajeroRepetidoExce_Exception.class, value = "http://servidor/PublicadorUsuarios/realizarCheckIn/Fault/ClientePasajeroRepetidoExce")
    })
    public void realizarCheckIn(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws ClientePasajeroRepetidoExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataCheckInArray
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorUsuarios/getCheckInsRequest", output = "http://servidor/PublicadorUsuarios/getCheckInsResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/getCheckIns/Fault/UsuarioNoExisteExce")
    })
    public DataCheckInArray getCheckIns(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/seguirRequest", output = "http://servidor/PublicadorUsuarios/seguirResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/seguir/Fault/UsuarioNoExisteExce")
    })
    public void seguir(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/dejarSeguirRequest", output = "http://servidor/PublicadorUsuarios/dejarSeguirResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/dejarSeguir/Fault/UsuarioNoExisteExce")
    })
    public void dejarSeguir(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg10
     * @param arg11
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @param arg9
     * @throws EmailRepetidoExce_Exception
     * @throws NicknameRepetidoExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/crearClienteRequest", output = "http://servidor/PublicadorUsuarios/crearClienteResponse", fault = {
        @FaultAction(className = EmailRepetidoExce_Exception.class, value = "http://servidor/PublicadorUsuarios/crearCliente/Fault/EmailRepetidoExce"),
        @FaultAction(className = NicknameRepetidoExce_Exception.class, value = "http://servidor/PublicadorUsuarios/crearCliente/Fault/NicknameRepetidoExce")
    })
    public void crearCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9,
        @WebParam(name = "arg10", partName = "arg10")
        StringArray arg10,
        @WebParam(name = "arg11", partName = "arg11")
        StringArray arg11)
        throws EmailRepetidoExce_Exception, NicknameRepetidoExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @throws EmailRepetidoExce_Exception
     * @throws NicknameRepetidoExce_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/PublicadorUsuarios/crearAerolineaRequest", output = "http://servidor/PublicadorUsuarios/crearAerolineaResponse", fault = {
        @FaultAction(className = EmailRepetidoExce_Exception.class, value = "http://servidor/PublicadorUsuarios/crearAerolinea/Fault/EmailRepetidoExce"),
        @FaultAction(className = NicknameRepetidoExce_Exception.class, value = "http://servidor/PublicadorUsuarios/crearAerolinea/Fault/NicknameRepetidoExce")
    })
    public void crearAerolinea(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        StringArray arg7,
        @WebParam(name = "arg8", partName = "arg8")
        StringArray arg8)
        throws EmailRepetidoExce_Exception, NicknameRepetidoExce_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.DataAerolineaArray
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorUsuarios/getAerolineasRequest", output = "http://servidor/PublicadorUsuarios/getAerolineasResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/getAerolineas/Fault/UsuarioNoExisteExce")
    })
    public DataAerolineaArray getAerolineas()
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @return
     *     returns servidor.DataClienteArray
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorUsuarios/getClientesRequest", output = "http://servidor/PublicadorUsuarios/getClientesResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/getClientes/Fault/UsuarioNoExisteExce")
    })
    public DataClienteArray getClientes()
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataAerolinea
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorUsuarios/verInfoAerolineaRequest", output = "http://servidor/PublicadorUsuarios/verInfoAerolineaResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/verInfoAerolinea/Fault/UsuarioNoExisteExce")
    })
    public DataAerolinea verInfoAerolinea(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteExce_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataCliente
     * @throws UsuarioNoExisteExce_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/PublicadorUsuarios/verInfoClienteRequest", output = "http://servidor/PublicadorUsuarios/verInfoClienteResponse", fault = {
        @FaultAction(className = UsuarioNoExisteExce_Exception.class, value = "http://servidor/PublicadorUsuarios/verInfoCliente/Fault/UsuarioNoExisteExce")
    })
    public DataCliente verInfoCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteExce_Exception
    ;

}
