package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Tienda;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class InventarioGeneral extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventarioGeneral frame = new InventarioGeneral();
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
	public InventarioGeneral() {
		setTitle("Inventario General");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InventarioGeneral.class.getResource("/Imagenes/InventarioGeneralp.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 394);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JLabel moderboardIcon = new JLabel("");
		JLabel moderboardIcon = new JLabel(new ImageIcon(
			    new ImageIcon(getClass().getResource("/Imagenes/tarjeta-madre.png")).getImage()
			    .getScaledInstance(90, 90, Image.SCALE_SMOOTH))
			);
		moderboardIcon.setBounds(22, 23, 86, 92);
		contentPane.add(moderboardIcon);
		
		//JLabel ssdIcon = new JLabel("");
		JLabel ssdIcon = new JLabel(new ImageIcon(
			    new ImageIcon(getClass().getResource("/Imagenes/disco-ssd.png")).getImage()
			    .getScaledInstance(90, 90, Image.SCALE_SMOOTH))
			);
		ssdIcon.setBounds(22, 192, 86, 92);
		contentPane.add(ssdIcon);
		
		//JLabel ramIcono = new JLabel("");
		JLabel ramIcono = new JLabel(new ImageIcon(
			    new ImageIcon(getClass().getResource("/Imagenes/memoria-ram.png")).getImage()
			    .getScaledInstance(90, 90, Image.SCALE_SMOOTH))
			);
		ramIcono.setBounds(217, 192, 86, 92);
		contentPane.add(ramIcono);
		
		//JLabel microprocesadorIcono = new JLabel("");
		JLabel microprocesadorIcono = new JLabel(new ImageIcon(
			    new ImageIcon(getClass().getResource("/Imagenes/microprocesador.png")).getImage()
			    .getScaledInstance(90, 90, Image.SCALE_SMOOTH))
			);
		microprocesadorIcono.setBounds(217, 23, 86, 92);
		contentPane.add(microprocesadorIcono);
		
		int [] array =new int[4];
		array=Tienda.getInstance().cantInventario();
		String motherboardtexto = "Hay: "+array[0]+"\nMotherboard";
		String ssdtexto ="Hay: "+array[1]+"\nDiscos de Almacenamiento";
		String ramtexto = "Hay: "+array[2]+" RAM";
		String microprocesadortexto = "Hay: "+array[3]+"\nMicroprocesadores";
		 /*Nota: lo del <html> es para que se formatee como uno y me deje usar textos con saltos de línea y "<br>" es salto de línea en HTML*/
		JLabel motherboardTxt = new JLabel("<html>" + motherboardtexto.replace("\n", "<br>") + "</html>");
		motherboardTxt.setHorizontalAlignment(SwingConstants.CENTER);
		motherboardTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		
		motherboardTxt.setBounds(22, 126, 86, 39);
		contentPane.add(motherboardTxt);
		
		JLabel microprocesadorTxt = new JLabel("<html>" + microprocesadortexto.replace("\n", "<br>") + "</html>");
		microprocesadorTxt.setHorizontalAlignment(SwingConstants.CENTER);
		microprocesadorTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		microprocesadorTxt.setBounds(201, 126, 135, 39);
		contentPane.add(microprocesadorTxt);
		
		JLabel ssdTxt = new JLabel("<html>" + ssdtexto.replace("\n", "<br>") + "</html>");
		ssdTxt.setHorizontalAlignment(SwingConstants.CENTER);
		ssdTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		ssdTxt.setBounds(22, 295, 112, 49);
		contentPane.add(ssdTxt);
		
		JLabel ramTxt = new JLabel(ramtexto);
		ramTxt.setHorizontalAlignment(SwingConstants.CENTER);
		ramTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		ramTxt.setBounds(217, 295, 86, 39);
		contentPane.add(ramTxt);
		setLocationRelativeTo(null);
	}
}
