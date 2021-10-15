package icm.sphynx.styles;

import icm.sphynx.ui.components.metro.MetroUIConfigTheme;
import icm.sphynx.ui.components.metro.UIButton;
import icm.sphynx.ui.components.metro.UICheckBox;
import icm.sphynx.ui.components.metro.UIComboBox;
import icm.sphynx.ui.components.metro.UIFormattedTextField;
import icm.sphynx.ui.components.metro.UISpinner;
import icm.sphynx.ui.components.metro.UILabel;
import icm.sphynx.ui.components.metro.UIList;
import icm.sphynx.ui.components.metro.UIMenu;
import icm.sphynx.ui.components.metro.UIMenuBar;
import icm.sphynx.ui.components.metro.UIMenuItem;
import icm.sphynx.ui.components.metro.UIOptionPane;
import icm.sphynx.ui.components.metro.UIPanel;
import icm.sphynx.ui.components.metro.UIPasswordField;
import icm.sphynx.ui.components.metro.UIProgressBar;
import icm.sphynx.ui.components.metro.UIRadioButton;
import icm.sphynx.ui.components.metro.UIScrollBar;
import icm.sphynx.ui.components.metro.UISlider;
import icm.sphynx.ui.components.metro.UISplitPane;
import icm.sphynx.ui.components.metro.UITabbedPane;
import icm.sphynx.ui.components.metro.UITable;
import icm.sphynx.ui.components.metro.UITableHeader;
import icm.sphynx.ui.components.metro.UITextArea;
import icm.sphynx.ui.components.metro.UITextField;
import icm.sphynx.ui.components.metro.UIToggleButton;
import icm.sphynx.ui.components.metro.UITools;
import icm.sphynx.ui.tools.IconsMetroUI;
import icm.sphynx.ui.tools.StyleColorsMetro;
import icm.sphynx.ui.tools.ThemeMetroUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * @author israel-icm
 */
