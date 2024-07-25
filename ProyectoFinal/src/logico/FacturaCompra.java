package logico;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class FacturaCompra extends Factura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Proveedor proveedor;
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public FacturaCompra(String id, LocalDate fechaFactura, ArrayList<Producto> productosFacturados,
			Proveedor proveedor,int CantidadxProducto) {
		super(id, fechaFactura, productosFacturados,CantidadxProducto);
		this.proveedor = proveedor;
	
	}

}
