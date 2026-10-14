package johnarrieta.seminario.imc.rmi.lib;

/**
 * Interfaz remota compartida por cliente y servidor.
 * Con LipeRMI es una interfaz Java normal: no extiende Remote
 * ni declara RemoteException.
 *
 * @author JOHN CARLOS ARRIETA ARRIETA
 */
public interface IRemotaCalculoImc {

    public DatosImc calcularImc(DatosImc datos);
}
