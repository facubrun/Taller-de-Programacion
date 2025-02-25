
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataVuelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataVuelo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fecha" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="duracion" type="{http://servidor/}localTime" minOccurs="0"/>
 *         <element name="maxTurista" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="maxEjecutivo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="reservas" type="{http://servidor/}dataReserva" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="duracionFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAltaFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataVuelo", propOrder = {
    "nombre",
    "fecha",
    "duracion",
    "maxTurista",
    "maxEjecutivo",
    "fechaAlta",
    "reservas",
    "duracionFormatted",
    "fechaAltaFormatted",
    "fechaFormatted"
})
public class DataVuelo {

    protected String nombre;
    protected LocalDate fecha;
    protected LocalTime duracion;
    protected Integer maxTurista;
    protected Integer maxEjecutivo;
    protected LocalDate fechaAlta;
    protected List<DataReserva> reservas;
    protected String duracionFormatted;
    protected String fechaAltaFormatted;
    protected String fechaFormatted;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad duracion.
     * 
     * @return
     *     possible object is
     *     {@link LocalTime }
     *     
     */
    public LocalTime getDuracion() {
        return duracion;
    }

    /**
     * Define el valor de la propiedad duracion.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalTime }
     *     
     */
    public void setDuracion(LocalTime value) {
        this.duracion = value;
    }

    /**
     * Obtiene el valor de la propiedad maxTurista.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxTurista() {
        return maxTurista;
    }

    /**
     * Define el valor de la propiedad maxTurista.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxTurista(Integer value) {
        this.maxTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad maxEjecutivo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxEjecutivo() {
        return maxEjecutivo;
    }

    /**
     * Define el valor de la propiedad maxEjecutivo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxEjecutivo(Integer value) {
        this.maxEjecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaAlta(LocalDate value) {
        this.fechaAlta = value;
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
     * Obtiene el valor de la propiedad duracionFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuracionFormatted() {
        return duracionFormatted;
    }

    /**
     * Define el valor de la propiedad duracionFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuracionFormatted(String value) {
        this.duracionFormatted = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAltaFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAltaFormatted() {
        return fechaAltaFormatted;
    }

    /**
     * Define el valor de la propiedad fechaAltaFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAltaFormatted(String value) {
        this.fechaAltaFormatted = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFormatted() {
        return fechaFormatted;
    }

    /**
     * Define el valor de la propiedad fechaFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFormatted(String value) {
        this.fechaFormatted = value;
    }

}
