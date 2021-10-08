package icm.sphynx.ui.components.metro;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 * Asigna los estilos para las barras de menú
 * @author israel-icm
 */
public class UIMenuBar extends BasicMenuBarUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIMenuBar();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        // menuBar.setBackground(Color.WHITE);
        // menuBar.setFont(new Font(UITools.FONT_DEFAULT, menuBar.getFont().getStyle(), menuBar.getFont().getSize()));
        // menuBar.setForeground(Color.BLACK);
    }
    
}
