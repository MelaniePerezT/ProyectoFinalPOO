package logico;

import java.util.ArrayList;

import javax.swing.JOptionPane;



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
	public Factura buscarFacturaId(String idFactura) {
		Factura factura=null;
		boolean encontrado = false;
		int i = 0;
			while (!encontrado && i < listaFacturas.size()) {
				if(listaFacturas.get(i).getId().equalsIgnoreCase(idFactura)){
					encontrado = true;
					factura= listaFacturas.get(i);
				}
				i++;
		}
		
		return factura;
	}
	
	public Producto buscarProductoId(String idProducto) {
		Producto producto=null;
		boolean encontrado = false;
		int i = 0;
			while (!encontrado && i < listaProductos.size()) {
				if(listaProductos.get(i).getId().equalsIgnoreCase(idProducto)){
					encontrado = true;
					producto= listaProductos.get(i);
				}
				i++;
		}
		
		return producto;
	}
	public Persona buscarPersonaId(String idPersona) {
		Persona persona=null;
		boolean encontrado = false;
		int i = 0;
			while (!encontrado && i < listaPersonas.size()) {
				if(listaPersonas.get(i).getId().equalsIgnoreCase(idPersona)){
					encontrado = true;
					persona= listaPersonas.get(i);
				}
				i++;
		}
		
		return persona;
	}
	
	public int buscarPersonaByIdgetIndex(String idPersona) {
		int persona = -1;
		boolean encontrado  = false;
		int i =0;
		while (!encontrado && i < listaPersonas.size()) {
			if(listaPersonas.get(i).getId().equalsIgnoreCase(idPersona)){
				persona = i;
				encontrado = true;
			}
			i++;
		}

		return persona;
	}

	public void updatePersona(Persona persona) {
		int index = buscarPersonaByIdgetIndex(persona.getId());
		if(index!= -1){
			listaPersonas.set(index, persona);
		}
		
	}

	public void eliminarPersona(String idPersonaSeleccionada) {
		Persona aux = buscarPersonaId(idPersonaSeleccionada);
		if(aux!=null){
			listaPersonas.remove(aux);
		}
		
	}

	public void VerSiClienteVIP() { /*Nota: Lo hice de manera en que se llame esta funcion para actualizar a TODOS los Clientes de la lista*/
		
		for (Persona cliente : listaPersonas) {
			
			if(cliente instanceof Cliente) {
				
				int cantidadVentas = ((Cliente) cliente).getCantVentas();
				
				if(cantidadVentas > 5) {
					((Cliente) cliente).setClasificacion('V');
					
				}
			}
			
		}
		
	}
	
	public float calcularSalarioEmpleado(Empleado empleado) {
		float totalSalario =0;
		totalSalario  = empleado.getComisionVentas() * empleado.getCantVentas();
		return totalSalario;
	}

	public int buscarProductoByIdgetIndex(String idProducto) {
	    int index = -1;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < listaProductos.size()) {
	        if (listaProductos.get(i).getId().equalsIgnoreCase(idProducto)) {
	            index = i;
	            encontrado = true;
	        }
	        i++;
	    }
	    return index;
	}

	public void updateProducto(Producto producto) {
	    int index = buscarProductoByIdgetIndex(producto.getId());
	    if (index != -1) {
	        listaProductos.set(index, producto);
	    }
	}

	public void eliminarProducto(String idProducto) {
	    Producto aux = buscarProductoId(idProducto);
	    if (aux != null) {
	        listaProductos.remove(aux);
	    }
	}

	public int buscarFacturaByIdgetIndex(String idFactura) {
	    int index = -1;
	    boolean encontrado = false;
	    int i = 0;
	    while (!encontrado && i < listaFacturas.size()) {
	        if (listaFacturas.get(i).getId().equalsIgnoreCase(idFactura)) {
	            index = i;
	            encontrado = true;
	        }
	        i++;
	    }
	    return index;
	}

	public void updateFactura(Factura factura) {
	    int index = buscarFacturaByIdgetIndex(factura.getId());
	    if (index != -1) {
	        listaFacturas.set(index, factura);
	    }
	}

	public void eliminarFactura(String idFactura) {
	    Factura aux = buscarFacturaId(idFactura);
	    if (aux != null) {
	        listaFacturas.remove(aux);
	    }
	}
	
	/*Nota:*/
	
	public boolean alarmaProducto(String idProducto)
	{
		Boolean alarma=false;
		Producto producto=buscarProductoId(idProducto);
		if(producto.cantDisponible<=1)
		{
			alarma=true;
			
		}
		return alarma;
	}

	public float totalVentas()
	{
		float total=0;
		for (Factura fat : listaFacturas) {
			total+=fat.precioTotal();
		}
		return total;
	}
	
	
	
}
