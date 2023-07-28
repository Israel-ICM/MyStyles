# MyStyles
Es una librería para modificar la interfaz de cualquier programa JAVA Swing con una sencilla línea de código que corresponde a la instalación del estilo en la aplicación que necesita.

Con esta librería convertirá un programa que se vé así:

![Captura de pantalla 1](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/screen_normal.jpg)

Utilizando una simple línea de código conseguirá que se vea de la siguiente forma:

![Captura de pantalla 2](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/screen_light.jpg)

O utilizar su modo oscuro:

![Captura de pantalla dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/screen_dark.jpg)

Apreciarás también que se agregan algunas funcionalidades extra como el botón para ver el password en el passwordfield o el botón para limpiar los textfield o ciertas animaciones en los scroll y tambien incluyendo nuevos componentes como el switch.

A continuación se muestran algunas capturas de los componentes en su modo dia y modo noche:

### Panel de pestañas

![tabbed pane](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/TabbedPane.gif)
![tabbed pane dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/TabbedPaneDark.gif)

### Cajas de texto y combobox

![inputs](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/Inputs.gif)
![inputs dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/InputsDark.gif)

### Botones

![buttons](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/Buttons.gif)
![buttons_dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/ButtonsDark.gif)

### Componentes de selección múltiple

![selection](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/Selection.gif)
![selection dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/SelectionDark.gif)

### Componentes de progreso

![progress](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/Progress.gif)
![progress dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/ProgressDark.gif)

### Contenedores, listas y tablas

![contents](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/Contents.gif)
![contents dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/ContentsDark.gif)

### Diálogos

![contents](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/Dialogs.gif)
![contents](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/DialogsDark.gif)

### Tooltips y menús

![tooltip menu](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/TooltipMenu.gif)
![tooltip menu dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/TooltipMenuDark.gif)
![menubar](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/MenuBar.gif)
![menubar dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/MenuBarDark.gif)

## Comenzando 🚀

Primeramente te aviso que el proyecto fué desarrollado con **JAVA 8** pero los métodos utilizados no deberían dar problemas en posteriores versiones.

## Descargas 📁

Versiones disponibles:

