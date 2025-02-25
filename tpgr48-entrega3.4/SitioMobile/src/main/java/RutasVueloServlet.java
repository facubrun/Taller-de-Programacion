import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DataRutaVuelo;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.RVNoExisteExce_Exception;

@WebServlet(description = "Lista Rutas de vuelo, se muestra en el index", urlPatterns = { "/index" })
public class RutasVueloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PublicadorRutasVueloService publicadorRutasVueloService;
    private PublicadorRutasVuelo publicadorRutasVuelo;

    public RutasVueloServlet() {
        super();
        
        
        try {
        	publicadorRutasVueloService = new PublicadorRutasVueloService();
            publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
			
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			List<servidor.DataRutaVuelo> rutasVuelo =  publicadorRutasVuelo.listarRutasVuelo().getItem();

            request.setAttribute("rutas", rutasVuelo);
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
		} catch (RVNoExisteExce_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}