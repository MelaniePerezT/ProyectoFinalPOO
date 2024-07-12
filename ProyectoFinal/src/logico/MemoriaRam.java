package logico;

public class MemoriaRam extends Producto {
	
	private int cantMemoria;
	private String tipoMemoria;
	public int getCantMemoria() {
		return cantMemoria;
	}
	public void setCantMemoria(int cantMemoria) {
		this.cantMemoria = cantMemoria;
	}
	public String getTipoMemoria() {
		return tipoMemoria;
	}
	public void setTipoMemoria(String tipoMemoria) {
		this.tipoMemoria = tipoMemoria;
	}
	public MemoriaRam(String numSerie, int cantDisponible, String marca, float precio, int cantMemoria,
			String tipoMemoria) {
		super(numSerie, cantDisponible, marca, precio);
		this.cantMemoria = cantMemoria;
		this.tipoMemoria = tipoMemoria;
	}
	
	

}
