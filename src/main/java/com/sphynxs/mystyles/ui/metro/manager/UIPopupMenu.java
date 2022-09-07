package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

/**
 * Asigna los estilos para el menú popup
 * @author israel-icm
 */
public class UIPopupMenu extends BasicPopupMenuUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.MENU_POPUP_BACKGROUND;
    private String COLOR_BORDER = MetroUIStyleColors.MENU_POPUP_BORDER;
    private boolean componenteIniciado = false;

    public static ComponentUI createUI(JComponent c) {
        return new UIPopupMenu();
    }

    @Override
    public void installDefaults() {
        installColors();
        super.installDefaults();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if (!componenteIniciado) {
            if (!(popupMenu.getInvoker() instanceof JComboBox)) { // Para e combobox no se debe aplicar estos estilos
                popupMenu.setBackground(Color.decode(COLOR_BACKGROUND));
                popupMenu.setBorder(new LineBorder(Color.decode(COLOR_BORDER), 1));
                popupMenu.setBorder(BorderFactory.createCompoundBorder(popupMenu.getBorder(), BorderFactory.createEmptyBorder(5, 0, 5, 0)));
            }
            componenteIniciado = true;
        }
        super.paint(g, c);
    }

    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.MENU_POPUP_BACKGROUND_DARK;
            COLOR_BORDER = MetroUIStyleColors.MENU_POPUP_BORDER_DARK;
        }
    }
}
