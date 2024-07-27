package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Tienda;
import logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	private String codigo = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegUser dialog = new RegUser(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegUser(User usuario) {
		if (usuario != null) {
			setTitle("Modificación de Usuario");
			codigo = usuario.getUserName();
		} else {
			setTitle("Registro de Usuario");
		}
		setBounds(100, 100, 450, 228);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setBounds(20, 26, 97, 14);
		contentPanel.add(lblNombreUsuario);

		textField = new JTextField();
		textField.setBounds(20, 49, 127, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administrador", "Vendedor"}));
		comboBox.setBounds(20, 113, 127, 20);
		contentPanel.add(comboBox);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 88, 97, 14);
		contentPanel.add(lblTipo);

		textField_1 = new JTextField();
		textField_1.setBounds(190, 49, 147, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(189, 26, 97, 14);
		contentPanel.add(lblPassword);

		JLabel lblConfirmarPassword = new JLabel("Confirmar Password:");
		lblConfirmarPassword.setBounds(189, 88, 167, 14);
		contentPanel.add(lblConfirmarPassword);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(190, 113, 147, 20);
		contentPanel.add(textField_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				if (usuario != null) {
					okButton.setText("Actualizar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty() || "<Seleccione>".equalsIgnoreCase(comboBox.getSelectedItem().toString())) {
							ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/cancel.png"));
							MensajeAlerta mensajito = new MensajeAlerta(iconito, "Operación errónea.\nTodos los campos deben de estar llenos!");
							mensajito.setModal(true);
							mensajito.setVisible(true);
							return;
						} else {
							if (!textField_1.getText().toString().equalsIgnoreCase(textField_2.getText().toString())) {
								ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/cancel.png"));
								MensajeAlerta mensajito = new MensajeAlerta(iconito, "Operación errónea.\nLas contraseñas no coinciden!");
								mensajito.setModal(true);
								mensajito.setVisible(true);
								return;
							} else {
								if (usuario == null) {
									User user = new User(comboBox.getSelectedItem().toString(),textField.getText(),textField_1.getText());
									Tienda.getInstance().RegistrarUser(user);
									ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/check.png"));
									MensajeAlerta mensajito = new MensajeAlerta(iconito, "Operación satisfactoria.\nUsuario registrado!");
									mensajito.setModal(true);
									mensajito.setVisible(true);
									clean();
								} else {
									usuario.setUserName(textField.getText());
									usuario.setPass(textField_1.getText());
									usuario.setTipo(comboBox.getSelectedItem().toString());

									ImageIcon icono = new ImageIcon(VentanaOpcion.class.getResource("/Imagenes/alert.png"));
									String texto = "¿Seguro desea modificar el usuario: "+ codigo +"?";
									VentanaOpcion ventanita = new VentanaOpcion(icono, texto);
									ventanita.setModal(true);
									ventanita.setVisible(true);
									int option = ventanita.getResultado();	
									if(option == JOptionPane.YES_OPTION){
										Tienda.getInstance().updateUsuario(usuario);
										ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/check.png"));
										MensajeAlerta mensajito = new MensajeAlerta(iconito, "Operación satisfactoria.\nUsuario modificado!");
										mensajito.setModal(true);
										mensajito.setVisible(true);
										dispose();
									}
								}
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (usuario == null) {
							ImageIcon icono = new ImageIcon(VentanaOpcion.class.getResource("/Imagenes/alert.png"));
							String texto = "¿Seguro desea cancelar el registro del usuario en curso?";
							VentanaOpcion ventanita = new VentanaOpcion(icono, texto);
							ventanita.setModal(true);
							ventanita.setVisible(true);
							int option = ventanita.getResultado();			
							if(option == JOptionPane.YES_OPTION){
								dispose();
							}				
						}
						else {
							ImageIcon icono = new ImageIcon(VentanaOpcion.class.getResource("/Imagenes/alert.png"));
							String texto = "¿Seguro desea cancelar la modificación del usuario: "+ codigo +"?";
							VentanaOpcion ventanita = new VentanaOpcion(icono, texto);
							ventanita.setModal(true);
							ventanita.setVisible(true);
							int option = ventanita.getResultado();
							if(option == JOptionPane.YES_OPTION){
								dispose();
							}
						}
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
		cargarUsuario(usuario);
	}

	private void cargarUsuario(User user) {
		if (user != null) {
			textField.setText(user.getUserName());
			textField_1.setText(user.getPass());
			
			int i = 0;
			boolean encontrado = false;
			while (!encontrado && i < comboBox.getItemCount()) {
				if (user.getTipo().equalsIgnoreCase(comboBox.getItemAt(i).toString()))
				{
					comboBox.setSelectedIndex(i); 
					encontrado = true;
				}
				i++;
			}
			
			textField_2.setText(user.getPass());
		}
	}

	private void clean() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		comboBox.setSelectedItem(0);
	}
}
