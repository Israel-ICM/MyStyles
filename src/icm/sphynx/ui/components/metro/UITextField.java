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
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;

/**
 * @author israel-icm
 */
public class UITextField extends BasicTextFieldUI  {
    private JTextField textField;
    private final JLabel btnClear = new JLabel();
    private Dimension dimensionTextField = new Dimension(150, 38);
    private int heightButton = dimensionTextField.height - 10;
    private boolean inicializado = false;

    public UITextField() {
        inicializado = false;
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new UITextField();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        textField = (JTextField)super.getComponent();
        textField.setBackground(null);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#E0E0E0"));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());

        textField.setFont(new Font(UITools.FONT_DEFAULT, textField.getFont().getStyle(), textField.getFont().getSize()));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        textField.setBorder(border);
        
        addButtonClear();
        textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, heightButton + UITools.PADDING_CONTENTS)));

        if (!inicializado) {
            textField.setSize(new Dimension(textField.getWidth(), 30));
            btnClear.setVisible(false);
            btnClear.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ((JTextField) btnClear.getParent()).setText("");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    btnClear.setOpaque(true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    btnClear.setOpaque(false);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btnClear.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btnClear.setVisible(false);
                }
            });
            
            textField.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) { }

                @Override
                public void mouseReleased(MouseEvent e) { }

                @Override
                public void mouseEntered(MouseEvent e) {
                    btnClear.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btnClear.setVisible(false);
                }
            });
            inicializado = true;
        }
    }
    
    private void addButtonClear(){
        btnClear.setText("");
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClear.setIcon(createClearImage(15, 15, "#212121", 1));
        textField.add(btnClear);
        textField.setVisible(true);
        textField.setSelectionColor(btnClear.getBackground());
        buttonClearResized();
    }
    /** Ajusta la dimension y posicion del boton X*/
    private void buttonClearResized() {
        //tamaño boton
        heightButton = textField.getSize().height - 10;
        btnClear.setSize( new Dimension(heightButton, heightButton));
        btnClear.setPreferredSize(new Dimension(heightButton, heightButton));        
        //posicion
        btnClear.setLocation(textField.getWidth() - btnClear.getWidth() - 5, 5);
    }
    
    private ImageIcon createClearImage(int width, int height, String colorHex, int stroke) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Icono del botón clear
        graphics.setStroke(new BasicStroke(stroke));

        // Las ubicaciones se miden en porcentaje para que no se deforme si crece
        int inicioX = (10 * width) / 100;
        int inicioY = (10 * height) / 100;
        int finX = (90 * width) / 100;
        int finY = (90 * height) / 100;

        int inicioX2 = (90 * width) / 100;
        int inicioY2 = (10 * height) / 100;
        int finX2 = (10 * width) / 100;
        int finY2 = (90 * height) / 100;
        
        int centrar = (btnClear.getWidth() - width) / 2;
        
        graphics.setColor(Color.decode(colorHex));
        graphics.drawLine(inicioX + centrar, inicioY + centrar, finX + centrar, finY + centrar);
        graphics.drawLine(inicioX2 + centrar, inicioY2 + centrar, finX2 + centrar, finY2 + centrar);
        
        return new ImageIcon(image);
    }
}
