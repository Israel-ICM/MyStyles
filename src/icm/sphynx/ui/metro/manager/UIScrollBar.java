package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.MetroUIStyleColors;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Asigna estilos a las barras de scroll horizontal y vertical
 * @author israel-icm
 */
public class UIScrollBar extends BasicScrollBarUI implements MouseListener {
    private String COLOR_TRACK_BACKGROUND = MetroUIStyleColors.SCROLL_TRACK_BACKGROUND;
    private String COLOR_THUMB_BACKGROUND = MetroUIStyleColors.SCROLL_THUMB_BACKGROUND;
    
    private Graphics2D g2dThumb;

    private int estadoBarra = 0;
    private final static int DEFAULT = 0;
    private final static int OVER = 1;
    private final static int PRESSED = 2;
    
    private boolean trackIniciado = false;
    private boolean thumbIniciado = false;
    
    private static final int ICON_WIDTH_SCROLL = 1;
    private static final int ICON_HEIGHT_SCROLL = 1;
    
    private static final int SIZE_SCROLL_NORMAL = 3;
    private static final int SIZE_SCROLL_OVER = 16;
    
    // Controladores de animación
    private boolean verScroll = false; // Define si se visualizará el scroll
    private int controlAnimation = SIZE_SCROLL_NORMAL; // Define el tamaño del scroll

    public UIScrollBar() {
        super();
    }
    
    // Al usar UIManager.put este metodo tiene que estar si o si en las clases heredadas
    public static ComponentUI createUI(JComponent c) { 
        return new UIScrollBar(); 
    }

    @Override 
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        installColors();
        if (!trackIniciado) {
            c.addMouseListener(this);
            trackIniciado = true;
        }
        /*BufferedImage image = new BufferedImage (WIDTH_SCROLL, HEIGHT_SCROLL, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = (Graphics2D)image.getGraphics();
        g1.setColor(Color.decode("#FFFFFF"));
        g1.fillRect(WIDTH_SCROLL_DEFAULT - WIDTH_SCROLL, 0, WIDTH_SCROLL, HEIGHT_SCROLL);
        // g1.dispose();
        g.drawImage(image, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);*/
        c.setBackground(Color.decode(COLOR_TRACK_BACKGROUND));
    } 

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) { 
        if (!thumbIniciado) {
            c.addMouseListener(this);
            thumbIniciado = true;
        }
        
        g2dThumb = (Graphics2D)g;

        // La variable scrollbar viene de la herencia de BasicScrollBarUI
        if (verScroll) {
            if (controlAnimation < SIZE_SCROLL_OVER)
                controlAnimation++;
        }
        else {
            if (controlAnimation > SIZE_SCROLL_NORMAL)
                controlAnimation--;
        }
        switch (scrollbar.getOrientation()) {
            case JScrollBar.VERTICAL:
                scrollbar.setPreferredSize(new Dimension(controlAnimation, scrollbar.getHeight()));
                g2dThumb.drawImage(createImageThumb(ICON_WIDTH_SCROLL, ICON_HEIGHT_SCROLL, COLOR_THUMB_BACKGROUND), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);                
                // Con esto el el thumb del scroll se vuelve menos ancho
                // graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.7f), WIDTH_SCROLL_DEFAULT - WIDTH_SCROLL, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                break;
            case JScrollBar.HORIZONTAL:
                scrollbar.setPreferredSize(new Dimension(scrollbar.getWidth(), controlAnimation));
                g2dThumb.drawImage(createImageThumb(ICON_WIDTH_SCROLL, ICON_HEIGHT_SCROLL, COLOR_THUMB_BACKGROUND), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                break;
        }
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(null);
        return button;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton();
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setBorder(null);
        return button;
    }
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_TRACK_BACKGROUND = MetroUIStyleColors.SCROLL_TRACK_BACKGROUND_DARK;
            COLOR_THUMB_BACKGROUND = MetroUIStyleColors.SCROLL_THUMB_BACKGROUND_DARK;
        }
    }
    /**
     * Se crea la imagen del scroll
     * @param width
     * @param height
     * @param colorHex
     * @param alpha
     * @return 
     */
    private Image createImageThumb(int width, int height, String colorHex) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();

        switch (estadoBarra) {
            case PRESSED:
                graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                break;
            default:
                graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
                break;
        }

        graphics.setColor(Color.decode(colorHex));
        graphics.fillRect(0, 0, width, height);
        // graphics.dispose();
        return image;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        estadoBarra = PRESSED;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        estadoBarra = DEFAULT;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        verScroll = true;
        estadoBarra = OVER;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        estadoBarra = DEFAULT;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    if (estadoBarra != OVER) { // Esto es por si despues de alejar el mouse se vuelve a hacer hover no se oculte la barra
                        verScroll = false;
                        scrollbar.repaint();
                    }
                }
                catch (InterruptedException ex) { }
            }
        }.start();
    }
}