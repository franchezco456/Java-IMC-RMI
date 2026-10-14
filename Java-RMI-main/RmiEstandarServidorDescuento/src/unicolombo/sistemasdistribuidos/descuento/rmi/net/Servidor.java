package unicolombo.sistemasdistribuidos.descuento.rmi.net;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Servidor que utiliza la tecnología RMI Estándar nativa de Java (java.rmi.*).
 * No requiere librerías externas de terceros.
 */
public class Servidor {

    // Puerto estándar para RMI Registry suele ser 1099
    private int puerto = 1099;
    private String nombreServicio = "CalculoDescuentoServicio";
    private Registry registro;
    private CalculoRmiDescuentoImplem calculoDescuento;

    public Servidor() {
    }

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() throws Exception {
        try {
            // 1. Instanciamos la implementación remota
            calculoDescuento = new CalculoRmiDescuentoImplem();

            // 2. Creamos o localizamos el registro RMI en el puerto especificado
            try {
                registro = LocateRegistry.createRegistry(puerto);
            } catch (RemoteException re) {
                // Si el registro ya fue creado previamente en este puerto
                registro = LocateRegistry.getRegistry(puerto);
            }

            // 3. Publicamos (rebind) el objeto remoto en el registro con un identificador
            registro.rebind(nombreServicio, calculoDescuento);

            String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
            System.out.println("==============================================================");
            System.out.println("  SERVIDOR RMI ESTÁNDAR DE JAVA (Sin librerías externas)");
            System.out.println("  Hora de inicio    : " + hora);
            System.out.println("  Puerto RMI Registry: " + puerto);
            System.out.println("  Servicio publicado: " + nombreServicio);
            System.out.println("  A la espera de peticiones de clientes...");
            System.out.println("==============================================================");
        } catch (RemoteException ex) {
            throw new Exception("Error al iniciar el registro o publicar servicio RMI: " + ex.getMessage(), ex);
        }
    }

    public void detener() {
        try {
            if (registro != null) {
                registro.unbind(nombreServicio);
                System.out.println("Servicio '" + nombreServicio + "' desvinculado del registro RMI.");
            }
        } catch (Exception ex) {
            System.err.println("Error al detener servidor: " + ex.getMessage());
        }
    }
}
