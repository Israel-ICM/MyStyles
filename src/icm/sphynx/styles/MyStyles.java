package icm.sphynx.styles;

/**
 * @author israel-icm
 * @date 2021-08-10
 * @version 1.0.0
 */
public class MyStyles {
    public final static int METRO_UI = 1;
    public final static int METRO_UI_DARK = 2;
    
    /**
     * Inicializa el estilo que se quiere utilizar
     * @param style Estilo que se utilizará (Ej. MyStyles.METRO_UI)
     */
    public static void install(int style) {
        switch (style) {
            case MyStyles.METRO_UI: new MetroUI().installLight(); break;
            case MyStyles.METRO_UI_DARK: new MetroUI().installDark(); break;
            default: break;
        }
    }
}
