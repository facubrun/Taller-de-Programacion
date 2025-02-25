package exceptions;

import jakarta.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "AerolineaNoExisteExce")
public class AerolineaNoExisteExce extends Exception {

	public AerolineaNoExisteExce(String message) {
		super(message);
	}
	
	public AerolineaNoExisteExce(String message, Throwable cause) {
        super(message, cause);
    }

}



