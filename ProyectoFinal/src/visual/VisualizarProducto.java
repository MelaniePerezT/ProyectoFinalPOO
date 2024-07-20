package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Microprocesador;
import logico.MotherBoard;
import logico.Producto;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class VisualizarProducto extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizarProducto frame = new VisualizarProducto(null);
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
	public VisualizarProducto(Producto producto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 595, 400);
		contentPane.add(panel);
		panel.setLayout(null);

		if(producto instanceof Microprocesador)
		{

			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(VisualizarProducto.class.getResource("/Imagenes/_ae4f232a-2ab0-4837-9577-63a581ef9885.gif")));
			lblNewLabel.setBounds(-82, 0, 721, 400);
			panel.add(lblNewLabel);
		}

		if(producto instanceof MemoriaRam)
		{
			JLabel lblNewLabel2 = new JLabel("");
			lblNewLabel2.setIcon(new ImageIcon(VisualizarProducto.class.getResource("/Imagenes/memoria ram.jpg")));
			lblNewLabel2.setBounds(-82, 0, 721, 400);
			panel.add(lblNewLabel2);
		}
		if(producto instanceof MotherBoard)
		{

			JLabel lblNewLabel3 = new JLabel("");
			lblNewLabel3.setIcon(new ImageIcon(VisualizarProducto.class.getResource("/Imagenes/motherboard.jpg")));
			lblNewLabel3.setBounds(-82, 0, 721, 400);
			panel.add(lblNewLabel3);
		}

		if(producto instanceof DiscoDuro)
		{

			JLabel lblNewLabel4 = new JLabel("");
			lblNewLabel4.setIcon(new ImageIcon(VisualizarProducto.class.getResource("/Imagenes/Disco duro.jpg")));
			lblNewLabel4.setBounds(-82, 0, 721, 400);
			panel.add(lblNewLabel4);
		}
	}

}
