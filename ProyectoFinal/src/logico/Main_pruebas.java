package logico;

public class Main_pruebas {

	public static void main(String[] args) {
		 Tienda miTienda = Tienda.getInstance();

	        
	        Persona cliente1 = new Cliente("Ambar Torres", 20, "40247211929", "ambar@gmail.com");
	        miTienda.RegistrarPersona(cliente1);
	        Persona empleado1 = new Empleado("Luis Reynaldo", 18, "40245484712", "reynaldo@gmail.com", (float) 0.25);
	        miTienda.RegistrarPersona(empleado1);
	        Persona proveedor1 = new Proveedor("Melanie Perez", 19, "40154874693", "melanie@gmail.com", "NVIDIA");
	        miTienda.RegistrarPersona(proveedor1);
	        for (Persona persona : miTienda.getListaPersonas()) {
	            System.out.println(persona.id +" "+ persona.nombre);
	        }
	        
	        
	        Persona buscada = miTienda.buscarPersonaId("Cliente - 1");
	        if (buscada != null) {
	            System.out.println("Persona encontrada: " + buscada.getNombre());
	        } else {
	            System.out.println("Persona no encontrada.");
	        }

	        
	        int indice = miTienda.buscarPersonaByIdgetIndex("Cliente - 1");
	        System.out.println("�ndice de persona: " + indice);

	        

	        miTienda.eliminarPersona("Empleado - 1");
	        Persona eliminada = miTienda.buscarPersonaId("Empleado - 1");
	        if (eliminada == null) {
	            System.out.println("Persona eliminada correctamente.");
	        } else {
	            System.out.println("Error al eliminar persona.");
	        }

	        Cliente clienteVIP = new Cliente("Cliente VIP", 30, "123456789", "vip@gmail.com");
	        clienteVIP.setCantVentas(10);
	        miTienda.RegistrarPersona(clienteVIP);
	        miTienda.VerSiClienteVIP();
	        if (clienteVIP.getClasificacion() == 'V') {
	            System.out.println("Cliente VIP actualizado correctamente.");
	        } else {
	            System.out.println("Error al actualizar cliente VIP.");
	        }
	        MemoriaRam memo= new MemoriaRam("Producto - 1", 1, proveedor1, "JAHA", 120, 64, "Tipo");
	        MemoriaRam memo2= new MemoriaRam("Producto - 2", 1, proveedor1, "JAHA", 120, 64, "Tipo");
	        miTienda.getInstance().RegistrarProducto(memo);
	        miTienda.getInstance().RegistrarProducto(memo2);
	        boolean alarma=miTienda.getInstance().alarmaProducto("Producto - 1");
	        if(alarma)
	        {
	        	 System.out.println("Se estan agotando");
	        }
	        else
	        {
	        	 System.out.println("Quedan disponibles");
	        }
	        
	        
	     
	    }
		

}
