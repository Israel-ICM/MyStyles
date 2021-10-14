package icm.sphynx.ui.components.metro;

import icm.sphynx.ui.tools.IconsMetroUI;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
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
    public static ComponentUI createUI(JComponent c) {
        return new UISpinner();
    }

    @Override
    protected void installNextButtonListeners(Component c) {
        JButton a = (JButton)c;
        a.setBackground(Color.WHITE);
        if (MetroUIConfigTheme.getDarkMode())
            a.setBackground(Color.decode("#CCCCCC"));
        super.installNextButtonListeners(c);
        // a.setForeground(Color.red);
        // a.setIcon(IconsMetroUI.iconListDetail(16, "#212121"));
    }

    @Override
    protected void installPreviousButtonListeners(Component c) {
        JButton a = (JButton)c;
        a.setBackground(Color.WHITE);
        if (MetroUIConfigTheme.getDarkMode())
            a.setBackground(Color.decode("#CCCCCC"));
        super.installPreviousButtonListeners(c);
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
    }

    @Override
    protected JComponent createEditor() {
        JTextField a = (JTextField)spinner.getEditor().getComponent(0);
        a.setFont(new Font(UITools.FONT_DEFAULT, a.getFont().getStyle(), a.getFont().getSize()));
        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        // a.setBorder(null);
        spinner.setBorder(null);
        
        /*spinner.setBorder(border);
        a.setBorder(BorderFactory.createCompoundBorder(a.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS - 1, UITools.PADDING_CONTENTS  - 1, UITools.PADDING_CONTENTS  - 1, UITools.PADDING_CONTENTS  - 1)));
        a.setBackground(Color.decode("#E0E0E0"));*/
        return super.createEditor();
    }
}
