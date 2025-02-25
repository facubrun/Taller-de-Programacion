

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
 * Servlet implementation class PerfilClienteServlet
 */
@WebServlet("/profile")
public class PerfilClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public PerfilClienteServlet() {
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
        HttpSession session = request.getSession();
        
        // Verificar si hay un usuario en la sesión y es un String
        String usuarioSesion = (session.getAttribute("usuario") instanceof String) ? (String) session.getAttribute("usuario") : null;
        boolean esPropioPerfil = usuarioSesion != null && usuarioSesion.equals(nickname);

        try {
            servidor.DataCliente cliente = publicadorUsuario.verInfoCliente(nickname);

            // Agregar datos al request
            request.setAttribute("cliente", cliente);
            request.setAttribute("esPropioPerfil", esPropioPerfil);

            // Redirigir al perfil del cliente
            RequestDispatcher dispatcher = request.getRequestDispatcher("perfilCliente.jsp");
            dispatcher.forward(request, response);

        } catch (UsuarioNoExisteExce_Exception e) {
            request.setAttribute("error", "El cliente no existe.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

}
