package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.components.BorderShadow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

/**
 *
 * @author israel-icm
 */
public class UIPopupMenu extends BasicPopupMenuUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIPopupMenu();
    }
    @Override
    public void installDefaults() {
        super.installDefaults();
        popupMenu.setBackground(Color.WHITE);
        popupMenu.setBorder(new LineBorder(Color.decode("#CCCCCC"), 1));
        popupMenu.setBorder(BorderFactory.createCompoundBorder(popupMenu.getBorder(), BorderFactory.createEmptyBorder(5, 0, 5, 0)));
    }
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
    }
}
