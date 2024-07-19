package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Microprocesador;
import logico.MotherBoard;
import logico.Producto;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MasInformacionProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtID;
	private JTextField txtNumSerie;
	private JTextField txtMarca;
	private JTextField txtProovedor;
	private JTextField txtCantidad;
	private JTextField txtPrecio;
	private JTextField txtModeloMicro;
	private JTextField txtSocketMicro;
	private JTextField txtVeProMicro;
	private JTextField txtModeloMother;
	private JTextField txtTipoRamMother;
	private JTextField txtSocketMother;
	private JTextField txtDiscoMother;
	private JPanel pnlMicro;
	private JPanel pnlMother;
	private JTextField txtCantRam;
	private JTextField txtTipoRam;
	private JTextField txtConexionDisco;
	private JTextField txtModeloDisco;
	private JTextField txtCapAlmDisco;
	private JPanel pnlDisco;
	private JPanel pnlRam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MasInformacionProducto dialog = new MasInformacionProducto(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param producto 
	 */
	public MasInformacionProducto(Producto producto) {
		setTitle("Mas informacion");
		setBounds(100, 100, 449, 298);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 414, 120);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Datos Generales");
				lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
				lblNewLabel.setBounds(135, 7, 192, 14);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("ID:");
				lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(10, 33, 46, 14);
				panel.add(lblNewLabel_1);
			}
			{
				txtID = new JTextField();
				txtID.setEnabled(false);
				txtID.setBounds(76, 32, 86, 20);
				panel.add(txtID);
				txtID.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Num. Serie:");
				lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(172, 31, 97, 19);
				panel.add(lblNewLabel_2);
			}
			{
				txtNumSerie = new JTextField();
				txtNumSerie.setEnabled(false);
				txtNumSerie.setBounds(257, 32, 147, 20);
				panel.add(txtNumSerie);
				txtNumSerie.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Marca:");
				lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_3.setBounds(10, 64, 46, 14);
				panel.add(lblNewLabel_3);
			}
			{
				txtMarca = new JTextField();
				txtMarca.setEnabled(false);
				txtMarca.setBounds(76, 61, 86, 20);
				panel.add(txtMarca);
				txtMarca.setColumns(10);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Prooverdor");
				lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_4.setBounds(172, 64, 87, 14);
				panel.add(lblNewLabel_4);
			}
			{
				txtProovedor = new JTextField();
				txtProovedor.setEnabled(false);
				txtProovedor.setBounds(258, 61, 146, 20);
				panel.add(txtProovedor);
				txtProovedor.setColumns(10);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Cantidad:");
				lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_5.setBounds(10, 90, 69, 14);
				panel.add(lblNewLabel_5);
			}
			{
				txtCantidad = new JTextField();
				txtCantidad.setEnabled(false);
				txtCantidad.setBounds(76, 92, 86, 20);
				panel.add(txtCantidad);
				txtCantidad.setColumns(10);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Precio:");
				lblNewLabel_6.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_6.setBounds(172, 97, 46, 14);
				panel.add(lblNewLabel_6);
			}
			{
				txtPrecio = new JTextField();
				txtPrecio.setEnabled(false);
				txtPrecio.setBounds(258, 92, 146, 20);
				panel.add(txtPrecio);
				txtPrecio.setColumns(10);
			}
		}
		{
			pnlMicro = new JPanel();
			pnlMicro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlMicro.setBounds(10, 142, 415, 79);
			contentPanel.add(pnlMicro);
			pnlMicro.setLayout(null);
			{
				JLabel lblNewLabel_7 = new JLabel("Datos Microprocesador");
				lblNewLabel_7.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
				lblNewLabel_7.setBounds(124, 8, 192, 14);
				pnlMicro.add(lblNewLabel_7);
			}
			{
				JLabel lblNewLabel_8 = new JLabel("Modelo:");
				lblNewLabel_8.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_8.setBounds(20, 31, 60, 14);
				pnlMicro.add(lblNewLabel_8);
			}
			{
				txtModeloMicro = new JTextField();
				txtModeloMicro.setEnabled(false);
				txtModeloMicro.setBounds(87, 30, 123, 20);
				pnlMicro.add(txtModeloMicro);
				txtModeloMicro.setColumns(10);
			}
			{
				JLabel lblNewLabel_9 = new JLabel("Socket:");
				lblNewLabel_9.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_9.setBounds(220, 33, 60, 14);
				pnlMicro.add(lblNewLabel_9);
			}
			{
				txtSocketMicro = new JTextField();
				txtSocketMicro.setEnabled(false);
				txtSocketMicro.setBounds(282, 30, 123, 20);
				pnlMicro.add(txtSocketMicro);
				txtSocketMicro.setColumns(10);
			}
			{
				JLabel lblNewLabel_10 = new JLabel("Velocidad de Procesamiento:");
				lblNewLabel_10.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_10.setBounds(20, 54, 200, 14);
				pnlMicro.add(lblNewLabel_10);
			}
			{
				txtVeProMicro = new JTextField();
				txtVeProMicro.setEnabled(false);
				txtVeProMicro.setBounds(218, 53, 187, 20);
				pnlMicro.add(txtVeProMicro);
				txtVeProMicro.setColumns(10);
			}
		}
		{
			pnlMother = new JPanel();
			pnlMother.setBounds(10, 142, 415, 79);
			contentPanel.add(pnlMother);
			pnlMother.setLayout(null);
			{
				JLabel lblNewLabel_11 = new JLabel("Datos Motherboard");
				lblNewLabel_11.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
				lblNewLabel_11.setBounds(119, 5, 200, 14);
				pnlMother.add(lblNewLabel_11);
			}
			{
				JLabel lblNewLabel_12 = new JLabel("Modelo:");
				lblNewLabel_12.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_12.setBounds(20, 28, 60, 14);
				pnlMother.add(lblNewLabel_12);
			}
			{
				txtModeloMother = new JTextField();
				txtModeloMother.setEnabled(false);
				txtModeloMother.setBounds(87, 25, 123, 20);
				pnlMother.add(txtModeloMother);
				txtModeloMother.setColumns(10);
			}
			{
				JLabel lblNewLabel_13 = new JLabel("Tipo Ram:");
				lblNewLabel_13.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_13.setBounds(20, 54, 86, 14);
				pnlMother.add(lblNewLabel_13);
			}
			{
				txtTipoRamMother = new JTextField();
				txtTipoRamMother.setEnabled(false);
				txtTipoRamMother.setBounds(87, 51, 86, 20);
				pnlMother.add(txtTipoRamMother);
				txtTipoRamMother.setColumns(10);
			}
			{
				JLabel lblNewLabel_14 = new JLabel("Socket:");
				lblNewLabel_14.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_14.setBounds(220, 30, 60, 14);
				pnlMother.add(lblNewLabel_14);
			}
			{
				txtSocketMother = new JTextField();
				txtSocketMother.setEnabled(false);
				txtSocketMother.setBounds(290, 27, 115, 20);
				pnlMother.add(txtSocketMother);
				txtSocketMother.setColumns(10);
			}
			{
				JLabel lblNewLabel_15 = new JLabel("Discos Aceptados:");
				lblNewLabel_15.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_15.setBounds(180, 56, 118, 14);
				pnlMother.add(lblNewLabel_15);
			}
			{
				txtDiscoMother = new JTextField();
				txtDiscoMother.setEnabled(false);
				txtDiscoMother.setBounds(300, 51, 105, 20);
				pnlMother.add(txtDiscoMother);
				txtDiscoMother.setColumns(10);
			}
		}
		{
			pnlRam = new JPanel();
			pnlRam.setBounds(10, 142, 415, 79);
			contentPanel.add(pnlRam);
			pnlRam.setLayout(null);
			{
				JLabel lblNewLabel_16 = new JLabel("Datos Memoria RAM");
				lblNewLabel_16.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
				lblNewLabel_16.setBounds(131, 5, 200, 14);
				pnlRam.add(lblNewLabel_16);
			}
			{
				JLabel lblNewLabel_17 = new JLabel("Cantidad:");
				lblNewLabel_17.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_17.setBounds(10, 36, 65, 14);
				pnlRam.add(lblNewLabel_17);
			}
			{
				txtCantRam = new JTextField();
				txtCantRam.setEnabled(false);
				txtCantRam.setBounds(85, 33, 86, 20);
				pnlRam.add(txtCantRam);
				txtCantRam.setColumns(10);
			}
			{
				JLabel lblNewLabel_18 = new JLabel("Tipo:");
				lblNewLabel_18.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_18.setBounds(243, 36, 46, 14);
				pnlRam.add(lblNewLabel_18);
			}
			{
				txtTipoRam = new JTextField();
				txtTipoRam.setEnabled(false);
				txtTipoRam.setBounds(299, 33, 86, 20);
				pnlRam.add(txtTipoRam);
				txtTipoRam.setColumns(10);
			}
		}
		{
			pnlDisco = new JPanel();
			pnlDisco.setBounds(10, 142, 415, 79);
			contentPanel.add(pnlDisco);
			pnlDisco.setLayout(null);
			{
				JLabel lblNewLabel_19 = new JLabel("Datos Disco Duro");
				lblNewLabel_19.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
				lblNewLabel_19.setBounds(126, 5, 200, 14);
				pnlDisco.add(lblNewLabel_19);
			}
			{
				JLabel lblNewLabel_20 = new JLabel("Modelo:");
				lblNewLabel_20.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_20.setBounds(20, 27, 100, 14);
				pnlDisco.add(lblNewLabel_20);
			}
			{
				JLabel lblNewLabel_21 = new JLabel("Tipo Conexion:");
				lblNewLabel_21.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_21.setBounds(220, 27, 100, 14);
				pnlDisco.add(lblNewLabel_21);
			}
			{
				JLabel lblNewLabel_22 = new JLabel("Capacidad de Almacenamiento:");
				lblNewLabel_22.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				lblNewLabel_22.setBounds(20, 54, 200, 14);
				pnlDisco.add(lblNewLabel_22);
			}
			{
				txtConexionDisco = new JTextField();
				txtConexionDisco.setEnabled(false);
				txtConexionDisco.setBounds(319, 26, 86, 20);
				pnlDisco.add(txtConexionDisco);
				txtConexionDisco.setColumns(10);
			}
			{
				txtModeloDisco = new JTextField();
				txtModeloDisco.setEnabled(false);
				txtModeloDisco.setBounds(87, 26, 123, 20);
				pnlDisco.add(txtModeloDisco);
				txtModeloDisco.setColumns(10);
			}
			{
				txtCapAlmDisco = new JTextField();
				txtCapAlmDisco.setEnabled(false);
				txtCapAlmDisco.setBounds(230, 53, 175, 20);
				pnlDisco.add(txtCapAlmDisco);
				txtCapAlmDisco.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnVisualizar = new JButton("Visualizar el Producto");
				btnVisualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						VisualizarProducto visualiza =new VisualizarProducto(producto);
						visualiza.setVisible(true);
					}
				});
				btnVisualizar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				btnVisualizar.setActionCommand("OK");
				buttonPane.add(btnVisualizar);
				getRootPane().setDefaultButton(btnVisualizar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		load(producto);
	}
	private void load(Producto producto) {
	    if (producto != null) {
	        
	        txtID.setText(producto.getId());
	        txtNumSerie.setText(producto.getNumSerie());
	        txtMarca.setText(producto.getMarca());
	        txtProovedor.setText(String.valueOf(producto.getProveedor()));
	        txtCantidad.setText(String.valueOf(producto.getCantDisponible()));
	        txtPrecio.setText(String.valueOf(producto.getPrecio()));
	        
	        if (producto instanceof Microprocesador) {
	            Microprocesador micro = (Microprocesador) producto;
	            pnlDisco.setEnabled(false);
	            pnlMicro.setEnabled(true);
	            pnlMother.setEnabled(false);
	            pnlRam.setEnabled(false);
	            txtModeloMicro.setText(micro.getModelo());
	            txtSocketMicro.setText(micro.getSocket());
	            txtVeProMicro.setText(String.valueOf(micro.getVelocidadProcesamiento()));
	        } else if (producto instanceof MotherBoard) {
	        	MotherBoard mother = (MotherBoard) producto;
	            pnlDisco.setVisible(false);
	            pnlMicro.setVisible(false);
	            pnlMother.setVisible(true);
	            pnlRam.setVisible(false);
	            txtModeloMother.setText(mother.getModelo());
	            txtTipoRamMother.setText(mother.getTipoRam());
	            txtSocketMother.setText(mother.getTipoSocket());
	            String discosAceptados = String.join(", ", mother.getListaDiscoDuroAceptados());
	            txtDiscoMother.setText(discosAceptados);
	        } else if (producto instanceof MemoriaRam) {
	            MemoriaRam ram = (MemoriaRam) producto;
	            pnlDisco.setVisible(false);
	            pnlMicro.setVisible(false);
	            pnlMother.setVisible(false);
	            pnlRam.setVisible(true);
	            txtCantRam.setText(String.valueOf(ram.getCantMemoria()));
	            txtTipoRam.setText(ram.getTipoMemoria());
	        } else if (producto instanceof DiscoDuro) {
	            DiscoDuro disco = (DiscoDuro) producto;
	            pnlDisco.setVisible(true);
	            pnlMicro.setVisible(false);
	            pnlMother.setVisible(false);
	            pnlRam.setVisible(false);
	            txtConexionDisco.setText(disco.getTipoConexion());
	            txtModeloDisco.setText(disco.getModelo());
	            txtCapAlmDisco.setText(String.valueOf(disco.getCapacidad()));
	        }
	    
	    }
	}
}

