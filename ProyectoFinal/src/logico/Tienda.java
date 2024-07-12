package logico;

public class Tienda {
	
	public static int numCliente = 1;
	public static int numEmpleado = 1;
	public static int numProveedor = 1;
	private static Tienda miTienda = null;
	
	
	
	public static Tienda getInstance(){
		if(miTienda == null){
			miTienda = new Tienda();
		}return miTienda;
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
}
