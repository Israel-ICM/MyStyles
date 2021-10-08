package icm.sphynx.ui.components.metro;

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
        table.setSelectionBackground(Color.decode(UITools.COLOR_PRIMARY));
        table.setSelectionForeground(Color.WHITE);
        // Los estilos de las celdas son agregadas mediante un renderer
        table.setDefaultRenderer(Object.class, new ToolsCellRendererTable());
        table.setGridColor(Color.decode(UITools.COLOR_PRIMARY));
        table.setRowHeight(30);
    }
}
