package unicolombo.sistemasdistribuidos.descuento.rmi;

import unicolombo.sistemasdistribuidos.descuento.rmi.vistas.VentanaPrincipal;

/**
 * Clase principal encargada de lanzar la interfaz gráfica del cliente.
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejecución en el Event Dispatch Thread de Swing
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
