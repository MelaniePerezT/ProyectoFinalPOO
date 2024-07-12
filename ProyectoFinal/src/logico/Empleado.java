package logico;

public class Empleado extends Persona {
	
	private boolean empleadoMes;
	private int cantVentas;
	private float comisionVentas;
	private float salario; /*Nota: preguntar sobre la utilidad de esta variable*/
	public boolean isEmpleadoMes() {
		return empleadoMes;
	}
	public void setEmpleadoMes(boolean empleadoMes) {
		this.empleadoMes = empleadoMes;
	}
	public int getCantVentas() {
		return cantVentas;
	}
	public void setCantVentas(int cantVentas) {
		this.cantVentas = cantVentas;
	}
	public float getComisionVentas() {
		return comisionVentas;
	}
	public void setComisionVentas(float comisionVentas) {
		this.comisionVentas = comisionVentas;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public float calcularSalario() {
		return comisionVentas * cantVentas;
	}
	
	
	
	
	public Empleado(String nombre, int edad, String cedula, String correo,
			float comisionVentas) {
		super(nombre, edad, cedula, correo);
		super.id = Tienda.getInstance().generarIdEmpleado();
		this.empleadoMes = false;/*Empiece asi por default*/
		this.cantVentas = 0;/*Empiece asi por default*/
		this.comisionVentas = comisionVentas;
		//this.salario = calcularSalario();
	}

}
