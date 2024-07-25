package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TipoFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCompra;
	private JButton btnVenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TipoFactura dialog = new TipoFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TipoFactura() {
		Color CyanOscuro = new Color(70, 133, 133);
        Color CyanMid = new Color(80, 180, 152);
        Color CyanClaro =  new Color (222, 249, 196);
        Color FondoClarito = new Color(240, 255, 240);
        
		setBounds(100, 100, 282, 141);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		btnCompra = new JButton("Compra");
		btnCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarFactura reg= new RegistrarFactura(true);
				reg.setModal(true);
				reg.setVisible(true);
				dispose();
			}
		});
		btnCompra.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnCompra.setBounds(10, 59, 89, 23);
		contentPanel.add(btnCompra);
		
		btnVenta = new JButton("Venta");
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarFactura reg= new RegistrarFactura(false);
				reg.setModal(true);
				reg.setVisible(true);
				dispose();
			}
		});
		btnVenta.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnVenta.setBounds(152, 59, 89, 23);
		contentPanel.add(btnVenta);
		
		JLabel lblNewLabel = new JLabel("Tipo de Factura");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblNewLabel.setBounds(67, 11, 129, 23);
		
		
		btnCompra.setBackground(new Color(250, 128, 114));
		btnVenta.setBackground(CyanMid);
		
		
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
	}
}
