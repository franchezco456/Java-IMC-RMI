# Ejemplo IMC con LipeRMI (cliente / servidor sobre TCP)

Réplica de la guía *"Desarrollo de aplicaciones distribuidas usando RMI"* (John Carlos Arrieta),
usando la librería de terceros **LipeRMI** (`lipermi-1.0.1.jar`, de Maven Central `com.tascape.qa:lipermi`).

## Estructura (los 3 proyectos de la guía)

```
IMC-LipeRMI/
├── IMC-LipeRMI.code-workspace      <- abrir ESTE archivo en VS Code
├── compilar.bat                    <- compila los 3 proyectos
├── ejecutar-servidor.bat
├── ejecutar-cliente.bat
├── EjemploLipeRmiLibImc/           (1) Librería común -> se empaqueta en un JAR
│   ├── src/johnarrieta/seminario/imc/rmi/lib/
│   │   ├── DatosImc.java               objeto Serializable que viaja por la red
│   │   └── IRemotaCalculoImc.java      interfaz remota
│   └── dist/EjemploLipeRmiLibImc.jar
├── EjemploLipeRmiServidorImc/      (2) Servidor
│   ├── lib/  (lipermi-1.0.1.jar + EjemploLipeRmiLibImc.jar)
│   └── src/johnarrieta/seminario/imc/rmi/
│       ├── Principal.java
│       └── net/ Servidor.java, CalculoRmiImcImplem.java
└── EjemploLipeRmiClienteImc/       (3) Cliente con GUI Swing
    ├── lib/  (lipermi-1.0.1.jar + EjemploLipeRmiLibImc.jar)
    └── src/johnarrieta/seminario/imc/rmi/
        ├── Principal.java
        └── vistas/VentanaPrincipal.java
```

## Requisitos

- JDK 8 o superior (probado con JDK 17).
- VS Code con la extensión **Extension Pack for Java** (Microsoft).

## Cómo ejecutar

### Opción A: VS Code
1. `File > Open Workspace from File...` → `IMC-LipeRMI.code-workspace`.
2. Si cambias el código de la librería común, ejecuta `compilar.bat` para regenerar el JAR
   y copiarlo a `lib/` del servidor y del cliente.
3. Abre `EjemploLipeRmiServidorImc/src/.../Principal.java` → **Run** (arriba del `main`).
4. Abre `EjemploLipeRmiClienteImc/src/.../Principal.java` → **Run**.

### Opción B: doble clic
1. `compilar.bat`
2. `ejecutar-servidor.bat` (debe mostrar `Servidor LipeRMI escuchando en el puerto TCP 9007`)
3. `ejecutar-cliente.bat`

### Probar
1. Pestaña **CONEXION**: IP `localhost`, puerto `9007` → **Conectar** (estado pasa a *Conectado*).
2. Pestaña **CALCULAR IMC**: peso (kg) `70`, altura (m) `1.75` → **CALCULAR** → `22.86 – Estas bien de peso`.

Para usarlo entre dos computadores, en el cliente escribe la IP del servidor y permite el
puerto 9007 en el firewall del servidor.

## ¿Cómo funciona? (LipeRMI sobre TCP)

- **Servidor**: `CallHandler.registerGlobal(IRemotaCalculoImc.class, impl)` registra el objeto y
  `Server.bind(9007, handler)` abre un `ServerSocket` **TCP** en el puerto 9007.
- **Cliente**: `new Client(ip, 9007, handler)` abre un `Socket` **TCP** y
  `client.getGlobal(IRemotaCalculoImc.class)` devuelve un *proxy dinámico* (stub). Cuando se llama
  `calcularImc(datos)`, el proxy serializa la llamada y el objeto `DatosImc`, los envía por el socket,
  el servidor ejecuta el método real y devuelve el resultado serializado.
- Todo va por **una sola conexión TCP** persistente (no hay registro de nombres aparte).
