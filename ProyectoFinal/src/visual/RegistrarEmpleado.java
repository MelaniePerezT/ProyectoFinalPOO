package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empleado;
import logico.Tienda;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class RegistrarEmpleado extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField idField;
    private JTextField nombreField;
    private JTextField cedulaField;
    private JTextField correoField;
    private JSpinner edadSpinner;
    private JSpinner Comsionspinner; // Usar este campo en lugar de la nueva instancia en el constructor
    private Empleado empleado; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
        	RegistrarEmpleado dialog = new RegistrarEmpleado(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegistrarEmpleado(Empleado empleado) {
        this.empleado = empleado; 
        
        setTitle("Registrar Empleado");
        setBounds(100, 100, 447, 320);
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
            idTxt.setBounds(29, 82, 46, 14);
            contentPanel.add(idTxt);
        }
        {
            JLabel nombreTxt = new JLabel("Nombre:");
            nombreTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            nombreTxt.setBounds(29, 120, 57, 14);
            contentPanel.add(nombreTxt);
        }
        {
            JLabel cedulaTxt = new JLabel("Cedula:");
            cedulaTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            cedulaTxt.setBounds(29, 151, 57, 14);
            contentPanel.add(cedulaTxt);
        }
        {
            JLabel correoTxt = new JLabel("Correo:");
            correoTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            correoTxt.setBounds(29, 180, 57, 14);
            contentPanel.add(correoTxt);
        }
        {
            JLabel edadTxt = new JLabel("Edad:");
            edadTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
            edadTxt.setBounds(235, 82, 46, 14);
            contentPanel.add(edadTxt);
        }
        
        idField = new JTextField();
        idField.setEditable(false);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        idField.setBounds(67, 78, 111, 20);
        contentPanel.add(idField);
        idField.setColumns(10);
        
        nombreField = new JTextField();
        nombreField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nombreField.setColumns(10);
        nombreField.setBounds(121, 114, 247, 20);
        contentPanel.add(nombreField);
        {
            cedulaField = new JTextField();
            cedulaField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            cedulaField.setColumns(10);
            cedulaField.setBounds(121, 145, 247, 20);
            contentPanel.add(cedulaField);
        }
        {
            correoField = new JTextField();
            correoField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            correoField.setColumns(10);
            correoField.setBounds(121, 174, 247, 20);
            contentPanel.add(correoField);
        }
        
        edadSpinner = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1)); 
        edadSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        edadSpinner.setBounds(294, 78, 86, 20);
        contentPanel.add(edadSpinner);
        
        JLabel comisionTxt = new JLabel("Comision por venta:");
        comisionTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        comisionTxt.setBounds(54, 209, 149, 16);
        contentPanel.add(comisionTxt);
        
        Comsionspinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Usar la instancia de clase
        Comsionspinner.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        Comsionspinner.setBounds(213, 205, 86, 20);
        contentPanel.add(Comsionspinner);
        
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
                        int comision = (int) Comsionspinner.getValue();                        
                        
                        if (empleado ==  null) {
                        	Empleado newEmpleado = new Empleado(nombreApellido, edad, cedula, correo, (float) (comision / 100.0));
                            Tienda.getInstance().RegistrarPersona(newEmpleado);
                            JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Empleado Creado", JOptionPane.INFORMATION_MESSAGE);
                            clear();
                        } else {
                        	empleado.setNombre(nombreApellido);
                        	empleado.setCedula(cedula);
                        	empleado.setCorreo(correo);
                        	empleado.setEdad(edad);
                        	empleado.setComisionVentas((float) (comision / 100.0));
                            Tienda.getInstance().updatePersona(empleado);
                            dispose();
                        }
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
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

        if (empleado != null) {
            cargarDatosCliente();
        } else {
            idField.setText("Empleado - " + Tienda.numEmpleado);
        }
    }

    private void cargarDatosCliente() {
        idField.setText(empleado.getId());
        nombreField.setText(empleado.getNombre());
        cedulaField.setText(empleado.getCedula());
        correoField.setText(empleado.getCorreo());
        edadSpinner.setValue(empleado.getEdad());
        Comsionspinner.setValue((int) (empleado.getComisionVentas() * 100)); 
    }
    
    private void clear() { 
        idField.setText("Empleado - " + Tienda.numEmpleado);
        nombreField.setText("");
        cedulaField.setText("");
        correoField.setText("");
        edadSpinner.setValue(18);
        Comsionspinner.setValue(0);
    }
}
