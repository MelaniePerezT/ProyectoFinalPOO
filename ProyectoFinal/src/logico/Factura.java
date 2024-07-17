package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	protected String id;
	protected LocalDate fechaFactura;
	protected ArrayList <Producto> productosFacturados;
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
	public Factura(String id, LocalDate fechaFactura, ArrayList<Producto> productosFacturados) {
		super();
		this.id = Tienda.getInstance().generarIdFactura();
		this.fechaFactura = fechaFactura;
		this.productosFacturados = productosFacturados;
	}

	public float precioTotal()
	{
		float precio=0;
		for (Producto producto : productosFacturados) {
			precio+=producto.precioVenta();
		}
		return precio;
	}
}
