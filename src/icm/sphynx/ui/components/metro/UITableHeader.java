package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.ToolsHeaderCellRendererTable;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;

/**
 * @author israel-icm
 */
public class UITableHeader extends BasicTableHeaderUI {
    public static ComponentUI createUI(JComponent c) {
        return new UITableHeader();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        /*header.setForeground(Color.WHITE);
        header.setBackground(Color.decode(UITools.COLOR_PRIMARY));
        header.setFont(new Font(UITools.FONT_DEFAULT, header.getFont().getStyle(), header.getFont().getSize()));*/
        header.setDefaultRenderer(new ToolsHeaderCellRendererTable());
    }
}
