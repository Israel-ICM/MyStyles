package com.sphynxs.mystyles.ui.metro.components;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.LineBorder;

/**
 *
 * @author israel-icm
 */
public class BorderShadow extends LineBorder {
    public BorderShadow(Color color, int thickness) {
        super(color, thickness);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D)g;

        Insets insets = getBorderInsets(c);
        g2d.setColor(lineColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float transparencia = 1;
        float div = (1.0f / insets.top);

        int bajarSombra = 4; // Establece si se bajará la sombra y cuantos puntos se bajará para hacer el cálculo y no dejar espacios en blanco
        
        int top = insets.top;
        int bottom = height - (insets.bottom + insets.top) - bajarSombra;
        int left = insets.left;
        int right = width - (insets.left + insets.right);
        for (int i = insets.left; i > 0; i--) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));
            g2d.drawRect(left, top + bajarSombra, right, bottom);
            top--;
            left--;
            right+=2;
            bottom+=2;
            transparencia = transparencia - div;
        }

        // Agregamos un borde complementario para el componente
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.decode("#CCCCCC"));
        g2d.drawRect(insets.left - 1, insets.top - 1, (width - (insets.left * 2)) + 1, (height - (insets.top * 2)) + 1);
    }
}
