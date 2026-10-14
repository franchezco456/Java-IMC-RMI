package unicolombo.sistemasdistribuidos.descuento.rmi;

import unicolombo.sistemasdistribuidos.descuento.rmi.vistas.VentanaPrincipal;

/**
 * Clase principal encargada de lanzar la interfaz gráfica del cliente RMI Estándar.
 */
public class Principal {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal v = new VentanaPrincipal();
                v.setLocationRelativeTo(null);
                v.setVisible(true);
            }
        });
    }
}
