package icm.sphynx.ui.metro.manager;

import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.tools.StyleColorsMetro;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicOptionPaneUI;

/**
 * Asigna los estilos para los mensajes de alerta (OptionPane)
 * @author israel-icm
 */
public class UIOptionPane extends BasicOptionPaneUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIOptionPane();
    }
    
    /**
     * Se modifican los iconos según el tipo de mensaje
     * @param top 
     */
    @Override
    protected void addIcon(Container top) {
        JLabel iconLabel = new JLabel();
        switch (optionPane.getMessageType()) {
            case JOptionPane.ERROR_MESSAGE:
                iconLabel = new JLabel(){
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2d = (Graphics2D)g;
                        int width = 50;
                        int height = 50;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        // AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                        // g2d.setComposite(ac);
                        g2d.setColor(Color.decode("#C1494B"));
                        g2d.fillOval(1, 1, width, height);

                        BasicStroke stroke = new BasicStroke(3);
                        g2d.setStroke(stroke);
                        g2d.setColor(Color.decode("#CCCCCC"));
                        g2d.drawOval(1, 1, width, height);

                        g2d.setColor(Color.WHITE);
                        g2d.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
                        g2d.drawString("!", 20, 40);
                    }
                };
                break;
            case JOptionPane.INFORMATION_MESSAGE:
                iconLabel = new JLabel(){
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2d = (Graphics2D)g;
                        int width = 50;
                        int height = 50;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        // AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                        // g2d.setComposite(ac);
                        g2d.setColor(Color.decode("#49A1C1"));
                        g2d.fillOval(1, 1, width, height);

                        BasicStroke stroke = new BasicStroke(3);
                        g2d.setStroke(stroke);
                        g2d.setColor(Color.decode("#CCCCCC"));
                        g2d.drawOval(1, 1, width, height);

                        g2d.setColor(Color.WHITE);
                        g2d.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
                        g2d.drawString("i", 20, 40);
                    }
                };
                break;
            case JOptionPane.WARNING_MESSAGE:
                iconLabel = new JLabel(){
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2d = (Graphics2D)g;
                        int width = 50;
                        int height = 50;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        // AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                        // g2d.setComposite(ac);
                        g2d.setColor(Color.decode("#C19349"));
                        g2d.fillOval(1, 1, width, height);

                        BasicStroke stroke = new BasicStroke(3);
                        g2d.setStroke(stroke);
                        g2d.setColor(Color.decode("#CCCCCC"));
                        g2d.drawOval(1, 1, width, height);

                        g2d.setColor(Color.WHITE);
                        g2d.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
                        g2d.drawString("!", 20, 40);
                    }
                };
                break;
            case JOptionPane.QUESTION_MESSAGE:
                iconLabel = new JLabel(){
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2d = (Graphics2D)g;
                        int width = 50;
                        int height = 50;
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        // AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                        // g2d.setComposite(ac);
                        g2d.setColor(Color.decode("#8C75DC"));
                        g2d.fillOval(1, 1, width, height);

                        BasicStroke stroke = new BasicStroke(3);
                        g2d.setStroke(stroke);
                        g2d.setColor(Color.decode("#CCCCCC"));
                        g2d.drawOval(1, 1, width, height);

                        g2d.setColor(Color.WHITE);
                        g2d.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
                        g2d.drawString("?", 18, 40);
                    }
                };
                break;
            case JOptionPane.PLAIN_MESSAGE: break;
        }
        if (optionPane.getMessageType() != JOptionPane.PLAIN_MESSAGE) {
            iconLabel.setPreferredSize(new Dimension(53, 53));
            iconLabel.setName("OptionPane.iconLabel");
            iconLabel.setVerticalAlignment(SwingConstants.TOP);
            top.add(iconLabel, BorderLayout.BEFORE_LINE_BEGINS);
        }
    }
    
    @Override
    protected void addMessageComponents(Container container, GridBagConstraints cons, Object msg, int maxll, boolean internallyCreated) {
        super.addMessageComponents(container, cons, msg, maxll, internallyCreated);
        optionPane.setBackground(Color.decode(StyleColorsMetro.PANEL_BACKGROUND));
        if (MetroUIConfigTheme.isDarkMode()) {
            optionPane.setBackground(Color.decode(StyleColorsMetro.PANEL_BACKGROUND_DARK));
            container.getComponent(0).setForeground(Color.decode(StyleColorsMetro.PANEL_FOREGROUND_DARK));
        }
        UIManager.put("LabelUI", null); // Se anula el label porque la tipografia utilizada hacía que se desborde y no se vean los ultimos caracteres
    }
}
