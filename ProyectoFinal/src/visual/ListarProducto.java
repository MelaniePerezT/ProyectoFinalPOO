package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import logico.Empleado;
import logico.Persona;
import logico.Producto;
import logico.Tienda;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ListarProducto extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel tableModel;
	private  Object[] rows;
	private JButton btnVerMas;
	private JButton botonActualizar;

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
		setTitle("Lista de Productos");
		setBounds(100, 100, 919, 505);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null); /*Poner en el centro*/

		String[] columnas = {"ID","NO. Serie","Cantidad","Proovedor","Precio"};
		tableModel = new DefaultTableModel(columnas, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    btnVerMas.setEnabled(true);
                    botonActualizar.setEnabled(true);
                }
            }
        });

		JScrollPane scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
			}
			{
				botonActualizar = new JButton("Actualizar");
				botonActualizar.setEnabled(false);
				botonActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String id = (String) tableModel.getValueAt(selectedRow, 0);
                            Producto pro = (Producto) Tienda.getInstance().buscarProductoId(id);
                            if (pro != null) {
                                RegistrarProducto act = new RegistrarProducto(pro);
                                act.setModal(true);
                                act.setVisible(true);
                                
                                cargarProducto();
                            }
                        }
					}
				});
				{
					btnVerMas = new JButton("Mas Informacion");
					btnVerMas.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int selectedRow = table.getSelectedRow();
							if (selectedRow != -1) {
								String id = (String) tableModel.getValueAt(selectedRow, 0);
								Producto pro = (Producto) Tienda.getInstance().buscarProductoId(id);
								MasInformacionProducto mas = new MasInformacionProducto(pro);
								mas.setModal(true);
								mas.setVisible(true);
								dispose();
							}
						}
					});
					btnVerMas.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
					btnVerMas.setEnabled(false);
					buttonPane.add(btnVerMas);
				}
				botonActualizar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				botonActualizar.setActionCommand("OK");
				buttonPane.add(botonActualizar);
				getRootPane().setDefaultButton(botonActualizar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(e -> dispose());
				buttonPane.add(cancelButton);
			}
		}
		cargarProducto();
	}

	public void cargarProducto() {
		tableModel.setRowCount(0);
		rows = new Object[tableModel.getColumnCount()];
		int cant = Tienda.getInstance().getListaProductos().size();
		for (int i = 0; i < cant; i++) {
			rows[0] = Tienda.getInstance().getListaProductos().get(i).getId();
			rows[1] = Tienda.getInstance().getListaProductos().get(i).getNumSerie();
			rows[2] = Tienda.getInstance().getListaProductos().get(i).getCantDisponible();
			rows[3]=Tienda.getInstance().getListaProductos().get(i).getProveedor();
			rows[4]=Tienda.getInstance().getListaProductos().get(i).getPrecio();
			tableModel.addRow(rows);
		}
		
	}
}
