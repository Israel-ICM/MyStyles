package com.icm.sphynx.mystyles.ui.metro.manager;

import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuBarUI;

/**
 * Asigna los estilos para las barras de menú
 * @author israel-icm
 */
public class UIMenuBar extends BasicMenuBarUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.MENUBAR_BACKGROUND;
    private String COLOR_BORDER = MetroUIStyleColors.MENUBAR_BORDER;

    public static ComponentUI createUI(JComponent c) {
        return new UIMenuBar();
    }

    @Override
    public void installUI(JComponent c) {
        installColor();
        super.installUI(c);
        menuBar.setBackground(Color.decode(COLOR_BACKGROUND));
        menuBar.setBorder(new MatteBorder(0, 0, 1, 0, Color.decode(COLOR_BORDER)));
        // menuBar.setFont(new Font(UITools.FONT_DEFAULT, menuBar.getFont().getStyle(), menuBar.getFont().getSize()));
        // menuBar.setForeground(Color.BLACK);
    }
    private void installColor() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.MENUBAR_BACKGROUND_DARK;
        }
    }
}
