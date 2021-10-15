package icm.sphynx.ui.tools;
import icm.sphynx.ui.components.metro.UITools;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * Renderer para asignar estilos a las celdas de la cabecera de los tables
 * @author Israel-ICM
 */
public class ToolsHeaderCellRendererTable implements TableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent jcomponent = null;
        
        if(value instanceof String ) {
            jcomponent = new JLabel((String) "   " + value);
            // ((JLabel)jcomponent).setHorizontalAlignment(SwingConstants.LEFT);
            // ((JLabel)jcomponent).setSize(60, jcomponent.getWidth());
            // ((JLabel)jcomponent).setPreferredSize(new Dimension(6, jcomponent.getWidth()));
            ((JLabel)jcomponent).setFont(new Font(UITools.FONT_DEFAULT, table.getFont().getStyle(), table.getFont().getSize() + 1));
        } 
        
        // jcomponent.setEnabled(true);
        // UIManager.put("Label.foreground", Color.WHITE);
        jcomponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.decode("#CCCCCC")));
        jcomponent.setOpaque(true);
        jcomponent.setBackground(Color.decode(StyleColorsMetro.COLOR_PRIMARY));
        jcomponent.setForeground(Color.decode("#FFFFFF"));
        //jcomponent.setToolTipText("Colum No. "+(column+1));
        return jcomponent;
    }
}