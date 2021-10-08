package icm.sphynx.ui.components.metro;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

/**
 * Asigna los estilos para todos los menús
 * @author israel-icm
 */
public class UIMenu extends BasicMenuUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIMenu();
    }
}
