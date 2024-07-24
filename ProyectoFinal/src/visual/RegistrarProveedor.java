package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import logico.Proveedor;
import logico.Tienda;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

public class RegistrarProveedor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField idField;
	private JTextField nombreField;
	private JTextField cedulaField;
	private JTextField correoField;
	private JTextField empresaField;
	private JSpinner EdadSpinner; 
	private Proveedor proveedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarProveedor dialog = new RegistrarProveedor(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarProveedor(Proveedor proveedor) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarProveedor.class.getResource("/Imagenes/supplier.png")));
		setTitle("Registrar Proveedor");
		this.proveedor = proveedor; 
		setResizable(false);
		
		Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color CyanClaro =  new Color (222, 249, 196);
		Color FondoClarito = new Color(240, 255, 240);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 2, 0, CyanOscuro);
		
		setBounds(100, 100, 441, 295);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null); /*Poner en el centro*/
		
		JLabel label = new JLabel("Ingrese los datos:");
		label.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		label.setBounds(136, 11, 182, 25);
		contentPanel.add(label);
		
		JLabel idTxt = new JLabel("ID: ");
		idTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		idTxt.setBounds(37, 53, 46, 14);
		contentPanel.add(idTxt);
		
		idField = new JTextField();
		idField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(88, 49, 111, 20);
		idField.setBorder(bottomBorder);
		idField.setBackground(CyanClaro);
		contentPanel.add(idField);
		
		JLabel edadTxt = new JLabel("Edad:");
		edadTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		edadTxt.setBounds(230, 49, 46, 20);
		contentPanel.add(edadTxt);
		
		EdadSpinner = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
		EdadSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		EdadSpinner.setBounds(290, 48, 86, 20);
		EdadSpinner.setBorder(bottomBorder);
		/*Nota: esta parte de aqui es para cambiar el Background del spinner*/
		JComponent editor = EdadSpinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
			spinnerEditor.getTextField().setBackground(CyanClaro);}
		contentPanel.add(EdadSpinner);
		
		JLabel nombreTxt = new JLabel("Nombre:");
		nombreTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		nombreTxt.setBounds(37, 84, 57, 14);
		contentPanel.add(nombreTxt);
		
		nombreField = new JTextField();
		nombreField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		nombreField.setColumns(10);
		nombreField.setBounds(129, 78, 247, 20);
		nombreField.setBorder(bottomBorder);
		nombreField.setBackground(CyanClaro);
		contentPanel.add(nombreField);
		
		cedulaField = new JTextField();
		cedulaField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		cedulaField.setColumns(10);
		cedulaField.setBounds(129, 109, 247, 20);
		cedulaField.setBackground(CyanClaro);
		cedulaField.setBorder(bottomBorder);
		contentPanel.add(cedulaField);
		
		JLabel cedulaTxt = new JLabel("Cedula:");
		cedulaTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		cedulaTxt.setBounds(37, 115, 57, 14);
		contentPanel.add(cedulaTxt);
		
		JLabel correoTxt = new JLabel("Correo:");
		correoTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		correoTxt.setBounds(37, 144, 57, 14);
		contentPanel.add(correoTxt);
		
		correoField = new JTextField();
		correoField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		correoField.setColumns(10);
		correoField.setBounds(129, 138, 247, 20);
		correoField.setBackground(CyanClaro);
		correoField.setBorder(bottomBorder);
		contentPanel.add(correoField);
		
		JLabel empresaTxt = new JLabel("Empresa:");
		empresaTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		empresaTxt.setBounds(37, 168, 82, 25);
		contentPanel.add(empresaTxt);
		
		empresaField = new JTextField();
		empresaField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		empresaField.setColumns(10);
		empresaField.setBounds(129, 169, 247, 20);
		empresaField.setBackground(CyanClaro);
		empresaField.setBorder(bottomBorder);
		contentPanel.add(empresaField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setBackground(new Color(240, 255, 240));
			
			JButton okButton = new JButton("Registrar");
			okButton.setForeground(Color.WHITE);
			okButton.setBackground(CyanMid);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (nombreField.getText().isEmpty() || cedulaField.getText().isEmpty() || correoField.getText().isEmpty() || empresaField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Operación errónea. Todos los campos deben de estar llenos!", "Error", JOptionPane.WARNING_MESSAGE);
						return;
					}
					
					String nombreApellido = nombreField.getText();
                    String cedula = cedulaField.getText();
                    String correo = correoField.getText();
                    int edad = (int) EdadSpinner.getValue();
                    String empresa = empresaField.getText();                    
                    
                    if (proveedor == null) {
                        Proveedor newProveedor = new Proveedor(nombreApellido, edad, cedula, correo, empresa);
                        Tienda.getInstance().RegistrarPersona(newProveedor);
                        JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Proveedor Creado", JOptionPane.INFORMATION_MESSAGE);
                        clear();
                    } else {
                    	proveedor.setNombre(nombreApellido);
                    	proveedor.setCedula(cedula);
                    	proveedor.setCorreo(correo);
                    	proveedor.setEdad(edad);
                    	proveedor.setEmpresa(empresa);
                        Tienda.getInstance().updatePersona(proveedor);
                        dispose();
                    }
				}
			});
			okButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setForeground(Color.WHITE);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
			cancelButton.setActionCommand("Cancel");
			cancelButton.setBackground(new Color(250, 128, 114));
			buttonPane.add(cancelButton);
		}
		
		if (proveedor != null) {
			cargarDatosProveedor();
	    } else {
	        idField.setText("Proveedor - " + Tienda.numProveedor);
	    }
	}
	
	private void cargarDatosProveedor() {
        idField.setText(proveedor.getId());
        nombreField.setText(proveedor.getNombre());
        cedulaField.setText(proveedor.getCedula());
        correoField.setText(proveedor.getCorreo());
        empresaField.setText(proveedor.getEmpresa());
        EdadSpinner.setValue(proveedor.getEdad());
    }
    
    private void clear() { 
        idField.setText("Proveedor - " + Tienda.numProveedor);
        nombreField.setText("");
        cedulaField.setText("");
        correoField.setText("");
        empresaField.setText("");
        EdadSpinner.setValue(18);
    }
}
