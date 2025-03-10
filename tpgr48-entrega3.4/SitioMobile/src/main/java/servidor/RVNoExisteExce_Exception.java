
package servidor;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "RVNoExisteExce", targetNamespace = "http://servidor/")
public class RVNoExisteExce_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private RVNoExisteExce faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public RVNoExisteExce_Exception(String message, RVNoExisteExce faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public RVNoExisteExce_Exception(String message, RVNoExisteExce faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.RVNoExisteExce
     */
    public RVNoExisteExce getFaultInfo() {
        return faultInfo;
    }

}
