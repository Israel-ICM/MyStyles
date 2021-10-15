package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

/**
 * Asigna los estilos de todos los paneles
 * @author israel-icm
 */
public class UIPanel extends BasicPanelUI {
    JPanel panel;
    public static ComponentUI createUI(JComponent c) {
        return new UIPanel();
    }
    
    @Override
    protected void installDefaults(JPanel p) {
        this.panel = p;
        super.installDefaults(p);

        if (MetroUIConfigTheme.isDarkMode())
            panel.setBackground(Color.decode(StyleColorsMetro.DARK_BACKGROUND_PANEL));
        else
            panel.setBackground(Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
    }
    @Override
    public int getBaseline(JComponent c, int width, int height) {
        /*if (MetroUIConfigTheme.getDarkMode())
            panel.setBackground(Color.decode(StyleColors.DARK_BACKGROUND_PANEL));
        else
            panel.setBackground(Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));*/
        return super.getBaseline(c, width, height);
    }
}
