package johnarrieta.seminario.imc.rmi.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota compartida por cliente y servidor.
 * En RMI estandar DEBE extender java.rmi.Remote y cada metodo
 * DEBE declarar RemoteException.
 *
 * @author JOHN CARLOS ARRIETA ARRIETA
 */
public interface IRemotaCalculoImc extends Remote {

    /** Nombre con el que se publica el objeto en el RMI Registry. */
    String NOMBRE_SERVICIO = "CalculoImc";

    public DatosImc calcularImc(DatosImc datos) throws RemoteException;
}
