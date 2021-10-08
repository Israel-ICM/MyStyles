package icm.sphynx.ui.components.metro;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * @author israel-icm
 */
public class UIComboBox extends BasicComboBoxUI {
    public static ComponentUI createUI(JComponent c) {
        return new UIComboBox();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        
        
        /*JComboBox cbx = (JComboBox)c;
        System.out.println("jaaa");
        c.setBorder(BorderFactory.createCompoundBorder(c.getBorder(), BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS)));
*/
    }
}
