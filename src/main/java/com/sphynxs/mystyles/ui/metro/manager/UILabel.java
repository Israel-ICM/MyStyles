package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

/**
 * Asigna los estilos para todos los labels
 * @author israel-icm
 */
public class UILabel extends BasicLabelUI {
    public static ComponentUI createUI(JComponent c) {
        return new UILabel();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, c.getFont().getSize()));
        if (MetroUIConfigTheme.isDarkMode())
            c.setForeground(Color.WHITE);
        super.paint(g, c);
    }

    /*@Override
    protected void paintEnabledText(JLabel label, Graphics g, String s, int textX, int textY) {
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode(StyleColors.LIGHT_FOREGROUND));
        g2d.setFont(new Font(UITools.FONT_DEFAULT, label.getFont().getStyle(), label.getFont().getSize()));
        g2d.drawString(s, textX, textY);
    }*/
}
