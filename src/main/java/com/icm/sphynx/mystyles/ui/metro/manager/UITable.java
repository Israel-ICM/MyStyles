package com.icm.sphynx.mystyles.ui.metro.manager;

import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.icm.sphynx.mystyles.ui.metro.tools.MetroUIStyleColors;
import com.icm.sphynx.mystyles.ui.metro.tools.ToolsCellRendererTable;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;

/**
 * Asigna los estilos de las tablas
 * @author israel-icm
 */
public class UITable extends BasicTableUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.TABLE_BACKGROUND;
    private String COLOR_FOREGROUND = MetroUIStyleColors.TABLE_FOREGROUND;
    
    public static ComponentUI createUI(JComponent c) {
        return new UITable();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        installColors();

        // Los estilos de las celdas son agregadas mediante un renderer
        table.setDefaultRenderer(Object.class, new ToolsCellRendererTable());
        table.setSelectionBackground(MetroUIConfigTheme.getPrimaryColor());
        table.setSelectionForeground(Color.WHITE);
        table.setBackground(Color.decode(COLOR_BACKGROUND));
        table.getParent().setBackground(Color.decode(COLOR_BACKGROUND));
        table.setForeground(Color.decode(COLOR_FOREGROUND));
        table.setGridColor(MetroUIConfigTheme.getThirdColor());
        table.setRowHeight(30);
    }
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.TABLE_BACKGROUND_DARK;
            COLOR_FOREGROUND = MetroUIStyleColors.TABLE_FOREGROUND_DARK;
        }
    }
}
