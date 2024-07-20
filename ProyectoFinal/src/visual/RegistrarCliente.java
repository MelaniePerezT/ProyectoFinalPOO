package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

public class RegistrarCliente extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField idField;
    private JTextField nombreField;
    private JTextField cedulaField;
    private JTextField correoField;
    private JSpinner edadSpinner; 
    private Cliente cliente; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegistrarCliente dialog = new RegistrarCliente(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegistrarCliente(Cliente cliente) {
        this.cliente = cliente; 
        
        setTitle("Registrar Cliente");
        setBounds(100, 100, 447, 264);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null); /*Poner en el centro*/
        
        {
            JLabel lblNewLabel = new JLabel("Ingrese los datos:");
            lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
            lblNewLabel.setBounds(132, 11, 182, 25);
            contentPanel.add(lblNewLabel);
        }
        {
            JLabel idTxt = new JLabel("ID: ");
            idTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            idTxt.setBounds(35, 51, 46, 14);
            contentPanel.add(idTxt);
        }
        {
            JLabel nombreTxt = new JLabel("Nombre:");
            nombreTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            nombreTxt.setBounds(35, 89, 57, 14);
            contentPanel.add(nombreTxt);
        }
        {
            JLabel cedulaTxt = new JLabel("Cedula:");
            cedulaTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            cedulaTxt.setBounds(35, 120, 57, 14);
            contentPanel.add(cedulaTxt);
        }
        {
            JLabel correoTxt = new JLabel("Correo:");
            correoTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            correoTxt.setBounds(35, 149, 57, 14);
            contentPanel.add(correoTxt);
        }
        {
            JLabel edadTxt = new JLabel("Edad:");
            edadTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            edadTxt.setBounds(216, 51, 46, 14);
            contentPanel.add(edadTxt);
        }
        
        idField = new JTextField();
        idField.setEditable(false);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        idField.setBounds(78, 47, 86, 20);
        contentPanel.add(idField);
        idField.setColumns(10);
        
        nombreField = new JTextField();
        nombreField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nombreField.setColumns(10);
        nombreField.setBounds(127, 83, 247, 20);
        contentPanel.add(nombreField);
        {
            cedulaField = new JTextField();
            cedulaField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            cedulaField.setColumns(10);
            cedulaField.setBounds(127, 114, 247, 20);
            contentPanel.add(cedulaField);
        }
        {
            correoField = new JTextField();
            correoField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            correoField.setColumns(10);
            correoField.setBounds(127, 143, 247, 20);
            contentPanel.add(correoField);
        }
        
        edadSpinner = new JSpinner(new SpinnerNumberModel(10, 10, 100, 1)); 
        edadSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        edadSpinner.setBounds(272, 47, 86, 20);
        contentPanel.add(edadSpinner);
        
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Registrar");
                okButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
						if (nombreField.getText().isEmpty() || cedulaField.getText().isEmpty() || correoField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Operación errónea. Todos los campos deben de estar llenos!", "Error", JOptionPane.WARNING_MESSAGE);
							return;
						}
                    	
                        String nombreApellido = nombreField.getText();
                        String cedula = cedulaField.getText();
                        String correo = correoField.getText();
                        int edad = (int) edadSpinner.getValue();
                        
                        
                        if (cliente ==  null) {
                            Cliente newCliente = new Cliente(nombreApellido, edad, cedula, correo);
                            Tienda.getInstance().RegistrarPersona(newCliente);
                            JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Cliente Creado", JOptionPane.INFORMATION_MESSAGE);
                            clear();
                        } else {
                            cliente.setNombre(nombreApellido);
                            cliente.setCedula(cedula);
                            cliente.setCorreo(correo);
                            cliente.setEdad(edad);
                            Tienda.getInstance().updatePersona(cliente);
                            dispose();
                        }
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }

        if (cliente != null) {
            cargarDatosCliente();
        } else {
            idField.setText("Cliente - " + Tienda.numCliente);
        }
    }

    private void cargarDatosCliente() {
        idField.setText(cliente.getId());
        nombreField.setText(cliente.getNombre());
        cedulaField.setText(cliente.getCedula());
        correoField.setText(cliente.getCorreo());
        edadSpinner.setValue(cliente.getEdad());
    }
    
    private void clear() { 
        idField.setText("Cliente - " + Tienda.numCliente);
        nombreField.setText("");
        cedulaField.setText("");
        correoField.setText("");
        edadSpinner.setValue(10);
    }
}
