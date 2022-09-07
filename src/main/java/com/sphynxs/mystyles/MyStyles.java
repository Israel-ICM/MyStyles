package com.sphynxs.mystyles;

import com.sphynxs.mystyles.styles.MetroUI;
import com.sphynxs.mystyles.ui.metro.tools.MetroUIStyleColors;

/**
 * @author israel-icm
 * @date 2021-08-10
 * @version 1.1.1
 */
public class MyStyles {
    public final static int METRO_UI = 1;
    public final static int METRO_UI_DARK = 2;
    
    public final static String COLOR_RED = "#EA4E4E";
    public final static String COLOR_ORANGE = "#F97637";
    public final static String COLOR_YELLOW = "#FFDA67";
    public final static String COLOR_GREEN_LIGHT = "#7CEA82";
    public final static String COLOR_GREEN_DARK = "#38AA5E";
    public final static String COLOR_BLUE_LIGHT = "#63C6E8";
    public final static String COLOR_BLUE_DARK = "#2D8DAF";
    public final static String COLOR_PURPLE_LIGHT = "#CB7BF7";
    public final static String COLOR_PURPLE_DARK = "#924BB7";
    public final static String COLOR_ROSE = "#F97EC1";

    /**
     * Inicializa el estilo que se quiere utilizar
     * @param style Estilo que se utilizará (Ej. MyStyles.METRO_UI)
     */
    public static void install(int style) {
        install(style, MetroUIStyleColors.COLOR_PRIMARY, false);
    }
    /**
     * Inicializa el estilo que se quiere utilizar
     * @param style Estilo que se utilizará (Ej. MyStyles.METRO_UI)
     * @param primaryColor Color hexadecimal que se utilizará en el estilo
     */
    public static void install(int style, String primaryColor) {
        install(style, primaryColor, false);
    }
    /**
     * Inicializa el estilo que se quiere utilizar
     * @param style Estilo que se utilizará (Ej. MyStyles.METRO_UI)
     * @param primaryColor Color hexadecimal que se utilizará en el estilo
     * @param autoColorCorrector Define si se utilizará el corrector de color según la variable primaryColor
     */
    public static void install(int style, String primaryColor, boolean autoColorCorrector) {
        switch (style) {
            case MyStyles.METRO_UI: MetroUI.installLight(primaryColor, autoColorCorrector); break;
            case MyStyles.METRO_UI_DARK: MetroUI.installDark(primaryColor, autoColorCorrector); break;
            default: break;
        }
    }
}
