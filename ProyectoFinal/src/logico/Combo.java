package logico;

import java.util.ArrayList;

public class Combo {
	private String nombreCombo;
	private ArrayList<Producto> misProductos;
	private float precio;
	private boolean seleccionado;
	private int CantDisponible;
	public String getNombreCombo() {
		return nombreCombo;
	}
	public void setNombreCombo(String nombreCombo) {
		this.nombreCombo = nombreCombo;
	}
	public ArrayList<Producto> getMisProductos() {
		return misProductos;
	}
	public void setMisProductos(ArrayList<Producto> misProductos) {
		this.misProductos = misProductos;
	}
	public Combo(String nombreCombo, int CantDisponible) {
		super();
		this.nombreCombo = nombreCombo;
		this.misProductos = new ArrayList<>();
		this.seleccionado=false;
		this.CantDisponible= CantDisponible;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public void obtComPrecio()
	{
		float precio=0; 
		setPrecio(precio+=Tienda.getInstance().calculaPrecioProducto(misProductos, "", true));
		
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public int getCantDisponible() {
		return CantDisponible;
	}
	public void setCantDisponible(int cantDisponible) {
		CantDisponible = cantDisponible;
	}
	
}