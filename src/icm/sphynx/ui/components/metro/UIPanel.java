package icm.sphynx.ui.components.metro;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 * @author israel-icm
 */
public class UIPanel extends BasicPanelUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIPanel();
    }
    @Override
    protected void installDefaults(JPanel p) {
        super.installDefaults(p);
        // p.setBackground(Color.decode(UITools.COLOR_PANEL_DEFAULT));
    }
}
