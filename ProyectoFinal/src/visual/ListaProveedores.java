package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logico.Persona;
import logico.Proveedor;
import logico.Tienda;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ListaProveedores extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListaProveedores dialog = new ListaProveedores();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListaProveedores() {
    	setFont(new Font("Bahnschrift", Font.PLAIN, 13));
        setTitle("Lista de Proveedores");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null); /*Poner en el centro*/

        String[] columnas = {"ID", "Nombre", "Cedula", "Correo", "Empresa"};
        tableModel = new DefaultTableModel(columnas, 0);
        table = new JTable(tableModel);
        table.setBorder(null);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setFont(new Font("Bahnschrift", Font.BOLD, 14));
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        cargarDatosProveedor();

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                {
                	JButton eliminarbotton = new JButton("Eliminar");
                	eliminarbotton.setEnabled(false);
                	eliminarbotton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                	eliminarbotton.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                                String id = (String) tableModel.getValueAt(selectedRow, 0);
                                eliminarProveedorSeleccionado();
                                actualizarTabla();
                            }
                		}
                	});
                	JButton bottonActualizar = new JButton("Actualizar");
                	bottonActualizar.setEnabled(false);
                	bottonActualizar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                	bottonActualizar.addActionListener(new ActionListener() {
                	    public void actionPerformed(ActionEvent e) {
                	        int selectedRow = table.getSelectedRow();
                	        if (selectedRow != -1) {
                	            String id = (String) tableModel.getValueAt(selectedRow, 0);
                	            Proveedor proveedor = (Proveedor) Tienda.getInstance().buscarPersonaId(id);
                	            if (proveedor != null) {
                	                RegistrarProveedor registrarProveedorDialog = new RegistrarProveedor(proveedor);
                	                registrarProveedorDialog.setModal(true);
                	                registrarProveedorDialog.setVisible(true);
                	                
                	                actualizarTabla();
                	            }
                	        }
                	    }
                	});
                	bottonActualizar.setActionCommand("OK");
                	buttonPane.add(bottonActualizar);
                	getRootPane().setDefaultButton(bottonActualizar);
                	buttonPane.add(eliminarbotton);
                }
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                cancelButton.addActionListener(e -> dispose());
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    private void cargarDatosProveedor() {
        ArrayList<Persona> listaPersonas = Tienda.getInstance().getListaPersonas();
        for (Persona persona : listaPersonas) {
            if (persona instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) persona;
                Object[] row = {
                    proveedor.getId(),
                    proveedor.getNombre(),
                    proveedor.getCedula(),
                    proveedor.getCorreo(),
                    proveedor.getEmpresa()
                };
                tableModel.addRow(row);
            }
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0); 
        cargarDatosProveedor(); 
    }
    
    private void eliminarProveedorSeleccionado() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String idProveedor = (String) tableModel.getValueAt(selectedRow, 0);
            int confirmacion = JOptionPane.showConfirmDialog(this,"¿Estas seguro de que deseas eliminar este proveedor?","Confirmacion de eliminacion",JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                Tienda.getInstance().eliminarPersona(idProveedor);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Eliminacion cancelada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor para eliminar.");
        }
    }


}
