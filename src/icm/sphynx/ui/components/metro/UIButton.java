package icm.sphynx.ui.components.metro;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Estilos para los botones
 * @author israel-icm
 */
public class UIButton extends BasicButtonUI {
    private Graphics2D g2d;
    private JButton button;
    private boolean componenteIniciado = false;
    private int width = 0;
    private int height = 0;
    private String buttonBackground = "#C9CBCB";

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_OVER = 1;
    private static final int STATE_PRESSED = 2;
    private int currentStateButton = 0; // Controla los estados del botón
    
    public static ComponentUI createUI(JComponent c) {
        return new UIButton();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        button = (JButton)c;
        g2d = (Graphics2D)g;
        width = c.getWidth();
        height = c.getHeight();
        
        int border = 5;
        button.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));
        installProperties();
        installEvents();
        super.paint(g, c);
    }
    private void installButtonStyles() {
        switch (currentStateButton) {
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
    /**
     * Estilos para los botones del componente Spinner
     */
    private void installButtonStylesSpinner() {
        switch (currentStateButton) {
            case STATE_DEFAULT:
                g2d.setColor(Color.decode("#FFFFFF"));
                g2d.fillRect(0, 0, width, height);

                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.decode("#CCCCCC"));
                g2d.drawRect(1, 1, width - 2, height - 2);
                break;
            case STATE_OVER:
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.decode("#7C7D7D"));
                g2d.drawRect(1, 1, width - 2, height - 2);
                break;
            case STATE_PRESSED:
                g2d.setColor(Color.decode("#D3D4D3"));
                g2d.fillRect(0, 0, width, height);

                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.decode("#D3D4D3"));
                g2d.drawRect(1, 1, width - 2, height - 2);
                break;
        }
    }
    private void installProperties() {
        // ButtonEmpty
        if (MetroUIComponent.getPropertyButtonEmpty(button.getName())) {
            button.setBackground(null);
            button.setBorder(null);
        }
        // ButtonLink
        else if (MetroUIComponent.getPropertyButtonLink(button.getName())) {
            button.setBackground(null);
            button.setBorder(null);
        }
        // Estilo personalizado para los botones del componente Spinner
        else if (button.getName() != null && (button.getName().equals(UISpinner.BUTTON_NAME_PREVIOUS) || button.getName().equals(UISpinner.BUTTON_NAME_NEXT))) {
            installButtonStylesSpinner();
        }
        // ButtonNormal
        else
            installButtonStyles();
        
    }
    private void installEvents() {
        if (!componenteIniciado) {
            button.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    currentStateButton = STATE_PRESSED;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    currentStateButton = STATE_DEFAULT;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentStateButton = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentStateButton = STATE_DEFAULT;
                }
            });
            componenteIniciado = true;
        }
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        super.paintText(g, c, textRect, text);

        // Propiedad link
        if (MetroUIComponent.getPropertyButtonLink(button.getName()))
            installTextStyles(c);
        else {
            c.setForeground(Color.decode(UITools.COLOR_BUTTON_FOREGROUND_DEFAULT));
            c.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, c.getFont().getSize()));
        }
    }
    
    private void installTextStyles(JComponent c) {
        c.setFont(new Font(UITools.FONT_DEFAULT, Font.BOLD, c.getFont().getSize()));
        switch (currentStateButton) {
            case STATE_DEFAULT:
                c.setForeground(MetroUIConfigTheme.getPrimaryColor());
                break;
            case STATE_OVER:
                c.setForeground(Color.decode("#7C7D7D"));
                break;
            case STATE_PRESSED:
                c.setForeground(Color.decode("#AEAFAF"));
                break;
        }
    }
}
