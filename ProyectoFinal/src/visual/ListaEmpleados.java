package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import logico.Empleado;
import logico.Persona;
import logico.Tienda;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Toolkit;

public class ListaEmpleados extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton botonActualizar;
    private JButton btnEliminar;

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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(ListaEmpleados.class.getResource("/Imagenes/to-do-list.png")));
        setTitle("Lista de Empleados");
        setBounds(100, 100, 919, 505);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(240, 255, 240));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        setLocationRelativeTo(null); /*Poner en el centro*/
        
        Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color CyanClaro =  new Color (222, 249, 196);
		Color FondoClarito = new Color(240, 255, 240);

        String[] columnas = {"ID", "Nombre", "Cedula", "Correo", "Comision por Ventas", "Empleado del Mes"};
        tableModel = new DefaultTableModel(columnas, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        table.setBackground(new Color(240, 255, 240));
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);

        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    botonActualizar.setEnabled(true);
                    btnEliminar.setEnabled(true);
                } else {
                    botonActualizar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        cargarDatosEmpleado();

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(new Color(240, 255, 240));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                botonActualizar = new JButton("Actualizar");
                botonActualizar.setForeground(new Color(255, 255, 255));
                botonActualizar.setEnabled(false);
                botonActualizar.setBackground(CyanMid);
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
                cancelButton.setForeground(new Color(255, 255, 255));
                cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                cancelButton.setActionCommand("Cancel");
                cancelButton.setBackground(CyanMid);
                cancelButton.addActionListener(e -> dispose());
                {
                    btnEliminar = new JButton("Eliminar");
                    btnEliminar.setForeground(new Color(255, 255, 255));
                    btnEliminar.setEnabled(false);
                    btnEliminar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                    btnEliminar.setBackground(new Color(250, 128, 114));
                    btnEliminar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            eliminarEmpleadoSeleccionado();
                        }
                    });
                    buttonPane.add(btnEliminar);
                }
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
            int confirmacion = JOptionPane.showConfirmDialog(this, "�Est�s seguro de que deseas eliminar este empleado?", "Confirmaci�n de eliminaci�n", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                Tienda.getInstance().eliminarPersona(idEmpleado);
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Eliminaci�n cancelada.");
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
