package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.MetroUIStyleColors;
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
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicListUI;

/**
 * Asigna los estilos para todos los list
 * @author israel-icm
 */
public class UIList extends BasicListUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.LIST_BACKGROUND;
    private String COLOR_ITEM_FOREGROUND = MetroUIStyleColors.LIST_ITEM_FOREGROUND;
    
    private JList list;
    public static ComponentUI createUI(JComponent b) {
        return new UIList();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D)g;
        list = (JList)c;
        list.setBackground(null);

        installColors();
        g2d.setColor(Color.decode(COLOR_BACKGROUND));
        g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
        
        c.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 13));
        /*c.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 13));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 0); // Para darle borde solo modificar el cero de esta linea
        c.setBorder(border);*/
        list.setBorder(null);
        
        super.paint(g2d, c);
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.LIST_BACKGROUND_DARK;
            COLOR_ITEM_FOREGROUND = MetroUIStyleColors.LIST_ITEM_FOREGROUND_DARK;
        }
    }

    @Override
    protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer cellRenderer, ListModel dataModel, ListSelectionModel selModel, int leadIndex) {
        super.paintCell(g, row, rowBounds, cellRenderer, dataModel, selModel, leadIndex);
        /*g.setColor(Color.red);
        g.fillRect(0, 0, cellWidth, cellHeight);*/
        list.setSelectionBackground(MetroUIConfigTheme.getPrimaryColor());
        list.setSelectionForeground(Color.decode(COLOR_ITEM_FOREGROUND));
        list.setFixedCellHeight(35);
    }
    
    
}
