package logico;

public class Proveedor extends Persona {
	private String empresa; 
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public Proveedor(String nombre, int edad, String cedula, String correo, String empresa) {
		super(nombre, edad, cedula, correo);
		super.id = Tienda.getInstance().generarIdProveedor();
		this.empresa = empresa;
		
	}
	
}
