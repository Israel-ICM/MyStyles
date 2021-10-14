package icm.sphynx.ui.components.metro;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author israel-icm
 */
public class UISplitPane extends BasicSplitPaneUI {
    public static ComponentUI createUI(JComponent b)    {
        return new UISplitPane();
    }

    @Override
    public void paint(Graphics g, JComponent jc) {
        splitPane.setBackground(Color.decode("#404141"));
        splitPane.setDividerSize(5);
        splitPane.setBorder(new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 0));
        divider.setBorder(new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 0));
        // System.out.println("ver " + splitPane.getDividerLocation());
        // divider.set
    }
    @Override
    protected void dragDividerTo(int location) {
        // esto es para que se agranden los paneles al realizar el drag
        splitPane.setDividerLocation(location);
    }

    @Override
    protected void startDragging() {
        // Este método no tiene nada porque su función es mostrar esa sombra que se queda cuando se arrastra el divider
        // Sobreescribiendola ya no muestra nada
    }
}
