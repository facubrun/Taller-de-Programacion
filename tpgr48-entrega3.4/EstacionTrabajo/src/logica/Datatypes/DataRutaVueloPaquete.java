
package logica.Datatypes;

public class DataRutaVueloPaquete {

	private DataRutaVuelo dataRV;
	private Integer cantidad;

	public DataRutaVueloPaquete(DataRutaVuelo dataRV, Integer cantidad) {
		this.dataRV = dataRV;
		this.cantidad = cantidad;
	}

	public DataRutaVuelo getDataRV() {
		return this.dataRV;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}
	
	public void setDataRV(DataRutaVuelo dataRV) {
		this.dataRV = dataRV;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
