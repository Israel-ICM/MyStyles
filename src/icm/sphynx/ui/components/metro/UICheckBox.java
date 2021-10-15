package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicCheckBoxUI;

/**
 * Asigna los estilos a todos los checkbox
 * @author israel-icm
 */
public class UICheckBox extends BasicCheckBoxUI {
    public static ComponentUI createUI(JComponent b) {
        return new UICheckBox();
    }
    
    @Override
    public synchronized void paint(Graphics g, JComponent c) {
        JCheckBox chk = (JCheckBox)c;
        Graphics2D g2d = (Graphics2D)g;
        super.paint(g2d, chk);

        chk.setBackground(null);
        chk.setForeground(Color.decode(StyleColorsMetro.LIGHT_FOREGROUND));
        chk.setFont(new Font(UITools.FONT_DEFAULT, chk.getFont().getStyle(), chk.getFont().getSize()));
        chk.setIcon(createCheckImage(18, 18, chk.isSelected()));
    }
    
    /**
     * Crea la imagen del checkbox
     * @param width Ancho de la imagen
     * @param height Alto de la imagen
     * @param checked Define si el checkbox esta seleccionado o no
     * @return Retorna la imagen generada
     */
    private ImageIcon createCheckImage(int width, int height, boolean checked) {
        BufferedImage image = new BufferedImage(width + 5, height + 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo del check
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#FFFFFF"));
        if (MetroUIConfigTheme.isDarkMode())
            g2d.setColor(Color.decode("#CCCCCC"));
        g2d.fillRect(1, 1, width, height);

        // Borde del check
        AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2d.setComposite(ac2);
        g2d.setColor(Color.decode("#212121"));
        if (MetroUIConfigTheme.isDarkMode())
            g2d.setColor(Color.decode("#A6A6A6"));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(1, 1, width - 1, height - 1);

        if (checked) {
            // Icono del check activado
            g2d.setStroke(new BasicStroke(3));

            // Las ubicaciones se miden en porcentaje para que no se deforme si crece
            int inicioX = (50 * width) / 100;
            int inicioY = (75 * height) / 100;
            int medioX = (75 * width) / 100;
            int medioY = (35 * height) / 100;
            int finalX = (35 * width) / 100;
            int finalY = (60 * height) / 100;

            g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
            g2d.drawLine(inicioX, inicioY, medioX, medioY);            
            g2d.drawLine(finalX, finalY, inicioX, inicioY);
        }
        
        return new ImageIcon(image);
    }
}
