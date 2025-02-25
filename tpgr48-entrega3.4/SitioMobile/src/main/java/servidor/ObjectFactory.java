
package servidor;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PaqueteExisteExce_QNAME = new QName("http://servidor/", "PaqueteExisteExce");
    private final static QName _RVNoExisteExce_QNAME = new QName("http://servidor/", "RVNoExisteExce");
    private final static QName _VueloNoExisteExce_QNAME = new QName("http://servidor/", "VueloNoExisteExce");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PaqueteExisteExce }
     * 
     * @return
     *     the new instance of {@link PaqueteExisteExce }
     */
    public PaqueteExisteExce createPaqueteExisteExce() {
        return new PaqueteExisteExce();
    }

    /**
     * Create an instance of {@link RVNoExisteExce }
     * 
     * @return
     *     the new instance of {@link RVNoExisteExce }
     */
    public RVNoExisteExce createRVNoExisteExce() {
        return new RVNoExisteExce();
    }

    /**
     * Create an instance of {@link VueloNoExisteExce }
     * 
     * @return
     *     the new instance of {@link VueloNoExisteExce }
     */
    public VueloNoExisteExce createVueloNoExisteExce() {
        return new VueloNoExisteExce();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link DataPaquete }
     * 
     * @return
     *     the new instance of {@link DataPaquete }
     */
    public DataPaquete createDataPaquete() {
        return new DataPaquete();
    }

    /**
     * Create an instance of {@link DataPaqueteArray }
     * 
     * @return
     *     the new instance of {@link DataPaqueteArray }
     */
    public DataPaqueteArray createDataPaqueteArray() {
        return new DataPaqueteArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteExisteExce }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PaqueteExisteExce }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "PaqueteExisteExce")
    public JAXBElement<PaqueteExisteExce> createPaqueteExisteExce(PaqueteExisteExce value) {
        return new JAXBElement<>(_PaqueteExisteExce_QNAME, PaqueteExisteExce.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RVNoExisteExce }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RVNoExisteExce }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "RVNoExisteExce")
    public JAXBElement<RVNoExisteExce> createRVNoExisteExce(RVNoExisteExce value) {
        return new JAXBElement<>(_RVNoExisteExce_QNAME, RVNoExisteExce.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VueloNoExisteExce }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VueloNoExisteExce }{@code >}
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "VueloNoExisteExce")
    public JAXBElement<VueloNoExisteExce> createVueloNoExisteExce(VueloNoExisteExce value) {
        return new JAXBElement<>(_VueloNoExisteExce_QNAME, VueloNoExisteExce.class, null, value);
    }

}
