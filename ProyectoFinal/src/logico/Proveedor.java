package logico;

import java.util.ArrayList;

public class Proveedor extends Persona {
	private String empresa;
	private ArrayList <Producto> productos; 
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public Proveedor(String nombre, int edad, String cedula, String correo, String empresa,
			ArrayList<Producto> productos) {
		super(nombre, edad, cedula, correo);
		super.id = Tienda.getInstance().generarIdProveedor();
		this.empresa = empresa;
		this.productos = productos;
	}
	
}
