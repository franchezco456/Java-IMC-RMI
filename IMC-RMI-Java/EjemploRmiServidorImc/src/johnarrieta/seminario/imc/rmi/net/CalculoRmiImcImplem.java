package johnarrieta.seminario.imc.rmi.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import johnarrieta.seminario.imc.rmi.lib.DatosImc;
import johnarrieta.seminario.imc.rmi.lib.IRemotaCalculoImc;

/**
 * Implementacion real del servicio. Se ejecuta en el servidor.
 * Extiende UnicastRemoteObject para que se exporte automaticamente
 * (queda escuchando llamadas remotas por TCP).
 *
 * @author JOHN CARLOS ARRIETA ARRIETA
 */
public class CalculoRmiImcImplem extends UnicastRemoteObject implements IRemotaCalculoImc {

    private static final long serialVersionUID = 1L;

    public CalculoRmiImcImplem() throws RemoteException {
        super();
    }

    @Override
    public DatosImc calcularImc(DatosImc datos) throws RemoteException {
        System.out.println("Peticion recibida -> peso: " + datos.getPeso() + ", altura: " + datos.getAltura());

        float resultado = 0;
        if (datos.getPeso() <= 0 || datos.getAltura() <= 0) {
            datos.setInterpretacion("ERROR: El peso y la altura deben ser mayores que 0");
            return datos;
        } else {
            resultado = datos.getPeso() / (datos.getAltura() * datos.getAltura());
            datos.setResultado(resultado);
            if (resultado < 18.5) {
                datos.setInterpretacion("Debes consultar un Medico, tu peso es muy bajo");
            } else if (resultado >= 18.5 && resultado <= 24.9) {
                datos.setInterpretacion("Estas bien de peso");
            } else if (resultado > 24.9 && resultado <= 29.9) {
                datos.setInterpretacion("Debes bajar un poco de peso");
            } else {
                datos.setInterpretacion("Debes consultar un Medico, tu peso es muy alto");
            }
            return datos;
        }
    }
}
