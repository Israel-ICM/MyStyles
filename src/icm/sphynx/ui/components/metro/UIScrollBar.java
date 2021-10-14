package icm.sphynx.ui.components.metro;

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
    private Graphics2D graphicsBarra;
    private Rectangle rectangleBarra;
    private JComponent barra;
    // private JComponent 
    /**
     * Estados: 0 = default, 1 = over, 2 = pressed
     */
    private int estadoBarra = 0;
    private static int DEFAULT = 0;
    private static int OVER = 1;
    private static int PRESSED = 2;
    
    private static int WIDTH_SCROLL_DEFAULT = 10; // Lo normal es 16
    private static int HEIGHT_SCROLL_DEFAULT = 10;
    private static int WIDTH_SCROLL = 1;
    private static int HEIGHT_SCROLL = 1;

    public UIScrollBar() {
        super();
    }
    
    // Al usar UIManager.put este metodo tiene que estar si o si en las clases heredadas
    public static ComponentUI createUI(JComponent c) { 
        return new UIScrollBar(); 
    }

    @Override 
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        /*BufferedImage image = new BufferedImage (WIDTH_SCROLL, HEIGHT_SCROLL, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g1 = (Graphics2D)image.getGraphics();
        g1.setColor(Color.decode("#FFFFFF"));
        g1.fillRect(WIDTH_SCROLL_DEFAULT - WIDTH_SCROLL, 0, WIDTH_SCROLL, HEIGHT_SCROLL);
        // g1.dispose();
        g.drawImage(image, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, null);*/
        c.setBackground(Color.decode("#CCCCCC"));
        if (MetroUIConfigTheme.getDarkMode())
            c.setBackground(Color.decode("#A6A6A6"));
    } 

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) { 
        c.addMouseListener(this);
        graphicsBarra = (Graphics2D)g;
        rectangleBarra = thumbBounds;

        // La variable scrollbar viene de la herencia de BasicScrollBarUI
        switch (scrollbar.getOrientation()) {
            case JScrollBar.VERTICAL:
                // graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 1f), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                if (estadoBarra == UIScrollBar.OVER) {
                    scrollbar.setPreferredSize(new Dimension(16, scrollbar.getHeight()));
                    graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.9f), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                }
                // else if (estadoBarra == UIScrollBar.PRESSED)
                //     graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, WIDTH_SCROLL_DEFAULT, COLOR_DEFAULT, 0.7f), WIDTH_SCROLL_DEFAULT - WIDTH_SCROLL, thumbBounds.y, WIDTH_SCROLL, thumbBounds.height, null);
                else {
                    scrollbar.setPreferredSize(new Dimension(8, scrollbar.getHeight()));
                    graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.6f), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                    // Con esto el el thumb del scroll se vuelve menos ancho
                    // graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.7f), WIDTH_SCROLL_DEFAULT - WIDTH_SCROLL, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                }
                break;
            case JScrollBar.HORIZONTAL:
                
                if (estadoBarra == UIScrollBar.OVER) {
                    scrollbar.setPreferredSize(new Dimension(scrollbar.getWidth(), 16));
                    graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.9f), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                }
                // else if (estadoBarra == UIScrollBar.PRESSED)
                //     graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, WIDTH_SCROLL_DEFAULT, COLOR_DEFAULT, 0.7f), WIDTH_SCROLL_DEFAULT - WIDTH_SCROLL, thumbBounds.y, WIDTH_SCROLL, thumbBounds.height, null);
                else {
                    scrollbar.setPreferredSize(new Dimension(scrollbar.getWidth(), 8));
                    graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.6f), thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, null);
                    // graphicsBarra.drawImage(createImageThumb(WIDTH_SCROLL, HEIGHT_SCROLL, UITools.COLOR_SCROLL_DEFAULT, 0.7f), thumbBounds.x, HEIGHT_SCROLL_DEFAULT - HEIGHT_SCROLL, thumbBounds.width, thumbBounds.height, null);
                }
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
    /**
     * Se crea la imagen del scroll
     * @param width
     * @param height
     * @param colorHex
     * @param alpha
     * @return 
     */
    private Image createImageThumb(int width, int height, String colorHex, float alpha) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)image.getGraphics();
        
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        graphics.setComposite(ac);
        
        graphics.setColor(Color.decode(colorHex));
        graphics.fillRect(0, 0, width, height);
        // graphics.dispose();
        return image;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // estadoBarra = UIScrollBar.PRESSED;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // estadoBarra = UIScrollBar.DEFAULT;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        estadoBarra = OVER;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        estadoBarra = DEFAULT;
    }
}