
package servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "EmailRepetidoExce", targetNamespace = "http://servidor/")
public class EmailRepetidoExce_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EmailRepetidoExce faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EmailRepetidoExce_Exception(String message, EmailRepetidoExce faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public EmailRepetidoExce_Exception(String message, EmailRepetidoExce faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.EmailRepetidoExce
     */
    public EmailRepetidoExce getFaultInfo() {
        return faultInfo;
    }

}
