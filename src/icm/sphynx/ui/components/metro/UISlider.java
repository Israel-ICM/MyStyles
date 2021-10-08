package icm.sphynx.ui.components.metro;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
    private JSlider slider;
    public UISlider(JSlider b) {
        super(b);
        slider = b;
        slider.setFocusable(false);
    }
    
    public static ComponentUI createUI(JComponent b)    {
        return new UISlider((JSlider) b);
    }
    
    @Override
    public void paint(Graphics g, JComponent c)   {
        super.paint(g, c);
        c.setBackground(null);
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
        g2d.setColor(Color.decode("#787878"));
        
        if (slider.getOrientation() == SwingConstants.HORIZONTAL)
            g2d.drawLine(0, slider.getHeight() / 2, slider.getWidth(), slider.getHeight() / 2);
        else // Si es vertical
            g2d.drawLine(slider.getWidth() / 2, 0, slider.getWidth()/ 2, slider.getHeight());
    }
    
    /**
     * Crea el componente que asigna el valor, para vertical se crea una vista diferente a la de horizontal
     * @param g 
     */
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.setPaint(Color.decode(UITools.COLOR_PRIMARY));
            g2d.fillRoundRect(thumbRect.x, thumbRect.y, 8, thumbRect.height, 10, 10);
        }
        else { // Si es vertical
            int size = thumbRect.width - 4;
            g2d.setPaint(Color.decode("#787878"));
            g2d.drawOval(thumbRect.x + 1, thumbRect.y, size, size);
            g2d.setPaint(Color.decode("#FFFFFF"));
            g2d.fillOval(thumbRect.x + 1, thumbRect.y, size, size);
        }
    }
    
    /* public void paintTicks(Graphics g)  {
        
    }
    
    public void paintLabels( Graphics g ) {
        
    }
    
    protected void paintHorizontalLabel( Graphics g, int value, Component label ) {
        
    }*/
}
