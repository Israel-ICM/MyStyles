package icm.sphynx.ui.components.metro;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;

/**
 * @author israel-icm
 */
public class UIRadioButton extends BasicRadioButtonUI {
    public static ComponentUI createUI(JComponent b) {
        return new UIRadioButton();
    }
    
    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        JRadioButton chk = (JRadioButton)c;
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d, chk);

        chk.setBackground(null);
        chk.setFont(new Font(UITools.FONT_DEFAULT, chk.getFont().getStyle(), chk.getFont().getSize()));
        chk.setIcon(createCheckImage(19, 19, chk.isSelected()));
    }
    
    private ImageIcon createCheckImage(int width, int height, boolean checked) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fondo del check
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#FFFFFF"));
        g2d.fillOval(1, 1, width, height);
        
        // Borde del check
        AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2d.setComposite(ac2);
        g2d.setColor(Color.decode("#212121"));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(1, 1, width - 1, height - 1);

        if (checked) {
            int medio = width / 2;
            
            int x = medio - (medio / 2);
            int y = medio - (medio / 2);
            
            int ancho = (60 * width) / 100; // El check sera el 60% del cuadro original

            g2d.setColor(Color.decode(UITools.COLOR_PRIMARY));
            g2d.fillOval(x, y, ancho, ancho);
        }
        
        return new ImageIcon(image);
    }
}
