

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import servidor.PublicadorRutasVueloService;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Servlet implementation class VerificarUsuarioServlet
 */
@WebServlet("/verificarUsuario")
public class VerificarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private PublicadorUsuariosService publicadorUsuarioService;
    private PublicadorUsuarios publicadorUsuario;

    public VerificarUsuarioServlet() {
        super();
        
        try {
        	publicadorUsuarioService = new PublicadorUsuariosService();
            publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        boolean usuarioExiste = false;

        try {
            List<servidor.DataCliente> clientes = publicadorUsuario.getClientes().getItem();
            List<servidor.DataAerolinea> aerolineas = publicadorUsuario.getAerolineas().getItem();
            
            for (servidor.DataCliente cliente : clientes) {
                if (cliente.getEmail().equals(email) || cliente.getNickname().equals(email)) {
                    usuarioExiste = true;
                    break;
                }
            }
            
            if (!usuarioExiste) {
                for (servidor.DataAerolinea aerolinea : aerolineas) {
                    if (aerolinea.getEmail().equals(email) || aerolinea.getNickname().equals(email)) {
                        usuarioExiste = true;
                        break;
                    }
                }
            }
        } catch (UsuarioNoExisteExce_Exception e) {
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"existe\": " + usuarioExiste + "}");
        out.flush();
    }

}
