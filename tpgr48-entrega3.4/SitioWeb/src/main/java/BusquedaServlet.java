

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DataRutaVuelo;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.RVNoExisteExce_Exception;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class BusquedaServlet
 */
@WebServlet("/resultados-busqueda")
public class BusquedaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PublicadorRutasVueloService publicadorRutasService;
    private PublicadorRutasVuelo publicadorRutas;

    public BusquedaServlet() {
        try {
            publicadorRutasService = new PublicadorRutasVueloService();
            publicadorRutas = publicadorRutasService.getPublicadorRutasVueloPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio de rutas de vuelo.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query").toLowerCase();
        List<DataRutaVuelo> rutasVuelo = new ArrayList<>(); // Inicializamos la variable
        
        try {
            rutasVuelo = publicadorRutas.listarRutasVuelo().getItem(); // Asignamos las rutas si no hay excepciones
        } catch (RVNoExisteExce_Exception e) {
            e.printStackTrace();
        }

        List<DataRutaVuelo> resultados = new ArrayList<>();
        for (DataRutaVuelo ruta : rutasVuelo) {
            if (ruta.getDescCorta().toLowerCase().contains(query)) {
                resultados.add(ruta);
            }
        }

        request.setAttribute("rutas", resultados);
        request.setAttribute("query", query);
        request.getRequestDispatcher("/resultados-busqueda.jsp").forward(request, response);
    }
}
