package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
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
import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

/**
 * Asigna los estilos para los menús de tipo combobox
 * @author israel-icm
 */
public class UICheckBoxMenuItem extends BasicCheckBoxMenuItemUI{
    private String COLOR_FOREGROUND = MetroUIStyleColors.MENU_FOREGROUND;
    private String COLOR_BACKGROUND = MetroUIStyleColors.MENU_BACKGROUND;
    private String COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
    private String COLOR_ICON_BACKGROUND = MetroUIStyleColors.CHECKBOX_ICON_BACKGROUND;
    private String COLOR_ICON_BORDER = MetroUIStyleColors.CHECKBOX_ICON_BORDER;
    private String COLOR_ICON_CHECK = MetroUIStyleColors.CHECKBOX_ICON_CHECK;
    
    public static ComponentUI createUI(JComponent c) {
        return new UICheckBoxMenuItem();
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
            COLOR_ICON_CHECK = MetroUIStyleColors.CHECKBOX_ICON_CHECK_DARK;
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

        if (!checked) {
            // Fondo del check
            g2d.setColor(Color.decode(COLOR_ICON_BACKGROUND));
            g2d.fillRect(1, 1, width, height);

            // Borde del check
            g2d.setColor(Color.decode(COLOR_ICON_BORDER));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(1, 1, width - 1, height - 1);
        }
        else {
            // Fondo del check
            g2d.setColor(Color.decode(COLOR_BACKGROUND_SELECT));
            g2d.fillRect(1, 1, width, height);

            // Borde del check
            g2d.setColor(Color.decode(COLOR_BACKGROUND_SELECT));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(1, 1, width - 1, height - 1);
                    
            // Icono del check activado
            g2d.setStroke(new BasicStroke(2));

            // Las ubicaciones se miden en porcentaje para que no se deforme si crece
            int inicioX = (18 * width) / 100;
            int inicioY = (60 * height) / 100;
            int medioX = (40 * width) / 100;
            int medioY = (80 * height) / 100;
            int finalX = (90 * width) / 100;
            int finalY = (30 * height) / 100;

            g2d.setColor(Color.decode(COLOR_ICON_CHECK));
            g2d.drawLine(inicioX, inicioY, medioX, medioY);
            g2d.drawLine(medioX, medioY, finalX, finalY);
        }
        return new ImageIcon(image);
    }
}
