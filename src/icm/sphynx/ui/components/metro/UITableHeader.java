package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.ToolsHeaderCellRendererTable;
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
    public static ComponentUI createUI(JComponent c) {
        return new UITableHeader();
    }
    
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        header.setForeground(MetroUIConfigTheme.getPrimaryColor());
        header.setBackground(Color.decode("#FFFFFF"));
        // header.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, header.getFont().getSize()));
        header.setFont(new Font(UITools.FONT_DEFAULT, Font.PLAIN, 15));
        
        // Color divider = Color.decode("#CCCCCC");
        Color divider = MetroUIConfigTheme.getPrimaryColor();
        if (MetroUIConfigTheme.isDarkMode())
            divider = Color.decode("#A6A6A6");
        MatteBorder bordeDelgadoGris = BorderFactory.createMatteBorder(0, 1, 2, 1, divider);
        CompoundBorder bordeAnchoCelda = BorderFactory.createCompoundBorder(bordeDelgadoGris, BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS));
        header.setBorder(bordeAnchoCelda);

        // Los estilos se asignan a travez de un renderer
        // header.setDefaultRenderer(new ToolsHeaderCellRendererTable());
    }
}
