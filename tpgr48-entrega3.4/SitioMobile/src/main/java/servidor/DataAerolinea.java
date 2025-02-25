
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataAerolinea complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataAerolinea">
 *   <complexContent>
 *     <extension base="{http://servidor/}dataUsuario">
 *       <sequence>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="web" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="rutasVuelo" type="{http://servidor/}dataRutaVuelo" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataAerolinea", propOrder = {
    "descripcion",
    "web",
    "rutasVuelo"
})
public class DataAerolinea
    extends DataUsuario
{

    protected String descripcion;
    protected String web;
    @XmlElement(nillable = true)
    protected List<DataRutaVuelo> rutasVuelo;

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad web.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeb() {
        return web;
    }

    /**
     * Define el valor de la propiedad web.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeb(String value) {
        this.web = value;
    }

    /**
     * Gets the value of the rutasVuelo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the rutasVuelo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRutasVuelo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataRutaVuelo }
     * 
     * 
     * @return
     *     The value of the rutasVuelo property.
     */
    public List<DataRutaVuelo> getRutasVuelo() {
        if (rutasVuelo == null) {
            rutasVuelo = new ArrayList<>();
        }
        return this.rutasVuelo;
    }

}
