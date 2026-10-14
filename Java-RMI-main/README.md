# Sistemas Distribuidos - Java RMI (Ejercicio 17)

Desarrollo del **Ejercicio 17: Descuento de una compra** utilizando **Invocación a Métodos Remotos (RMI)** sobre TCP/IP en Java.

## 📋 Descripción del Ejercicio
El cliente solicita el precio original de un producto y el porcentaje de descuento a través de una interfaz gráfica (Swing). El servidor calcula el dinero que se descuenta y el precio final de la compra, enviando ambos valores calculados al cliente.

---

## 🚀 Versiones Implementadas

El repositorio contiene dos implementaciones completas del sistema distribuido:

### 1. Versión con Librería de Terceros (LipeRMI)
* **`LipeRmiLibDescuento`**: Librería compartida que contiene la clase transportadora serializable (`DatosDescuento`) y la interfaz remota (`IRemotaCalculoDescuento`).
* **`LipeRmiServidorDescuento`**: Servidor que utiliza `CallHandler` y `Server` de la librería LipeRMI para escuchar en el puerto TCP `9007` y registrar logs de conexión y cálculos en consola.
* **`LipeRmiClienteDescuento`**: Cliente con interfaz gráfica Swing (`VentanaPrincipal`) que se conecta al servidor y procesa el descuento de forma asíncrona mediante hilos (`Thread`).
* **`lib/lipermi-1.0.1.jar`**: Dependencia de LipeRMI.

### 2. Versión sin Librerías Externas (Java RMI Estándar)
* **`RmiEstandarLibDescuento`**: Librería que define `DatosDescuento` (`Serializable`) e `IRemotaCalculoDescuento` (extendiendo `java.rmi.Remote` y declarando `throws RemoteException`).
* **`RmiEstandarServidorDescuento`**: Servidor que hereda de `UnicastRemoteObject` y publica el servicio en el registro nativo de Java (`LocateRegistry`) en el puerto `1099`.
* **`RmiEstandarClienteDescuento`**: Cliente gráfico que localiza el stub mediante `LocateRegistry.getRegistry(...)` y ejecuta las invocaciones remotas nativas.

---

## ⚙️ Compilación y Ejecución

En la raíz del proyecto se incluyen scripts `.bat` para facilitar el uso en Windows:

1. **Compilar todo:**
   * Ejecuta `compilar_todo.bat` para compilar los 6 proyectos y generar sus archivos `.jar`.
2. **Ejecutar versión con librerías (LipeRMI):**
   * Ejecuta `ejecutar_con_librerias.bat`. Abre automáticamente el servidor en consola y el cliente visual.
3. **Ejecutar versión sin librerías (RMI Estándar):**
   * Ejecuta `ejecutar_sin_librerias.bat`. Abre automáticamente el servidor RMI en consola y el cliente visual.
