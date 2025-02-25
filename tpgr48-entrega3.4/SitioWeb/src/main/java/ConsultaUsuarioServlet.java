

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

//import logica.Datatypes.DataAerolinea;
//import logica.Datatypes.DataCliente;


import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.DataAerolineaArray;
import servidor.DataClienteArray;
import servidor.UsuarioNoExisteExce_Exception;

/**
 * Servlet implementation class ConsultaUsuarioServlet
 */
@WebServlet("/consulta-usuario")
public class ConsultaUsuarioServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    private PublicadorUsuariosService publicadorUsuarioService;
	    private PublicadorUsuarios publicadorUsuario;

	    public ConsultaUsuarioServlet() {
	        super();
	        try {
	            publicadorUsuarioService = new PublicadorUsuariosService();
	            publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("Error al inicializar el servicio PublicadorUsuarios", e);
	        }
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getParameter("action");
	        String targetNickname = request.getParameter("nickname");
	        String usuarioLogueado = (String) request.getSession().getAttribute("usuario");
	        String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario"); // "cliente" o "aerolinea"

	        List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
	        List<servidor.DataCliente> clientes = new ArrayList<>();
	        List<String> usuariosSeguidos = new ArrayList<>();

	        try {
	            // Realizar la acción de seguir o dejar de seguir
	            if (action != null && targetNickname != null && usuarioLogueado != null) {
	                if ("seguir".equals(action)) {
	                    publicadorUsuario.seguir(usuarioLogueado, targetNickname);
	                } else if ("dejar".equals(action)) {
	                    publicadorUsuario.dejarSeguir(usuarioLogueado, targetNickname);
	                }
	            }

	            if ("cliente".equals(tipoUsuario)) {
	                usuariosSeguidos = publicadorUsuario.verInfoCliente(usuarioLogueado).getSeguidos();
	            } else if ("aerolinea".equals(tipoUsuario)) {
	                usuariosSeguidos = publicadorUsuario.verInfoAerolinea(usuarioLogueado).getSeguidos();
	            }

	            // Obtener listas de usuarios
	            clientes = publicadorUsuario.getClientes().getItem();
	            aerolineas = publicadorUsuario.getAerolineas().getItem();

	            // Pasar datos al JSP
	            request.setAttribute("clientes", clientes);
	            request.setAttribute("aerolineas", aerolineas);
	            request.setAttribute("usuariosSeguidos", usuariosSeguidos);
	        } catch (UsuarioNoExisteExce_Exception e) {
	            request.setAttribute("mensaje", "No hay usuarios registrados en el sistema.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Error al obtener los usuarios: " + e.getMessage());
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("consulta-usuario.jsp");
	        dispatcher.forward(request, response);
	    }

}
