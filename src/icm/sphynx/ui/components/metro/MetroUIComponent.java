package icm.sphynx.ui.components.metro;

import javax.swing.UIManager;

/**
 * Agrega propiedades a los componentes que lo soportan
 * @author israel-icm
 */
public class MetroUIComponent {
    private static final String TEXT_FIELD = "MetroTextField";
    private static final String PASSWORD_FIELD = "MetroPasswordField";

    private static final String TEXT_FIELD_PROPERTY_PLACEHOLDER = "placeholder";
    private static final String TEXT_FIELD_PROPERTY_ICON = "icon";
    public static final String ICON_SEARCH = "search";
    
    /**
     * Agrega el ícono predeterminado a un textfield
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Valor de la propiedad (Ej: MetroUIConfigComponent.ICON_SEARCH)
     * @example MetroUIConfigComponent.setPropertyTextField("buscar", TEXT_FIELD_PROPERTY_PLACEHOLDER, "Buscar...");
     */
    public static void setTextFieldIcon(String nameComponent, String value) {
        UIManager.put(TEXT_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_ICON, value);
    }
    /**
     * Agrega el placeholder a un textfield en específico
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Texto que se mostrará en la caja de texto
     * @example MetroUIConfigComponent.setPropertyTextField("buscar", TEXT_FIELD_PROPERTY_PLACEHOLDER, "Buscar...");
     */
    public static void setTextFieldPlaceholder(String nameComponent, String value) {
        UIManager.put(TEXT_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER, value);
    }
    /**
     * Agrega el placeholder a un passwordField en específico
     * @param nameComponent Nombre del componente al que se aplicará la propiedad
     * @param value Texto que se mostrará en la caja de texto
     * @example MetroUIConfigComponent.setPropertyTextField("buscar", TEXT_FIELD_PROPERTY_PLACEHOLDER, "Buscar...");
     */
    public static void setPasswordFieldPlaceholder(String nameComponent, String value) {
        UIManager.put(PASSWORD_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER, value);
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
    public static String getPropertyPasswordFieldPlaceholder(String nameComponent) {
        return UIManager.getString(PASSWORD_FIELD + "." + nameComponent + "." + TEXT_FIELD_PROPERTY_PLACEHOLDER);
    }
}
