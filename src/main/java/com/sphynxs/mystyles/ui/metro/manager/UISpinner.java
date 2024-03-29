package com.sphynxs.mystyles.ui.metro.manager;

import com.sphynxs.mystyles.ui.metro.tools.MetroUIConfigTheme;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIIcons;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSpinnerUI;

/**
 *
 * @author israel-icm
 */
public class UISpinner extends BasicSpinnerUI {
    public static final String BUTTON_NAME_PREVIOUS = "ButtonSpinnerPreviousMetroUI";
    public static final String BUTTON_NAME_NEXT = "ButtonSpinnerNextMetroUI";
    
    public String COLOR_BUTTON_ICON = MetroUIStyleColors.FORMATTED_TEXT_FIELD_BUTTON_ICON;

    public static ComponentUI createUI(JComponent c) {
        return new UISpinner();
    }

    @Override
    protected void installNextButtonListeners(Component c) {
        JButton a = (JButton)c;
        a.setBackground(Color.WHITE);
        if (MetroUIConfigTheme.isDarkMode())
            a.setBackground(Color.decode("#CCCCCC"));
        super.installNextButtonListeners(c);
        // a.setForeground(Color.red);
        // a.setIcon(IconsMetroUI.iconListDetail(16, "#212121"));
    }

    @Override
    protected void installPreviousButtonListeners(Component c) {
        JButton a = (JButton)c;
        a.setBackground(Color.WHITE);
        if (MetroUIConfigTheme.isDarkMode())
            a.setBackground(Color.decode("#CCCCCC"));
        super.installPreviousButtonListeners(c);
    }

    @Override
    protected void installDefaults() {
        installColors();
        super.installDefaults();
    }

    @Override
    protected JComponent createEditor() {
        JTextField a = (JTextField)spinner.getEditor().getComponent(0);
        a.setFont(new Font(UITools.FONT_DEFAULT, a.getFont().getStyle(), a.getFont().getSize()));
        // LineBorder border = new LineBorder(Color.decode("#6A6A6A"), 2);
        // a.setBorder(null);
        spinner.setBorder(null);
        
        /*spinner.setBorder(border);
        a.setBorder(BorderFactory.createCompoundBorder(a.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS - 1, UITools.PADDING_CONTENTS  - 1, UITools.PADDING_CONTENTS  - 1, UITools.PADDING_CONTENTS  - 1)));
        a.setBackground(Color.decode("#E0E0E0"));*/
        return super.createEditor();
    }
    
    @Override
    protected Component createPreviousButton() {
        JButton btnPrevious = new JButton();
        btnPrevious.setName(BUTTON_NAME_PREVIOUS); // Se le asigna un nombre específico para poder estilzarlo desde la clase UIButton
        btnPrevious.setIcon(createPreviousImage(20, 20));
        installPreviousButtonListeners(btnPrevious);
        return btnPrevious;
        // return super.createPreviousButton(); //If this UI is added to a non CustomJSpinner, then return default implementation.
    }
    
    @Override
    protected Component createNextButton() {
        JButton btnNext = new JButton();
        btnNext.setName(BUTTON_NAME_NEXT); // Se le asigna un nombre específico para poder estilzarlo desde la clase UIButton
        btnNext.setIcon(createNextImage(20, 20));
        installNextButtonListeners(btnNext);
        return btnNext;
        // return super.createNextButton(); //If this UI is added to a non CustomJSpinner, then return default implementation.
    }
    
    private ImageIcon createPreviousImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int yIniFin = (height / 2) - 2;
        int yPuntoMedio = (height / 2) + 3;
        int xPuntoMedio = width / 2;

        g2d.setColor(Color.decode(COLOR_BUTTON_ICON));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(5, yIniFin, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, width - 5, yIniFin);
        return new ImageIcon(image);
    }
    
    private ImageIcon createNextImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D)image.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int yIniFin = (height / 2) + 2;
        int yPuntoMedio = 7;
        int xPuntoMedio = width / 2;

        g2d.setColor(Color.decode(COLOR_BUTTON_ICON));
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(5, yIniFin, xPuntoMedio, yPuntoMedio);
        g2d.drawLine(xPuntoMedio, yPuntoMedio, width - 5, yIniFin);
        return new ImageIcon(image);
    }
    
    private void installColors() {
        if (MetroUIConfigTheme.isDarkMode()) {
            COLOR_BUTTON_ICON = MetroUIStyleColors.FORMATTED_TEXT_FIELD_BUTTON_ICON_DARK;
        }
    }
}
