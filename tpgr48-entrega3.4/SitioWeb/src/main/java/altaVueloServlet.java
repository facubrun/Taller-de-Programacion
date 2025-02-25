import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import servidor.DataRutaVuelo;
import servidor.DataRutaVueloArray;
import servidor.NombreVRepetidoExce_Exception;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorVuelos;
import servidor.PublicadorVuelosService;
import servidor.RVNoExisteExce_Exception;
import servidor.UsuarioNoExisteExce_Exception;


@WebServlet("/alta-vuelo")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50
)
public class altaVueloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	private PublicadorVuelosService publicadorVuelosService;
	private PublicadorVuelos publicadorVuelos;
	private PublicadorRutasVueloService publicadorRutasVueloService;
	private PublicadorRutasVuelo publicadorRutasVuelo;
    
    public altaVueloServlet() {
        super();

		

		 try {
			publicadorVuelosService = new PublicadorVuelosService();
			publicadorVuelos = publicadorVuelosService.getPublicadorVuelosPort();
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
                   //response.sendRedirect("403.jsp");
	    }else {
	    	List<servidor.DataRutaVuelo> rutas = new ArrayList<>();
	    	Logger logger = Logger.getLogger(altaVueloServlet.class.getName());		    
    		logger.info(session.getAttribute("usuario").toString().trim());
	    	try {
	    		rutas = publicadorRutasVuelo.listarRutasVueloPorAerolinea(session.getAttribute("usuario").toString().trim()).getItem();
	    		System.out.println(session.getAttribute("usuario").toString().trim());
			} catch (UsuarioNoExisteExce_Exception e1) {
				request.setAttribute("error", "RVNoExisteExce");
			}
	    	request.setAttribute("rutas",rutas);
	    	
	        RequestDispatcher dispatcher = request.getRequestDispatcher("alta-vuelo.jsp");
	        dispatcher.forward(request, response);
	    }
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre").trim();
        String ruta = request.getParameter("ruta").trim();
        
        String fecha;
        LocalDate fechaDate = LocalDate.parse(request.getParameter("fecha").trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        fecha = fechaDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        String duracion;
        LocalTime duracionTime = LocalTime.parse(request.getParameter("duracion").trim(), DateTimeFormatter.ofPattern("HH:mm"));
        duracion = duracionTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        
        String turista = request.getParameter("turista").trim();
        String ejecutivo = request.getParameter("ejecutivo").trim();
        
        Part filePart = request.getPart("image");
        
        try {
        	publicadorVuelos.ingresarDatosVuelo(ruta, nombre, fecha, duracion, Integer.parseInt(turista), Integer.parseInt(ejecutivo), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        	request.setAttribute("error", "exito");
            if(filePart!=null) {
            	
            	String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            	// Extraer la extensión del archivo original
            	String fileExtension = "";
            	int dotIndex = fileName.lastIndexOf(".");
            	if (dotIndex != -1) {
            	    fileExtension = fileName.substring(dotIndex);
            	}
            	
            	fileName= nombre+fileExtension;
    	        System.out.println(fileName);
    	        
    	        String uploadPath = getServletContext().getRealPath("") + File.separator + "vuelos_images";
    	        File uploadDir = new File(uploadPath);
    	        if (!uploadDir.exists()) uploadDir.mkdir();
    	        
    	        System.out.println(uploadPath);
    	        
    	        filePart.write(uploadPath + File.separator + fileName);
    	        
    	        String vueloImageUrl = "vuelos_images/" + fileName;
            }
		} catch (NombreVRepetidoExce_Exception e) {
			request.setAttribute("error", "NombreVRepetidoExce");
		} catch (RVNoExisteExce_Exception e) {
			request.setAttribute("error", "error");
		} catch(DateTimeParseException e) {
			request.setAttribute("error", "error");
		} catch(NumberFormatException e) {
			request.setAttribute("error", "error");
		}
        
        HttpSession session = request.getSession(false);
        DataRutaVueloArray vuelos = new DataRutaVueloArray();
    	try {
    		vuelos = publicadorRutasVuelo.listarRutasVueloPorAerolinea(session.getAttribute("usuario").toString().trim());
    		
		} catch (UsuarioNoExisteExce_Exception e1) {
			request.setAttribute("error", "RVNoExisteExce");
		}
    	List<DataRutaVuelo> rutas = vuelos.getItem();

    	request.setAttribute("rutas",rutas);
        
        request.getRequestDispatcher("/alta-vuelo.jsp").forward(request, response);
    	
    }




}