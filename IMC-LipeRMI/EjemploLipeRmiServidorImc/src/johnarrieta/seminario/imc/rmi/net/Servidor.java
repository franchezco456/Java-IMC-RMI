package johnarrieta.seminario.imc.rmi.net;

import java.io.IOException;
import johnarrieta.seminario.imc.rmi.lib.IRemotaCalculoImc;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Server;

/**
 * Publica el objeto remoto usando LipeRMI.
 * LipeRMI abre un ServerSocket TCP en el puerto indicado.
 *
 * @author JOHN CARLOS ARRIETA ARRIETA
 */
public class Servidor {

    private int puerto = 9007;
    private CallHandler invocador;
    private Server servidor;
    private CalculoRmiImcImplem calculoImc;

    public Servidor() {
        invocador = new CallHandler();
        servidor = new Server();
        calculoImc = new CalculoRmiImcImplem();
    }

    public void iniciar() throws Exception {
        try {
            invocador.registerGlobal(IRemotaCalculoImc.class, calculoImc);
            servidor.bind(puerto, invocador);
            System.out.println("Servidor LipeRMI escuchando en el puerto TCP " + puerto);
        } catch (LipeRMIException ex) {
            throw new Exception("Error: No es posible invocar metodos remotos");
        } catch (IOException ex) {
            throw new Exception("Error: I/O (" + ex.getMessage() + ")");
        }
    }

    public void detener() {
        servidor.close();
    }
}
