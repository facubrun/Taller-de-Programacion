
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataCliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataCliente">
 *   <complexContent>
 *     <extension base="{http://servidor/}dataUsuario">
 *       <sequence>
 *         <element name="documento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="apellido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nacimiento" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="tipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="reservas" type="{http://servidor/}dataReserva" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="checkIns" type="{http://servidor/}dataCheckIn" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="nacimientoFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCliente", propOrder = {
    "documento",
    "apellido",
    "nacimiento",
    "tipoDocumento",
    "nacionalidad",
    "reservas",
    "checkIns",
    "nacimientoFormatted"
})
public class DataCliente
    extends DataUsuario
{

    protected String documento;
    protected String apellido;
    protected LocalDate nacimiento;
    protected String tipoDocumento;
    protected String nacionalidad;
    @XmlElement(nillable = true)
    protected List<DataReserva> reservas;
    @XmlElement(nillable = true)
    protected List<DataCheckIn> checkIns;
    protected String nacimientoFormatted;

    /**
     * Obtiene el valor de la propiedad documento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Define el valor de la propiedad documento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumento(String value) {
        this.documento = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Define el valor de la propiedad apellido.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido(String value) {
        this.apellido = value;
    }

    /**
     * Obtiene el valor de la propiedad nacimiento.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getNacimiento() {
        return nacimiento;
    }

    /**
     * Define el valor de la propiedad nacimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setNacimiento(LocalDate value) {
        this.nacimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Define el valor de la propiedad tipoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Gets the value of the reservas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the reservas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataReserva }
     * 
     * 
     * @return
     *     The value of the reservas property.
     */
    public List<DataReserva> getReservas() {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        return this.reservas;
    }

    /**
     * Gets the value of the checkIns property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the checkIns property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCheckIns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataCheckIn }
     * 
     * 
     * @return
     *     The value of the checkIns property.
     */
    public List<DataCheckIn> getCheckIns() {
        if (checkIns == null) {
            checkIns = new ArrayList<>();
        }
        return this.checkIns;
    }

    /**
     * Obtiene el valor de la propiedad nacimientoFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacimientoFormatted() {
        return nacimientoFormatted;
    }

    /**
     * Define el valor de la propiedad nacimientoFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacimientoFormatted(String value) {
        this.nacimientoFormatted = value;
    }

}
