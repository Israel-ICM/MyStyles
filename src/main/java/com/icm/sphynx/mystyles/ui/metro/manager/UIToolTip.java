package com.icm.sphynx.mystyles.ui.metro.manager;

import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.ToolTipManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolTipUI;

/**
 *
 * @author israel-icm
 */
public class UIToolTip extends BasicToolTipUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.TOOLTIP_BACKGROUND;
    private String COLOR_FOREGROUND = MetroUIStyleColors.TOOLTIP_FOREGROUND;
    private String COLOR_BORDER = MetroUIStyleColors.TOOLTIP_BORDER;
    
    private boolean iniciado = false;

    public static ComponentUI createUI(JComponent c) {
        return new UIToolTip();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        installColors();
        
        JToolTip a = (JToolTip)c;
        if (a.getComponent() != null) {
            if (!iniciado) {
                a.getComponent().addMouseListener(new MouseAdapter() { // ESto es para que el tooltip dure 10 segundos antes de ocultarse
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        ToolTipManager.sharedInstance().setDismissDelay(15000);
                    }
                    /*@Override
                    public void mouseExited(MouseEvent e) {
                        ToolTipManager.sharedInstance().setDismissDelay(3000);
                    }*/
                });
                iniciado = true;
            }
        }

        int width = c.getWidth();
        int height = c.getHeight();
        c.setBorder(null);
        c.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 12));
        c.setForeground(Color.decode(COLOR_FOREGROUND));
        int border = 5;
        c.setBorder(BorderFactory.createEmptyBorder(border, border, border, border));

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.decode(COLOR_BACKGROUND));
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.decode(COLOR_BORDER));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(1, 1, width - 2, height - 2);
        super.paint(g, c);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension orig  = super.getPreferredSize(c);
        orig.height = orig.height + 10;
        orig.width = orig.width + 10;
        return orig;
    }
    
    protected void installListeners(JComponent c) {
        
    }

    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.TOOLTIP_BACKGROUND_DARK;
            COLOR_FOREGROUND = MetroUIStyleColors.TOOLTIP_FOREGROUND_DARK;
            COLOR_BORDER = MetroUIStyleColors.TOOLTIP_BORDER_DARK;
        }
    }
}
