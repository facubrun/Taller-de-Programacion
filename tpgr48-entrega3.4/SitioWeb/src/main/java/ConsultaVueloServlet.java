import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DataVuelo;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.PublicadorVuelos;
import servidor.PublicadorVuelosService;
import servidor.UsuarioNoExisteExce_Exception;


@WebServlet(description = "Consultar vuelos", urlPatterns = { "/consulta-vuelos" })
public class ConsultaVueloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private PublicadorUsuariosService publicadorUsuariosService;
    private PublicadorUsuarios publicadorUsuarios;
    private PublicadorVuelosService publicadorVuelosService;
    private PublicadorVuelos publicadorVuelos;
	
    public ConsultaVueloServlet() {
        super();

       

        try {
            publicadorUsuariosService = new PublicadorUsuariosService();
            publicadorUsuarios = publicadorUsuariosService.getPublicadorUsuariosPort();
            publicadorVuelosService = new PublicadorVuelosService();
            publicadorVuelos = publicadorVuelosService.getPublicadorVuelosPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
        
		try {
			aerolineas = publicadorUsuarios.getAerolineas().getItem();
			request.setAttribute("aerolineas", aerolineas);
		} catch (UsuarioNoExisteExce_Exception e) {
			request.setAttribute("error", e.getMessage());
		}
    	
    	ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/consulta-vuelo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vuelo = (String) request.getParameter("vuelo").toString();
        String aerolinea = (String) request.getParameter("aerolinea").toString();
        
        DataVuelo infoVuelo;
        List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
		try {
			infoVuelo = publicadorVuelos.verInfoVuelo(vuelo);
			request.setAttribute("infoVuelo", infoVuelo);

            aerolineas = publicadorUsuarios.getAerolineas().getItem();
			request.setAttribute("aerolineas", aerolineas);
            request.setAttribute("aerolinea", aerolinea);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}
        
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/consulta-vuelo.jsp");
        dispatcher.forward(request, response);
    }
}
