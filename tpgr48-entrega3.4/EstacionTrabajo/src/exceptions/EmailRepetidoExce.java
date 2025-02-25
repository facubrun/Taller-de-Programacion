package exceptions;

import jakarta.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "EmailRepetidoExce")
public class EmailRepetidoExce extends Exception {

	public EmailRepetidoExce(String message) {
        super(message);
    }

    public EmailRepetidoExce(String message, Throwable cause) {
        super(message, cause);
    }

}
