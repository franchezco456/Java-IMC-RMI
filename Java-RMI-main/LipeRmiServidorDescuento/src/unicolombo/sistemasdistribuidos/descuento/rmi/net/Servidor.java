package unicolombo.sistemasdistribuidos.descuento.rmi.net;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.IServerListener;
import net.sf.lipermi.net.Server;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.IRemotaCalculoDescuento;

/**
 * Clase que configura y administra el ciclo de vida del servidor LipeRMI,
 * incluyendo listeners para el registro de conexiones y desconexiones de clientes.
 */
public class Servidor {

    private int puerto = 9007;
    private CallHandler invocador;
    private Server servidor;
    private CalculoRmiDescuentoImplem calculoDescuento;

    public Servidor() {
        invocador = new CallHandler();
        servidor = new Server();
        calculoDescuento = new CalculoRmiDescuentoImplem();

        // Listener para registrar eventos de conexión y desconexión en los logs del servidor
        servidor.addServerListener(new IServerListener() {
            @Override
            public void clientConnected(Socket socket) {
                String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
                System.out.println("\n[" + hora + " | LOG SERVIDOR - NUEVA CONEXIÓN]");
                System.out.println("  -> Cliente conectado desde: " + socket.getRemoteSocketAddress());
            }

            @Override
            public void clientDisconnected(Socket socket) {
                String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());
                System.out.println("\n[" + hora + " | LOG SERVIDOR - DESCONEXIÓN]");
                System.out.println("  -> Cliente desconectado: " + socket.getRemoteSocketAddress());
            }
        });
    }

    public void iniciar() throws Exception {
        try {
            invocador.registerGlobal(IRemotaCalculoDescuento.class, calculoDescuento);
            servidor.bind(puerto, invocador);
            System.out.println("========================================================");
            System.out.println("  Servidor LipeRMI iniciado en el puerto " + puerto);
            System.out.println("  A la espera de peticiones de clientes...");
            System.out.println("========================================================");
        } catch (LipeRMIException ex) {
            throw new Exception("Error: No es posible invocar métodos remotos", ex);
        } catch (IOException ex) {
            throw new Exception("Error: I/O al intentar escuchar en el puerto " + puerto, ex);
        }
    }

    public void detener() {
        if (servidor != null) {
            servidor.close();
            System.out.println("Servidor detenido.");
        }
    }
}
