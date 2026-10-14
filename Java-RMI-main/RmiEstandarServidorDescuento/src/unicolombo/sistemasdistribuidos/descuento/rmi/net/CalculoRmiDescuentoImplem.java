package unicolombo.sistemasdistribuidos.descuento.rmi.net;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.DatosDescuento;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.IRemotaCalculoDescuento;

/**
 * Implementación de la interfaz remota para RMI Estándar de Java.
 * En RMI Estándar:
 * 1. Hereda de UnicastRemoteObject para habilitar la exportación del objeto sobre sockets TCP.
 * 2. Su constructor debe declarar throws RemoteException.
 * 3. Los métodos remotos declaran throws RemoteException.
 */
public class CalculoRmiDescuentoImplem extends UnicastRemoteObject implements IRemotaCalculoDescuento {

    private static final long serialVersionUID = 1L;

    public CalculoRmiDescuentoImplem() throws RemoteException {
        super();
    }

    private String getTimestamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    @Override
    public DatosDescuento calcularDescuento(DatosDescuento datos) throws RemoteException {
        if (datos == null) {
            return null;
        }

        float precioOriginal = datos.getPrecioOriginal();
        float porcentaje = datos.getPorcentajeDescuento();

        System.out.println("\n[" + getTimestamp() + " | LOG SERVIDOR RMI ESTÁNDAR - PETICIÓN]");
        System.out.printf("  > Precio Original : $ %,.2f%n", precioOriginal);
        System.out.printf("  > Descuento       : %.2f%%%n", porcentaje);

        // Validaciones de negocio
        if (precioOriginal <= 0) {
            datos.setMensaje("ERROR: El precio original debe ser mayor que 0");
            datos.setValorDescuento(0);
            datos.setPrecioFinal(precioOriginal);
            System.out.println("  [!] Error: Precio menor o igual a cero.");
            return datos;
        }

        if (porcentaje < 0 || porcentaje > 100) {
            datos.setMensaje("ERROR: El porcentaje de descuento debe estar entre 0 y 100");
            datos.setValorDescuento(0);
            datos.setPrecioFinal(precioOriginal);
            System.out.println("  [!] Error: Porcentaje fuera de rango (0 - 100).");
            return datos;
        }

        // Lógica de cálculo
        float valorDescuento = (precioOriginal * porcentaje) / 100.0f;
        float precioFinal = precioOriginal - valorDescuento;

        datos.setValorDescuento(valorDescuento);
        datos.setPrecioFinal(precioFinal);
        datos.setMensaje("Descuento aplicado correctamente (RMI Estándar)");

        System.out.printf("  > Dinero descontado      : $ %,.2f%n", valorDescuento);
        System.out.printf("  > Precio final de compra : $ %,.2f%n", precioFinal);
        System.out.println("  [OK] Respuesta enviada exitosamente al cliente.");

        return datos;
    }
}
