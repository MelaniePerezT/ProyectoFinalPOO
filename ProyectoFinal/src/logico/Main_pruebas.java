package logico;

public class Main_pruebas {

	public static void main(String[] args) {
		 Tienda miTienda = Tienda.getInstance();

	        
	        Persona cliente1 = new Cliente("Ambar Torres", 20, "40247211929", "ambar@gmail.com");
	        miTienda.RegistrarPersona(cliente1);
	        Persona empleado1 = new Empleado("Luis Reynaldo", 18, "40245484712", "reynaldo@gmail.com", (float) 0.25);
	        miTienda.RegistrarPersona(empleado1);
	        Persona proveedor1 = new Proveedor("Melanie Perez", 19, "40154874693", "melanie@gmail.com", "NVIDIA", null);
	        miTienda.RegistrarPersona(proveedor1);
	        for (Persona persona : miTienda.getListaPersonas()) {
	            System.out.println(persona.id +" "+ persona.nombre);
	        }
		
	}

}
