package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Microprocesador;
import logico.MotherBoard;
import logico.Producto;
import logico.Tienda;
import logico.User;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class ListadoUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel tableModel;
	private Object[] rows;
	private JButton deleteBtn;
	private JButton updateBtn;
	private JTable table;
	private String codigo = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoUsuarios dialog = new ListadoUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoUsuarios() {
		
		Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color CyanClaro =  new Color (222, 249, 196);
		
		setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoUsuarios.class.getResource("/Imagenes/clienteregistrar.png")));
		setTitle("Listado de Usuarios");
		setBounds(100, 100, 525, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane, BorderLayout.CENTER);

			String[] columnas = {"Usuario", "Contrase�a", "Tipo"};
			tableModel = new DefaultTableModel(columnas, 0);
			table = new JTable(tableModel);
			table.setBorder(null);
			table.setShowVerticalLines(false);
			table.setShowHorizontalLines(false);
			table.setShowGrid(false);
			table.setBackground(new Color(240, 255, 240));
			table.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
			table.getTableHeader().setFont(new Font("Bahnschrift", Font.PLAIN, 14));
			DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
	        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
	        }
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			table.setFillsViewportHeight(true);

			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getSelectedRow();
					if (index >= 0) {
						codigo = new String(table.getValueAt(index, 0).toString());
						deleteBtn.setEnabled(true);
						updateBtn.setEnabled(true);
					}
				}
			});
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(240, 255, 240));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				updateBtn = new JButton("Actualizar");
				updateBtn.setForeground(Color.WHITE);
				updateBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				updateBtn.setBackground(CyanMid);
				updateBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!codigo.equalsIgnoreCase("")){
							User usuario = Tienda.getInstance().buscarUsuarioNombre(codigo);
							if(usuario!= null){
								RegUser regUser = new RegUser(usuario);
								regUser.setVisible(true);
								cargarUsuarios();
								deleteBtn.setEnabled(false);
								updateBtn.setEnabled(false);
							}
						}
					}
				});
				updateBtn.setEnabled(false);
				updateBtn.setActionCommand("OK");
				buttonPane.add(updateBtn);
			}
			{
				deleteBtn = new JButton("Eliminar");
				deleteBtn.setForeground(Color.WHITE);
				deleteBtn.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				deleteBtn.setBackground(new Color(250, 128, 114));
				deleteBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(codigo != ""){
							if (codigo.equalsIgnoreCase("Admin")) {
								JOptionPane.showMessageDialog(null, "Operaci�n err�nea. El usuario [ADMIN] no puede ser eliminado!", "Error", JOptionPane.WARNING_MESSAGE);
								return;
							} else {
								ImageIcon icono = new ImageIcon(VentanaOpcion.class.getResource("/Imagenes/alert.png"));
					            String texto = "Seguro desea eliminar el usuario: "+ codigo;
					            VentanaOpcion ventanita = new VentanaOpcion(icono, texto);
					            ventanita.setModal(true);
					            ventanita.setVisible(true);
								int option = ventanita.getResultado();
								//int option = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el usuario: "+codigo, "Confirmaci�n", JOptionPane.WARNING_MESSAGE);
								if(option == JOptionPane.YES_OPTION){
									Tienda.getInstance().eliminarUsuario(codigo);
									deleteBtn.setEnabled(false);
									updateBtn.setEnabled(false);
									JOptionPane.showMessageDialog(null, "Operaci�n satisfactoria", "Eliminaci�n", JOptionPane.INFORMATION_MESSAGE);
									cargarUsuarios();	
								}
							}
						}
					}
				});
				deleteBtn.setEnabled(false);
				deleteBtn.setActionCommand("OK");
				buttonPane.add(deleteBtn);
				getRootPane().setDefaultButton(deleteBtn);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				cancelButton.setBackground(CyanMid);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarUsuarios();
	}

	public void cargarUsuarios() {
		tableModel.setRowCount(0);
		rows = new Object[tableModel.getColumnCount()];

		for (User user : Tienda.getInstance().getMisUsers()) {
			rows[0] = user.getUserName();
			rows[1] = user.getPass();
			rows[2] = user.getTipo();

			tableModel.addRow(rows);
		}
	}
}
