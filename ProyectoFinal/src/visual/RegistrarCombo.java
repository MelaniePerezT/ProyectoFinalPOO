package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logico.Combo;
import logico.DiscoDuro;
import logico.Microprocesador;
import logico.MotherBoard;
import logico.Producto;
import logico.Tienda;

public class RegistrarCombo extends JDialog {

    private final JPanel contentPanel = new JPanel();
   
    private JTextField txtNombre;
    private DefaultTableModel modeloPro = new DefaultTableModel();
    private DefaultTableModel modeloProCarri = new DefaultTableModel();
    private Object[] dispProRows;
    private Object[] caProRows;
    private JTable tableProDisponible;
    private JTable tableProCarrito;
    private int indexProCarrito;
    private int indexProDisponible;
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private double precioTotal = 0;

    private JTextField txtTotal;
    
    private JButton btnAgregar;
    private JButton btnQuitar;

    public static void main(String[] args) {
        try {
            RegistrarCombo dialog = new RegistrarCombo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegistrarCombo() {

		Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color CyanClaro =  new Color (222, 249, 196);
		Color FondoClarito = new Color(240, 255, 240);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 2, 0, CyanOscuro);
		
        setTitle("Registrar Combo");
        setBounds(100, 100, 700, 436);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

       

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 660, 327);
        contentPanel.add(panel);
        panel.setLayout(null);


       
        

        JLabel lblNewLabel_1 = new JLabel("Nombre:");
        lblNewLabel_1.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 10, 73, 14);
        panel.add(lblNewLabel_1);

        txtNombre = new JTextField();
        txtNombre.setBounds(93, 10, 134, 20);
        txtNombre.setBorder(bottomBorder);
        txtNombre.setBackground(CyanClaro);
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        JPanel pnlProdDisponible = new JPanel();
        pnlProdDisponible.setBounds(10, 104, 250, 190);
        panel.add(pnlProdDisponible);

