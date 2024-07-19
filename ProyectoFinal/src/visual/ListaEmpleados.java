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
import logico.Tienda;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListaEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaEmpleados dialog = new ListaEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaEmpleados() {
		setTitle("Lista de Empleados");
		setBounds(100, 100, 919, 505);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null); /*Poner en el centro*/

		String[] columnas = {"ID", "Nombre", "Cedula", "Correo", "Comision por Ventas", "Empleado del Mes"};
		tableModel = new DefaultTableModel(columnas, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.getTableHeader().setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		cargarDatosEmpleado();

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						eliminarEmpleadoSeleccionado();
					}
				});
				buttonPane.add(btnEliminar);
			}
			{
				JButton botonActualizar = new JButton("Actualizar");
				botonActualizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            String id = (String) tableModel.getValueAt(selectedRow, 0);
                            Empleado empleado = (Empleado) Tienda.getInstance().buscarPersonaId(id);
                            if (empleado != null) {
                                RegistrarEmpleado registrarEmpleadoDialog = new RegistrarEmpleado(empleado);
                                registrarEmpleadoDialog.setModal(true);
                                registrarEmpleadoDialog.setVisible(true);
                                
                                actualizarTabla();
                            }
                        }
					}
				});
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
	}

	private void cargarDatosEmpleado() {
		ArrayList<Persona> listaPersonas = Tienda.getInstance().getListaPersonas();
		for (Persona persona : listaPersonas) {
			if (persona instanceof Empleado) {
				Empleado empleado = (Empleado) persona;
				Object[] row = {
					empleado.getId(),
					empleado.getNombre(),
					empleado.getCedula(),
					empleado.getCorreo(),
					empleado.getComisionVentas(),
					empleado.isEmpleadoMes() ? "S�" : "No"
				};
				tableModel.addRow(row);
			}
		}
	}

	private void eliminarEmpleadoSeleccionado() {
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow >= 0) {
	        String idEmpleado = (String) tableModel.getValueAt(selectedRow, 0);
	        int confirmacion = JOptionPane.showConfirmDialog(this, "�Estas seguro de que deseas eliminar este empleado?","Confirmacion de eliminacion",JOptionPane.YES_NO_OPTION);

	        if (confirmacion == JOptionPane.YES_OPTION) {
	            Tienda.getInstance().eliminarPersona(idEmpleado);
	            tableModel.removeRow(selectedRow);
	            JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(this, "Eliminacion cancelada.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Por favor, seleccione un empleado para eliminar.");
	    }
	}

	
	private void actualizarTabla() {
        tableModel.setRowCount(0); 
        cargarDatosEmpleado();
    }
}
