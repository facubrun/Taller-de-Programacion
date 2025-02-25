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
import jakarta.xml.ws.WebServiceProvider;

import servidor.DataRutaVuelo;
import servidor.DataRutaVueloArray;
import servidor.DataReserva;
import servidor.DataVuelo;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorVuelos;
import servidor.PublicadorVuelosService;
import servidor.DataAerolinea;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce;
import servidor.UsuarioNoExisteExce_Exception;
import servidor.VueloNoExisteExce_Exception;


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
	private PublicadorVuelosService publicadorVuelosService;
	private PublicadorVuelos publicadorVuelos;

    public consultaReservaServlet() {
        super();

        
		try {
			publicadorRutasVueloService = new PublicadorRutasVueloService();
	        publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();

	        publicadorUsuariosService = new PublicadorUsuariosService();
	        publicadorUsuarios = publicadorUsuariosService.getPublicadorUsuariosPort();
	        
			publicadorVuelosService = new PublicadorVuelosService();
			publicadorVuelos = publicadorVuelosService.getPublicadorVuelosPort();
			
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	String aerolinea = request.getParameter("Aerolinea");
    	String ruta = request.getParameter("Ruta");
    	request.setAttribute("ruta", ruta);
    	request.setAttribute("aerolinea", aerolinea);
    	
        String vuelo = request.getParameter("Vuelos");
        Boolean ok= false;
        ArrayList<DataReserva> reservas = new ArrayList<>();
        HttpSession session = request.getSession(false);
    	String nombreCliente = session.getAttribute("usuario").toString().trim();
    	nombreCliente = nombreCliente.split(" - ")[0];
    	DataReserva reserva = null;
    	
		try {
			// Fetch routes based on airline
			if (vuelo != null && !vuelo.isEmpty()) {
	        	DataVuelo dataVuelo  = publicadorVuelos.verInfoVuelo(vuelo.trim());
	        	reservas = (ArrayList<DataReserva>) dataVuelo.getReservas();
	        	for (int i = 0; i < reservas.size(); i++) {
	        	    if(nombreCliente.equals(reservas.get(i).getNickC().trim())) {
	        	    	ok=true;
	        	    	reserva = reservas.get(i);
	        	    	break;
	        	    }
	        	}
			}
			
		} catch (VueloNoExisteExce_Exception e) {
		}
		

		if(ok) {
			request.setAttribute("ok", "true");
		}else {
			request.setAttribute("ok", "false");
		}
		
        request.setAttribute("reserva", reserva);
        RequestDispatcher dispatcher = request.getRequestDispatcher("detalle_reserva.jsp");
        dispatcher.forward(request, response);
    }


}