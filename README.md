# MyStyles
Es una librería para modificar la interfaz de cualquier programa JAVA con una sencilla línea de código que corresponde a la instalación del estilo en la aplicación que necesita.

Con esta librería convertirá un programa que se vé así:

![Captura de pantalla 1](https://github.com/Israel-ICM/MyStyles/blob/master/captures/example1.PNG)

Utilizando una simple línea de código conseguirá que se vea de la siguiente forma:

![Captura de pantalla 2](https://github.com/Israel-ICM/MyStyles/blob/master/captures/example2.PNG)

## Comenzando 🚀

Primeramente te aviso que el proyecto fué desarrollado con **JAVA 1.8.x** pero los métodos utilizados no deberían dar problemas en posteriores versiones.

## Descargas 📁

Versiones disponibles:

08-10-2021 | [MyStyles v1.0.0](https://drive.google.com/file/d/1kbMJpoXLzu3adX4CygeAvaButAwCVHGN/view?usp=sharing)

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

import icm.sphynx.styles.MyStyles;

public class MyStylesTest {
    public static void main(String[] args) {
        MyStyles.install(MyStyles.METRO_UI); // Aquí inicializamos los estilos

        new Test().setVisible(true);
    }
}
```
En este ejemplo se vé como se inicializan los estilos de **METRO_UI** que es la primera librería que se creó para los primeros usos, posteriormente se crearán mas estilos, los disponibles por el momento en esta primera versión **1.0.0** son **METRO_UI** y **METRO_UI_DARK**.

## Autores ✒️

_Por el momento soy el único contribuidor de éste proyecto_

* **Israel Condori Mañueco** - *Trabajo Inicial* - [Israel-ICM](https://www.youtube.com/channel/UCGmN_BvrLlCeSREmZ0tykSw)

## ¿Quieres invitarme un café? ☕
Puedes hacerlo haciendo click en el siguiente link:

[Quiero invitarte un café](https://www.buymeacoffee.com/israel.icm)

## Licencia 📄

Este proyecto está bajo la Licencia (MIT) - mira el archivo [LICENSE.md](https://github.com/Israel-ICM/MyStyles/blob/master/LICENSE) para más detalles

---
⌨️ Con ❤️ por [Israel-ICM](https://github.com/Israel-ICM) 😊
