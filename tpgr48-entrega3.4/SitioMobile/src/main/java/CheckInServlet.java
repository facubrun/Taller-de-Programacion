import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;
import servidor.ClientePasajeroRepetidoExce_Exception;

/**
 * Servlet implementation class ConsultaUsuarioServlet
 */
@WebServlet("/check-in")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public CheckInServlet() {
        super();
        
		try {
			publicadorUsuarioService = new PublicadorUsuariosService();
			publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
        	request.getRequestDispatcher("login").forward(request, response);
                   //response.sendRedirect("403.jsp");
	    }else {	    
    		String nickname = session.getAttribute("usuario").toString().trim();

	       try {
	        	List<servidor.DataReserva> reservas_total = publicadorUsuario.verInfoCliente(nickname.split(" - ")[0]).getReservas();
	        	
	        	List<servidor.DataReserva> reservas = new ArrayList<>();
	        	for(servidor.DataReserva res: reservas_total) {
	        		if(!res.isCheckIn()) {
	        			reservas.add(res);
	        		}
	        	}
	        	
	            request.setAttribute("reservas", reservas);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("realizar-checkin.jsp");
	            dispatcher.forward(request, response);
	
	        } catch (UsuarioNoExisteExce_Exception e) {
	            request.setAttribute("error", "El cliente no existe.");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
    	
        if (session == null || session.getAttribute("usuario") == null) {
        	request.getRequestDispatcher("login").forward(request, response);
                   //response.sendRedirect("403.jsp");
	    }else {	    
    		String nickname = session.getAttribute("usuario").toString().trim();
    		String vuelo = (String) request.getParameter("vuelo").trim();
	        try {
	        	publicadorUsuario.realizarCheckIn(nickname.split(" - ")[0],vuelo);
	        	
	        	List<servidor.DataReserva> reservas_total = publicadorUsuario.verInfoCliente(nickname.split(" - ")[0]).getReservas();
	        	
	        	List<servidor.DataReserva> reservas = new ArrayList<>();
	        	for(servidor.DataReserva res: reservas_total) {
	        		if(!res.isCheckIn()) {
	        			reservas.add(res);
	        		}
	        	}
	        	
	            request.setAttribute("reservas", reservas);
	
	            request.setAttribute("error", "exito");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("realizar-checkin.jsp");
	            dispatcher.forward(request, response);
	
	        } catch (ClientePasajeroRepetidoExce_Exception | UsuarioNoExisteExce_Exception e) {
	            request.setAttribute("error", "error");
	            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
    }

}