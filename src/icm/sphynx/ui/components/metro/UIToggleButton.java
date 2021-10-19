package icm.sphynx.ui.components.metro;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.scene.control.ToggleButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;import javax.swing.JToggleButton;
import javax.swing.UIManager;
;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToggleButtonUI;

/**
 * Estilos para los botones
 * @author israel-icm
 */
public class UIToggleButton extends BasicToggleButtonUI {
    private Graphics2D g2d;
    private JToggleButton toggleButton;
    private boolean componenteIniciado = false;
    private int width = 0;
    private int height = 0;
    private String buttonBackground = "#C9CBCB";
    
    private static final int STATE_DEFAULT = 0;
    private static final int STATE_OVER = 1;
    private static final int STATE_PRESSED = 2;
    private int currentStateToggleButton = 0; // Controla los estados del botón
    
    public static ComponentUI createUI(JComponent c) {
        return new UIToggleButton();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        toggleButton = (JToggleButton)c;
        g2d = (Graphics2D)g;
        width = c.getWidth();
        height = c.getHeight();

        int border = 5;
        toggleButton.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));
        installButtonStyles();
        installEvents();

        if (toggleButton.isSelected())
            toggleButton.setBackground(MetroUIConfigTheme.getSecondColor());
        else
            toggleButton.setBackground(MetroUIConfigTheme.getPrimaryColor());

        super.paint(g, c);
    }
    
    private void installButtonStyles() {
        if (!toggleButton.isSelected()) {
            switch (currentStateToggleButton) {
                case STATE_DEFAULT:
                    g2d.setColor(Color.decode(buttonBackground));
                    g2d.fillRect(0, 0, width, height);

                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(Color.decode(buttonBackground));
                    g2d.drawRect(1, 1, width - 2, height - 2);
                    break;
                case STATE_OVER:
                    g2d.setColor(Color.decode(buttonBackground));
                    g2d.fillRect(0, 0, width, height);

                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(Color.decode("#7C7D7D"));
                    g2d.drawRect(1, 1, width - 2, height - 2);
                    break;
                case STATE_PRESSED:
                    g2d.setColor(Color.decode("#7C7D7D"));
                    g2d.fillRect(0, 0, width, height);

                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(Color.decode("#7C7D7D"));
                    g2d.drawRect(1, 1, width - 2, height - 2);
                    break;
            }
        }
        else {
            g2d.setColor(Color.decode("#7C7D7D"));
            g2d.fillRect(0, 0, width, height);

            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.decode("#7C7D7D"));
            g2d.drawRect(1, 1, width - 2, height - 2);
        }
    }
    private void installEvents() {
        if (!componenteIniciado) {
            toggleButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    currentStateToggleButton = STATE_PRESSED;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    currentStateToggleButton = STATE_DEFAULT;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentStateToggleButton = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentStateToggleButton = STATE_DEFAULT;
                }
            });
            componenteIniciado = true;
        }
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        super.paintText(g, c, textRect, text);
        c.setForeground(Color.decode(UITools.COLOR_BUTTON_FOREGROUND_DEFAULT));
        c.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, c.getFont().getSize()));
    }
}
