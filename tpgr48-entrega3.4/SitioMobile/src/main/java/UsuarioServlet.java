import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import net.java.dev.jaxb.array.StringArray;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.LocalDate;


import servidor.EmailRepetidoExce_Exception;
import servidor.NicknameRepetidoExce_Exception;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce_Exception;

@WebServlet("/registro_user")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50
)
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public UsuarioServlet() {
        super();
        
		
		try {
			publicadorUsuarioService = new PublicadorUsuariosService();
			publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
			
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("crearUsuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");

        // Validación de contraseñas
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("crearUsuario.jsp").forward(request, response);
            return;
        }

        // Procesar la imagen opcionalmente
        Part filePart = request.getPart("profileImage");
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "utils" + File.separator + "images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            filePart.write(uploadPath + File.separator + fileName);
        }
        String profileImageUrl = fileName != null ? fileName : "";

        if ("cliente".equals(tipo)) {
            String nickname = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String apellido = request.getParameter("apellido");
            String nacimiento = request.getParameter("nacimiento");
            String nacionalidad = request.getParameter("nacionalidad");
            String tipoDocumento = request.getParameter("tipoDocumento");
            String documento = request.getParameter("documento");

            try {
            	publicadorUsuario.crearCliente(nickname, nombre, email, apellido, nacimiento, nacionalidad, tipoDocumento, documento, password, profileImageUrl, new StringArray(), new StringArray());
            } catch (EmailRepetidoExce_Exception | NicknameRepetidoExce_Exception  e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("crearUsuario.jsp").forward(request, response);
                return;
            }

        } else if ("aerolinea".equals(tipo)) {
            String nickname = request.getParameter("nickname");
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String descripcion = request.getParameter("descripcion");
            String link = request.getParameter("link");

            try {
            	publicadorUsuario.crearAerolinea(nickname, nombre, email, descripcion, link, password, profileImageUrl, new StringArray(), new StringArray());
            } catch (EmailRepetidoExce_Exception | NicknameRepetidoExce_Exception e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("crearUsuario.jsp").forward(request, response);
                return;
            }
        }

        try {
            request.setAttribute("clientes", publicadorUsuario.getClientes().getItem());
            request.setAttribute("aerolineas", publicadorUsuario.getAerolineas().getItem());
        } catch (UsuarioNoExisteExce_Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
