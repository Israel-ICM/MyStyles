package icm.sphynx.ui.components.metro;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 * Variables para valores por default de toda la librería
 * @author israel-icm
 */
public class UITools {
    public static int PADDING_CONTENTS = 3;
    public static String FONT_DEFAULT = "Segoe UI Semilight";

    public static String COLOR_SCROLL_DEFAULT = "#3F3F3E";
    // public static String COLOR_PANEL_DEFAULT = "#FFFFFF";
    public static String COLOR_BUTTON_FOREGROUND_DEFAULT = "#000000";
    public static String COLOR_BORDER_DEFAULT = "#6A6A6A";
    
    public static final String PATH_IMAGE = "/sphynx/ui/components/img/";
    
    public static String aclararColor(String colorHex) {
        return aclararColor(colorHex, 1);
    }
    /**
     * Aclara un color cualquiera en hexadecimal
     * @param colorHex
     * @param tonos Cuantos tonos se aclarará el color
     * @return
     * @deprecated 
     */
    public static String aclararColor(String colorHex, int tonos) {
        /*
        Aclarar un color es ir aumentando el primer digito de cada dos caracteres del hexadecimal
        ejemplo: #453B3B => #554B4B => #655B5B => #756B6B => ...
        Para cambiar el primer digito en rgb es ir sumando 16 a cada color
        */
        
        // String colorHex = "#453B3B";
        int r = Color.decode(colorHex).getRed();
        int g = Color.decode(colorHex).getGreen();
        int b = Color.decode(colorHex).getBlue();
        while (r <= 255 && g <= 255 && b <= 255 && tonos > 0) {
            // Cada 16 es un nivel
            r = r + 16;
            g = g + 16;
            b = b + 16;
            tonos--;
        }

        String hex = String.format("#%02x%02x%02x", r, g, b);
        // System.out.println("color " + hex);
        return hex;
    }
    /**
     * Convierte un color a codigo hexadecimal
     * @param color
     * @return 
     */
    public static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
    /**
     * Oscurece un color cualquiera en hexadecimal
     * @param colorHex
     * @param tonos Cuantos tonos se oscurecerá el color
     * @return
     * @deprecated
     */
    public static String oscurecerColor(String colorHex, int tonos) {
        /*
        Aclarar un color es ir aumentando el primer digito de cada dos caracteres del hexadecimal
        ejemplo: #453B3B => #554B4B => #655B5B => #756B6B => ...
        Para cambiar el primer digito en rgb es ir sumando 16 a cada color
        */
        
        // String colorHex = "#453B3B";
        int r = Color.decode(colorHex).getRed();
        int g = Color.decode(colorHex).getGreen();
        int b = Color.decode(colorHex).getBlue();
        while (r >= 0 && g >= 0 && b >= 0 && tonos > 0) {
            // Cada 16 es un nivel
            r = r - 16;
            g = g - 16;
            b = b - 16;
            tonos--;
        }

        String hex = String.format("#%02x%02x%02x", r, g, b);  
        // System.out.println("color " + hex);
        return hex;
    }
    public static boolean esColorOscuro(String colorHex) {
        /*
        HSB es sinónimo de tono, saturación, brillo - el brillo es también conocida como la luminosidad.
        Con los valores de la clase de color son entre 1 y 0. Ergo, 0,5 brillo es el punto de la mitad de camino
        entre los colores más brillantes y los colores más oscuros).
        */
        return getBrillo(colorHex) < 0.5;
    }
    /**
     * Retorna el brillo de un color determinado, es medido entre 0.0 y 1.0
     * @param colorHex
     * @return 
     */
    public static float getBrillo(String colorHex) {
        // String colorHex = "#0cf356";
        // remove hash character from string
        // String rawFontColor = colorHex.substring(1, colorHex.length());

        // convert hex string to int
        // int rgb = Integer.parseInt(rawFontColor, 16);

        // Color c = new Color(rgb);
        
        Color c = Color.decode(colorHex);
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        float brightness = hsb[2];
        return brightness;
    }
    /**
     * Retorna un color equilibrado (Color crema) al ingresado
     * @return 
     */
    public static String getColorPredeterminado(String colorHex) {
        /*DecimalFormat formato1 = new DecimalFormat("#.0");
        String number = formato1.format(UITools.getBrillo(colorHex));
        Float round = Float.parseFloat(number.replace(",", "."));

        try {
            while (round > 0.6) { // es claro
                colorHex = UITools.oscurecerColor(colorHex, 1);
                number = formato1.format(UITools.getBrillo(colorHex));
                round = Float.parseFloat(number.replace(",", "."));
            }
            while (round < 0.6) { // es oscuro
                colorHex = UITools.aclararColor(colorHex);
                number = formato1.format(UITools.getBrillo(colorHex));
                round = Float.parseFloat(number.replace(",", "."));
            }
        }
        catch (NumberFormatException e) {
            colorHex = UITools.COLOR_PRIMARY;
            Logger.getGlobal().log(Level.WARNING, "El color ingresado no es soportado por el tema: {0}", e.getMessage());
        }*/
        // String colorHex = "#FF0000";

        Color color = Color.decode(colorHex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        // Saturación siempre será 60 y valor/brillo siempre con 80
        color = Color.getHSBColor(hsb[0], 0.6f, 0.8f);
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

        // System.out.println("color " + hex);

        return hex;
    }
    /**
     * @param colorHex
     * @return 
     */
    public static String getSecondColor(String colorHex) {
        Color color = Color.decode(colorHex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float newBrillo = hsb[2] - 0.15f;
        color = Color.getHSBColor(hsb[0], hsb[1], newBrillo < 0 ? newBrillo + 0.3f : newBrillo); // Si es muy oscuro el brillo se sube en lugar de bajarlo
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        // System.out.println("color " + hex);
        return hex;
    }
    
    /**
     * @param colorHex
     * @return 
     */
    public static String getThirdColor(String colorHex) {
        return UITools.subirBrillo(colorHex);
    }
    
    public static String getFourthColor(String colorHex) {
        Color color = Color.decode(colorHex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float newBrillo = hsb[2] + 0.1f;
        newBrillo = newBrillo > 1 ? 1 : newBrillo;
        newBrillo = newBrillo < 0 ? 0 : newBrillo;

        color = Color.getHSBColor(hsb[0], 0.4f, newBrillo); // Si es muy oscuro el brillo se sube en lugar de bajarlo
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        // System.out.println("color " + hex);
        return hex;
    }
    
    public static String subirBrillo(String colorHex) {
        Color color = Color.decode(colorHex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float newBrillo = hsb[2] + 0.1f;

        color = Color.getHSBColor(hsb[0], 0.2f, newBrillo > 1 ? newBrillo - 0.1f : newBrillo); // Si es muy oscuro el brillo se sube en lugar de bajarlo
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        // System.out.println("color " + hex);
        return hex;
    }
    public static String bajarBrillo(String colorHex) {
        Color color = Color.decode(colorHex);
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float newBrillo = hsb[2] - 0.20f;
        color = Color.getHSBColor(hsb[0], hsb[1], newBrillo < 0 ? newBrillo + 0.20f : newBrillo); // Si es muy oscuro el brillo se sube en lugar de bajarlo
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        // System.out.println("color " + hex);
        return hex;
    }
    
    public static double redondear(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
    /**
     * Verifica si el mouse esta dentro del rango del componente o fuera
     * @param c
     * @param e
     * @return 
     */
    public static boolean isMouseInComponent(JComponent c, MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (x < 0 || y < 0 || x > c.getWidth() || y > c.getHeight())
            return false;
        else
            return true;
    }
    /*public static String alphaColor(String colorHex, float alpha) {
        Color colorOriginal = Color.decode(colorHex);
        Color color = new Color(colorOriginal.getRed(), colorOriginal.getGreen(), colorOriginal.getBlue(), 0.9f);
        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        return hex;
    }*/
}
