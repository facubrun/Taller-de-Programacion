
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.DataRutaVuelo;
import servidor.DataRutaVueloArray;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;


@WebServlet("/consulta-reserva")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50
)
public class consultaReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PublicadorRutasVueloService publicadorRutasVueloService;
    private PublicadorRutasVuelo publicadorRutasVuelo;
    private PublicadorUsuariosService publicadorUsuariosService;
    private PublicadorUsuarios publicadorUsuarios;

    public consultaReservaServlet() {
        super();

        
        try {
        	publicadorUsuariosService = new PublicadorUsuariosService();
            publicadorUsuarios = publicadorUsuariosService.getPublicadorUsuariosPort();
            publicadorRutasVueloService = new PublicadorRutasVueloService();
            publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setAttribute("error", null);
    	HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
        	request.getRequestDispatcher("login").forward(request, response);
        }else if(session.getAttribute("tipoUsuario") == "cliente"){
            List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
            
            try {
                aerolineas = publicadorUsuarios.getAerolineas().getItem();
            } catch (UsuarioNoExisteExce_Exception e) {
                request.setAttribute("mensaje", "No hay usuarios registrados en el sistema.");
            } catch (Exception e) {
                request.setAttribute("error", "Ocurrió un error al obtener los usuarios.");
            }

	    	request.setAttribute("aerolineas",aerolineas);
	    	
	        RequestDispatcher dispatcher = request.getRequestDispatcher("consulta-reserva.jsp");
	        dispatcher.forward(request, response);
            
            
        }else if(session.getAttribute("tipoUsuario") == "aerolinea"){
        	
        	DataRutaVueloArray vuelos = new DataRutaVueloArray();
        	try {
        		vuelos = publicadorRutasVuelo.listarRutasVueloPorAerolinea(session.getAttribute("usuario").toString().trim());
        		
    		} catch (UsuarioNoExisteExce_Exception e1) {
    			request.setAttribute("error", "RVNoExisteExce");
    		}
        	List<DataRutaVuelo> rutas = vuelos.getItem();

        	request.setAttribute("rutas",rutas);
	    		    	
	        RequestDispatcher dispatcher = request.getRequestDispatcher("consulta-reserva.jsp");
	        dispatcher.forward(request, response);
	    }
    }

}