
package servidor;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataReserva complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataReserva">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nickC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nomV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaReserva" type="{http://servidor/}localDate" minOccurs="0"/>
 *         <element name="tipoAsiento" type="{http://servidor/}tipoAsiento" minOccurs="0"/>
 *         <element name="cantPasajes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="cantExtra" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="costoTotal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         <element name="pasajes" type="{http://servidor/}dataPasajes" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="checkIn" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         <element name="fechaReservaFormatted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="rutaVueloV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudadOrigenV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudadDestinoV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horaV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataReserva", propOrder = {
    "nickC",
    "nomV",
    "fechaReserva",
    "tipoAsiento",
    "cantPasajes",
    "cantExtra",
    "costoTotal",
    "pasajes",
    "checkIn",
    "fechaReservaFormatted",
    "rutaVueloV",
    "ciudadOrigenV",
    "ciudadDestinoV",
    "horaV",
    "fechaV"
})
public class DataReserva {

    protected String nickC;
    protected String nomV;
    protected LocalDate fechaReserva;
    @XmlSchemaType(name = "string")
    protected TipoAsiento tipoAsiento;
    protected Integer cantPasajes;
    protected Integer cantExtra;
    protected float costoTotal;
    protected List<DataPasajes> pasajes;
    protected boolean checkIn;
    protected String fechaReservaFormatted;
    protected String rutaVueloV;
    protected String ciudadOrigenV;
    protected String ciudadDestinoV;
    protected String horaV;
    protected String fechaV;

    /**
     * Obtiene el valor de la propiedad nickC.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNickC() {
        return nickC;
    }

    /**
     * Define el valor de la propiedad nickC.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNickC(String value) {
        this.nickC = value;
    }

    /**
     * Obtiene el valor de la propiedad nomV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomV() {
        return nomV;
    }

    /**
     * Define el valor de la propiedad nomV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomV(String value) {
        this.nomV = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaReserva.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Define el valor de la propiedad fechaReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaReserva(LocalDate value) {
        this.fechaReserva = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAsiento.
     * 
     * @return
     *     possible object is
     *     {@link TipoAsiento }
     *     
     */
    public TipoAsiento getTipoAsiento() {
        return tipoAsiento;
    }

    /**
     * Define el valor de la propiedad tipoAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoAsiento }
     *     
     */
    public void setTipoAsiento(TipoAsiento value) {
        this.tipoAsiento = value;
    }

    /**
     * Obtiene el valor de la propiedad cantPasajes.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantPasajes() {
        return cantPasajes;
    }

    /**
     * Define el valor de la propiedad cantPasajes.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantPasajes(Integer value) {
        this.cantPasajes = value;
    }

    /**
     * Obtiene el valor de la propiedad cantExtra.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantExtra() {
        return cantExtra;
    }

    /**
     * Define el valor de la propiedad cantExtra.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantExtra(Integer value) {
        this.cantExtra = value;
    }

    /**
     * Obtiene el valor de la propiedad costoTotal.
     * 
     */
    public float getCostoTotal() {
        return costoTotal;
    }

    /**
     * Define el valor de la propiedad costoTotal.
     * 
     */
    public void setCostoTotal(float value) {
        this.costoTotal = value;
    }

    /**
     * Gets the value of the pasajes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pasajes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPasajes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPasajes }
     * 
     * 
     * @return
     *     The value of the pasajes property.
     */
    public List<DataPasajes> getPasajes() {
        if (pasajes == null) {
            pasajes = new ArrayList<>();
        }
        return this.pasajes;
    }

    /**
     * Obtiene el valor de la propiedad checkIn.
     * 
     */
    public boolean isCheckIn() {
        return checkIn;
    }

    /**
     * Define el valor de la propiedad checkIn.
     * 
     */
    public void setCheckIn(boolean value) {
        this.checkIn = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaReservaFormatted.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaReservaFormatted() {
        return fechaReservaFormatted;
    }

    /**
     * Define el valor de la propiedad fechaReservaFormatted.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaReservaFormatted(String value) {
        this.fechaReservaFormatted = value;
    }

    /**
     * Obtiene el valor de la propiedad rutaVueloV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaVueloV() {
        return rutaVueloV;
    }

    /**
     * Define el valor de la propiedad rutaVueloV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaVueloV(String value) {
        this.rutaVueloV = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadOrigenV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadOrigenV() {
        return ciudadOrigenV;
    }

    /**
     * Define el valor de la propiedad ciudadOrigenV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadOrigenV(String value) {
        this.ciudadOrigenV = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudadDestinoV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadDestinoV() {
        return ciudadDestinoV;
    }

    /**
     * Define el valor de la propiedad ciudadDestinoV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadDestinoV(String value) {
        this.ciudadDestinoV = value;
    }

    /**
     * Obtiene el valor de la propiedad horaV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraV() {
        return horaV;
    }

    /**
     * Define el valor de la propiedad horaV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraV(String value) {
        this.horaV = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaV.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaV() {
        return fechaV;
    }

    /**
     * Define el valor de la propiedad fechaV.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaV(String value) {
        this.fechaV = value;
    }

}