08-10-2021 | [MyStyles v1.0.0-beta](https://github.com/Israel-ICM/MyStyles/releases/download/v1.0.0-beta/MyStylesv1.0.0.jar)

14-10-2021 | [MyStyles v1.0.1-beta](https://github.com/Israel-ICM/MyStyles/releases/download/v1.0.1-beta/MyStylesv1.0.1.jar)

25-10-2021 | [MyStyles v1.1.0](https://github.com/Israel-ICM/MyStyles/releases/download/1.1.0/MyStylesv1.1.0.jar)

También puedes descargar el ejemplo funcional que se muestra en las capturas anteriores (Proyecto NetBeans)

25-10-2021 | [MyStylesTest v1.1.0](https://github.com/Israel-ICM/MyStylesTest)

## Ejecutando las pruebas ⚙️
Como verás a continuación el funcionamiento es bastante simple:

Primeramente se debe agregar la libreria *".jar"* al proyecto donde se quiere aplicar los estilos.

Una vez agregada la librería al proyecto, solo debe instanciar la clase **MyStyles** y llamar al método estático **install(style)** el parámetro es una variable estática también declarada en la clase **MyStyles** siendo la única línea de código `MyStyles.install(MyStyles.METRO_UI);` que debe importarse de: `import icm.sphynx.styles.MyStyles;`, a continuación se muestra un ejemplo básico:

Un main común en java se vé de la siguiente forma:

```java
package mystylestest;

public class MyStylesTest {
    public static void main(String[] args) {
        new Test().setVisible(true);
    }
}
```
En este caso particular `Test` es nuestro **JFrame** razón por la que se inicializa en el main

Ahora si queremos asignarle los estilos de la librería **MyStyles** lo único que haremos será agregar la línea de instalación antes de todos los métodos, por ejemplo:
```java
package mystylestest;

import com.sphynxs.MyStyles;

public class MyStylesTest {
    public static void main(String[] args) {
        MyStyles.install(MyStyles.METRO_UI); // Aquí inicializamos los estilos

        new Test().setVisible(true);
    }
}
```
En este ejemplo se ve como se inicializan los estilos de **METRO_UI** que es el primer estilo que se creó para los primeros usos, posteriormente se crearán mas estilos, los disponibles por el momento en esta versión son **METRO_UI** y **METRO_UI_DARK**.

Si lo que quieres es cambiar el color del tema lograrás hacerlo simplemente agregando la siguiente linea donde quieras, en un botón en un menú o en cualquier evento que hayas programado:

```java
MetroUIConfigTheme.setPrimaryColor(MyStyles.COLOR_RED);
```
Para el caso anterior le dimos un color rojo, los colores disponibles son los siguientes:

```java
MyStyles.COLOR_YELLOW
MyStyles.COLOR_BLUE_LIGHT
MyStyles.COLOR_BLUE_DARK
MyStyles.COLOR_ORANGE
MyStyles.COLOR_RED
MyStyles.COLOR_ROSE
MyStyles.COLOR_PURPLE_LIGHT
MyStyles.COLOR_PURPLE_DARK
MyStyles.COLOR_GREEN_LIGHT
MyStyles.COLOR_GREEN_DARK
```
### Propiedades de componentes

Para el manejo de esta librería se agregó la sencilla funcionalidad de poder cambiar un componente por otro *equivalente* solo modificando su propiedad  a partir de la clase **`MetroUIComponent`**, para saber que propiedades tiene cada componente podemos realizarlo a partir del autocompletador que tiene nuestro IDE, en este caso solo escribir **MetroUIComponent.set** y veremos la lista de propiedades.

Cada propiedad inicia con el nombre de su componente, por ejemplo para un botón seria algo así **MetroUIComponent.setButton**Property(...), para el caso de un checkbox seria **MetroUIComponent.setCheckBox**Property(...), así para cada tipo de componente que tenga la posibilidad de poder cambiar de forma o funcionalidad.

A continuación veremos un ejemplo de como podemos convertir un componente checkbox a un componente **switch**:

![switch](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/switch.jpg)
![switch dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/switchDark.jpg)

En el JFrame o JPanel asignamos primeramente el nombre al/los componentes que queremos aplicar la propiedad:

```java
jCheckBox1.setName("mySwitch");
// Si lo necesitamos podemos darle el mismo nombre a mas checkbox y aplicar la propiedad solo a ese nombre para que todos cambien a la vez.
// jCheckBox2.setName("mySwitch");
// jCheckBox3.setName("mySwitch");
```
Una vez agregados los nombres al/los componentes aplicamos la propiedad **Checkbox como switch (CheckBoxAsSwitch)**:

```java
// Los parámetros en este caso son el nombre del componente, texto cuando está encendido y texto cuando esta apagado
MetroUIComponent.setCheckBoxAsSwitch(jCheckBox1.getName(), "On", "Off");
```

De igual forma podemos hacer que un botón se vea como un **link** de la siguiente forma:

```java
jButton1.setName("myLink"); // Ojo el nombre puede ser el que nosotros queramos

// Aplicamos la propiedad botón como link
MetroUIComponent.setButtonAsLink(jButton3.getName());
```

![link](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/link.jpg)
![link dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/linkDark.jpg)

Como último ejemplo veremos como asignar un **placeholder** a un textField, passwordField o textArea

```java
jPasswordField1.setName("myPassword");

MetroUIComponent.setPasswordFieldPlaceholder(jPasswordField1.getName(), "Password...");

// En el caso de que el componente se encuentre en otro panel donde no se pueda utilizar jPasswordField1.getName() simplemente utilizar el nombre tal cual
MetroUIComponent.setPasswordFieldPlaceholder("myPassword", "Password...");
```
![password](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/passwordField.jpg)
![password dark](https://github.com/Israel-ICM/MyStyles/blob/master/captures/version1.1.0/passwordFieldDark.jpg)


## Soporte ✔

Por el momento los componentes que son soportados por la librería son:

- JButton
- JCheckBox
- JCheckBoxMenuItem
- JComboBox
- JFormattedTextField
- JLabel
- JList
- JMenu
- JMenuBar
- JMenuItem
- JOptionPane
- JPanel
- JPasswordField
- JPopupMenu
- JProgressBar
- JRadioButton
- JRadioButtonMenuItem
- JScrollBar
- JSlider
- JSpinner
- JSplitPane
- JTabbedPane
- JTable
- JTextArea
- JTextField
- JToggleButton
- JToolTip

## Autores ✒️

_Por el momento soy el único contribuidor de éste proyecto_

* [Israel-ICM](https://www.youtube.com/channel/UCGmN_BvrLlCeSREmZ0tykSw)

## ¿Quieres invitarme un café? ☕
Puedes hacerlo haciendo click en el siguiente link:

[![buymeacoffee](https://img.shields.io/badge/-%E2%98%95%20Inv%C3%ADtame%20%20un%20caf%C3%A9-yellow)](https://www.buymeacoffee.com/israel.icm)


## Licencia 📄

Este proyecto está bajo la Licencia (MIT) - mira el archivo [LICENSE.md](https://github.com/Israel-ICM/MyStyles/blob/master/LICENSE) para más detalles

---
⌨️ Con ❤️ por [Israel-ICM](https://github.com/Israel-ICM) 😊
