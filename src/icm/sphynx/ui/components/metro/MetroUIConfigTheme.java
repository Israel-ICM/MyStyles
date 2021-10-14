package icm.sphynx.ui.components.metro;

import icm.sphynx.styles.MetroUI;
import icm.sphynx.ui.tools.StyleColors;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author israel-icm
 */
public class MetroUIConfigTheme {
    public static void setPrimaryColor(String colorHex) {
        setPrimaryColor(colorHex, false);
    }
    public static void setPrimaryColor(String colorHex, boolean refresh) {
        UIManager.put("MetroUI.primaryColor", Color.decode(colorHex));
        setSecondColor(colorHex);
    }
    
    private static void setSecondColor(String colorHex) {
        UIManager.put("MetroUI.secondColor", Color.decode(UITools.getSecondColor(colorHex)));
    }
    
    public static void setDarkMode(boolean dark) {
        UIManager.put("MetroUI.darkMode", dark);
    }
    
    public static Color getPrimaryColor() {
        return UIManager.getColor("MetroUI.primaryColor");
    }
    
    public static Color getSecondColor() {
        return UIManager.getColor("MetroUI.secondColor");
    }
    
    public static boolean getDarkMode() {
        return UIManager.getBoolean("MetroUI.darkMode");
    }
}
