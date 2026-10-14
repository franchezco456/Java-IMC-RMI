package unicolombo.sistemasdistribuidos.descuento.rmi;

import unicolombo.sistemasdistribuidos.descuento.rmi.net.Servidor;

/**
 * Clase principal que arranca el servidor RMI Estándar de Java.
 */
public class Principal {

    public static void main(String[] args) {
        Servidor servicio = new Servidor();
        try {
            servicio.iniciar();
        } catch (Exception ex) {
            System.err.println("Error al iniciar el servidor RMI Estándar: " + ex.getLocalizedMessage());
        }
    }
}
