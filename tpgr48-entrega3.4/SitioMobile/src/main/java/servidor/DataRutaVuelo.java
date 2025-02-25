
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataRutaVuelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataRutaVuelo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="aerolinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="ciudadDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudadOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="costoEjecutivo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="costoExtra" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="costoTurista" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="descCorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estado" type="{http://servidor/}estadoRutaVuelo" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="fechaAltaFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="hora" type="{http://servidor/}localTime" minOccurs="0"/>
 *         <element name="horaFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="vuelos" type="{http://servidor/}dataVuelo" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataRutaVuelo", propOrder = {
    "aerolinea",
    "categorias",
    "ciudadDestino",
    "ciudadOrigen",
    "costoEjecutivo",
    "costoExtra",
    "costoTurista",
    "descCorta",
    "descripcion",
    "estado",
    "fechaAlta",
    "fechaAltaFormatted",
    "hora",
    "horaFormatted",
    "imagen",
    "nombre",
    "video",
    "vuelos"
})
public class DataRutaVuelo {

    protected String aerolinea;
    @XmlElement(nillable = true)
    protected List<String> categorias;
    protected String ciudadDestino;
    protected String ciudadOrigen;
    protected float costoEjecutivo;
    protected float costoExtra;
    protected float costoTurista;
    protected String descCorta;
    protected String descripcion;
    @XmlSchemaType(name = "string")
    protected EstadoRutaVuelo estado;
    protected LocalDate fechaAlta;
    protected String fechaAltaFormatted;
    protected LocalTime hora;
    protected String horaFormatted;
    protected String imagen;
    protected String nombre;
    protected String video;
    @XmlElement(nillable = true)
    protected List<DataVuelo> vuelos;

    /**
     * Obtiene el valor de la propiedad aerolinea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * Define el valor de la propiedad aerolinea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAerolinea(String value) {
        this.aerolinea = value;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the categorias property.
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
    }

    /**
     * Obtiene el valor de la propiedad ciudadDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadDestino() {
        return ciudadDestino;
    }

    /**
     * Define el valor de la propiedad ciudadDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadDestino(String value) {
        this.ciudadDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    /**
     * Define el valor de la propiedad ciudadOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadOrigen(String value) {
        this.ciudadOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad costoEjecutivo.
     * 
     */
    public float getCostoEjecutivo() {
        return costoEjecutivo;
    }

    /**
     * Define el valor de la propiedad costoEjecutivo.
     * 
     */
    public void setCostoEjecutivo(float value) {
        this.costoEjecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad costoExtra.
     * 
     */
    public float getCostoExtra() {
        return costoExtra;
    }

    /**
     * Define el valor de la propiedad costoExtra.
     * 
     */
    public void setCostoExtra(float value) {
        this.costoExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad costoTurista.
     * 
     */
    public float getCostoTurista() {
        return costoTurista;
    }

    /**
     * Define el valor de la propiedad costoTurista.
     * 
     */
    public void setCostoTurista(float value) {
        this.costoTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad descCorta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescCorta() {
        return descCorta;
    }

    /**
     * Define el valor de la propiedad descCorta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescCorta(String value) {
        this.descCorta = value;
    }

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
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoRutaVuelo }
     *     
     */
    public EstadoRutaVuelo getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoRutaVuelo }
     *     
     */
    public void setEstado(EstadoRutaVuelo value) {
        this.estado = value;
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
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link LocalTime }
     *     
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalTime }
     *     
     */
    public void setHora(LocalTime value) {
        this.hora = value;
    }

    /**
     * Obtiene el valor de la propiedad horaFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraFormatted() {
        return horaFormatted;
    }

    /**
     * Define el valor de la propiedad horaFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraFormatted(String value) {
        this.horaFormatted = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

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
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Define el valor de la propiedad video.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

    /**
     * Gets the value of the vuelos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the vuelos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVuelos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataVuelo }
     * 
     * 
     * @return
     *     The value of the vuelos property.
     */
    public List<DataVuelo> getVuelos() {
        if (vuelos == null) {
            vuelos = new ArrayList<>();
        }
        return this.vuelos;
    }

}
