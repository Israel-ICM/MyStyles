package icm.sphynx.ui.tools;

import icm.sphynx.ui.components.metro.UITools;
import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * Cambio para valores default del tema utilizado (Por el momento no se lo utilizará)
 * @author israel-icm
 */
public class ThemeMaterialUI extends DefaultMetalTheme {
    private final ColorUIResource primary1 = new ColorUIResource(Color.decode("#FFFFFF"));
    private final ColorUIResource primary2 = new ColorUIResource(Color.decode("#49A6C5"));
    private final ColorUIResource primary3 = new ColorUIResource(Color.decode("#006699"));
    private final ColorUIResource secondary1 = new ColorUIResource(Color.decode(UITools.COLOR_PRIMARY));
    private final ColorUIResource secondary2 = new ColorUIResource(Color.decode("#F0F0F0"));
    private final ColorUIResource secondary3 = new ColorUIResource(Color.decode("#FFFFFF"));
    
    private final FontUIResource menuTextFont = new FontUIResource(UITools.FONT_DEFAULT, Font.PLAIN, 13);
    private final FontUIResource windowTitleFont = new FontUIResource(UITools.FONT_DEFAULT, Font.PLAIN, 12);

    @Override
    public String getName() { return "MaterialUI"; }
    @Override
    protected ColorUIResource getPrimary1() { return primary1; }
    @Override
    protected ColorUIResource getPrimary2() { return primary2; }
    @Override
    protected ColorUIResource getPrimary3() { return primary3; }
    @Override
    protected ColorUIResource getSecondary1() { return secondary1; }
    @Override
    protected ColorUIResource getSecondary2() { return secondary2; }
    @Override
    protected ColorUIResource getSecondary3() { return secondary3; }
    @Override
    public FontUIResource getMenuTextFont() { return menuTextFont; }
    @Override
    public FontUIResource getWindowTitleFont() { return windowTitleFont; }
}
