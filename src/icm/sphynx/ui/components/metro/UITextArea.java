package icm.sphynx.ui.components.metro;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextAreaUI;

/**
 * Asigna estilos a todas las areas de texto
 * @author israel-icm
 */
public class UITextArea extends BasicTextAreaUI {
    private Color primaryColor;

    public static ComponentUI createUI(JComponent c) {
        return new UITextArea();
    }

    @Override
    protected void paintBackground(Graphics g) {
        primaryColor = UIManager.getColor("MetroUI.primaryColor");
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        JTextArea textField = (JTextArea)super.getComponent();
        textField.setBackground(null);
        textField.setSelectionColor(primaryColor);
        textField.setSelectedTextColor(Color.WHITE);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#E0E0E0"));
        if (MetroUIConfigTheme.getDarkMode())
            g2d.setColor(Color.decode("#A6A6A6"));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());

        textField.setFont(new Font(UITools.FONT_DEFAULT, textField.getFont().getStyle(), textField.getFont().getSize()));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        textField.setBorder(border);
        // textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
    }
}
