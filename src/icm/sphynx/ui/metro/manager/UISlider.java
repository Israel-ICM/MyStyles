package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.MetroUIStyleColors;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 * Asina los estilos para los slider
 * @author israel-icm
 */
public class UISlider extends BasicSliderUI {
    private String COLOR_TRACK_BACKGROUND = MetroUIStyleColors.SLIDER_TRACK_BACKGROUND;
    private String COLOR_THUMB_BACKGROUND_OVER = MetroUIStyleColors.SLIDER_THUMB_BACKGROUND_OVER;
    private String COLOR_THUMB_BACKGROUND_PRESSED = MetroUIStyleColors.SLIDER_THUMB_BACKGROUND_PRESSED;
    
    private static final int STATE_DEFAULT = 0;
    private static final int STATE_OVER = 1;
    private static final int STATE_PRESSED = 2;
    private int currentStateButton = 0; // Controla los estados del slider

    private JSlider slider;
    private boolean componenteIniciado = false;

    public UISlider(JSlider b) {
        super(b);
        slider = b;
        slider.setFocusable(false);
        installEvents();
    }
    
    public static ComponentUI createUI(JComponent b) {
        return new UISlider((JSlider) b);
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        installColors();
        c.setBackground(null);
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_TRACK_BACKGROUND = MetroUIStyleColors.SLIDER_TRACK_BACKGROUND_DARK;
            COLOR_THUMB_BACKGROUND_OVER = MetroUIStyleColors.SLIDER_THUMB_BACKGROUND_OVER_DARK;
            COLOR_THUMB_BACKGROUND_PRESSED = MetroUIStyleColors.SLIDER_THUMB_BACKGROUND_PRESSED_DARK;
        }
    }

    private void installEvents() {
        if (!componenteIniciado) {
            slider.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    currentStateButton = STATE_PRESSED;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (UITools.isMouseInComponent(slider, e))
                        currentStateButton = STATE_OVER;
                    else
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
    
    /**
     * Dibuja la línea para movimiento del slider
     * @param g 
     */
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        BasicStroke stroke = new BasicStroke(2);
        g2d.setStroke(stroke);
        g2d.setColor(Color.decode(COLOR_TRACK_BACKGROUND));
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            int paddingIniFin = 4;
            g2d.drawLine(paddingIniFin, slider.getHeight() / 2, slider.getWidth() - paddingIniFin, slider.getHeight() / 2);
            g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(0, slider.getHeight() / 2, thumbRect.x, slider.getHeight() / 2);
        }
        else { // Si es vertical
            int paddingIniFin = 5;
            g2d.drawLine(slider.getWidth() / 2, 0, slider.getWidth() / 2, slider.getHeight() - paddingIniFin);
            g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(slider.getWidth() / 2, thumbRect.y + 1, slider.getWidth() / 2, slider.getHeight() - paddingIniFin);
        }
    }
    
    /**
     * Crea el componente que asigna el valor, para vertical se crea una vista diferente a la de horizontal
     * @param g 
     */
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch (currentStateButton) {
            case STATE_DEFAULT:
                g2d.setPaint(MetroUIConfigTheme.getPrimaryColor());
                break;
            case STATE_OVER:
                g2d.setPaint(Color.decode(COLOR_THUMB_BACKGROUND_OVER));
                break;
            case STATE_PRESSED:
                g2d.setPaint(Color.decode(COLOR_THUMB_BACKGROUND_PRESSED));
                break;
        }
        
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.fillRoundRect(thumbRect.x, thumbRect.y, 8, thumbRect.height, 10, 10);
        }
        else { // Si es vertical
            g2d.fillRoundRect(thumbRect.x, thumbRect.y, thumbRect.width, 8, 10, 10);
            
            // Esto es para un slider redondo blanco
            /*int size = thumbRect.width - 4;
            g2d.setPaint(Color.decode("#787878"));
            g2d.drawOval(thumbRect.x + 2, thumbRect.y, size, size);
            g2d.setPaint(Color.decode("#FFFFFF"));
            if (MetroUIConfigTheme.isDarkMode())
                g2d.setPaint(Color.decode("#A6A6A6"));
            g2d.fillOval(thumbRect.x + 2, thumbRect.y, size, size);*/
        }
    }
    
    /* public void paintTicks(Graphics g)  {
        
    }
    
    public void paintLabels( Graphics g ) {
        
    }
    
    protected void paintHorizontalLabel( Graphics g, int value, Component label ) {
        
    }*/
}
