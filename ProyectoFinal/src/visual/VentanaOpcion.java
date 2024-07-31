package visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class VentanaOpcion extends JDialog {
    private int resultado = -1; 

    /**
     * Create the dialog.
     */
    public VentanaOpcion(ImageIcon icono, String texto) {
    	
    	Color CyanOscuro = new Color(70, 133, 133);
		Color CyanMid = new Color(80, 180, 152);
		Color Rojito = new Color(250, 128, 114);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 2, 0, CyanOscuro);
		
        setUndecorated(true);
        setBounds(100, 100, 331, 228);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JButton yesButton = new JButton("Confirmar");
        yesButton.setForeground(new Color(255, 255, 255));
        yesButton.setBackground(CyanMid);
        yesButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        yesButton.setBounds(58, 176, 111, 23);
        getContentPane().add(yesButton);
        yesButton.setActionCommand("S�");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultado = 0;
                dispose();
            }
        });
        getRootPane().setDefaultButton(yesButton);

        JButton noButton = new JButton("No");
        noButton.setForeground(new Color(255, 255, 255));
        noButton.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        noButton.setBackground(Rojito);
        noButton.setBounds(203, 176, 80, 23);
        getContentPane().add(noButton);
        noButton.setActionCommand("No");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultado = 1;
                dispose(); 
            }
        });

        /*Nota: lo del <html> es para que se formatee como uno y me deje usar textos con saltos de l�nea y "<br>" es salto de l�nea en HTML*/
        JLabel txtTxt = new JLabel("<html>" + texto.replace("\n", "<br>") + "</html>");
        txtTxt.setHorizontalAlignment(SwingConstants.CENTER);
        txtTxt.setBorder(bottomBorder);
        txtTxt.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtTxt.setBounds(20, 40, 188, 94);
        getContentPane().add(txtTxt);

        /*Nota: esto es para cambiar el tama�o de la imagen*/

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 255, 240));
        panel.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
        panel.setBounds(0, 0, 331, 228);
        getContentPane().add(panel);
        panel.setLayout(null);
        JLabel iconLabel = new JLabel(new ImageIcon(
            icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))
        );
        iconLabel.setBounds(210, 41, 86, 82);
        panel.add(iconLabel);
    }

    public int getResultado() {
        return resultado;
    }
}
