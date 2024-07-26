package visual;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class MensajeAlerta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ImageIcon icono;
	private String Texto;

	/**
	 * Create the dialog.
	 */
	public MensajeAlerta(ImageIcon icono, String texto) {
		this.icono = icono;
        this.Texto = texto;
		
		Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color CyanClaro =  new Color (222, 249, 196);
		Color FondoClarito = new Color(240, 255, 240);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 2, 0, CyanOscuro);
		
		setUndecorated(true);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(MensajeAlerta.class.getResource("/Imagenes/alert.png")));
		setBounds(100, 100, 312, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(FondoClarito);
		
		JButton okbutton = new JButton("OK");
		okbutton.setForeground(new Color(255, 255, 255));
		okbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okbutton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		okbutton.setBounds(115, 143, 73, 33);
		okbutton.setBackground(CyanMid);
		contentPanel.add(okbutton);
		/*Nota: lo del <html> es para que se formate como uno y me deje usar textos con saltos de linea y "<br>" es salto de linea en HTML*/
		  JLabel txtTxt = new JLabel("<html>" + Texto.replace("\n", "<br>")  + "</html>");
	        txtTxt.setHorizontalAlignment(SwingConstants.CENTER);
	        txtTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
	        txtTxt.setBounds(34, 36, 154, 74);
	        txtTxt.setBorder(bottomBorder);
	        txtTxt.setBackground(FondoClarito);
	        contentPanel.add(txtTxt);
		/*Nota: esto es para cambiar el tamaño de la imagen*/
		JLabel iconLabel = new JLabel(new ImageIcon(
			    new ImageIcon(getClass().getResource("/Imagenes/alert.png")).getImage()
			    .getScaledInstance(50, 50, Image.SCALE_SMOOTH))
			);
		iconLabel.setBounds(219, 50, 67, 64);
		contentPanel.add(iconLabel);
		setLocationRelativeTo(null);
	}
	
}
