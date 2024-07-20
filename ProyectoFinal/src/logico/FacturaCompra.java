package logico;

import java.time.LocalDate;
import java.util.ArrayList;

public class FacturaCompra extends Factura {
	
	private Proveedor proveedor;
	

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public FacturaCompra(String id, LocalDate fechaFactura, ArrayList<Producto> productosFacturados,
			Proveedor proveedor) {
		super(id, fechaFactura, productosFacturados);
		this.proveedor = proveedor;
	
	}

	
	

}
