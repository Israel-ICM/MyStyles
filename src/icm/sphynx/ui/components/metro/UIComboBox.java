package icm.sphynx.ui.components.metro;

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
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * Asigna los estilos para todos los combobox
 * @author israel-icm
 */
public class UIComboBox extends BasicComboBoxUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIComboBox();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        JComboBox cbx = (JComboBox)c;
        cbx.setBackground(MetroUIConfigTheme.getPrimaryColor());
        cbx.setForeground(Color.WHITE);
        cbx.setPreferredSize(new Dimension(cbx.getWidth(), 30));
        // c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
    
    @Override 
    protected JButton createArrowButton() {                 
        JButton button = new JButton();
        button.setText("");
        button.setBorder(BorderFactory.createLineBorder(MetroUIConfigTheme.getPrimaryColor(), 0));
        button.setContentAreaFilled(false);
        button.setIcon(createCbxImage(20, 20));
        button.setFocusable(false);
        return button;
    }
    
    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus){
        g.setColor(MetroUIConfigTheme.getPrimaryColor());
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    @Override
    protected ListCellRenderer createRenderer(){
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                list.setSelectionBackground(MetroUIConfigTheme.getPrimaryColor());
                if (isSelected){
                    setBackground(MetroUIConfigTheme.getPrimaryColor());
                    setForeground(Color.WHITE);
                }
                else {
                    setBackground(Color.WHITE);
                    if (MetroUIConfigTheme.getDarkMode())
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
        
        g2d.setColor(Color.decode("#FFFFFF"));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(5, yIniFin, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, width - 5, yIniFin);
        return new ImageIcon(image);
    }
}
