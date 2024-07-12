package logico;

public class Producto {
	
	protected String id;
	protected String numSerie;
	protected int cantDisponible;
	protected boolean estado;
	protected String marca;
	protected float precio;
	public String getId() {
		return id;
	}
	
	public String getNumSerie() {
		return numSerie;
	}
	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}
	public int getCantDisponible() {
		return cantDisponible;
	}
	public void setCantDisponible(int cantDisponible) {
		this.cantDisponible = cantDisponible;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Producto(String numSerie, int cantDisponible, String marca, float precio) {
		super();
		this.id = Tienda.getInstance().generarIdProducto();
		this.numSerie = numSerie;
		this.cantDisponible = cantDisponible;
		this.estado = true;
		this.marca = marca;
		this.precio = precio;
	}
	
	
	

}
