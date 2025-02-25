

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DataRutaVuelo;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servlet implementation class RutasVuelosSearchServlet
 */
public class RutasVuelosSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private PublicadorRutasVuelo publicadorRutasVuelo;

    public RutasVuelosSearchServlet() {
        super();
       
        try {
        	 PublicadorRutasVueloService service = new PublicadorRutasVueloService();
             publicadorRutasVuelo = service.getPublicadorRutasVueloPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (query == null || query.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"La consulta no puede estar vacía\"}");
            return;
        }

        try {
            List<DataRutaVuelo> rutas = publicadorRutasVuelo.listarRutasVuelo().getItem();
            List<DataRutaVuelo> resultados = rutas.stream()
                .filter(ruta -> ruta.getNombre().toLowerCase().contains(query.toLowerCase())
                        || ruta.getCiudadOrigen().toLowerCase().contains(query.toLowerCase())
                        || ruta.getCiudadDestino().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

            StringBuilder jsonBuilder = new StringBuilder("[");
            for (int i = 0; i < resultados.size(); i++) {
                DataRutaVuelo ruta = resultados.get(i);
                jsonBuilder.append("{")
                        .append("\"nombre\":\"").append(ruta.getNombre()).append("\",")
                        .append("\"ciudadOrigen\":\"").append(ruta.getCiudadOrigen()).append("\",")
                        .append("\"ciudadDestino\":\"").append(ruta.getCiudadDestino()).append("\"")
                        .append("}");
                if (i < resultados.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("]");

            PrintWriter out = response.getWriter();
            out.print(jsonBuilder.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error al buscar rutas de vuelo\"}");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
