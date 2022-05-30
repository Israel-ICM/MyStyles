package com.icm.sphynx.mystyles.ui.metro.manager;

import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

/**
 * Asigna los estilos para los menús de tipo radio button
 * @author israel-icm
 */
public class UIRadioButtonMenuItem extends BasicRadioButtonMenuItemUI {
    private String COLOR_FOREGROUND = MetroUIStyleColors.MENU_FOREGROUND;
    private String COLOR_BACKGROUND = MetroUIStyleColors.MENU_BACKGROUND;
    private String COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
    private String COLOR_ICON_BACKGROUND = MetroUIStyleColors.RADIO_BUTTON_ICON_BACKGROUND;
    private String COLOR_ICON_BORDER = MetroUIStyleColors.RADIO_BUTTON_ICON_BORDER;
    
    public static ComponentUI createUI(JComponent c) {
        return new UIRadioButtonMenuItem();
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
        checkIcon = createCheckImage(20, 20, menuItem.isSelected());
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
            COLOR_ICON_BACKGROUND = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND_DARK;
            COLOR_ICON_BORDER = MetroUIStyleColors.CHECKBOX_ICON_BORDER_DARK;
        }
        else {
            COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
        }
    }
    
    @Override
    protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
        Dimension size = super.getPreferredMenuItemSize(c, checkIcon, arrowIcon, defaultTextIconGap);
        size.width = size.width + 20; // Se agrega 20 por el tamaño del checkbox
        return size;
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
            // Fondo del check
            g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND));
            g2d.fillOval(1, 1, width, height);

            // Borde del check
            g2d.setColor(Color.decode(COLOR_ICON_BORDER));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(1, 1, width - 1, height - 1);
        }
        else {
            // Fondo del check
            g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND));
            g2d.fillOval(1, 1, width, height);

            // Borde del check
            g2d.setColor(Color.decode(COLOR_ICON_BORDER));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(1, 1, width - 1, height - 1);

            int medio = width / 2;
            int x = medio - (medio / 2);
            int y = medio - (medio / 2);
            int ancho = (60 * width) / 100; // El check sera el 60% del cuadro original
            
            g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
            g2d.fillOval(x, y, ancho, ancho);
        }
        return new ImageIcon(image);
    }
}
