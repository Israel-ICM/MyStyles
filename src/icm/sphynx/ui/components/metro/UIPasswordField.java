package icm.sphynx.ui.components.metro;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

/**
 * @author israel-icm
 */
public class UIPasswordField extends BasicPasswordFieldUI {
    
    private JPasswordField passwordField;
    private final JLabel btnVerPassword = new JLabel();
    private Dimension dimensionTextField = new Dimension(150, 38);
    private int heightButton = dimensionTextField.height - 10;
    private boolean inicializado = false;
    private String strPasswordAux = "";
    private boolean swBtnVerPresionado = false;

    public UIPasswordField() {
        inicializado = false;
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new UIPasswordField();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        passwordField = (JPasswordField)super.getComponent();
        passwordField.setBackground(null);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#E0E0E0"));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());

        passwordField.setFont(new Font(UITools.FONT_DEFAULT, passwordField.getFont().getStyle(), passwordField.getFont().getSize()));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        passwordField.setBorder(border);
        
        addButtonVerPassword();
        passwordField.setBorder(BorderFactory.createCompoundBorder(passwordField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, heightButton + UITools.PADDING_CONTENTS)));

        if (!inicializado) {
            btnVerPassword.setVisible(false);
            btnVerPassword.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    strPasswordAux = ((JTextField) btnVerPassword.getParent()).getText();
                    ((JTextField) btnVerPassword.getParent()).setText("");
                    swBtnVerPresionado = true;
                    btnVerPassword.setOpaque(true);
                    passwordField.setFocusable(false);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    ((JTextField) btnVerPassword.getParent()).setText(strPasswordAux);
                    strPasswordAux = "";
                    swBtnVerPresionado = false;
                    btnVerPassword.setOpaque(false);
                    passwordField.setFocusable(true);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btnVerPassword.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btnVerPassword.setVisible(false);
                }
            });
            
            passwordField.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btnVerPassword.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btnVerPassword.setVisible(false);
                }
            });
            
            inicializado = true;
        }

        if(swBtnVerPresionado){
            // g.setFont(new Font(UITools.COLOR_FONT_DEFAULT, passwordField.getFont().getStyle(), passwordField.getFont().getSize()));
            g.setColor(passwordField.getForeground());
            g.drawString(strPasswordAux, passwordField.getMargin().left, (passwordField.getSize().height)/2 + passwordField.getFont().getSize()/2 );
        }
    }
    
    private void addButtonVerPassword(){
        btnVerPassword.setText("");
        btnVerPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerPassword.setIcon(createClearImage(15, 15, "#212121", 2));
        passwordField.add(btnVerPassword);
        passwordField.setVisible(true);
        passwordField.setSelectionColor(btnVerPassword.getBackground());
        buttonVerPasswordResized();
    }
    /** Ajusta la dimension y posicion del boton X*/
    private void buttonVerPasswordResized() {
        //tamaño boton
        heightButton = passwordField.getSize().height - 10;
        btnVerPassword.setSize( new Dimension(heightButton, heightButton));
        btnVerPassword.setPreferredSize(new Dimension(heightButton, heightButton));        
        //posicion
        btnVerPassword.setLocation(passwordField.getWidth() - btnVerPassword.getWidth() - 5, 5);
    }
    
    private ImageIcon createClearImage(int width, int height, String colorHex, int stroke) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Icono del botón clear
        graphics.setStroke(new BasicStroke(stroke));

        // Las ubicaciones se miden en porcentaje para que no se deforme si crece
        int inicioX = (45 * width) / 100;
        int inicioY = (35 * height) / 100;
        
        int centrarX = (btnVerPassword.getWidth() - width) / 2;
        int centrarY = (btnVerPassword.getHeight() - height) / 2;

        graphics.setColor(Color.decode(colorHex));
        graphics.drawArc(centrarX, inicioY, width, height, 10, 160);
        graphics.fillOval(inicioX, inicioY + centrarY, width / 2, height / 2);
        
        return new ImageIcon(image);
    }
}
