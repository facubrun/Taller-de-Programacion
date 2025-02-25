
package servidor;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "PublicadorUsuariosService", targetNamespace = "http://servidor/")
public class PublicadorUsuariosService
    extends Service
{

	private final static QName PUBLICADORUSUARIOSSERVICE_QNAME = new QName("http://servidor/", "PublicadorUsuariosService");

    public PublicadorUsuariosService() throws MalformedURLException {
        super(getDynamicWsdlUrl(), PUBLICADORUSUARIOSSERVICE_QNAME);
    }

    public PublicadorUsuariosService(WebServiceFeature... features) throws MalformedURLException {
        super(getDynamicWsdlUrl(), PUBLICADORUSUARIOSSERVICE_QNAME, features);
    }

    private static URL getDynamicWsdlUrl() {
        Properties properties = new Properties();
        try {
            String configPath = System.getProperty("user.home") + "/.volandoUy/config.properties";
            properties.load(new FileInputStream(configPath));

            String hostIP = properties.getProperty("hostIP", "localhost");
            String hostPort = properties.getProperty("hostPortUsuario", "9100");
            String wsdlPath = "/PublicadorUsuario?wsdl";

            // Construir la URL dinámica
            String wsdlUrl = "http://" + hostIP + ":" + hostPort + wsdlPath;
            return new URL(wsdlUrl);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la URL del WSDL desde config.properties", e);
        }
    }

    public PublicadorUsuarios getPublicadorUsuariosPort() {
        return super.getPort(new QName("http://servidor/", "PublicadorUsuariosPort"), PublicadorUsuarios.class);
    }

    public PublicadorUsuarios getPublicadorUsuariosPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servidor/", "PublicadorUsuariosPort"), PublicadorUsuarios.class, features);
    }

}
