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

import logico.Cliente;
import logico.Persona;
import logico.Tienda;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaClientes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListaClientes dialog = new ListaClientes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListaClientes() {
    	setFont(new Font("Bahnschrift", Font.PLAIN, 13));
        setTitle("Lista de Clientes");
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null); /*Poner en el centro*/

        String[] columnas = {"ID", "Nombre", "Cedula", "Correo", "Clasificacion"};
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
        cargarDatosCliente();

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                {
                	JButton botonEliminar = new JButton("Eliminar");
                	botonEliminar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                	botonEliminar.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                                String id = (String) tableModel.getValueAt(selectedRow, 0);
                                eliminarClienteSeleccionado();
                                actualizarTabla();
                            }
                		}
                	});
                	JButton botonActualizar = new JButton("Actualizar");
                	botonActualizar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                	botonActualizar.addActionListener(new ActionListener() {
                	    public void actionPerformed(ActionEvent e) {
                	    	int selectedRow = table.getSelectedRow();
                	        if (selectedRow != -1) {
                	        	String id = (String) tableModel.getValueAt(selectedRow, 0);
                	            Cliente cliente = (Cliente) Tienda.getInstance().buscarPersonaId(id);
                	            if (cliente != null) {
                	                RegistrarCliente registrarClienteDialog = new RegistrarCliente(cliente);
                	                registrarClienteDialog.setModal(true);
                	                registrarClienteDialog.setVisible(true);
                	                actualizarTabla();
                	            }
                	        }
                	    }
                	});
                	botonActualizar.setActionCommand("OK");
                	buttonPane.add(botonActualizar);
                	getRootPane().setDefaultButton(botonActualizar);
                	buttonPane.add(botonEliminar);
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

    private void cargarDatosCliente() {
        ArrayList<Persona> listaPersonas = Tienda.getInstance().getListaPersonas();
        for (Persona persona : listaPersonas) {
            if (persona instanceof Cliente) {
                Cliente cliente = (Cliente) persona;
                Object[] row = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getCedula(),
                    cliente.getCorreo(),
                    cliente.getClasificacion()
                };
                tableModel.addRow(row);
            }
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0); 
        cargarDatosCliente(); 
    }
    
    private void eliminarClienteSeleccionado() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String idCliente = (String) tableModel.getValueAt(selectedRow, 0);
            
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este cliente?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                Tienda.getInstance().eliminarPersona(idCliente);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Eliminación cancelada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente para eliminar.");
        }
    }


}
