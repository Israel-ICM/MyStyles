package icm.sphynx.ui.components.metro;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicLookAndFeel;

/**
 * @author israel-icm
 */
public class UILookAndFeel extends BasicLookAndFeel {
    public static UILookAndFeel createUI(JComponent c) {
        return new UILookAndFeel();
    }
    
    public void initialize() {
        super.initialize();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getID() {
        return "";
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
    
}
