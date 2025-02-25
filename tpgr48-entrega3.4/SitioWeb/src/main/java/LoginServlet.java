

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public LoginServlet() {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean autenticado = false;

        try {
        	List<servidor.DataCliente> clientes = new ArrayList<>();
            try {
            	clientes = publicadorUsuario.getClientes().getItem();
            } catch (UsuarioNoExisteExce_Exception e) {
            }
            
            for (servidor.DataCliente cliente : clientes) {
                if ((cliente.getEmail().equals(email) || cliente.getNickname().equals(email)) && cliente.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", cliente.getNickname());
                    session.setAttribute("tipoUsuario", "cliente");
                    response.sendRedirect(request.getContextPath() + "/");
                    autenticado = true;
                    break;
                }
            }

            if (!autenticado) {
            	List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
                try {
                    aerolineas = publicadorUsuario.getAerolineas().getItem();
                } catch (UsuarioNoExisteExce_Exception e) {
                }
                
                for (servidor.DataAerolinea aerolinea : aerolineas) {
                    if ((aerolinea.getEmail().equals(email) || aerolinea.getNickname().equals(email)) && aerolinea.getPassword().equals(password)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("usuario", aerolinea.getNickname());
                        session.setAttribute("tipoUsuario", "aerolinea");
                        response.sendRedirect(request.getContextPath() + "/");
                        autenticado = true;
                        break;
                    }
                }
            }

            if (!autenticado) {
                request.setAttribute("error", "Credenciales incorrectas");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al procesar el inicio de sesión");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
}
}
