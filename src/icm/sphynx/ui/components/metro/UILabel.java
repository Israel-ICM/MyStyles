package icm.sphynx.ui.components.metro;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

/**
 * @author israel-icm
 */
public class UILabel extends BasicLabelUI {
    public static ComponentUI createUI(JComponent c) {
        return new UILabel();
    }

    @Override
    protected void paintEnabledText(JLabel label, Graphics g, String s, int textX, int textY) {
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(label.getForeground());
        g2d.setFont(new Font(UITools.FONT_DEFAULT, label.getFont().getStyle(), label.getFont().getSize()));
        g2d.drawString(s, textX, textY);
    }
}
