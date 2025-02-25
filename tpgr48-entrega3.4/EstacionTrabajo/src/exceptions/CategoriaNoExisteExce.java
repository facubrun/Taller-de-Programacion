package exceptions;

import jakarta.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "CategoriaNoExisteExce")
public class CategoriaNoExisteExce extends Exception {

	public CategoriaNoExisteExce(String message) {
		super(message);
	}
	
	public CategoriaNoExisteExce(String message, Throwable cause) {
        super(message, cause);
    }

}



