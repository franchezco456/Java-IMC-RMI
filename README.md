# Sistemas Distribuidos - Cálculo de IMC con Java RMI y LipeRMI

Este repositorio contiene las implementaciones de sistemas distribuidos cliente-servidor utilizando **Invocación a Métodos Remotos (RMI)** sobre TCP/IP en Java para el cálculo del Índice de Masa Corporal (IMC).

---

## 📁 Proyectos Incluidos

### 1. `IMC-LipeRMI/`
Implementación del cálculo de IMC utilizando la librería de terceros **LipeRMI** (`lipermi-1.0.1.jar`).
- **`EjemploLipeRmiLibImc`**: Librería común con la interfaz remota (`IRemotaCalculoImc`) y el objeto serializable (`DatosImc`).
- **`EjemploLipeRmiServidorImc`**: Servidor que registra el objeto con `CallHandler.registerGlobal(...)` y expone el servicio TCP en el puerto `9007`.
- **`EjemploLipeRmiClienteImc`**: Cliente GUI Swing que obtiene el stub mediante proxy dinámico y solicita el cálculo.
- Scripts de compilación y ejecución directa: `compilar.bat`, `ejecutar-servidor.bat`, `ejecutar-cliente.bat`.

### 2. `IMC-RMI-Java/`
Implementación con **Java RMI Estándar** nativo del JDK (`java.rmi`), sin dependencias externas.
- **`EjemploRmiLibImc`**: Librería compartida con la interfaz remota (`IRemotaCalculoImc extends Remote`) y `DatosImc`.
- **`EjemploRmiServidorImc`**: Servidor que extiende `UnicastRemoteObject` y publica el servicio en el registro RMI (`LocateRegistry`) en el puerto `9007`.
- **`EjemploRmiClienteImc`**: Cliente Swing que localiza el stub mediante `LocateRegistry.getRegistry(...)` y efectúa la llamada RPC.
- Scripts de compilación y ejecución directa: `compilar.bat`, `ejecutar-servidor.bat`, `ejecutar-cliente.bat`.

### 3. `Java-RMI-main/`
Implementación complementaria del ejercicio de cálculo de descuentos mediante RMI estándar y LipeRMI.

---

## 🚀 Requisitos

- **Java JDK**: 8 o superior (probado en JDK 17).
- Entorno Windows con soporte para ejecución de scripts `.bat` o cualquier IDE compatible con Java (VS Code, IntelliJ IDEA, Eclipse, NetBeans).

---

## ⚙️ Instrucciones de Ejecución

Cada carpeta contiene scripts `.bat` listos para usar:
1. Ejecutar `compilar.bat` para compilar los fuentes y empaquetar los JARs correspondientes.
2. Iniciar el servidor ejecutando `ejecutar-servidor.bat`.
3. Iniciar el cliente ejecutando `ejecutar-cliente.bat`.
4. En la interfaz gráfica del cliente, especificar los parámetros requeridos y presionar **CALCULAR**.
