

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class ModificarUsuarioServlet
 */
@WebServlet("/modificar-usuario")
public class ModificarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//    private IUsuariosController controlUsr;
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public ModificarUsuarioServlet() {
        super();
//        Fabrica fabrica = Fabrica.getInstance();
//        controlUsr = fabrica.getIControladorUsuario();
        try {
			publicadorUsuarioService = new PublicadorUsuariosService();
			publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("modificar-usuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String nacimiento = request.getParameter("nacimiento");
        String nacionalidad = request.getParameter("nacionalidad");
        
        try {
        	
        	servidor.DataCliente cliente = publicadorUsuario.verInfoCliente(nickname);
        	
        	publicadorUsuario.modificarCliente(nickname, nombre, cliente.getEmail(), apellido, nacimiento, nacionalidad, cliente.getTipoDocumento(), cliente.getDocumento(), cliente.getPassword(), "");
           
            response.sendRedirect("profile?nickname=" + nickname);
        } catch (UsuarioNoExisteExce_Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("modificar-usuario.jsp").forward(request, response);
        }
    }

}
