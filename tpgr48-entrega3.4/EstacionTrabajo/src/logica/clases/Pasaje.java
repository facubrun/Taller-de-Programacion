package logica.clases;

public class Pasaje {
    private Reserva reserva;
    private String nombre;
    private String apellido;
    private Integer asientoAsig;

    public Pasaje() {}

    public Pasaje(Reserva reserva) {
        this.reserva = reserva;
        this.asientoAsig = null;
    }

    public Pasaje(Reserva reserva, String nombre, String apellido) {
        this.reserva = reserva;
        this.nombre = nombre;
        this.apellido = apellido;
        this.asientoAsig = null;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Reserva getReserva() {
        return this.reserva;
    }
    
    public Integer getAsientoAsig() {
    	return this.asientoAsig;
    }
    
    public void setAsientoAsig(Integer asientoAsig) {
    	this.asientoAsig = asientoAsig;
    }
}
