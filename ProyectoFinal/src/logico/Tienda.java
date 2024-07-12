package logico;

import java.util.ArrayList;


public class Tienda {
	
	public static int numCliente = 1;
	public static int numEmpleado = 1;
	public static int numProveedor = 1;
	public static int numProducto = 1;
	public static int numFactura =1;
	
	private ArrayList <Persona> listaPersonas;
	private ArrayList <Producto> listaProductos;
	private ArrayList <Factura> listaFacturas;

	
	private static Tienda miTienda = null;

	
	public static Tienda getInstance(){
		if(miTienda == null){
			miTienda = new Tienda();
		}return miTienda;
	}
	
	public String generarIdProducto() {		
		String id = "Producto - " + numProducto;
		numProducto++;
		return id;

	}
	
	public String generarIdCliente() {		
		String id = "Cliente - " + numCliente;
		numCliente++;
		return id;

	}
	
	public String generarIdEmpleado() {		
		String id = "Empleado - " + numEmpleado;
		numEmpleado++;
		return id;

	}
	
	public String generarIdProveedor() {		
		String id = "Proveedor - " + numProveedor;
		numProveedor++;
		return id;

	}
	
	public String generarIdFactura() {		
		String id = "Factura - " + numFactura;
		numFactura++;
		return id;

	}

	public static int getNumCliente() {
		return numCliente;
	}

	public static void setNumCliente(int numCliente) {
		Tienda.numCliente = numCliente;
	}

	public static int getNumEmpleado() {
		return numEmpleado;
	}

	public static void setNumEmpleado(int numEmpleado) {
		Tienda.numEmpleado = numEmpleado;
	}

	public static int getNumProveedor() {
		return numProveedor;
	}

	public static void setNumProveedor(int numProveedor) {
		Tienda.numProveedor = numProveedor;
	}

	public static int getNumProducto() {
		return numProducto;
	}

	public static void setNumProducto(int numProducto) {
		Tienda.numProducto = numProducto;
	}

	public static int getNumFactura() {
		return numFactura;
	}

	public static void setNumFactura(int numFactura) {
		Tienda.numFactura = numFactura;
	}

	public ArrayList<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public ArrayList<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(ArrayList<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public Tienda() {
		super();
		this.listaPersonas = new ArrayList<>();
		this.listaProductos = new ArrayList<>();
		this.listaFacturas = new ArrayList<>();
	}
	
	public boolean RegistrarPersona(Persona newPersona) {
		listaPersonas.add(newPersona);
		return true;
		
	}
	
	public boolean RegistrarProducto(Producto newProducto) {
		listaProductos.add(newProducto);
		return true;
		
	}
	
	public boolean RegistrarFactura(Factura newFactura) {
		listaFacturas.add(newFactura);
		return true;
		
	}
	
	
	
	
}