        String[] proHeaders = { "ID", "Num. Serie", "Tipo" };
        modeloPro.setColumnIdentifiers(proHeaders);
        tableProDisponible = new JTable(modeloPro);
        tableProDisponible.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableProDisponible.setBackground(CyanClaro);
        tableProDisponible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexProDisponible = tableProDisponible.getSelectedRow();
                btnAgregar.setEnabled(true);
            }
        });
        pnlProdDisponible.setLayout(null);

        JScrollPane scrollProDisponible = new JScrollPane(tableProDisponible);
        scrollProDisponible.setBounds(0, 0, 250, 191);
        pnlProdDisponible.add(scrollProDisponible);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(CyanOscuro);
                setForeground(Color.WHITE);
                setFont(new Font("Bahnschrift", Font.BOLD, 12));
                return this;
            }
        };
        for (int i = 0; i < tableProDisponible.getColumnModel().getColumnCount(); i++) {
            tableProDisponible.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        JPanel pnlProductoCarri = new JPanel();
        pnlProductoCarri.setBounds(400, 104, 250, 182);
        panel.add(pnlProductoCarri);

        modeloProCarri.setColumnIdentifiers(proHeaders);
        tableProCarrito = new JTable(modeloProCarri);
        tableProCarrito.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableProCarrito.setBackground(CyanClaro);
        tableProCarrito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                indexProCarrito = tableProCarrito.getSelectedRow();
                btnAgregar.setEnabled(true);
				btnQuitar.setEnabled(true);
            }
        });
        pnlProductoCarri.setLayout(null);

        JScrollPane scrollProCarri = new JScrollPane(tableProCarrito);
        scrollProCarri.setBounds(0, 0, 250, 181);
        pnlProductoCarri.add(scrollProCarri);

        for (int i = 0; i < tableProCarrito.getColumnModel().getColumnCount(); i++) {
            tableProCarrito.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        JLabel lblNewLabel_2 = new JLabel("Cantidad:");
        lblNewLabel_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(325, 10, 78, 14);
        panel.add(lblNewLabel_2);

        JSpinner spnCantidad = new JSpinner();
        spnCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
        spnCantidad.setBounds(413, 9, 153, 20);
        panel.add(spnCantidad);

        txtTotal = new JTextField();
        txtTotal.setEnabled(false);
        txtTotal.setBounds(93, 51, 134, 20);
        txtTotal.setBorder(bottomBorder);
        txtTotal.setBackground(CyanClaro);
        panel.add(txtTotal);
        txtTotal.setColumns(10);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setForeground(new Color(255, 255, 255));
        btnAgregar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnAgregar.setBackground(CyanMid);
        btnAgregar.setBounds(270, 182, 100, 23);
        btnAgregar.setEnabled(false);
        panel.add(btnAgregar);

        btnQuitar = new JButton("Quitar");
        btnQuitar.setBackground(new Color(250, 128, 114));
        btnQuitar.setForeground(new Color(255, 255, 255));
        btnQuitar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnQuitar.setBounds(270, 228, 100, 23);
        btnQuitar.setEnabled(false);
        panel.add(btnQuitar);
        
                JLabel lblTotal = new JLabel("Total:");
                lblTotal.setBounds(10, 51, 46, 14);
                panel.add(lblTotal);
                lblTotal.setFont(new Font("Bahnschrift", Font.PLAIN, 14));

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indexProDisponible >= 0) {
                    Producto producto = Tienda.getInstance().getProductoNoSeleccionados().get(indexProDisponible);
                    productos.add(producto);
                    cargarProductoDisponible();
                    cargarProCarritoDisponible();
                    btnAgregar.setEnabled(false);
                    float precio = Tienda.getInstance().calculaPrecioProducto(Tienda.getInstance().getProductosSeleccionados(), "", true);
                    txtTotal.setText(String.valueOf(precio));

                    }
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indexProCarrito >= 0) {
                    Producto producto = productos.get(indexProCarrito);
                    productos.remove(indexProCarrito);
                    cargarProductoDisponible();
                    cargarProCarritoDisponible();
                    btnQuitar.setEnabled(false);
                    float precio = Tienda.getInstance().calculaPrecioProducto(Tienda.getInstance().getProductosSeleccionados(), "", true);
                    txtTotal.setText(String.valueOf(precio));

                }
            }
        });

        

       

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnRegistrar.setForeground(new Color(255, 255, 255));
        btnRegistrar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnRegistrar.setBackground(CyanMid);
        btnRegistrar.setBounds(440, 350, 100, 25);
        contentPanel.add(btnRegistrar);
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!txtNombre.getText().isEmpty() && !productos.isEmpty()) {
                	
                	double precio = Double.parseDouble(txtTotal.getText());
                	Combo combo= new Combo(txtNombre.getText(), (int) spnCantidad.getValue(),(float) precio);
                	productos= Tienda.getInstance().getProductosSeleccionados();
					combo.setMisProductos(productos);
                	Tienda.getInstance().creacionCombos(combo);
                   clean();
                }
            }
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(250, 128, 114));
        btnCancelar.setForeground(new Color(255, 255, 255));
        btnCancelar.setBounds(550, 350, 100, 25);
        contentPanel.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cargarProductoDisponible();
        cargarProCarritoDisponible();
    }

    public void cargarProductoDisponible() {
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
               
                modeloPro.addRow(dispProRows);
            }
        }
    }

    public void cargarProCarritoDisponible() {
        precioTotal = 0;
        modeloProCarri.setRowCount(0);
        caProRows = new Object[tableProCarrito.getColumnCount()];
        for (Producto pro : productos) {
            precioTotal += pro.getPrecio();
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
            
            modeloProCarri.addRow(caProRows);
        }
    }
    public void clean() {

		
		txtTotal.setText("0.0");
		
		txtNombre.setText("");
		

		modeloPro.setRowCount(0);
		modeloProCarri.setRowCount(0);
		

		productos.clear();

		dispProRows = null;
		caProRows = null;

		indexProCarrito = 0;
		indexProDisponible = 0;

		btnAgregar.setEnabled(false);
		btnQuitar.setEnabled(false);
		
	}
}
