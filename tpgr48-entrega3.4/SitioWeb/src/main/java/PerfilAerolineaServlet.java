

import java.io.IOException;
import java.net.MalformedURLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;


/**
 * Servlet implementation class PerfilAerolineaServlet
 */
public class PerfilAerolineaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public PerfilAerolineaServlet() {
        super();
        
		try {
			publicadorUsuarioService = new PublicadorUsuariosService();
			publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
       } catch (MalformedURLException e) {
           throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        HttpSession session = request.getSession(false); 
        String usuarioSesion = null;
        		
		if (session != null && session.getAttribute("usuario") != null) {
	        usuarioSesion = session.getAttribute("usuario").toString().trim();
	    }

        try {
            servidor.DataAerolinea aerolinea = publicadorUsuario.verInfoAerolinea(nickname);
            boolean esPropioPerfil = nickname.equals(usuarioSesion);

            request.setAttribute("aerolinea", aerolinea);
            request.setAttribute("esPropioPerfil", esPropioPerfil);

            RequestDispatcher dispatcher = request.getRequestDispatcher("perfilAerolinea.jsp");
            dispatcher.forward(request, response);

        } catch (UsuarioNoExisteExce_Exception e) {
            request.setAttribute("error", "La aerolínea no existe.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

}
