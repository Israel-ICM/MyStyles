package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;

/**
 * Asigna los estilos para todos los list
 * @author israel-icm
 */
public class UIList extends BasicListUI {
    private JList list;
    public static ComponentUI createUI(JComponent b)    {
        return new UIList();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D)g;
        list = (JList)c;
        list.setBackground(null);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#E0E0E0"));
        if (MetroUIConfigTheme.isDarkMode())
            g2d.setColor(Color.decode(StyleColorsMetro.DARK_BACKGROUND_PANEL));
        g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
        
        c.setFont(new Font(UITools.FONT_DEFAULT, c.getFont().getStyle(), c.getFont().getSize()));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 0); // Para darle borde solo modificar el cero de esta linea
        c.setBorder(border);
        
        super.paint(g2d, c);
    }

    @Override
    protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer cellRenderer, ListModel dataModel, ListSelectionModel selModel, int leadIndex) {
        super.paintCell(g, row, rowBounds, cellRenderer, dataModel, selModel, leadIndex);
        /*g.setColor(Color.red);
        g.fillRect(0, 0, cellWidth, cellHeight);*/
        list.setSelectionBackground(MetroUIConfigTheme.getPrimaryColor());
        list.setSelectionForeground(Color.decode("#FFFFFF"));
        list.setFixedCellHeight(30);
    }
}
