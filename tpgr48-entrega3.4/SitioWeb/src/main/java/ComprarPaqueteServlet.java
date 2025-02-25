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


import servidor.PublicadorPaquete;
import servidor.PublicadorPaqueteService;
import servidor.PublicadorUsuariosService;


@WebServlet("/comprar-paquete")
public class ComprarPaqueteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PublicadorPaqueteService publicadorPaqueteService;
    private PublicadorPaquete publicadorPaquete;

    public ComprarPaqueteServlet() {
    	super();
        
        try {
        	publicadorPaqueteService = new PublicadorPaqueteService();
            publicadorPaquete = publicadorPaqueteService.getPublicadorPaquetePort();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("Error al inicializar el servicio PublicadorPaquete", e);
	        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ArrayList<DataPaquete> paquetes = new ArrayList<>();
    	List<servidor.DataPaquete> paquetes = new ArrayList<>();
        String paqueteNombre = request.getParameter("nombre");
        try {
            paquetes = publicadorPaquete.listarPaquetesDisponibles().getItem();
        } catch (Exception e) {
            request.setAttribute("error", "Ocurrió un error al obtener los paquetes.");
        }

        request.setAttribute("paquetes", paquetes);
        request.setAttribute("mensaje", "Compra realizada exitosamente para el paquete: " + paqueteNombre);
        RequestDispatcher dispatcher = request.getRequestDispatcher("comprar-paquete.jsp");
        dispatcher.forward(request, response);
    }
}
