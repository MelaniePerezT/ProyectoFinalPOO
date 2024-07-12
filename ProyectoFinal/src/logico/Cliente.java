package logico;

public class Cliente extends Persona {
	
	private char clasificacion;
	private int cantVentas;
	public char getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(char clasificacion) {
		this.clasificacion = clasificacion;
	}
	public int getCantVentas() {
		return cantVentas;
	}
	public void setCantVentas(int cantVentas) {
		this.cantVentas = cantVentas;
	}
	public Cliente(String nombre, int edad, String cedula, String correo, char clasificacion,
			int cantVentas) {
		super(nombre, edad, cedula, correo);
		super.id = Tienda.getInstance().generarIdCliente();
		this.clasificacion = 'C'; /*Nota: Es para que empiece siempre en Comun*/
		this.cantVentas = cantVentas;
	}
	
	

}
