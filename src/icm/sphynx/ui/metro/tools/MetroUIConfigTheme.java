package icm.sphynx.ui.metro.tools;

import icm.sphynx.ui.metro.manager.UITools;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author israel-icm
 */
public class MetroUIConfigTheme {
    public static void setPrimaryColor(String colorHex) {
        UIManager.put("MetroUI.primaryColor", Color.decode(colorHex));
        setSecondColor(colorHex);
        setThirdColor(colorHex);
        setFourthColor(colorHex);
    }
    
    private static void setSecondColor(String colorHex) {
        UIManager.put("MetroUI.secondColor", Color.decode(UITools.getSecondColor(colorHex)));
    }
    
    private static void setThirdColor(String colorHex) {
        UIManager.put("MetroUI.thirdColor", Color.decode(UITools.getThirdColor(colorHex)));
    }
    
    private static void setFourthColor(String colorHex) {
        UIManager.put("MetroUI.fourthColor", Color.decode(UITools.getFourthColor(colorHex)));
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
    
    public static Color getThirdColor() {
        return UIManager.getColor("MetroUI.thirdColor");
    }

    public static Color getFourthColor() {
        return UIManager.getColor("MetroUI.fourthColor");
    }
    
    public static boolean isDarkMode() {
        return UIManager.getBoolean("MetroUI.darkMode");
    }
}
