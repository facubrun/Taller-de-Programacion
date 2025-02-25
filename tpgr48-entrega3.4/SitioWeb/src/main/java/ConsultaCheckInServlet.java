

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class ConsultaCheckInServlet
 */
public class ConsultaCheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public ConsultaCheckInServlet() {
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
	    }else if(session.getAttribute("tipoUsuario") == "cliente"){
    		String nickname = (String) session.getAttribute("usuario");

	        List<servidor.DataCheckIn> checkins = new ArrayList<>();
			try {
				checkins = publicadorUsuario.getCheckIns(nickname).getItem();
			} catch (UsuarioNoExisteExce_Exception e) {
				e.printStackTrace();	
			}

			request.setAttribute("checkins", checkins);
			RequestDispatcher dispatcher = request.getRequestDispatcher("consulta-check-in.jsp");
			dispatcher.forward(request, response);
	    }
    }

}
