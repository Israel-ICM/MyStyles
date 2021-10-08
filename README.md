# MyStyles
Es una librería para modificar la interfaz de cualquier programa JAVA con una sencilla línea de código que corresponde a la instalación del estilo en la aplicación que necesita.

## Comenzando 🚀

Primeramente te aviso que el proyecto fué desarrollado con **JAVA 1.8.x** pero los métodos utilizados no denerían dar problemas en posteriores versiones.

## Ejecutando las pruebas ⚙️
Como verás a continuación el funcionamiento es bastante simple:

Primeramente se debe agregar la libreria *".jar"* al proyecto donde se quiere aplicar los estilos.

Una vez agregada la libreria al proyecto, solo debe instanciar la clase **MyStyles** y llamar al método estático **install(style)** el parámetro es una variable estática también declarada en la clase **MyStyles** siendo la única línea de código `MyStyles.install(MyStyles.METRO_UI);` que debe importarse de: `import icm.sphynx.styles.MyStyles;`, a continuacion se muestra un ejemplo básico:

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

## Licencia 📄

Este proyecto está bajo la Licencia (MIT) - mira el archivo [LICENSE.md](https://github.com/Israel-ICM/MyStyles/blob/master/LICENSE) para más detalles

---
⌨️ Con ❤️ por [Israel-ICM](https://github.com/Israel-ICM) 😊
