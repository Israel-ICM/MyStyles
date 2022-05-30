package com.icm.sphynx.mystyles.ui.metro.tools;

import com.icm.sphynx.mystyles.ui.metro.manager.UITools;
import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * Cambio para valores default del tema utilizado (Por el momento no se lo utilizará)
 * @author israel-icm
 */
public class ThemeMetroUI extends DefaultMetalTheme {
    private final ColorUIResource primary1 = new ColorUIResource(Color.decode("#212121")); // bordes del menu
    private final ColorUIResource primary2 = new ColorUIResource(Color.decode("#00FF00"));
    private final ColorUIResource primary3 = new ColorUIResource(Color.decode("#0000FF"));
    private final ColorUIResource secondary1 = new ColorUIResource(Color.decode("#212121")); // bordes de componentes
    private final ColorUIResource secondary2 = new ColorUIResource(Color.decode("#FFFFFF")); // bordes del contenedos principal
    private final ColorUIResource secondary3 = null; // borde al presionar un button toggle
    
    private final FontUIResource menuTextFont = new FontUIResource(UITools.FONT_DEFAULT, Font.PLAIN, 13);
    private final FontUIResource windowTitleFont = new FontUIResource(UITools.FONT_DEFAULT, Font.PLAIN, 12);

    @Override
    public String getName() { return "MetroUI"; }
    @Override
    protected ColorUIResource getPrimary1() { return primary1; }
    @Override
    protected ColorUIResource getPrimary2() { return primary2; }
    @Override
    protected ColorUIResource getPrimary3() { return primary3; }
    @Override
    protected ColorUIResource getSecondary1() { return /*new ColorUIResource(MetroUIConfigTheme.getPrimaryColor());*/secondary1; } // bordes de componentes
    @Override
    protected ColorUIResource getSecondary2() { return secondary2; }
    @Override
    protected ColorUIResource getSecondary3() { return secondary3; }
    @Override
    public FontUIResource getMenuTextFont() { return menuTextFont; }
    @Override
    public FontUIResource getWindowTitleFont() { return windowTitleFont; }
}
