package com.sphynxs.mystyles.ui.metro.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

/**
 *
 * @author israel-icm
 */
public class CaretMetro extends DefaultCaret {
    /*@Override
    protected synchronized void damage(Rectangle r) {
        if (r == null)
            return;
        x = r.x;
        y = r.y;
        height = r.height;
        if (width <= 0) width = getComponent().getWidth();
            repaint();      
    }*/

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 3, height);
        
        /*JTextComponent comp = getComponent();
        if (comp == null)
            return;

        int dot = getDot();
        Rectangle r = null;
        char dotChar;
        try {
            r = comp.modelToView(dot);
            if (r == null)
                return;
            dotChar = comp.getText(dot, 1).charAt(0);
        } catch (BadLocationException e) {
            return;
        }

        if ( (x != r.x) || (y != r.y) ) {
            repaint(); 
            x = r.x;
            y = r.y;
            height = r.height;
        }

        g.setColor(Color.decode("#FF0000"));
        g.setXORMode(comp.getBackground());        
        // ImageIcon imgcaret=null;

        if (dotChar == '\n') {
            int diam = r.height;
            if (isVisible()) {
                g.fillRect(r.x, r.y, 10, 16);
                // g.drawImage(imgcaret.getImage(), r.x, r.y,20,17, comp);
            }
            width = diam / 2 + 2;
            return;
        }

        width = g.getFontMetrics().charWidth(dotChar);
        if (isVisible())
            g.fillRect(r.x, r.y, width, r.height);*/
    }
}
