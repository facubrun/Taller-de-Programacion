import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.java.dev.jaxb.array.StringArray;
import servidor.*;

@WebServlet("/alta-ruta-vuelo")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class AltaRutaVueloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PublicadorRutasVueloService publicadorRutasVueloService;
    private PublicadorRutasVuelo publicadorRutasVuelo;

    public AltaRutaVueloServlet() {
        super();
        
        try {
        	publicadorRutasVueloService = new PublicadorRutasVueloService();
            publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> ciudades = publicadorRutasVuelo.listarCiudadesDisponibles().getItem();
        request.setAttribute("ciudades", ciudades);

        List<Categoria> categorias = publicadorRutasVuelo.listarCategorias().getItem();
        ArrayList<String> nombresCategorias = new ArrayList<>();
        for (Categoria cat : categorias) {
            nombresCategorias.add(cat.getNombre());
        }
        request.setAttribute("nombresCategorias", nombresCategorias);

        request.setAttribute("error", null);
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            request.getRequestDispatcher("login").forward(request, response);
        } else if ("cliente".equals(session.getAttribute("tipoUsuario"))) {
            response.sendRedirect("unauthorized.jsp"); // Redirige a una página de error si es necesario
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("alta-ruta-vuelo.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	List<String> ciudades = publicadorRutasVuelo.listarCiudadesDisponibles().getItem();
        request.setAttribute("ciudades", ciudades);

        List<Categoria> categorias = publicadorRutasVuelo.listarCategorias().getItem();
        ArrayList<String> nombresCategorias = new ArrayList<>();
        for (Categoria cat : categorias) {
            nombresCategorias.add(cat.getNombre());
        }
        request.setAttribute("nombresCategorias", nombresCategorias);
        
        String nombre = request.getParameter("nombre");
        String descripcionCorta = request.getParameter("descripcion_corta");
        String descripcion = request.getParameter("descripcion");
        String horaParam = request.getParameter("hora");
        String costoTuristaParam = request.getParameter("costo_turista");
        String costoEjecutivoParam = request.getParameter("costo_ejecutivo");
        String costoExtraParam = request.getParameter("costo_extra");
        String ciudadOrigen = request.getParameter("ciudad_origen");
        String ciudadDestino = request.getParameter("ciudad_destino");
        String fechaAltaParam = request.getParameter("fecha_alta");

        LocalTime hora = (horaParam != null && !horaParam.isEmpty()) ? LocalTime.parse(horaParam) : null;
        float costoTurista = (costoTuristaParam != null && !costoTuristaParam.isEmpty()) ? Float.parseFloat(costoTuristaParam) : 0.0f;
        float costoEjecutivo = (costoEjecutivoParam != null && !costoEjecutivoParam.isEmpty()) ? Float.parseFloat(costoEjecutivoParam) : 0.0f;
        float costoExtra = (costoExtraParam != null && !costoExtraParam.isEmpty()) ? Float.parseFloat(costoExtraParam) : 0.0f;
        LocalDate fechaAlta = (fechaAltaParam != null && !fechaAltaParam.isEmpty()) ? LocalDate.parse(fechaAltaParam) : null;

        HttpSession session = request.getSession();
        String usuario = (String) session.getAttribute("usuario");

        Part filePart = request.getPart("imagen");
        String rutaImagen = "";
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : "";
            fileName = nombre + fileExtension;

            String uploadPath = getServletContext().getRealPath("") + File.separator + "utils" + File.separator + "images" + File.separator + "vuelos_images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs(); 

            filePart.write(uploadPath + File.separator + fileName);

            rutaImagen = "vuelos_images/" + fileName;
        }
        
        String video = request.getParameter("video");
        video = (video != null && !video.trim().isEmpty()) ? video : "";

        String[] categoriasSeleccionadas = request.getParameterValues("categorias");
        List<String> categories = (categoriasSeleccionadas != null) ? Arrays.asList(categoriasSeleccionadas) : new ArrayList<>();
        StringArray array = new StringArray();
        array.setItem(categories);

        try {
        	if (!video.isEmpty() && !esURLValidaDeYouTube(video)) {
                throw new Exception("La URL del video no es válida o no corresponde a YouTube.");
            }
            if (!ciudadDestino.equals(ciudadOrigen)) {
                publicadorRutasVuelo.registrarRutaDeVuelo(nombre, descripcion, hora.toString(), LocalDate.now().toString(),
                        ciudadOrigen, ciudadDestino, costoEjecutivo, costoTurista, costoExtra, array, usuario,
                        rutaImagen, descripcionCorta, servidor.EstadoRutaVuelo.Ingresada.toString(), video);
                request.setAttribute("success", "Ruta de vuelo agregada con éxito");
            } else {
                throw new Exception("La ciudad de origen y destino no pueden ser la misma");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/alta-ruta-vuelo.jsp");
        dispatcher.forward(request, response);
    }
    
    private boolean esURLValidaDeYouTube(String url) {
        String patronYouTube = "^(https?://)?(www\\.)?(youtube\\.com|youtu\\.be)/(watch\\?v=|embed/|v/)?[a-zA-Z0-9_-]+(&.*)?$";
        return url.matches(patronYouTube);
    }
}
