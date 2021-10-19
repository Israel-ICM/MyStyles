package icm.sphynx.ui.components.metro;

import javax.swing.UIManager;

/**
 * Agrega propiedades a los componentes que lo soportan
 * @author israel-icm
 */
public class MetroUIComponent {
    private static final String BUTTON = "MetroButton";
    private static final String CHECK_BOX = "MetroCheckBox";
    private static final String PASSWORD_FIELD = "MetroPasswordField";
    private static final String TEXT_FIELD = "MetroTextField";
    private static final String TEXT_AREA = "MetroTextArea";

    private static final String BUTTON_EMPTY = "empty";
    private static final String BUTTON_LINK = "link";
    private static final String CHECK_BOX_AS_SWITCH = "switch";
    private static final String CHECK_BOX_SWITCH_TEXT_ON = "switchTextOn";
    private static final String CHECK_BOX_SWITCH_TEXT_OFF = "switchTextOff";
    public static final String ICON_SEARCH = "search";
    private static final String TEXT_FIELD_PROPERTY_PLACEHOLDER = "placeholder";
    private static final String TEXT_FIELD_PROPERTY_ICON = "icon";
    
    /**
     * Agrega el ícono predeterminado a un textfield
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Valor de la propiedad (Ej: MetroUIConfigComponent.ICON_SEARCH)
     */
    public static void setTextFieldIcon(String nameComponent, String value) {
        UIManager.put(TEXT_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_ICON, value);
    }
    /**
     * Agrega el placeholder a un textarea en específico
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Texto que se mostrará en la caja de texto
     * @example MetroUIConfigComponent.setPropertyTextField("buscar", "Buscar...");
     */
    public static void setTextFieldPlaceholder(String nameComponent, String value) {
        UIManager.put(TEXT_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER, value);
    }
    /**
     * Agrega el placeholder a un textfield en específico
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Texto que se mostrará en la caja de texto
     * @example MetroUIConfigComponent.setPropertyTextField("buscar", "Ingrese el texto...");
     */
    public static void setTextAreaPlaceholder(String nameComponent, String value) {
        UIManager.put(TEXT_AREA + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER, value);
    }
    /**
     * Agrega el placeholder a un passwordField en específico
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Texto que se mostrará en la caja de texto
     * @example MetroUIConfigComponent.setPropertyTextField("buscar", "Password");
     */
    public static void setPasswordFieldPlaceholder(String nameComponent, String value) {
        UIManager.put(PASSWORD_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER, value);
    }
    /**
     * Estable el botón sin bordes y sin relleno
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     */
    public static void setButtonEmpty(String nameComponent) {
        UIManager.put(BUTTON + "." + nameComponent + "." + BUTTON_EMPTY, true);
    }
    /**
     * Asigna estilos al botón para que se vea como un link
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     */
    public static void setButtonLink(String nameComponent) {
        UIManager.put(BUTTON + "." + nameComponent + "." + BUTTON_LINK, true);
    }
    /**
     * Convierte un componente checkbox en un switch
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param textOn Texto que se mostrará cuando el switch se encuentre activado
     * @param textOff Texto que se mostrará cuando el switch se encuentre desactivado 
     */
    public static void setCheckBoxAsSwitch(String nameComponent, String textOn, String textOff) {
        UIManager.put(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_AS_SWITCH, true);
        UIManager.put(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_SWITCH_TEXT_ON, textOn);
        UIManager.put(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_SWITCH_TEXT_OFF, textOff);
    }
    /**
     * Convierte un componente checkbox en un switch
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     */
    public static void setCheckBoxAsSwitch(String nameComponent) {
        UIManager.put(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_AS_SWITCH, true);
    }

    /**
     * Retorna el valor de la propiedad icon
     * @param nameComponent
     * @return 
     */
    public static String getPropertyTextFieldIcon(String nameComponent) {
        return UIManager.getString(TEXT_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_ICON);
    }
    /**
     * Retorna el valor de la propiedad placeholder
     * @param nameComponent
     * @return 
     */
    public static String getPropertyTextFieldPlaceholder(String nameComponent) {
        return UIManager.getString(TEXT_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER);
    }
    /**
     * Retorna el valor de la propiedad placeholder
     * @param nameComponent
     * @return 
     */
    public static String getPropertyTextAreaPlaceholder(String nameComponent) {
        return UIManager.getString(TEXT_AREA + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER);
    }
    /**
     * Retorna el valor de la propiedad placeholder
     * @param nameComponent
     * @return 
     */
    public static String getPropertyPasswordFieldPlaceholder(String nameComponent) {
        return UIManager.getString(PASSWORD_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER);
    }
    /**
     * Retorna el valor de la propiedad empty button
     * @param nameComponent
     * @return 
     */
    public static boolean getPropertyButtonEmpty(String nameComponent) {
        return UIManager.getBoolean(BUTTON + "." + nameComponent + "." + BUTTON_EMPTY);
    }
    /**
     * Retorna el valor de la propiedad link button
     * @param nameComponent
     * @return 
     */
    public static boolean getPropertyButtonLink(String nameComponent) {
        return UIManager.getBoolean(BUTTON + "." + nameComponent + "." + BUTTON_LINK);
    }
    /**
     * Retorna el valor de la propiedad empty button
     * @param nameComponent
     * @return 
     */
    public static boolean getPropertyCheckBoxAsSwitch(String nameComponent) {
        return UIManager.getBoolean(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_AS_SWITCH);
    }
    /**
     * Retorna el valor de la propiedad text on del componente switch
     * @param nameComponent
     * @return 
     */
    public static String getPropertyCheckBoxSwitchTextOn(String nameComponent) {
        return UIManager.getString(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_SWITCH_TEXT_ON);
    }
    /**
     * Retorna el valor de la propiedad text off del componente switch
     * @param nameComponent
     * @return 
     */
    public static String getPropertyCheckBoxSwitchTextOff(String nameComponent) {
        return UIManager.getString(CHECK_BOX + "." + nameComponent + "." + CHECK_BOX_SWITCH_TEXT_OFF);
    }
}
