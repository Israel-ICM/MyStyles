package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;

/**
 * Asigna los estilos para todos los radio buttons
 * @author israel-icm
 */
public class UIRadioButton extends BasicRadioButtonUI {
    private boolean componenteIniciado = false;
    private JRadioButton radioButton;
    
    private static final int STATE_DEFAULT = 0;
    private static final int STATE_OVER = 1;
    private static final int STATE_PRESSED = 2;
    private int currentStateRadioButton = 0; // Controla los estados del checkbox

    public static ComponentUI createUI(JComponent b) {
        return new UIRadioButton();
    }
    
    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        radioButton = (JRadioButton)c;
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d, radioButton);

        radioButton.setBackground(null);
        radioButton.setForeground(Color.decode(StyleColorsMetro.LIGHT_FOREGROUND));
        radioButton.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, radioButton.getFont().getSize()));
        radioButton.setIcon(createCheckImage(19, 19, radioButton.isSelected()));
        installEvents();
    }
    
    private void installEvents() {
        if (!componenteIniciado) {
            radioButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    currentStateRadioButton = STATE_PRESSED;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    currentStateRadioButton = STATE_DEFAULT;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentStateRadioButton = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentStateRadioButton = STATE_DEFAULT;
                }
            });
            componenteIniciado = true;
        }
    }
    
    /**
     * Genera la imagen del radio button esté seleccionado o no
     * @param width
     * @param height
     * @param checked
     * @return 
     */
    private ImageIcon createCheckImage(int width, int height, boolean checked) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!checked) {
            switch (currentStateRadioButton) {
                case STATE_DEFAULT:
                    // Fondo del check
                    g2d.setColor(Color.decode("#FFFFFF"));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode("#CCCCCC"));
                    g2d.fillOval(1, 1, width, height);

                    // Borde del check
                    g2d.setColor(Color.decode("#000000"));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode("#A6A6A6"));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(1, 1, width - 1, height - 1);
                    break;
                case STATE_OVER:
                    // Borde del check
                    g2d.setColor(Color.decode("#000000"));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode("#A6A6A6"));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(1, 1, width - 1, height - 1);
                    break;
                case STATE_PRESSED:
                    // Fondo del check
                    g2d.setColor(Color.decode("#626262"));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode("#CCCCCC"));
                    g2d.fillOval(1, 1, width, height);

                    // Borde del check
                    g2d.setColor(Color.decode("#626262"));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode("#A6A6A6"));
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(1, 1, width - 1, height - 1);
                    break;
                default:
                    break;
            }
        }
        else {
            // Fondo del check
            g2d.setColor(Color.decode("#FFFFFF"));
            if (MetroUIConfigTheme.isDarkMode())
                g2d.setColor(Color.decode("#CCCCCC"));
            g2d.fillOval(1, 1, width, height);

            // Borde del check
            g2d.setColor(Color.decode("#000000"));
            if (MetroUIConfigTheme.isDarkMode())
                g2d.setColor(Color.decode("#A6A6A6"));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(1, 1, width - 1, height - 1);

            int medio = width / 2;
            int x = medio - (medio / 2);
            int y = medio - (medio / 2);
            int ancho = (60 * width) / 100; // El check sera el 60% del cuadro original
            switch (currentStateRadioButton) {
                case STATE_DEFAULT:
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    break;
                case STATE_OVER:
                    g2d.setColor(Color.BLACK);
                    break;
                case STATE_PRESSED:
                    g2d.setColor(Color.decode("#626262"));
                    break;
                default:
                    break;
            }
            g2d.fillOval(x, y, ancho, ancho);
        }
        return new ImageIcon(image);
    }
}
