package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColors;
import icm.sphynx.ui.tools.ToolsCellRendererTable;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableUI;

/**
 * Asigna los estilos de las tablas
 * @author israel-icm
 */
public class UITable extends BasicTableUI {
    public static ComponentUI createUI(JComponent c) {
        return new UITable();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        // Los estilos de las celdas son agregadas mediante un renderer
        table.setDefaultRenderer(Object.class, new ToolsCellRendererTable());
        table.setSelectionBackground(MetroUIConfigTheme.getPrimaryColor());
        table.setSelectionForeground(Color.WHITE);
        table.setBackground(Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));
        table.setForeground(Color.decode(StyleColors.LIGHT_FOREGROUND));
        if (MetroUIConfigTheme.getDarkMode()) {
            table.setBackground(Color.decode(StyleColors.DARK_BACKGROUND_PANEL));
            table.setForeground(Color.decode(StyleColors.DARK_FOREGROUND));
        }
        table.setGridColor(MetroUIConfigTheme.getPrimaryColor());
        table.setRowHeight(30);
    }
}
