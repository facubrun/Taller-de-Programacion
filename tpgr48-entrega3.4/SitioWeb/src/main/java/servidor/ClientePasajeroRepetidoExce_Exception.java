
package servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "ClientePasajeroRepetidoExce", targetNamespace = "http://servidor/")
public class ClientePasajeroRepetidoExce_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ClientePasajeroRepetidoExce faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ClientePasajeroRepetidoExce_Exception(String message, ClientePasajeroRepetidoExce faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public ClientePasajeroRepetidoExce_Exception(String message, ClientePasajeroRepetidoExce faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.ClientePasajeroRepetidoExce
     */
    public ClientePasajeroRepetidoExce getFaultInfo() {
        return faultInfo;
    }

}
