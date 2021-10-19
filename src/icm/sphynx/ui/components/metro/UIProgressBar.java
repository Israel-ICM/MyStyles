package icm.sphynx.ui.components.metro;

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
    public static ComponentUI createUI(JComponent c) {
        return new UIProgressBar();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        
        progressBar.setBackground(Color.decode("#C9CBCB"));
        if (MetroUIConfigTheme.isDarkMode())
            progressBar.setBackground(Color.decode("#A6A6A6"));
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
    
    /*@Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        super.paintDeterminate(g, c);
        g.setColor(Color.decode(UITools.COLOR_PRIMARY));
        g.fillRect(0, 0, progressBar.getInsets().left, progressBar.getHeight());
    }*/
}
