package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Producto;
import logico.Tienda;
import java.awt.Font;

public class ListarProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnEliminar;
	private JButton btnCancelar;
	private  DefaultTableModel model;
	private JTable table;
	private  Object[] rows;
	private Producto produc=null;
	private JButton btnMasInformacion;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarProducto dialog = new ListarProducto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarProducto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 8, 414, 217);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 15, 404, 183);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane);
			{
				String headers[]= {"ID","NO. Serie","Cantidad","Proovedor", "Precio"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i = table.getSelectedRow();
						if(i >-1){
							btnEliminar.setEnabled(true);
							btnMasInformacion.setEnabled(true);
							produc = Tienda.getInstance().buscarProductoId(table.getValueAt(i, 0).toString());
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
				
			}
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int op= JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el producto con codigo: "+ produc.getId(), "Eliminar", JOptionPane.WARNING_MESSAGE);
						if(op==JOptionPane.YES_OPTION)
						{
							Tienda.getInstance().eliminarProducto(produc.getId());
							cargarProducto();
						}
					}
				});
				
				btnMasInformacion = new JButton("Mas informacion");
				btnMasInformacion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MasInformacionProducto masIn= new MasInformacionProducto(produc);
						masIn.setModal(true);
						masIn.setVisible(true);
					}
				});
				btnMasInformacion.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				btnMasInformacion.setEnabled(false);
				buttonPane.add(btnMasInformacion);
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		cargarProducto();
	}
	public void cargarProducto() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		int cant = Tienda.getInstance().getListaProductos().size();
		for (int i = 0; i < cant; i++) {
			rows[0] = Tienda.getInstance().getListaProductos().get(i).getId();
			rows[1] = Tienda.getInstance().getListaProductos().get(i).getNumSerie();
			rows[2] = Tienda.getInstance().getListaProductos().get(i).getCantDisponible();
			rows[3]=Tienda.getInstance().getListaProductos().get(i).getProveedor();
			rows[4]=Tienda.getInstance().getListaProductos().get(i).getPrecio();
			model.addRow(rows);
		}
		
	}
}

