package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Persona;
import logico.Tienda;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
		buttonRegistrarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		buttonRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente ventanita = new RegistrarCliente(null);
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		clientesMenu.add(buttonRegistrarCliente);
		
		JMenuItem muenoListaClientes = new JMenuItem("Lista ");
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
		menubuttonRegistrarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarEmpleado ventanita = new RegistrarEmpleado(null);
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		menubuttonRegistrarEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuEmpleado.add(menubuttonRegistrarEmpleado);
		
		JMenuItem menubuttonListaEMpleado = new JMenuItem("Lista ");
		menubuttonListaEMpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaEmpleados ventanita = new ListaEmpleados();
				ventanita.setModal(true);
				ventanita.setVisible(true);
			}
		});
		menubuttonListaEMpleado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuEmpleado.add(menubuttonListaEMpleado);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
