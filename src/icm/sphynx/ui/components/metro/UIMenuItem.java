package icm.sphynx.ui.components.metro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;

/**
 * Asigna los estilos para los items de menú
 * @author israel-icm
 */
public class UIMenuItem extends BasicMenuItemUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIMenuItem();
    }

    @Override
    protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        super.paintBackground(g, menuItem, bgColor);
        // menuItem.setBackground(Color.WHITE);
        // menuItem.setBorder(BorderFactory.createCompoundBorder(menuItem.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }
    
    @Override
    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font(UITools.FONT_DEFAULT, menuItem.getFont().getStyle(), menuItem.getFont().getSize()));
        g2d.drawString(text, textRect.x, textRect.height - textRect.y);
    }
}
