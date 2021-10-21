package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.StyleColorsMetro;
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
    private String COLOR_BACKGROUND = StyleColorsMetro.PANEL_BACKGROUND;
    
    JPanel panel;
    public static ComponentUI createUI(JComponent c) {
        return new UIPanel();
    }
    
    @Override
    protected void installDefaults(JPanel p) {
        this.panel = p;
        installColors();
        super.installDefaults(p);

        panel.setBackground(Color.decode(COLOR_BACKGROUND));
    }
    @Override
    public int getBaseline(JComponent c, int width, int height) {
        /*if (MetroUIConfigTheme.getDarkMode())
            panel.setBackground(Color.decode(StyleColors.DARK_BACKGROUND_PANEL));
        else
            panel.setBackground(Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));*/
        return super.getBaseline(c, width, height);
    }
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = StyleColorsMetro.PANEL_BACKGROUND_DARK;
        }
    }
}
