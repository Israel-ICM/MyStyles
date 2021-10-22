package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;

/**
 * Asigna los estilos para los items de menú
 * @author israel-icm
 */
public class UIMenuItem extends BasicMenuItemUI {
    private String COLOR_FOREGROUND = MetroUIStyleColors.MENU_FOREGROUND;
    private String COLOR_BACKGROUND = MetroUIStyleColors.MENU_BACKGROUND;
    private String COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());

    public static ComponentUI createUI(JComponent c) {
        return new UIMenuItem();
    }
    @Override
    protected void installDefaults() {
        menuItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        // menuItem.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 13)); // Esto solo es un auxiliar para poder centrar el texto del menu
        super.installDefaults();
    }
    @Override
    protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        installColors();
        menuItem.setBackground(Color.decode(COLOR_BACKGROUND));
        bgColor = Color.decode(COLOR_BACKGROUND_SELECT);
        // g.setColor(Color.decode(COLOR_BACKGROUND));
        // g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
        g.setColor(Color.decode(COLOR_FOREGROUND)); // Para el texto solo es necesario setear el color
        super.paintBackground(g, menuItem, bgColor);
        // menuItem.setBorder(BorderFactory.createCompoundBorder(menuItem.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }
    
    @Override
    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 14));
        g2d.drawString(text, textRect.x, textRect.y + (textRect.height / 2) + 5);
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_FOREGROUND = MetroUIStyleColors.MENU_FOREGROUND_DARK;
            COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getSecondColor());
            COLOR_BACKGROUND = MetroUIStyleColors.MENU_BACKGROUND_DARK;
        }
        else {
            COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
        }
    }
}
