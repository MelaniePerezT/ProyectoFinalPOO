package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.DiscoDuro;
import logico.Microprocesador;
import logico.MotherBoard;
import logico.Persona;
import logico.Producto;
import logico.Proveedor;
import logico.Tienda;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu clientesMenu = new JMenu("Clientes");
		clientesMenu.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		menuBar.add(clientesMenu);
		
		JMenuItem buttonRegistrarCliente = new JMenuItem("Registrar");
		buttonRegistrarCliente.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/ClienteRegistrar1.png")));
		buttonRegistrarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		buttonRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente ventanita = new RegistrarCliente(null);
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		clientesMenu.add(buttonRegistrarCliente);
		
		JMenuItem muenoListaClientes = new JMenuItem("Lista Clientes");
		muenoListaClientes.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/listaClientes.png")));
		muenoListaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		muenoListaClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes ventanita = new ListaClientes();
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		clientesMenu.add(muenoListaClientes);
		
		JMenu menuEmpleado = new JMenu("Empleados");
		menuEmpleado.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		menuBar.add(menuEmpleado);
		
		JMenuItem menubuttonRegistrarEmpleado = new JMenuItem("Registrar");
		menubuttonRegistrarEmpleado.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/EmpleadoRegistrar.png")));
		menubuttonRegistrarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarEmpleado ventanita = new RegistrarEmpleado(null);
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		menubuttonRegistrarEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuEmpleado.add(menubuttonRegistrarEmpleado);
		
		JMenuItem menubuttonListaEMpleado = new JMenuItem("Lista Empleados");
		menubuttonListaEMpleado.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/listaClientes.png")));
		menubuttonListaEMpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaEmpleados ventanita = new ListaEmpleados();
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		menubuttonListaEMpleado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuEmpleado.add(menubuttonListaEMpleado);
		
		JMenu menuProveedor = new JMenu("Proveedores");
		menuProveedor.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		menuBar.add(menuProveedor);
		
		JMenuItem menuRegistrarProveedor = new JMenuItem("Registrar");
		menuRegistrarProveedor.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/supplier2.png")));
		menuRegistrarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarProveedor ventanita = new RegistrarProveedor(null);
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		menuRegistrarProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuProveedor.add(menuRegistrarProveedor);
		
		JMenuItem menuListaProveedor = new JMenuItem("Lista Proveedores");
		menuListaProveedor.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/listaClientes.png")));
		menuListaProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaProveedores ventanita = new ListaProveedores();
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		menuListaProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuProveedor.add(menuListaProveedor);
		
		JMenu mnNewMenu = new JMenu("Producto");
		mnNewMenu.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cantProveedores = 0;	
				for (Persona persona : Tienda.getInstance().getListaPersonas()) {
					if (persona instanceof Proveedor) {
						cantProveedores++;
					}
				}	
				if (cantProveedores == 0) {
					JOptionPane.showMessageDialog(null, "Operación errónea. Debe haber por lo menos un proveedor registrado!", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					RegistrarProducto registrarProducto = new RegistrarProducto(null);
					registrarProducto.setVisible(true);	
				}
				
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Lista");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarProducto lista= new ListarProducto();
				lista.setModal(true);
				lista.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_2 = new JMenu("Facturas");
		mnNewMenu_2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Registrar");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoFactura tipo= new TipoFactura();
				tipo.setModal(true);
				tipo.setVisible(true);
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Lista");
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_2.add(mntmNewMenuItem_8);
		
		JMenu mnNewMenu_1 = new JMenu("Administracion");
		mnNewMenu_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sobre nosotros");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "Ambar Nicole Torres Viamonte, Luis Reynaldo Colon Garcia y Melanie Perez Trinidad";
				   JOptionPane.showMessageDialog(null, mensaje, "Desarrolladores", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Producto Favorito");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Producto pro=Tienda.getInstance().productoFavorito();
				String tipo;
				if(pro instanceof MotherBoard)
					tipo="MotherBoard";
				else if(pro instanceof Microprocesador)
					tipo="Microprocesador";
				else if(pro instanceof DiscoDuro)
					tipo="DiscoDuro";
				else
					tipo="Memoria Ram";
				String cad = "ID" + pro.getId()+ ", Tipo: " + tipo;
				
				JOptionPane.showMessageDialog(null,cad , "Producto mas vendido", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Disponibilidad");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String cad = (String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto:", "Mensaje", JOptionPane.QUESTION_MESSAGE);
                  if (cad != null) {
                	  boolean hay=Tienda.getInstance().disponibleProducto(cad);
                  	
                	  if(hay)
                		  JOptionPane.showMessageDialog(null, "Quedan ejemplares disponibles", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                	  else
                		  JOptionPane.showMessageDialog(null, "No quedan ejemplares disponibles", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                	  
                  } 
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cantidad de un producto");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String cad = (String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto:", "Mensaje", JOptionPane.QUESTION_MESSAGE);
                 if (cad != null) {
               	  Producto pro=Tienda.getInstance().buscarProductoId(cad);
                 	
               	  if(pro!=null)
               		  JOptionPane.showMessageDialog(null, "Quedan "+pro.getCantDisponible(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
               	  else
               		  JOptionPane.showMessageDialog(null, "El producto no existe", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
               	  
                 } 
				
			}
		});
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Inventario general");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int [] array =new int[4];
				array=Tienda.getInstance().cantInventario();
				String pres = "MotherBoard: " + array[0] + ", DiscoDuro: " + array[1] + ", MemoriaRam: " + array[2]+ ", Microprocesador: " + array[3];
				JOptionPane.showMessageDialog(null,pres , "Inventario", JOptionPane.INFORMATION_MESSAGE);


			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
