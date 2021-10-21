package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.StyleColorsMetro;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;

/**
 * Asigna estilos a la cabecera de las tablas
 * @author israel-icm
 */
public class UITableHeader extends BasicTableHeaderUI {
    private String COLOR_BACKGROUND = StyleColorsMetro.TABLE_HEADER_BACKGROUND;
    
    public static ComponentUI createUI(JComponent c) {
        return new UITableHeader();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        installColors();
        header.setForeground(MetroUIConfigTheme.getPrimaryColor());
        header.setBackground(Color.decode(COLOR_BACKGROUND));
        // header.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, header.getFont().getSize()));
        header.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 15));

        // Color divider = Color.decode("#CCCCCC");
        Color divider = MetroUIConfigTheme.getPrimaryColor();
        MatteBorder bordeDelgadoGris = BorderFactory.createMatteBorder(0, 1, 2, 1, divider);
        CompoundBorder bordeAnchoCelda = BorderFactory.createCompoundBorder(bordeDelgadoGris, BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS));
        header.setBorder(bordeAnchoCelda);

        // Los estilos se asignan a travez de un renderer
        // header.setDefaultRenderer(new ToolsHeaderCellRendererTable());
    }

    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BACKGROUND = StyleColorsMetro.TABLE_HEADER_BACKGROUND_DARK;
        }
    }
}
