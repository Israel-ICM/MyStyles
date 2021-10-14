package icm.sphynx.ui.components.metro;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;

/**
 * Asigna estilos a todas las areas de texto
 * @author israel-icm
 */
public class UIFormattedTextField extends BasicFormattedTextFieldUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIFormattedTextField();
    }

    @Override
    protected void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintBackground(g2d);
        JFormattedTextField formattedTextField = (JFormattedTextField)super.getComponent();
        formattedTextField.setBackground(null);

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f);
        g2d.setComposite(ac);
        g2d.setColor(Color.decode("#E0E0E0"));
        g2d.fillRect(0, 0, super.getComponent().getWidth(), super.getComponent().getHeight());

        formattedTextField.setFont(new Font(UITools.FONT_DEFAULT, formattedTextField.getFont().getStyle(), formattedTextField.getFont().getSize()));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        formattedTextField.setBorder(border);
        formattedTextField.setBorder(BorderFactory.createCompoundBorder(formattedTextField.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
        formattedTextField.setSelectionColor(MetroUIConfigTheme.getPrimaryColor());
        formattedTextField.setSelectedTextColor(Color.WHITE);
    }
}
