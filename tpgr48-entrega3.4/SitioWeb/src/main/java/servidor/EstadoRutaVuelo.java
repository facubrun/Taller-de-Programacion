
package servidor;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoRutaVuelo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="estadoRutaVuelo">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Ingresada"/>
 *     <enumeration value="Confirmada"/>
 *     <enumeration value="Rechazada"/>
 *     <enumeration value="Finalizada"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estadoRutaVuelo")
@XmlEnum
public enum EstadoRutaVuelo {

//    @XmlEnumValue("Ingresada")
//    INGRESADA("Ingresada"),
//    @XmlEnumValue("Confirmada")
//    CONFIRMADA("Confirmada"),
//    @XmlEnumValue("Rechazada")
//    RECHAZADA("Rechazada"),
//    @XmlEnumValue("Finalizada")
//    FINALIZADA("Finalizada");
//    private final String value;
//
//    EstadoRutaVuelo(String v) {
//        value = v;
//    }
//
//    public String value() {
//        return value;
//    }
//
//    public static EstadoRutaVuelo fromValue(String v) {
//        for (EstadoRutaVuelo c: EstadoRutaVuelo.values()) {
//            if (c.value.equals(v)) {
//                return c;
//            }
//        }
//        throw new IllegalArgumentException(v);
//    }
	Ingresada,
	Confirmada,
	Rechazada,
	Finalizada;

	public String value() {
      return name();
	}
	
	public static EstadoRutaVuelo fromValue(String v) {
        return valueOf(v);
    }
}
