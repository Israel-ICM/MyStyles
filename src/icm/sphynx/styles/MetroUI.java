/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icm.sphynx.styles;

import icm.sphynx.ui.components.metro.UIButton;
import icm.sphynx.ui.components.metro.UICheckBox;
import icm.sphynx.ui.components.metro.UILabel;
import icm.sphynx.ui.components.metro.UIList;
import icm.sphynx.ui.components.metro.UILookAndFeel;
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
import icm.sphynx.ui.components.metro.UITabbedPane;
import icm.sphynx.ui.components.metro.UITable;
import icm.sphynx.ui.components.metro.UITableHeader;
import icm.sphynx.ui.components.metro.UITextArea;
import icm.sphynx.ui.components.metro.UITextField;
import icm.sphynx.ui.tools.MaterialColors;
import java.awt.Color;
import javax.swing.UIManager;

/**
 *
 * @author israel-icm
 */
public class MetroUI {
    public void installLight() {
        installComponents();
        UIManager.put("Panel.background", Color.decode(MaterialColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuBar.background", Color.decode(MaterialColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuBar.border", null);
        UIManager.put("Menu.background", Color.decode(MaterialColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuItem.background", Color.decode(MaterialColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("MenuItem.foreground", Color.decode(MaterialColors.LIGHT_FOREGROUND));
        UIManager.put("Label.foreground", Color.decode(MaterialColors.LIGHT_FOREGROUND));
        UIManager.put("CheckBox.foreground", Color.decode(MaterialColors.LIGHT_FOREGROUND));
        UIManager.put("RadioButton.foreground", Color.decode(MaterialColors.LIGHT_FOREGROUND));
        UIManager.put("Table.background", Color.decode(MaterialColors.LIGHT_BACKGROUND_PANEL));
        UIManager.put("Table.foreground", Color.decode(MaterialColors.LIGHT_FOREGROUND));
    }
    
    public void installDark() {
        installComponents();
        UIManager.put("Panel.background", Color.decode(MaterialColors.DARK_BACKGROUND_PANEL));
        UIManager.put("MenuBar.background", Color.decode(MaterialColors.DARK_BACKGROUND_PANEL));
        UIManager.put("MenuBar.border", Color.decode(MaterialColors.DARK_FOREGROUND));
        UIManager.put("Menu.background", Color.decode(MaterialColors.DARK_BACKGROUND_PANEL));
        UIManager.put("MenuItem.background", Color.decode(MaterialColors.DARK_BACKGROUND_PANEL));
        UIManager.put("MenuItem.foreground", Color.decode(MaterialColors.DARK_FOREGROUND));
        UIManager.put("Label.foreground", Color.decode(MaterialColors.DARK_FOREGROUND));
        UIManager.put("CheckBox.foreground", Color.decode(MaterialColors.DARK_FOREGROUND));
        UIManager.put("RadioButton.foreground", Color.decode(MaterialColors.DARK_FOREGROUND));
        UIManager.put("Table.background", Color.decode(MaterialColors.DARK_BACKGROUND_PANEL));
        UIManager.put("Table.foreground", Color.decode(MaterialColors.DARK_FOREGROUND));
        // UIManager.put("ScrollPane.background", Color.red);
    }
    
    private void installComponents() {
        // MetalLookAndFeel.setCurrentTheme(new ThemeMaterialUI());
        UIManager.put("LookAndFeelUI", UILookAndFeel.class.getName());
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
        // UIManager.put("ComboBoxUI", UIComboBox.class.getName());
    }
}
