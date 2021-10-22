package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * Asigna los estilos para las barras de carga
 * @author israel-icm
 */
public class UIProgressBar extends BasicProgressBarUI {
    private String COLOR_BACKGROUND = MetroUIStyleColors.PROGRESSBAR_BACKGROUND;

    public static ComponentUI createUI(JComponent c) {
        return new UIProgressBar();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);

        installColors();
        progressBar.setBackground(Color.decode(COLOR_BACKGROUND));
        progressBar.setForeground(MetroUIConfigTheme.getPrimaryColor());
        progressBar.setPreferredSize(new Dimension(progressBar.getWidth(), 20));
        progressBar.setBorder(null);
        /*if (progressBar.isIndeterminate()) {
            paintIndeterminate(g, c);
        } else {
            paintDeterminate(g, c);
            // g.setColor(Color.WHITE);
            // g.fillRect(0, 0, progressBar.getWidth(), progressBar.getHeight());
        }*/
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = MetroUIStyleColors.PROGRESSBAR_BACKGROUND_DARK;
        }
    }
    
    /*@Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        super.paintDeterminate(g, c);
        g.setColor(Color.decode(UITools.COLOR_PRIMARY));
        g.fillRect(0, 0, progressBar.getInsets().left, progressBar.getHeight());
    }*/
}
