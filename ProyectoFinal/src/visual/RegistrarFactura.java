package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Combo;
import logico.DiscoDuro;
import logico.FacturaCompra;
import logico.FacturaVenta;
import logico.Microprocesador;
import logico.MotherBoard;
import logico.Persona;
import logico.Producto;
import logico.Proveedor;
import logico.Tienda;
import logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;

public class RegistrarFactura extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtID;
    private JTextField txtFecha;
    private DefaultTableModel modeloPro = new DefaultTableModel();
    private DefaultTableModel modeloProCarri = new DefaultTableModel();
    private DefaultTableModel modeloCom = new DefaultTableModel();
    private DefaultTableModel modeloComCarri = new DefaultTableModel();
    private DefaultComboBoxModel<Proveedor> proveedoresRegistrados = new DefaultComboBoxModel<Proveedor>();
    private Object[] dispProRows;
    private Object[] caProRows;
    private Object[] dispComRows;
    private Object[] caComRows;
    private JTable tableProDisponible;
    private JTable tableProCarrito;
    private JTable tableComDisponible;
    private JTable tableComCarrito;
    private int indexProCarrito;
    private int indexProDisponible;
    private int indexComCarrito;
    private int indexComDisponible;
    private ArrayList<Producto> ProductosComprados = new ArrayList<Producto>();
    private float precioTotal = (float) 0.0;
    private JTextField txtTotal;
    private JTextField txtDescuento;
    private JButton btnAgregarPro;
    private JButton btnQuitarPro;
    private JButton btnProducto;
    private JButton btnCombos;
    private JPanel pnlCompra;
    private JTextField txtIdCliente;
    private JTextField txtEmpleado;
    private JPanel pnlVenta;
    private JTextField txtHora;
    private JPanel pnlProDisponible;
    private JPanel pnlProCarrito;
    private JPanel pnlComDisponible;
    private JPanel pnlComCarrito;
    private JComboBox cbxProveedor;
    private JButton btnBuscarCliente;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegistrarFactura dialog = new RegistrarFactura(true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegistrarFactura(boolean esCV) {
    	
    	Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color CyanClaro =  new Color (222, 249, 196);
		Color FondoClarito = new Color(240, 255, 240);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 2, 0, CyanOscuro);
    	
        setTitle("Registrar Factura");
        setBounds(100, 100, 750, 500);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        setLocationRelativeTo(null);
        contentPanel.setBackground(FondoClarito);
        

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 714, 404);
        contentPanel.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("ID:");
        lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel.setBounds(10, 13, 71, 14);
        panel.add(lblNewLabel);
        panel.setBackground(FondoClarito);

        txtID = new JTextField();
        txtID.setText(Tienda.getInstance().generarIdFactura());
        txtID.setEditable(false);
        txtID.setBounds(60, 10, 133, 20);
        txtID.setBorder(bottomBorder);
		txtID.setBackground(CyanClaro);
        panel.add(txtID);
        txtID.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Fecha:");
        lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(203, 13, 65, 14);
        panel.add(lblNewLabel_1);

        txtFecha = new JTextField();
        txtFecha.setEditable(false);
        txtFecha.setBounds(278, 10, 138, 20);
        LocalDate hoy= LocalDate.now();
        txtFecha.setText(hoy.toString());
        txtFecha.setBorder(bottomBorder);
		txtFecha.setBackground(CyanClaro);
        panel.add(txtFecha);
        txtFecha.setColumns(10);
     // Panel y tabla de productos disponibles
        pnlProDisponible = new JPanel();
        pnlProDisponible.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlProDisponible.setBounds(10, 165, 300, 200);
        panel.add(pnlProDisponible);
        pnlProDisponible.setLayout(new BorderLayout());

        String[] proHeaders = { "ID", "Num Serie", "Tipo", "Precio" };
        modeloPro.setColumnIdentifiers(proHeaders);
        tableProDisponible = new JTable(modeloPro);
        tableProDisponible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexProDisponible = tableProDisponible.getSelectedRow();
                btnAgregarPro.setEnabled(true);
                btnQuitarPro.setEnabled(true);
            }
        });

        JScrollPane scrollProDisponible = new JScrollPane(tableProDisponible);
        pnlProDisponible.add(scrollProDisponible, BorderLayout.CENTER);

        // Panel y tabla de productos en carrito
        pnlProCarrito = new JPanel();
        pnlProCarrito.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlProCarrito.setBounds(400, 165, 300, 200);
        panel.add(pnlProCarrito);
        pnlProCarrito.setLayout(new BorderLayout());

        modeloProCarri.setColumnIdentifiers(proHeaders);
        tableProCarrito = new JTable(modeloProCarri);
        tableProCarrito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexProCarrito = tableProCarrito.getSelectedRow();
                btnAgregarPro.setEnabled(true);
                btnQuitarPro.setEnabled(true);
                
            }
        });

        JScrollPane scrollProCarrito = new JScrollPane(tableProCarrito);
        pnlProCarrito.add(scrollProCarrito, BorderLayout.CENTER);

        // Panel y tabla de combos disponibles
        pnlComDisponible = new JPanel();
        pnlComDisponible.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlComDisponible.setBounds(10, 165, 300, 200);
        panel.add(pnlComDisponible);
        pnlComDisponible.setLayout(new BorderLayout());

        String[] comHeaders = { "Nombre", "Precio" };
        modeloCom.setColumnIdentifiers(comHeaders);
        tableComDisponible = new JTable(modeloCom);
        tableComDisponible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexComDisponible = tableComDisponible.getSelectedRow();
                btnAgregarPro.setEnabled(true);
                btnQuitarPro.setEnabled(true);
            }
        });

        JScrollPane scrollComDisponible = new JScrollPane(tableComDisponible);
        pnlComDisponible.add(scrollComDisponible, BorderLayout.CENTER);

        // Panel y tabla de combos en carrito
        pnlComCarrito = new JPanel();
        pnlComCarrito.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlComCarrito.setBounds(400, 165, 300, 200);
        panel.add(pnlComCarrito);
        pnlComCarrito.setLayout(new BorderLayout());

        modeloComCarri.setColumnIdentifiers(comHeaders);
        tableComCarrito = new JTable(modeloComCarri);
        tableComCarrito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexComCarrito = tableComCarrito.getSelectedRow();
                btnAgregarPro.setEnabled(true);
                btnQuitarPro.setEnabled(true);
            }
        });

        JScrollPane scrollComCarrito = new JScrollPane(tableComCarrito);
        scrollComCarrito.setBounds(5, 5, 290, 140);
        pnlComCarrito.add(scrollComCarrito);
        
        

        // Botones de agregar y quitar productos y combos
        btnAgregarPro = new JButton("Agregar ");
        btnAgregarPro.setForeground(new Color(255, 255, 255));
        btnAgregarPro.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnAgregarPro.setBackground(CyanMid);
        btnAgregarPro.setEnabled(false);
        btnAgregarPro.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnAgregarPro.setBounds(307, 220, 97, 25);
        btnAgregarPro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indexProDisponible >= 0) {
                    Producto producto = Tienda.getInstance().getProductoNoSeleccionados().get(indexProDisponible);
                    producto.setSeleccionado(true);
                    cargaProCarritoDisponible();
                    cargaProductoDisponible();
                }
            }
        });
        panel.add(btnAgregarPro);

        btnQuitarPro = new JButton("Quitar");
        btnQuitarPro.setSize(97, 25);
        btnQuitarPro.setLocation(307, 260);
        btnQuitarPro.setEnabled(false);
        btnQuitarPro.setBackground(new Color(250, 128, 114));
        btnQuitarPro.setForeground(new Color(255, 255, 255));
        btnQuitarPro.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnQuitarPro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indexProCarrito >= 0) {
                    Producto producto = Tienda.getInstance().getProductosSeleccionados().get(indexProCarrito);
                    producto.setSeleccionado(false);
                    cargaProCarritoDisponible();
                    cargaProductoDisponible();
                }
            }
        });
        panel.add(btnQuitarPro);
        
        pnlCompra = new JPanel();
        pnlCompra.setBounds(10, 53, 656, 52);
        panel.add(pnlCompra);
        pnlCompra.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("Proveedor:");
        lblNewLabel_4.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(10, 11, 89, 14);
        pnlCompra.add(lblNewLabel_4);

		for (Persona persona : Tienda.getInstance().getListaPersonas()) {
			if (persona instanceof Proveedor) {
				proveedoresRegistrados.addElement((Proveedor) persona);
			}
		}
        
        cbxProveedor = new JComboBox();
        cbxProveedor.setBounds(94, 10, 161, 20);
        pnlCompra.add(cbxProveedor);
        cbxProveedor.setBackground(CyanClaro);
        Component editor = cbxProveedor.getEditor().getEditorComponent();
        if (editor instanceof JTextField) {
         JTextField textFieldEditor = (JTextField) editor;
         textFieldEditor.setBackground(CyanClaro); 
     }
        
        
        btnProducto = new JButton("Productos");
        btnProducto.setEnabled(false);
        btnProducto.setForeground(new Color(255, 255, 255));
        btnProducto.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnProducto.setBackground(CyanMid);
        btnProducto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		btnCombos.setEnabled(true);
        		btnProducto.setEnabled(false);
        		pnlComCarrito.setVisible(false);
        		pnlComDisponible.setVisible(false);
        		pnlProCarrito.setVisible(true);
        		pnlProDisponible.setVisible(true);
        	}
        	
        });
        btnProducto.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnProducto.setBounds(10, 131, 108, 23);
        panel.add(btnProducto);
        
        btnCombos = new JButton("Combos");
        btnCombos.setForeground(new Color(255, 255, 255));
        btnCombos.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnCombos.setBackground(CyanMid);
        btnCombos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnProducto.setEnabled(true);
        		btnCombos.setEnabled(false);
        		pnlComCarrito.setVisible(true);
        		pnlComDisponible.setVisible(true);
        		pnlProCarrito.setVisible(false);
        		pnlProDisponible.setVisible(false);
        	}
        });
        btnCombos.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnCombos.setBounds(124, 131, 89, 23);
        panel.add(btnCombos);
        
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        txtTotal.setText("0.0");
        txtTotal.setBounds(310, 134, 86, 20);
        txtTotal.setBackground(CyanClaro);
        txtTotal.setBorder(bottomBorder);
        panel.add(txtTotal);
        txtTotal.setColumns(10);



    JLabel lblNewLabel_2 = new JLabel("Total:");
    lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
    lblNewLabel_2.setBounds(230, 135, 65, 14);
    panel.add(lblNewLabel_2);

        txtDescuento = new JTextField();
        txtDescuento.setEditable(false);
        txtDescuento.setText("0");
        txtDescuento.setBounds(312, 190, 86, 20);
        txtDescuento.setBackground(CyanClaro);
        txtDescuento.setBorder(bottomBorder);
        panel.add(txtDescuento);
        txtDescuento.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Descuento:");
        lblNewLabel_3.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(312, 165, 83, 14);
        panel.add(lblNewLabel_3);
        
        pnlVenta = new JPanel();
        pnlVenta.setBounds(10, 53, 656, 52);
        panel.add(pnlVenta);
        pnlVenta.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("ID cliente:");
        lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_5.setBounds(10, 11, 106, 14);
        pnlVenta.add(lblNewLabel_5);
        
        txtIdCliente = new JTextField();
        txtIdCliente.setBounds(89, 10, 86, 20);
        txtIdCliente.setBackground(CyanClaro);
        txtIdCliente.setBorder(bottomBorder);
        pnlVenta.add(txtIdCliente);
        txtIdCliente.setColumns(10);
        
        btnBuscarCliente = new JButton("Buscar");
        btnBuscarCliente.setForeground(new Color(255, 255, 255));
        btnBuscarCliente.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnBuscarCliente.setBackground(CyanMid);
        btnBuscarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int opcion=JOptionPane.NO_OPTION;
				Cliente client= (Cliente) Tienda.getInstance().buscarPersonaId(txtIdCliente.getText());
				if(client==null)
				{
					
					opcion = JOptionPane.showConfirmDialog(null, "Cliente no encontrado, ¿Desea registrar el cliente?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				}
				if(opcion==JOptionPane.YES_OPTION)
				{
					btnBuscarCliente.setEnabled(false);
					RegistrarCliente reg = new RegistrarCliente(null);
					reg.setModal(true);
					reg.setVisible(true);
					txtIdCliente.setText("Cliente - " + (Tienda.getInstance().numCliente - 1));
				}
				else if(client!=null)
				{
					txtIdCliente.setText(client.getId());
					ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/check.png"));
	                MensajeAlerta mensajito = new MensajeAlerta(iconito, "Busqueda exitosa.");
	                mensajito.setModal(true);
	                mensajito.setVisible(true);
	                btnBuscarCliente.setEnabled(false);
				}

        	}
        });
        btnBuscarCliente.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnBuscarCliente.setBounds(185, 9, 89, 23);
        pnlVenta.add(btnBuscarCliente);
        
        JLabel lblNewLabel_6 = new JLabel("Empleado:");
        lblNewLabel_6.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_6.setBounds(365, 11, 73, 14);
        pnlVenta.add(lblNewLabel_6);
        
        txtEmpleado = new JTextField();
        txtEmpleado.setText(Tienda.getInstance().getLoginUser().getUserName());
        txtEmpleado.setEditable(false);
        txtEmpleado.setBounds(450, 10, 134, 20);
        
        txtEmpleado.setBackground(CyanClaro);
        txtEmpleado.setBorder(bottomBorder);
        pnlVenta.add(txtEmpleado);
        txtEmpleado.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Hora:");
        lblNewLabel_7.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_7.setBounds(446, 13, 46, 14);
        panel.add(lblNewLabel_7);
        
        txtHora = new JTextField();
        txtHora.setEditable(false);
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        txtHora.setText(ahora.format(formatter)); 
        txtHora.setBounds(502, 12, 153, 20);
        txtHora.setBackground(CyanClaro);
        txtHora.setBorder(bottomBorder);
        panel.add(txtHora);
        txtHora.setColumns(10);

        // Panel de botones para registrar y cancelar
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnRegistrarFactura = new JButton("Registrar Factura");
        btnRegistrarFactura.setForeground(new Color(255, 255, 255));
        btnRegistrarFactura.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnRegistrarFactura.setBackground(CyanMid);
        btnRegistrarFactura.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (txtID.getText().isEmpty() || txtIdCliente.getText().isEmpty() ) {
					ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/alert.png"));
					MensajeAlerta mensajito = new MensajeAlerta(iconito, "Operación errónea.\nTodos los campos deben de\nestar llenos!");
					mensajito.setModal(true);
					mensajito.setVisible(true);
					return;
				}
        		ArrayList<Producto> productos=Tienda.getInstance().getProductosSeleccionados();
    			ArrayList<Combo> combos=Tienda.getInstance().getCombosSeleccionados();
    			for (Producto prod : productos) {
    			    ProductosComprados.add(prod);
    			}

    			for (Combo combo : combos) {
    			    for (Producto prod : combo.getMisProductos()) {
    			        ProductosComprados.add(prod);
    			    }
    			}
    			LocalDate hoy= LocalDate.now();
        		if(esCV)
        		{
        			Proveedor proveedor = null;
					if (cbxProveedor.getSelectedItem() != null) {
						proveedor = (Proveedor) Tienda.getInstance().buscarPersonaId(cbxProveedor.getSelectedItem().toString());
					}

        			FacturaCompra compra = new FacturaCompra(txtID.getText(), hoy,ProductosComprados,proveedor,(Tienda.getInstance().getCantProductos() + Tienda.getInstance().getCantCombos()) );
        		 clean();
        		}
        		else
        		{
        			Cliente clien = (Cliente) Tienda.getInstance().buscarPersonaId(txtIdCliente.getText());
        			 FacturaVenta venta = new FacturaVenta( txtID.getText(), hoy, ProductosComprados,  clien, (Tienda.getInstance().getCantProductos() + Tienda.getInstance().getCantCombos()) );
        			clean();
        	
        		}
        		ImageIcon iconito = new ImageIcon(MensajeAlerta.class.getResource("/Imagenes/check.png"));
				MensajeAlerta mensajito = new MensajeAlerta(iconito, "Factura registrada correctamente.");
				mensajito.setModal(true);
				mensajito.setVisible(true);
        		
        	}
        });
        btnRegistrarFactura.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnRegistrarFactura.setActionCommand("OK");
        buttonPane.add(btnRegistrarFactura);
        getRootPane().setDefaultButton(btnRegistrarFactura);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(250, 128, 114));
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCancelar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnCancelar.setActionCommand("Cancel");
        btnCancelar.setBackground(new Color(250, 128, 114));
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        buttonPane.add(btnCancelar);
        if(esCV)
    	{
    		pnlVenta.setVisible(false);
    		pnlCompra.setVisible(true);
    		txtDescuento.setText(String.valueOf(Tienda.getInstance().descuentoAplicado(txtIdCliente.getText(), !btnCombos.isEnabled())));
    		if(!btnCombos.isEnabled())
    			txtTotal.setText(String.valueOf(Tienda.getInstance().calculaPrecioProductoCombos(Tienda.getInstance().getCombosSeleccionados(), txtIdCliente.getText(), !btnCombos.isEnabled())));
    		else
    			txtTotal.setText(String.valueOf(Tienda.getInstance().calculaPrecioProducto(Tienda.getInstance().getProductosSeleccionados(), txtIdCliente.getText(), !btnCombos.isEnabled())));
    	}
    	else
    	{
    		pnlVenta.setVisible(true);
    		pnlCompra.setVisible(false);
    		txtDescuento.setText(String.valueOf(Tienda.getInstance().descuentoAplicado("", !btnCombos.isEnabled())));
    		if(!btnCombos.isEnabled())
    			txtTotal.setText(String.valueOf(Tienda.getInstance().calculaPrecioProductoCombos(Tienda.getInstance().getCombosSeleccionados(), "", !btnCombos.isEnabled())));
    		else
    			txtTotal.setText(String.valueOf(Tienda.getInstance().calculaPrecioProducto(Tienda.getInstance().getProductosSeleccionados(), "", !btnCombos.isEnabled())));
    		
    	}
        cargaComboCarritoDisponible();
        cargaComboDisponible(); 
        cargaProCarritoDisponible();
        cargaProductoDisponible();
        pnlComCarrito.setBackground(FondoClarito);
        pnlComDisponible.setBackground(FondoClarito);
        pnlCompra.setBackground(FondoClarito);
        pnlProCarrito.setBackground(FondoClarito);
        pnlProDisponible.setBackground(FondoClarito);
        pnlVenta.setBackground(FondoClarito);
    }

    public void cargaProductoDisponible() {
        modeloPro.setRowCount(0);
        dispProRows = new Object[tableProDisponible.getColumnCount()];
        for (Producto pro : Tienda.getInstance().getProductoNoSeleccionados()) {
            if (pro.getCantDisponible() > 0) {
                dispProRows[0] = pro.getId();
                dispProRows[1] = pro.getNumSerie();
                String tipo = null;
                if (pro instanceof MotherBoard) {
                    tipo = "MotherBoard";
                } else if (pro instanceof Microprocesador) {
                    tipo = "Microprocesador";
                } else if (pro instanceof DiscoDuro) {
                    tipo = "DiscoDuro";
                } else {
                    tipo = "Memoria Ram";
                }
                dispProRows[2] = tipo;
                dispProRows[3] = pro.getPrecio();
                modeloPro.addRow(dispProRows);
            }
        }
    }

    public void cargaComboCarritoDisponible() {
        modeloComCarri.setRowCount(0);
        caComRows = new Object[tableComCarrito.getColumnCount()];
        for (Combo pro : Tienda.getInstance().getCombosSeleccionados()) {
            if (pro.getCantDisponible() > 0) {
                caComRows[0] = pro.getNombreCombo();
                caComRows[1] = pro.getPrecio();
                modeloComCarri.addRow(caComRows);
            }
        }
    }

    public void cargaComboDisponible() {
        modeloCom.setRowCount(0);
        dispComRows = new Object[tableComCarrito.getColumnCount()];
        for (Combo pro : Tienda.getInstance().getCombosNoSeleccionados()) {
            if (pro.getCantDisponible() > 0) {
                dispComRows[0] = pro.getNombreCombo();
                dispComRows[1] = pro.getPrecio();
                modeloCom.addRow(dispComRows);
            }
        }
    }

    public void cargaProCarritoDisponible() {
        modeloProCarri.setRowCount(0);
        caProRows = new Object[tableProCarrito.getColumnCount()];
        for (Producto pro : Tienda.getInstance().getProductosSeleccionados()) {
            if (pro.getCantDisponible() > 0) {
                caProRows[0] = pro.getId();
                caProRows[1] = pro.getNumSerie();
                String tipo = null;
                if (pro instanceof MotherBoard) {
                    tipo = "MotherBoard";
                } else if (pro instanceof Microprocesador) {
                    tipo = "Microprocesador";
                } else if (pro instanceof DiscoDuro) {
                    tipo = "DiscoDuro";
                } else {
                    tipo = "Memoria Ram";
                }
                caProRows[2] = tipo;
                caProRows[3] = pro.getPrecio();
                modeloProCarri.addRow(caProRows);
            }
        }
    }
    public void clean() {
       
        txtID.setText("Factura -"+Tienda.numFactura);
        LocalDate hoy= LocalDate.now();
        txtFecha.setText(hoy.toString());
        txtTotal.setText("0.0");
        txtDescuento.setText("0.0");
        txtIdCliente.setText("");
        btnBuscarCliente.setEnabled(true);
        
        txtEmpleado.setText(Tienda.getInstance().getLoginUser().getUserName());
        txtHora.setText("");

        
        modeloPro.setRowCount(0);
        modeloProCarri.setRowCount(0);
        modeloCom.setRowCount(0);
        modeloComCarri.setRowCount(0);

        proveedoresRegistrados.removeAllElements();

        dispProRows = null;
        caProRows = null;
        dispComRows = null;
        caComRows = null;

        indexProCarrito = 0;
        indexProDisponible = 0;
        indexComCarrito = 0;
        indexComDisponible = 0;

       
        ProductosComprados.clear();
        precioTotal = 0.0f;

        btnAgregarPro.setEnabled(false);
        btnQuitarPro.setEnabled(false);
        btnProducto.setEnabled(false);
        btnCombos.setEnabled(true);
        
        

    	btnProducto.setEnabled(false);
		btnCombos.setEnabled(true);
		pnlComCarrito.setVisible(false);
		pnlComDisponible.setVisible(false);
		pnlProCarrito.setVisible(true);
		pnlProDisponible.setVisible(true);

      
        cbxProveedor.setSelectedIndex(0);
    }
}
