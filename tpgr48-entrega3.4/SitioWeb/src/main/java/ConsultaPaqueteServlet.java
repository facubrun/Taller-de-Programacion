

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//import servidor.PublicadorPaquete;
//import servidor.PublicadorPaqueteService;
import servidor.DataRutaVuelo;
/**
 * Servlet implementation class ConsultaUsuarioServlet
 */
@WebServlet("/consulta-paquete")
public class ConsultaPaqueteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    private PublicadorRutasVueloService publicadorPaqueteService;
//	private PublicadorPaquete publicadorPaquete;
	
    public ConsultaPaqueteServlet() {
        super();
//        publicadorPaqueteService = new PublicadorPaqueteService();
//        publicadorPaqueteService = publicadorPaqueteService.getPublicadorPaquetePort();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 //       ArrayList<DataPaquete> paquetes = new ArrayList<DataPaquete>();
        try {
//        	List<servidor.paquete.DataPaquete> paquetesDisponibles = publicadorPaquete.listarPaquetesDisponibles.getItem();
//            for (DataPaquete paquete : paquetesDisponibles) {
//                    paquetes.add(paquete);
//            }
        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al obtener los paquetes.");
        }

 //       request.setAttribute("paquetes", paquetes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta-paquete.jsp");
        dispatcher.forward(request, response);
    }
}
