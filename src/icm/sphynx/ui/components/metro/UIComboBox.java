package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.StyleColorsMetro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * Asigna los estilos para todos los combobox
 * @author israel-icm
 */
public class UIComboBox extends BasicComboBoxUI {
    private final String _colorBackground = StyleColorsMetro.COLOR_BACKGROUND_COMBOBOX;
    private final String _colorBorder = StyleColorsMetro.COLOR_BORDER_COMBOBOX;
    private final String _colorText = "#000000";
    private final String _colorTextSelection = "#000000";
    private final String _colorIconButton = "#838383";
    
    private JComboBox combobox;
    
    public static ComponentUI createUI(JComponent c) {
        return new UIComboBox();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        combobox = (JComboBox)c;

        combobox.setBackground(Color.decode(_colorBackground));
        combobox.setForeground(Color.decode(_colorText));
        combobox.setBorder(new LineBorder(Color.decode(_colorBorder), 2));
        if (combobox.getHeight() <= 26)
            combobox.setPreferredSize(new Dimension(combobox.getWidth(), 28));
        // c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
    
    @Override 
    protected JButton createArrowButton() {
        JButton button = new JButton();
        button.setName("ButtonComboboxMetroUI");
        button.setText("");
        button.setBorder(BorderFactory.createLineBorder(Color.decode(_colorBackground), 0));
        button.setContentAreaFilled(false);
        button.setIcon(createCbxImage(20, 20));
        button.setFocusable(false);
        
        // EL BOTON DEBERIA CAMBIAR TAMBIEN AL COLOR DEL FOREGROUND
        // TAL VEZ FUNCIONA CON LA NUEVA FUNCIONA DE PROPERTY
        
        button.setBackground(Color.decode(_colorBackground));
        return button;
    }
    
    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus){
        g.setColor(Color.decode(_colorBackground));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    @Override
    protected ListCellRenderer createRenderer(){
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                list.setSelectionBackground(Color.decode(_colorBackground));
                list.setSelectionForeground(Color.decode(_colorText));
                if (isSelected){
                    /* setBackground(MetroUIConfigTheme.getPrimaryColor());
                    setForeground(Color.WHITE);*/
                    setBackground(Color.decode(UITools.subirBrillo(UITools.colorToHex(MetroUIConfigTheme.getPrimaryColor()))));
                    setForeground(Color.decode(_colorTextSelection));
                }
                else {
                    setBackground(Color.WHITE);
                    if (MetroUIConfigTheme.isDarkMode())
                        setBackground(Color.decode("#A6A6A6"));
                    setForeground(Color.BLACK);
                }
                return this;
            }
        };
    }
    
    private ImageIcon createCbxImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /*AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        g2d.setComposite(ac);*/

        int yIniFin = (height / 2) - 2;
        int yPuntoMedio = (height / 2) + 3;
        int xPuntoMedio = width / 2;
        
        g2d.setColor(Color.decode(_colorIconButton));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(5, yIniFin, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, width - 5, yIniFin);
        return new ImageIcon(image);
    }
}
