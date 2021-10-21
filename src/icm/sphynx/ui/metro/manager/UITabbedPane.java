package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.StyleColorsMetro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * Asigna estilos al componente de tabs para paneles
 * @author israel-icm
 */
public class UITabbedPane extends BasicTabbedPaneUI {
    private String COLOR_BACKGROUND_LEFT = StyleColorsMetro.TAB_BACKGROUND;
    private String COLOR_BACKGROUND_TAB_LEFT_OVER = StyleColorsMetro.TAB_BACKGROUND_OVER;
    private String COLOR_BACKGROUND_TAB_LEFT_PRESSED = StyleColorsMetro.TAB_BACKGROUND_PRESSED;
    private String COLOR_FOREGROUND_TAB_LEFT = StyleColorsMetro.TAB_FOREGROUND;
    private static final int PADDING_LEFT_ICON = 10; // Indica el ancho que se deja a la izquierda del ícono (Si existe)

    private static final int STATE_DEFAULT = 0;
    private static final int STATE_OVER = 1;
    private static final int STATE_PRESSED = 2;
    private int currentStateButtonPane = 0; // Controla los estados del slider
    
    private boolean componenteIniciado = false;
    private int positionYMarcaSelect = 0; // Indica la posición del tab marcado
    int[] positionMouse = {0, 0}; // Sirve para identificar si el mouse esta sobre un tab (x, y)
    
    
    public static ComponentUI createUI(JComponent c) {
        return new UITabbedPane();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D)g;

        switch (tabPane.getTabPlacement()) {
            case TOP:
                // g.setColor(Color.decode(StyleColors.COLOR_PRIMARY));
                g2d.setColor(Color.decode("#FFFFFF"));
                if (MetroUIConfigTheme.isDarkMode())
                    g2d.setColor(Color.decode("#000000"));
                g2d.fillRect(0, 0, tabPane.getWidth(), tabPane.getHeight());
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                g2d.drawRect(1, 1, tabPane.getWidth() - 2, tabPane.getHeight() - 2);
                break;
            case LEFT:
                if (MetroUIConfigTheme.isDarkMode())
                    COLOR_BACKGROUND_LEFT = StyleColorsMetro.TAB_BACKGROUND_DARK;
                g2d.setColor(Color.decode(COLOR_BACKGROUND_LEFT));
                    
                g2d.fillRect(0, 0, calculateMaxTabWidth(LEFT) + 5, tabPane.getHeight());
                break;
        }
        c.setFocusable(false);
        super.paint(g, c);
        
