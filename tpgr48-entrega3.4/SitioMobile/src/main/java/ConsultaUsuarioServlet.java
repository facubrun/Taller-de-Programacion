

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
import servidor.PublicadorRutasVueloService;
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
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
        List<servidor.DataCliente> clientes = new ArrayList<>();
        
       
        
        try {
        	clientes = publicadorUsuario.getClientes().getItem();	
            aerolineas = publicadorUsuario.getAerolineas().getItem();
            request.setAttribute("clientes", clientes);
            request.setAttribute("aerolineas", aerolineas);
        } catch (UsuarioNoExisteExce_Exception e) {
            request.setAttribute("mensaje", "No hay usuarios registrados en el sistema.");
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("error", "Error al obtener los usuarios: " + e.getMessage());
            e.printStackTrace();
        }
        
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("consulta-usuario.jsp");
        dispatcher.forward(request, response);
    }
}
