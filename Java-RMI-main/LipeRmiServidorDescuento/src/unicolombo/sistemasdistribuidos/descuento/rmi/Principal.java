package unicolombo.sistemasdistribuidos.descuento.rmi;

import unicolombo.sistemasdistribuidos.descuento.rmi.net.Servidor;

/**
 * Clase principal encargada de ejecutar el Servidor de Descuentos.
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servidor servicio = new Servidor();
        try {
            servicio.iniciar();
            System.out.println("Servidor a la espera de peticiones de clientes...");
        } catch (Exception ex) {
            System.err.println("Ocurrió un error al iniciar el servidor: " + ex.getLocalizedMessage());
        }
    }
}