public class MetroUI {
    /**
     * Instala la versión normal del estilo
     * @param primaryColor Color hexadecimal que se utilizará en el estilo
     * @param autoColorCorrector Define si se utilizará el corrector de color según la variable primaryColor
     */
    public static void installLight(String primaryColor, boolean autoColorCorrector) {
        if (autoColorCorrector)
            primaryColor = UITools.getColorPredeterminado(primaryColor);

        MetroUIConfigTheme.setDarkMode(false);
        MetroUIConfigTheme.setPrimaryColor(primaryColor);

        installComponents();

        UIManager.put("MenuBar.background", Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuBar.border", null);

        // UIManager.put("Menu.background", Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("Menu.foreground", Color.decode(StyleColorsMetro.LIGHT_FOREGROUND));
        UIManager.put("Menu.font", new Font(UITools.FONT_DEFAULT, Font.BOLD, 13));

        UIManager.put("MenuItem.background", Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuItem.foreground", Color.decode(StyleColorsMetro.LIGHT_FOREGROUND));

        // LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        // UIManager.put("ComboBox.selectionBackground", MetroUIConfigTheme.getPrimaryColor());
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.font", new Font(UITools.FONT_DEFAULT, Font.PLAIN, 12));
        // UIManager.put("ComboBox.border", border);
        // UIManager.put("ComboBox.buttonHighlight", MetroUIConfigTheme.getPrimaryColor());

        UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));

        MatteBorder bordeDelgadoGris = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.decode("#CCCCCC"));
        CompoundBorder bordeAnchoCelda = BorderFactory.createCompoundBorder(bordeDelgadoGris, BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS));
        UIManager.put("TableHeader.cellBorder", bordeAnchoCelda);

        UIManager.put("FileChooser.detailsViewIcon", IconsMetroUI.iconListDetail(18, "#FFFFFF"));
        UIManager.put("FileChooser.homeFolderIcon", new IconsMetroUI().iconHome(18));
        UIManager.put("FileChooser.newFolderIcon", new IconsMetroUI().iconNewFolder(18));
        UIManager.put("FileChooser.listViewIcon", new IconsMetroUI().iconList(18));
        UIManager.put("FileChooser.upFolderIcon", new IconsMetroUI().iconUpFolder(18));

        UIManager.put("FileView.computerIcon", new IconsMetroUI().iconComputer(20));
        UIManager.put("FileView.fileIcon", new IconsMetroUI().iconFile(20));
        UIManager.put("FileView.directoryIcon", new IconsMetroUI().iconDirectory(20));
        UIManager.put("FileView.hardDriveIcon", new IconsMetroUI().iconHardDrive(20));
        UIManager.put("FileView.floppyDriveIcon", new IconsMetroUI().iconFloppyDrive(20));

        UIManager.put("ProgressBar.cycleTime", 1);
    }

    /**
     * Instala la versión oscura del estilo
     */
    public static void installDark(String primaryColor, boolean autoColorCorrector) {
        if (autoColorCorrector)
            primaryColor = UITools.getColorPredeterminado(primaryColor);

        MetroUIConfigTheme.setDarkMode(true);
        MetroUIConfigTheme.setPrimaryColor(primaryColor);

        installComponents();

        UIManager.put("MenuBar.background", Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuBar.border", null);

        // UIManager.put("Menu.background", Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("Menu.foreground", Color.decode(StyleColorsMetro.LIGHT_FOREGROUND));
        UIManager.put("Menu.font", new Font(UITools.FONT_DEFAULT, Font.BOLD, 13));

        UIManager.put("MenuItem.background", Color.decode(StyleColorsMetro.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuItem.foreground", Color.decode(StyleColorsMetro.LIGHT_FOREGROUND));

        LineBorder border = new LineBorder(Color.decode(UITools.COLOR_BORDER_DEFAULT), 2);
        // UIManager.put("ComboBox.selectionBackground", MetroUIConfigTheme.getPrimaryColor());
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.font", new Font(UITools.FONT_DEFAULT, Font.PLAIN, 12));
        UIManager.put("ComboBox.border", border);
        // UIManager.put("ComboBox.buttonHighlight", MetroUIConfigTheme.getPrimaryColor());

        Color bordeFocus = Color.WHITE;
        if (MetroUIConfigTheme.isDarkMode())
            bordeFocus = Color.decode("#CCCCCC");
        UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createMatteBorder(2, 2, 2, 2, bordeFocus));

        MatteBorder bordeDelgadoGris = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.decode("#CCCCCC"));
        CompoundBorder bordeAnchoCelda = BorderFactory.createCompoundBorder(bordeDelgadoGris, BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS));
        UIManager.put("TableHeader.cellBorder", bordeAnchoCelda);

        UIManager.put("FileChooser.detailsViewIcon", IconsMetroUI.iconListDetail(18, "#FFFFFF"));
        UIManager.put("FileChooser.homeFolderIcon", new IconsMetroUI().iconHome(18));
        UIManager.put("FileChooser.newFolderIcon", new IconsMetroUI().iconNewFolder(18));
        UIManager.put("FileChooser.listViewIcon", new IconsMetroUI().iconList(18));
        UIManager.put("FileChooser.upFolderIcon", new IconsMetroUI().iconUpFolder(18));

        UIManager.put("FileView.computerIcon", new IconsMetroUI().iconComputer(20));
        UIManager.put("FileView.fileIcon", new IconsMetroUI().iconFile(20));
        UIManager.put("FileView.directoryIcon", new IconsMetroUI().iconDirectory(20));
        UIManager.put("FileView.hardDriveIcon", new IconsMetroUI().iconHardDrive(20));
        UIManager.put("FileView.floppyDriveIcon", new IconsMetroUI().iconFloppyDrive(20));

        UIManager.put("ProgressBar.cycleTime", 1);
    }
    
    /**
     * Cambia los estilos componente a componente
     */
    private static void installComponents() {
        // MetalLookAndFeel.setCurrentTheme(new ThemeMetroUI());
        UIManager.put("ScrollBarUI", UIScrollBar.class.getName());
        UIManager.put("LabelUI", UILabel.class.getName());
        UIManager.put("PanelUI", UIPanel.class.getName());
        UIManager.put("ButtonUI", UIButton.class.getName());
        UIManager.put("CheckBoxUI", UICheckBox.class.getName());
        UIManager.put("RadioButtonUI", UIRadioButton.class.getName());
        UIManager.put("TextFieldUI", UITextField.class.getName());
        UIManager.put("PasswordFieldUI", UIPasswordField.class.getName());
        UIManager.put("TextAreaUI", UITextArea.class.getName());
        UIManager.put("SliderUI", UISlider.class.getName());
        UIManager.put("ListUI", UIList.class.getName());
        UIManager.put("TabbedPaneUI", UITabbedPane.class.getName());
        UIManager.put("ProgressBarUI", UIProgressBar.class.getName());
        UIManager.put("MenuBarUI", UIMenuBar.class.getName());
        UIManager.put("MenuItemUI", UIMenuItem.class.getName());
        UIManager.put("MenuUI", UIMenu.class.getName());
        UIManager.put("TableHeaderUI", UITableHeader.class.getName());
        UIManager.put("TableUI", UITable.class.getName());
        UIManager.put("OptionPaneUI", UIOptionPane.class.getName());
        UIManager.put("SpinnerUI", UISpinner.class.getName());
        UIManager.put("FormattedTextFieldUI", UIFormattedTextField.class.getName());
        UIManager.put("ToggleButtonUI", UIToggleButton.class.getName());
        // UIManager.put("FileChooserUI", UIFileChooser.class.getName());
        UIManager.put("ComboBoxUI", UIComboBox.class.getName());
        UIManager.put("SplitPaneUI", UISplitPane.class.getName());
        
        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            // e.printStackTrace();
        }*/
    }
}
