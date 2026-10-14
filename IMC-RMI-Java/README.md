# Ejemplo IMC con RMI estándar de Java (sin LipeRMI)

Mismo ejemplo de la guía, pero usando solo el API `java.rmi` que viene con el JDK
(actividad 2 de la guía). **No usa ninguna librería externa.**

## Estructura

```
IMC-RMI-Java/
├── IMC-RMI-Java.code-workspace     <- abrir ESTE archivo en VS Code
├── compilar.bat / ejecutar-servidor.bat / ejecutar-cliente.bat
├── EjemploRmiLibImc/               (1) Librería común -> dist/EjemploRmiLibImc.jar
│   └── src/.../lib/ DatosImc.java, IRemotaCalculoImc.java
├── EjemploRmiServidorImc/          (2) Servidor
│   ├── lib/ (solo EjemploRmiLibImc.jar)
│   └── src/.../ Principal.java, net/Servidor.java, net/CalculoRmiImcImplem.java
└── EjemploRmiClienteImc/           (3) Cliente GUI Swing
    ├── lib/ (solo EjemploRmiLibImc.jar)
    └── src/.../ Principal.java, vistas/VentanaPrincipal.java
```

## Cómo ejecutar

Igual que el proyecto LipeRMI:
- **VS Code**: abrir `IMC-RMI-Java.code-workspace`, ejecutar `Principal` del servidor y luego el del cliente.
- **Doble clic**: `compilar.bat` → `ejecutar-servidor.bat` → `ejecutar-cliente.bat`.

En el cliente: IP `localhost`, puerto `9007` → **Conectar**; luego peso `70`, altura `1.75` → **CALCULAR**.

> **Entre dos computadores**: el stub que recibe el cliente lleva la IP que el servidor anuncia.
> Si el servidor tiene varias interfaces de red, arráncalo indicando su IP:
> `java -Djava.rmi.server.hostname=192.168.X.X -cp ... johnarrieta.seminario.imc.rmi.Principal`.
> Además, RMI usa el puerto 9007 (registry) **y** otro puerto para el objeto exportado, así que
> el firewall debe permitir Java en el servidor.

## ¿Cómo funciona? (RMI / JRMP sobre TCP)

1. **Servidor**: `CalculoRmiImcImplem extends UnicastRemoteObject` → al crearse, el objeto queda
   *exportado* (escuchando llamadas por TCP). `LocateRegistry.createRegistry(9007)` levanta el
   **RMI Registry** (servicio de nombres) y `registro.rebind("CalculoImc", impl)` lo publica.
2. **Cliente**: `LocateRegistry.getRegistry(ip, 9007)` + `lookup("CalculoImc")` devuelve el **stub**.
3. Cada `calcularImc(datos)` viaja por el protocolo **JRMP**, que funciona sobre **TCP**:
   se serializan los parámetros (`DatosImc` es `Serializable`), el servidor ejecuta y responde.

## Diferencias y similitudes con LipeRMI

| Aspecto | LipeRMI | RMI estándar (java.rmi) |
|---|---|---|
| Dependencias | JAR externo `lipermi-1.0.1.jar` | Ninguna, viene en el JDK |
| Interfaz remota | Interfaz Java normal | Debe `extends Remote` y cada método `throws RemoteException` |
| Implementación | Clase normal (`implements`) | `extends UnicastRemoteObject` (o `exportObject`) |
| Publicar el servicio | `CallHandler.registerGlobal(...)` + `Server.bind(puerto, handler)` | `LocateRegistry.createRegistry(puerto)` + `registry.rebind(nombre, obj)` |
| Localizar el servicio | `new Client(ip, puerto, handler).getGlobal(Interfaz.class)` (por **tipo**) | `LocateRegistry.getRegistry(ip, puerto).lookup(nombre)` (por **nombre**) |
| Conexión | Un solo socket TCP persistente por cliente | Registry en un puerto + objeto exportado en otro puerto (TCP) |
| Desconectar | `client.close()` | No hay close explícito; se suelta el stub |
| Errores | `LipeRMIException`, `IOException` | `RemoteException`, `NotBoundException` |
| Callbacks / NAT | Diseñado para callbacks servidor→cliente por el mismo socket (útil tras NAT/firewall) | Callbacks requieren que el cliente también exporte objetos (puertos abiertos en el cliente) |
| **Similitudes** | Ambos: interfaz compartida en un JAR común, objetos `Serializable` como parámetros, proxy/stub en el cliente, transporte **TCP**, el cliente llama al método como si fuera local. | |
