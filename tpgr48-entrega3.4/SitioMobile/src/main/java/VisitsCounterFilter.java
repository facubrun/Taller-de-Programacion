
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.RVNoExisteExce_Exception;


/**
 * Servlet Filter implementation class VisitsCounterFilter
 */
// @WebFilter(servletNames = "DetalleRutaVueloServlet")
@WebFilter(urlPatterns = {"/detalle-ruta-vuelo/*"})
public class VisitsCounterFilter extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpFilter#HttpFilter()
     */
    public VisitsCounterFilter() {
        super();
// TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
// TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
// TODO Auto-generated method stub
        System.out.println("Filter invoked!");

        String ruta = request.getParameter("ruta");
        PublicadorRutasVueloService publicadorRutasVueloService = new PublicadorRutasVueloService();
        PublicadorRutasVuelo publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
        try {
			publicadorRutasVuelo.agregarVisitaRutaVuelo(ruta);
		} catch (RVNoExisteExce_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

// pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }
}
