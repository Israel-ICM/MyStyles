package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
        menuItem.setForeground(Color.decode(COLOR_FOREGROUND));
        bgColor = Color.decode(COLOR_BACKGROUND_SELECT);
        super.paintBackground(g, menuItem, bgColor);
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
    
    @Override
    protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
        Dimension size = super.getPreferredMenuItemSize(c, checkIcon, arrowIcon, defaultTextIconGap);
        size.width = size.width + 30; // Se agrega 30 por el tamaño del icono y el borde
        return size;
    }
}
