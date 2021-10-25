package icm.sphynx.styles;

import icm.sphynx.ui.metro.manager.UICheckBoxMenuItem;
import icm.sphynx.ui.metro.tools.MetroUIConfigTheme;
import icm.sphynx.ui.metro.manager.UIButton;
import icm.sphynx.ui.metro.manager.UICheckBox;
import icm.sphynx.ui.metro.manager.UIComboBox;
import icm.sphynx.ui.metro.manager.UIFormattedTextField;
import icm.sphynx.ui.metro.manager.UISpinner;
import icm.sphynx.ui.metro.manager.UILabel;
import icm.sphynx.ui.metro.manager.UIList;
import icm.sphynx.ui.metro.manager.UIMenu;
import icm.sphynx.ui.metro.manager.UIMenuBar;
import icm.sphynx.ui.metro.manager.UIMenuItem;
import icm.sphynx.ui.metro.manager.UIOptionPane;
import icm.sphynx.ui.metro.manager.UIPanel;
import icm.sphynx.ui.metro.manager.UIPasswordField;
import icm.sphynx.ui.metro.manager.UIPopupMenu;
import icm.sphynx.ui.metro.manager.UIProgressBar;
import icm.sphynx.ui.metro.manager.UIRadioButton;
import icm.sphynx.ui.metro.manager.UIRadioButtonMenuItem;
import icm.sphynx.ui.metro.manager.UIScrollBar;
import icm.sphynx.ui.metro.manager.UISlider;
import icm.sphynx.ui.metro.manager.UISplitPane;
import icm.sphynx.ui.metro.manager.UITabbedPane;
import icm.sphynx.ui.metro.manager.UITable;
import icm.sphynx.ui.metro.manager.UITableHeader;
import icm.sphynx.ui.metro.manager.UITextArea;
import icm.sphynx.ui.metro.manager.UITextField;
import icm.sphynx.ui.metro.manager.UIToggleButton;
import icm.sphynx.ui.metro.manager.UIToolTip;
import icm.sphynx.ui.metro.manager.UITools;
import icm.sphynx.ui.metro.tools.MetroUIIcons;
import icm.sphynx.ui.metro.tools.MetroUIStyleColors;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

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

        UIManager.put("MenuBar.background", Color.decode(MetroUIStyleColors.PANEL_BACKGROUND));
        UIManager.put("MenuBar.border", null);

        // UIManager.put("Menu.background", Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));
        //UIManager.put("Menu.foreground", Color.decode(MetroUIStyleColors.PANEL_FOREGROUND));
        // UIManager.put("Menu.font", new Font(UITools.FONT_DEFAULT, Font.BOLD, 13)); // Esto solo es un auxiliar para poder centrar el texto del menu

        // UIManager.put("MenuItem.background", Color.decode(MetroUIStyleColors.PANEL_BACKGROUND));
        // UIManager.put("MenuItem.foreground", Color.decode(MetroUIStyleColors.PANEL_FOREGROUND));
        // UIManager.put("MenuItem.opaque", false); // Esto tiene que estar siempre para poder cambiar el background

        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ComboBox.font", new Font(UITools.FONT_DEFAULT, Font.PLAIN, 12));

        UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));

        MatteBorder bordeDelgadoGris = BorderFactory.createMatteBorder(0, 1, 0, 1, Color.decode("#CCCCCC"));
        CompoundBorder bordeAnchoCelda = BorderFactory.createCompoundBorder(bordeDelgadoGris, BorderFactory.createEmptyBorder(UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS, UITools.PADDING_CONTENTS));
        UIManager.put("TableHeader.cellBorder", bordeAnchoCelda);

        UIManager.put("FileChooser.detailsViewIcon", MetroUIIcons.iconListDetail(18, "#000000"));
        UIManager.put("FileChooser.homeFolderIcon", new MetroUIIcons().iconHome(18, true));
        UIManager.put("FileChooser.newFolderIcon", new MetroUIIcons().iconNewFolder(18, true));
        UIManager.put("FileChooser.listViewIcon", new MetroUIIcons().iconList(18, true));
        UIManager.put("FileChooser.upFolderIcon", new MetroUIIcons().iconUpFolder(18, true));

        UIManager.put("FileView.computerIcon", new MetroUIIcons().iconComputer(20));
        UIManager.put("FileView.fileIcon", new MetroUIIcons().iconFile(20));
        UIManager.put("FileView.directoryIcon", new MetroUIIcons().iconDirectory(20));
        UIManager.put("FileView.hardDriveIcon", new MetroUIIcons().iconHardDrive(20));
        UIManager.put("FileView.floppyDriveIcon", new MetroUIIcons().iconFloppyDrive(20));

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

        UIManager.put("MenuBar.background", Color.decode(MetroUIStyleColors.PANEL_BACKGROUND));
        UIManager.put("MenuBar.border", null);

        // UIManager.put("Menu.background", Color.decode(StyleColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("Menu.foreground", Color.decode(MetroUIStyleColors.PANEL_FOREGROUND));
        UIManager.put("Menu.font", new Font(UITools.FONT_DEFAULT, Font.BOLD, 13));

        UIManager.put("MenuItem.background", Color.decode(MetroUIStyleColors.PANEL_BACKGROUND));
        UIManager.put("MenuItem.foreground", Color.decode(MetroUIStyleColors.PANEL_FOREGROUND));
        UIManager.put("MenuItem.opaque", false); // Esto tiene que estar siempre para poder cambiar el background

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

        UIManager.put("FileChooser.detailsViewIcon", MetroUIIcons.iconListDetail(18, "#FFFFFF"));
        UIManager.put("FileChooser.homeFolderIcon", new MetroUIIcons().iconHome(18, false));
        UIManager.put("FileChooser.newFolderIcon", new MetroUIIcons().iconNewFolder(18, false));
        UIManager.put("FileChooser.listViewIcon", new MetroUIIcons().iconList(18, false));
        UIManager.put("FileChooser.upFolderIcon", new MetroUIIcons().iconUpFolder(18, false));

        UIManager.put("FileView.computerIcon", new MetroUIIcons().iconComputer(20));
        UIManager.put("FileView.fileIcon", new MetroUIIcons().iconFile(20));
        UIManager.put("FileView.directoryIcon", new MetroUIIcons().iconDirectory(20));
        UIManager.put("FileView.hardDriveIcon", new MetroUIIcons().iconHardDrive(20));
        UIManager.put("FileView.floppyDriveIcon", new MetroUIIcons().iconFloppyDrive(20));

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
        UIManager.put("CheckBoxMenuItemUI", UICheckBoxMenuItem.class.getName());
        UIManager.put("RadioButtonMenuItemUI", UIRadioButtonMenuItem.class.getName());
        UIManager.put("MenuUI", UIMenu.class.getName());
        UIManager.put("PopupMenuUI", UIPopupMenu.class.getName());
        UIManager.put("TableHeaderUI", UITableHeader.class.getName());
        UIManager.put("TableUI", UITable.class.getName());
        UIManager.put("OptionPaneUI", UIOptionPane.class.getName());
        UIManager.put("SpinnerUI", UISpinner.class.getName());
        UIManager.put("FormattedTextFieldUI", UIFormattedTextField.class.getName());
        UIManager.put("ToggleButtonUI", UIToggleButton.class.getName());
        // UIManager.put("FileChooserUI", UIFileChooser.class.getName());
        UIManager.put("ComboBoxUI", UIComboBox.class.getName());
        UIManager.put("SplitPaneUI", UISplitPane.class.getName());
        UIManager.put("ToolTipUI", UIToolTip.class.getName());
        
        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            // e.printStackTrace();
        }*/
    }
}
