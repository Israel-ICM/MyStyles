package icm.sphynx.ui.components.metro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author israel-icm
 */
public class UISplitPane extends BasicSplitPaneUI {
    private static final int STATE_DEFAULT = 1;
    private static final int STATE_OVER = 2;
    private static final int STATE_PRESSED = 3;
    private int currentStateDivider = 1; // Controla los estados del textfield

    private boolean componenteIniciado = false;

    public static ComponentUI createUI(JComponent b)    {
        return new UISplitPane();
    }

    @Override
    public void paint(Graphics g, JComponent jc) {
        installBackground();
        if (!componenteIniciado) {
            installEvents();
            componenteIniciado = true;
        }
    }

    private void installBackground() {
        splitPane.setDividerSize(4);
        switch (currentStateDivider) {
            case STATE_DEFAULT:
                splitPane.setBackground(MetroUIConfigTheme.getSecondColor());
                break;
            case STATE_OVER:
                splitPane.setBackground(Color.decode("#404141"));
                break;
            case STATE_PRESSED:
                splitPane.setBackground(Color.decode("#404141"));
                break;
        }
    }

    private void installBorder() {
        splitPane.setBorder(new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 0));
        divider.setBorder(new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 0));
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
    
    private void installEvents() {
        divider.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) {
                currentStateDivider = STATE_PRESSED;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentStateDivider = STATE_DEFAULT;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                currentStateDivider = STATE_OVER;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                currentStateDivider = STATE_DEFAULT;
            }
        });
    }
}
