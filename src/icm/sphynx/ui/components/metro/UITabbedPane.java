package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * Asigna estilos al componente de tabs para paneles
 * @author israel-icm
 */
public class UITabbedPane extends BasicTabbedPaneUI {
    public static ComponentUI createUI(JComponent c) {
        return new UITabbedPane();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        // g.setColor(Color.decode(StyleColors.COLOR_PRIMARY));
        g.setColor(MetroUIConfigTheme.getPrimaryColor());
        g.fillRect(0, 0, tabPane.getWidth(), tabPane.getHeight());
        c.setFocusable(false);
        super.paint(g, c);
        // c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
        /*g.setColor(Color.WHITE);
        g.fillRect(0, 0, tabPane.getWidth(), tabPane.getHeight());*/
    }

    /**
     * Estilos de cada uno de los tabs estén seleccionados o no
     * @param g
     * @param tabPlacement
     * @param tabIndex
     * @param x
     * @param y
     * @param w
     * @param h
     * @param isSelected 
     */
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2d = (Graphics2D)g;

        if (isSelected) {
            g2d.setColor(Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
            if (MetroUIConfigTheme.isDarkMode())
                g2d.setColor(Color.decode(StyleColorsMetro.DARK_BACKGROUND_PANEL));
            g2d.fillRoundRect(x, y, w, h + 5, 8, 8);
            // g2d.fillRect(x, y, w, h + 3);
        }
        else {
            g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
            g2d.fillRect(x, y, w, h + 2);
        }
    }

    /**
     * Se pintan los bordes de cada tab esten seleccionados o no
     * @param g
     * @param tabPlacement
     * @param tabIndex
     * @param x
     * @param y
     * @param w
     * @param h
     * @param isSelected 
     */
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2d = (Graphics2D)g;
        
        if (isSelected) {
            BasicStroke stroke = new BasicStroke(2);
            g2d.setStroke(stroke);
            g2d.setColor(Color.decode("#CCCCCC"));
            g2d.drawRoundRect(x, y, w, h + 5, 8, 8);
        }
        else {
            /*g2d.setColor(Color.decode(UITools.COLOR_PRIMARY));
            g2d.drawRect(x, y, 0, 0);*/
            if (tabIndex > 0) {
                BasicStroke stroke = new BasicStroke(1);
                g2d.setStroke(stroke);
                g2d.setColor(Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
                if (MetroUIConfigTheme.isDarkMode())
                    g2d.setColor(Color.decode(StyleColorsMetro.DARK_BACKGROUND_PANEL));
                g2d.drawLine(x, y + 5, x, y + 28);
            }
        }
    }

    /**
     * Estilos del texto de cada uno de los tabs seleccionados o no
     * @param g
     * @param tabPlacement
     * @param font
     * @param metrics
     * @param tabIndex
     * @param title
     * @param textRect
     * @param isSelected 
     */
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font(UITools.FONT_DEFAULT, font.getStyle(), font.getSize()));
        if (isSelected) {
            g2d.setColor(Color.decode("#212121"));
            if (MetroUIConfigTheme.isDarkMode())
                g2d.setColor(Color.decode(StyleColorsMetro.DARK_FOREGROUND));
            g2d.drawString(title, textRect.x - 5, textRect.y + font.getSize() + 3);
        }
        else {
            g2d.setColor(Color.decode("#FFFFFF"));
            g2d.drawString(title, textRect.x - 5, textRect.y + font.getSize());
        }
    }
    
    /**
     * Borden del componente principal
     * @param g
     * @param tabPlacement
     * @param selectedIndex 
     */
    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        tabPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    }

    @Override
    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
        // rects[tabIndex].height = 40;
        // rects[tabIndex].y = -10; // rects[tabIndex].height;
        super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
    }

    /**
     * Asignamos un nuevo alto a los tabs
     * @param tabPlacement
     * @param tabIndex
     * @param fontHeight
     * @return 
     */
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        // Esto es para que el alto de los tabs no sea tan pequeño
        return fontHeight * 2;
    }
}