        installEvents();
        // c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
        /*g.setColor(Color.WHITE);
        g.fillRect(0, 0, tabPane.getWidth(), tabPane.getHeight());*/
    }

    private void installEvents() {
        if (!componenteIniciado) {
            tabPane.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) { }

                @Override
                public void mousePressed(MouseEvent e) {
                    currentStateButtonPane = STATE_PRESSED;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    currentStateButtonPane = STATE_DEFAULT;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    currentStateButtonPane = STATE_OVER;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    currentStateButtonPane = STATE_DEFAULT;
                }
            });
            tabPane.addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) { }

                @Override
                public void mouseMoved(MouseEvent e) {
                    positionMouse[0] = e.getX();
                    positionMouse[1] = e.getY();
                }
            });
            componenteIniciado = true;
        }
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

        switch (tabPlacement) {
            case TOP:
                if (isSelected) {
                    g2d.setColor(Color.decode(StyleColorsMetro.PANEL_BACKGROUND));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode(StyleColorsMetro.PANEL_BACKGROUND_DARK));
                    g2d.fillRoundRect(x, y, w, h + 5, 8, 8);
                    // g2d.fillRect(x, y, w, h + 3);
                }
                else {
                    g2d.setColor(Color.decode("#DCDCDC"));
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode("#000000"));
                    g2d.fillRect(x, y, w, h + 2);
                }
                break;
            case LEFT:
                if (MetroUIConfigTheme.isDarkMode()) {
                    COLOR_BACKGROUND_TAB_LEFT_OVER = StyleColorsMetro.TAB_BACKGROUND_OVER_DARK;
                    COLOR_BACKGROUND_TAB_LEFT_PRESSED = StyleColorsMetro.TAB_BACKGROUND_PRESSED_DARK;
                }
                // Si el mouse se encuentra sobre alguna pestaña (Mouse Over)
                if (positionMouse[0] >= x && positionMouse[0] <= (x + w) && positionMouse[1] >= y && positionMouse[1] <= (y + h)) {
                    g2d.setColor(Color.decode(COLOR_BACKGROUND_TAB_LEFT_OVER));
                    g2d.fillRect(x, y, w, h);
                    switch (currentStateButtonPane) {
                        /*case STATE_DEFAULT:
                            g2d.setColor(Color.decode(COLOR_BACKGROUND));
                            g2d.fillRect(x, y, w, h);
                            break;
                        case STATE_OVER:
                            g2d.setColor(Color.decode("#DCDEDC"));
                            g2d.fillRect(x, y, w, h);
                            break;*/
                        case STATE_PRESSED:
                            g2d.setColor(Color.decode(COLOR_BACKGROUND_TAB_LEFT_PRESSED));
                            g2d.fillRect(x, y, w, h);
                            break;
                    }
                }
                else { // Si el mouse no esta sobre ninguna pestaña
                    g2d.setColor(Color.decode(COLOR_BACKGROUND_LEFT));
                    g2d.fillRect(x, y, w, h);
                }
                
                if (isSelected) {
                    // Este pequeño bloque genera la animación para cuando se clickea una pestaña
                    if (y - positionYMarcaSelect > 0 || positionYMarcaSelect - y > 0) { // Esta validación es para que no cambie de posición a cada rato
                        if (y > positionYMarcaSelect)
                            positionYMarcaSelect++;
                        else
                            positionYMarcaSelect--;
                    }

                    int altoMarca = h / 2;
                    int yMarca = positionYMarcaSelect + (altoMarca / 2);
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    g2d.fillRect(x, yMarca, 4, altoMarca);
                }
                break;
        }
    }

    @Override
    protected void paintIcon(Graphics g, int tabPlacement, int tabIndex, Icon icon, Rectangle iconRect, boolean isSelected ) {
        if (icon != null) {
            switch (tabPlacement) {
                case LEFT:
                    icon.paintIcon(tabPane, g, PADDING_LEFT_ICON, iconRect.y);
                    break;
                case BOTTOM:
                case RIGHT:
                case TOP:
                    icon.paintIcon(tabPane, g, iconRect.x, iconRect.y);
                    break;
            }
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
        
        switch (tabPane.getTabPlacement()) {
            case TOP:
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
                        g2d.setColor(Color.decode(StyleColorsMetro.PANEL_BACKGROUND));
                        if (MetroUIConfigTheme.isDarkMode())
                            g2d.setColor(Color.decode(StyleColorsMetro.PANEL_BACKGROUND_DARK));
                        g2d.drawLine(x, y + 5, x, y + 28);
                    }
                }
                break;
            case LEFT:
                break;
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
        
        switch (tabPane.getTabPlacement()) {
            case TOP:
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // g2d.setFont(new Font(UITools.FONT_DEFAULT, font.getStyle(), font.getSize()));
                if (isSelected) {
                    g2d.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 14));
                    g2d.setColor(MetroUIConfigTheme.getPrimaryColor());
                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode(StyleColorsMetro.PANEL_FOREGROUND_DARK));
                    g2d.drawString(title, textRect.x - 5, textRect.y + font.getSize() + 3);
                }
                else {
                    g2d.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 13));
                    g2d.setColor(Color.decode("#000000"));
                    if (MetroUIConfigTheme.isDarkMode()) {
                        g2d.setColor(Color.decode("#CCCCCC"));
                    }
                    g2d.drawString(title, textRect.x - 5, textRect.y + font.getSize());
                }
                break;
            case LEFT:
                if (MetroUIConfigTheme.isDarkMode()) {
                    COLOR_FOREGROUND_TAB_LEFT = StyleColorsMetro.TAB_FOREGROUND_DARK;
                }
                
                int xPositionText = 0;
                if (tabPane.getIconAt(tabIndex) != null) {
                    xPositionText = tabPane.getIconAt(tabIndex).getIconWidth() + PADDING_LEFT_ICON + 10;
                }
                
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // g2d.setFont(new Font(UITools.FONT_DEFAULT, font.getStyle(), font.getSize()));
                if (isSelected) {
                    g2d.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 14));
                    g2d.setColor(Color.decode(COLOR_FOREGROUND_TAB_LEFT));
/*                    if (MetroUIConfigTheme.isDarkMode())
                        g2d.setColor(Color.decode(StyleColorsMetro.PANEL_FOREGROUND_DARK));*/
                    g2d.drawString(title, xPositionText, textRect.y + font.getSize() + 3);
                }
                else {
                    g2d.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 14));
                    g2d.setColor(Color.decode(COLOR_FOREGROUND_TAB_LEFT));
                    g2d.drawString(title, xPositionText, textRect.y + font.getSize() + 3);
                }
                break;
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
        switch (tabPane.getTabPlacement()) {
            case TOP:
                tabPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                break;
            case LEFT:
                tabPane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
                break;
        }
    }

    @Override
    protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
        // rects[tabIndex].y = rects[tabIndex].y + 40;
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
        switch (tabPane.getTabPlacement()) {
            case TOP:
                // Esto es para que el alto de los tabs no sea tan pequeño
                return fontHeight * 2;
            case LEFT:
                return (fontHeight * 3) + 1;
        }
        return fontHeight;
    }
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        switch (tabPane.getTabPlacement()) {
            case TOP:
                return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
            case LEFT:
                return super.calculateTabWidth(tabPlacement, tabIndex, metrics) + 10;
        }
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }
    /*protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabWidth) {
        switch (tabPane.getTabPlacement()) {
            case TOP:
                return super.calculateTabAreaWidth(tabPlacement, vertRunCount, maxTabWidth);
            case LEFT:
                return super.calculateTabAreaWidth(tabPlacement, vertRunCount, maxTabWidth + 30) - 30;
        }
        return super.calculateTabAreaWidth(tabPlacement, vertRunCount, maxTabWidth);
    }*/
}
