package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;import javax.swing.UIManager;
;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Estilos para los botones
 * @author israel-icm
 */
public class UIButton extends BasicButtonUI implements MouseListener {
    private Graphics2D g2d;
    private int width = 0;
    private int height = 0;
    private int estado = 0;
    
    public static ComponentUI createUI(JComponent c) {
        return new UIButton();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        // c.addMouseListener(this);
        g2d = (Graphics2D)g;
        width = c.getWidth();
        height = c.getHeight();

        if (estado == 0) {
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(ac);
            g.setColor(MetroUIConfigTheme.getPrimaryColor());
            g.fillRect(0, 0, width, height);
            /*LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BUTTON_DEFAULT), 3);
            c.setBorder(border);*/
        }
        /*else {
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
            g2d.setComposite(ac);
            g.setColor(Color.decode(UITools.COLOR_BUTTON_DEFAULT));
            g.fillRect(0, 0, width, height);
            LineBorder border = new LineBorder(Color.RED, 3);
            c.setBorder(border);
        }*/
        super.paint(g, c);
    }

    @Override
    protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {
        super.paintText(g, c, textRect, text);
        c.setForeground(Color.decode(UITools.COLOR_BUTTON_FOREGROUND_DEFAULT));
        c.setFont(new Font(UITools.FONT_DEFAULT, c.getFont().getStyle(), c.getFont().getSize()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /*g.setColor(Color.decode(UITools.COLOR_BUTTON_DEFAULT));
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BUTTON_DEFAULT), 2);
        b.setBorder(border);*/
        estado = 1;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /*if (estado != 0)
            estado = 0;*/
    }
}
