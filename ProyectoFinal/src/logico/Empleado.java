package logico;

public class Empleado extends Persona {
	
	private boolean empleadoMes;
	private int cantVentas;
	private float comisionVentas;
	private float salario;
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
	public Empleado(String nombre, int edad, String cedula, String correo, boolean empleadoMes, int cantVentas,
			float comisionVentas, float salario) {
		super(nombre, edad, cedula, correo);
		super.id = Tienda.getInstance().generarIdEmpleado();
		this.empleadoMes = empleadoMes;
		this.cantVentas = cantVentas;
		this.comisionVentas = comisionVentas;
		this.salario = salario;
	}

}
