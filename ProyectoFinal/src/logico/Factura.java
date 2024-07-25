package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Factura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected LocalDate fechaFactura;
	protected int cantidadxProducto;
	protected ArrayList <Producto> productosFacturados;
	private boolean esCombo;
	public String getId() {
		return id;
	}
	
	public LocalDate getFechaFactura() {
		return fechaFactura;
	}
	
	public void setFechaFactura(LocalDate fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	
	public ArrayList<Producto> getProductosFacturados() {
		return productosFacturados;
	}
	
	public void setProductosFacturados(ArrayList<Producto> productosFacturados) {
		this.productosFacturados = productosFacturados;
	}
	
	public Factura(String id, LocalDate fechaFactura, ArrayList<Producto> productosFacturados,int CantidadxProducto) {
		super();
		this.id = Tienda.getInstance().generarIdFactura();
		this.fechaFactura = fechaFactura;
		this.productosFacturados = productosFacturados;
		this.esCombo=false;
		this.cantidadxProducto=CantidadxProducto;
	}
	
	public boolean isEsCombo() {
		return esCombo;
	}
	
	public void setEsCombo(boolean esCombo) {
		this.esCombo = esCombo;
	}
	
	public int getCantidadxProducto() {
		return cantidadxProducto;
	}
	
	public void setCantidadxProducto(int cantidadxProducto) {
		this.cantidadxProducto = cantidadxProducto;
	}

	
}
