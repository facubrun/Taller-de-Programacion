
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataCheckIn complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataCheckIn">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="asientosAsignados" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaActual" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="fechaActualFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horaEmbarque" type="{http://servidor/}localTime" minOccurs="0"/>
 *         <element name="horaEmbarqueFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="reserva" type="{http://servidor/}dataReserva" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCheckIn", propOrder = {
    "asientosAsignados",
    "cliente",
    "fechaActual",
    "fechaActualFormatted",
    "horaEmbarque",
    "horaEmbarqueFormatted",
    "reserva"
})
public class DataCheckIn {

    @XmlElement(nillable = true)
    protected List<Integer> asientosAsignados;
    protected String cliente;
    protected LocalDate fechaActual;
    protected String fechaActualFormatted;
    protected LocalTime horaEmbarque;
    protected String horaEmbarqueFormatted;
    protected DataReserva reserva;

    /**
     * Gets the value of the asientosAsignados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the asientosAsignados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAsientosAsignados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     * @return
     *     The value of the asientosAsignados property.
     */
    public List<Integer> getAsientosAsignados() {
        if (asientosAsignados == null) {
            asientosAsignados = new ArrayList<>();
        }
        return this.asientosAsignados;
    }

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaActual.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaActual() {
        return fechaActual;
    }

    /**
     * Define el valor de la propiedad fechaActual.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaActual(LocalDate value) {
        this.fechaActual = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaActualFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaActualFormatted() {
        return fechaActualFormatted;
    }

    /**
     * Define el valor de la propiedad fechaActualFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaActualFormatted(String value) {
        this.fechaActualFormatted = value;
    }

    /**
     * Obtiene el valor de la propiedad horaEmbarque.
     * 
     * @return
     *     possible object is
     *     {@link LocalTime }
     *     
     */
    public LocalTime getHoraEmbarque() {
        return horaEmbarque;
    }

    /**
     * Define el valor de la propiedad horaEmbarque.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalTime }
     *     
     */
    public void setHoraEmbarque(LocalTime value) {
        this.horaEmbarque = value;
    }

    /**
     * Obtiene el valor de la propiedad horaEmbarqueFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraEmbarqueFormatted() {
        return horaEmbarqueFormatted;
    }

    /**
     * Define el valor de la propiedad horaEmbarqueFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraEmbarqueFormatted(String value) {
        this.horaEmbarqueFormatted = value;
    }

    /**
     * Obtiene el valor de la propiedad reserva.
     * 
     * @return
     *     possible object is
     *     {@link DataReserva }
     *     
     */
    public DataReserva getReserva() {
        return reserva;
    }

    /**
     * Define el valor de la propiedad reserva.
     * 
     * @param value
     *     allowed object is
     *     {@link DataReserva }
     *     
     */
    public void setReserva(DataReserva value) {
        this.reserva = value;
    }

}
