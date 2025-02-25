package exceptions;

import jakarta.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "NicknameRepetidoExce")
public class NicknameRepetidoExce extends Exception {

	public NicknameRepetidoExce(String message) {
		super(message);
	}
	
	public NicknameRepetidoExce(String message, Throwable cause) {
        super(message, cause);
    }
}
