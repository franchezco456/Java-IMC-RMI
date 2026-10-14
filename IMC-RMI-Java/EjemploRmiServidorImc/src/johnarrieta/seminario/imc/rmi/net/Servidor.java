package johnarrieta.seminario.imc.rmi.net;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import johnarrieta.seminario.imc.rmi.lib.IRemotaCalculoImc;

/**
 * Publica el objeto remoto usando RMI estandar de Java (java.rmi).
 * Crea un RMI Registry (servicio de nombres) en el puerto indicado
 * y registra ahi el objeto remoto con un nombre.
 *
 * @author JOHN CARLOS ARRIETA ARRIETA
 */
public class Servidor {

    private int puerto = 9007;
    private Registry registro;
    private CalculoRmiImcImplem calculoImc;

    public Servidor() {
    }

    public void iniciar() throws Exception {
        try {
            calculoImc = new CalculoRmiImcImplem();
            registro = LocateRegistry.createRegistry(puerto);
            registro.rebind(IRemotaCalculoImc.NOMBRE_SERVICIO, calculoImc);
            System.out.println("Servidor RMI escuchando en el puerto TCP " + puerto
                    + " con el nombre '" + IRemotaCalculoImc.NOMBRE_SERVICIO + "'");
        } catch (RemoteException ex) {
            throw new Exception("Error: No es posible publicar el objeto remoto (" + ex.getMessage() + ")");
        }
    }

    public void detener() {
        try {
            registro.unbind(IRemotaCalculoImc.NOMBRE_SERVICIO);
            UnicastRemoteObject.unexportObject(calculoImc, true);
            UnicastRemoteObject.unexportObject(registro, true);
        } catch (NoSuchObjectException ex) {
            // ya estaba detenido
        } catch (Exception ex) {
            System.out.println("Error al detener: " + ex.getMessage());
        }
    }
}
