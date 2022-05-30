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
import javax.swing.plaf.basic.BasicMenuUI;

/**
 * Asigna los estilos para todos los menús
 * @author israel-icm
 */
public class UIMenu extends BasicMenuUI {
    private String COLOR_FOREGROUND = MetroUIStyleColors.MENU_FOREGROUND;
    private String COLOR_BACKGROUND = MetroUIStyleColors.MENU_BACKGROUND;
    private String COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
    private String COLOR_ICON = MetroUIStyleColors.MENU_ICON;
    
    private boolean componenteIniciado = false;

    public static ComponentUI createUI(JComponent c) {
        return new UIMenu();
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

        bgColor = Color.decode(COLOR_BACKGROUND_SELECT);
        g.setColor(Color.decode(COLOR_BACKGROUND));
        g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
        g.setColor(Color.decode(COLOR_FOREGROUND)); // Para el texto solo es necesario setear el color
        super.paintBackground(g, menuItem, bgColor);
        // menuItem.setBorder(BorderFactory.createCompoundBorder(menuItem.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }
    
    @Override
    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 14));
        g2d.drawString(text, textRect.x, textRect.y + (textRect.height / 2) + 4);
    }

    @Override
    protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
        arrowIcon = createMenuMoreImage(20, 20);
        // background = Color.decode(COLOR_BACKGROUND_SELECT);
        super.paintMenuItem(g, c, checkIcon, arrowIcon, background, foreground, defaultTextIconGap);
        if (!componenteIniciado) { // Este repaint solo se ejecuta la primera vez ya que por alguna razón el color oscuro no lo carga bien en el icono generado
            c.repaint();
            componenteIniciado = true;
        }
    }

    @Override
    protected Dimension getPreferredMenuItemSize(JComponent c, Icon checkIcon, Icon arrowIcon, int defaultTextIconGap) {
        Dimension size = super.getPreferredMenuItemSize(c, checkIcon, arrowIcon, defaultTextIconGap);
        size.width = size.width + 30; // Se agrega 30 por el tamaño del icono y el borde
        return size;
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_FOREGROUND = MetroUIStyleColors.MENU_FOREGROUND_DARK;
            COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getSecondColor());
            COLOR_ICON = MetroUIStyleColors.MENU_ICON_DARK;
            COLOR_BACKGROUND = MetroUIStyleColors.MENU_BACKGROUND_DARK;
        }
        else {
            COLOR_BACKGROUND_SELECT = UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor());
        }
    }
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
    }
    
    private ImageIcon createMenuMoreImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int moverDerecha = 3;
        int xIniFin = ((width / 2) - 2) + moverDerecha;
        int xPuntoMedio = ((width / 2) + 3) + moverDerecha;
        int yPuntoMedio = (height / 2) - 1;

        g2d.setColor(Color.decode(COLOR_ICON));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(xIniFin, 2, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, xIniFin, height - 5);
        return new ImageIcon(image);
    }
}
