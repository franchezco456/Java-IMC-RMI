package unicolombo.sistemasdistribuidos.descuento.rmi.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota en RMI Estándar de Java.
 * A diferencia de LipeRMI:
 * 1. Debe heredar obligatoriamente de java.rmi.Remote.
 * 2. Todos los métodos remotos deben declarar throws RemoteException.
 */
public interface IRemotaCalculoDescuento extends Remote {

    /**
     * Calcula de forma remota el descuento y el total a pagar.
     * 
     * @param datos Objeto con precioOriginal y porcentajeDescuento.
     * @return El mismo objeto con valorDescuento y precioFinal calculados.
     * @throws RemoteException Si ocurre un error de comunicación RMI.
     */
    public DatosDescuento calcularDescuento(DatosDescuento datos) throws RemoteException;

}
