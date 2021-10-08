package icm.sphynx.ui.tools;

import icm.sphynx.ui.components.metro.UITools;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renderer para asignar estilos a las celdas de las tablas
 * @author israel-ICM
 */
public class ToolsCellRendererTable extends DefaultTableCellRenderer {
    private String _selected_color = UITools.COLOR_PRIMARY;
    private Color color1 = Color.decode("#FFFFFF");
    private Color color2 = Color.decode("#FF6600");

    private final String _fuente = UITools.FONT_DEFAULT;
    public int _textSize = 12;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
        setEnabled(table == null || table.isEnabled());
        setBackground(null);
        table.setFont(new Font(_fuente, Font.PLAIN, _textSize));
        setFont(new Font(_fuente, Font.PLAIN, _textSize));

        // Esto es para que se intercalen los colores de las filas
        /*if(selected)
            setBackground(Color.decode(_selected_color));
        else
            setBackground((row % 2 == 1) ? this.color1 : this.color2);*/

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }
}