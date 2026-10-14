package unicolombo.sistemasdistribuidos.descuento.rmi.net;

import java.text.SimpleDateFormat;
import java.util.Date;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.DatosDescuento;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.IRemotaCalculoDescuento;

/**
 * Implementación de la interfaz remota IRemotaCalculoDescuento.
 * Esta clase realiza la lógica de negocio y registra en consola los eventos.
 */
public class CalculoRmiDescuentoImplem implements IRemotaCalculoDescuento {

    public CalculoRmiDescuentoImplem() {
    }

    private String getTimestamp() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    @Override
    public DatosDescuento calcularDescuento(DatosDescuento datos) {
        if (datos == null) {
            return null;
        }

        float precioOriginal = datos.getPrecioOriginal();
        float porcentaje = datos.getPorcentajeDescuento();

        System.out.println("\n[" + getTimestamp() + " | LOG SERVIDOR - PETICIÓN RECIBIDA]");
        System.out.printf("  > Precio Original recibido : $ %,.2f%n", precioOriginal);
        System.out.printf("  > Descuento solicitado     : %.2f%%%n", porcentaje);

        // Validaciones de entrada
        if (precioOriginal <= 0) {
            datos.setMensaje("ERROR: El precio original debe ser mayor que 0");
            datos.setValorDescuento(0);
            datos.setPrecioFinal(precioOriginal);
            System.out.println("  [!] Validación fallida: Precio original menor o igual a cero.");
            return datos;
        }

        if (porcentaje < 0 || porcentaje > 100) {
            datos.setMensaje("ERROR: El porcentaje de descuento debe estar entre 0 y 100");
            datos.setValorDescuento(0);
            datos.setPrecioFinal(precioOriginal);
            System.out.println("  [!] Validación fallida: Porcentaje de descuento fuera de rango (0 - 100).");
            return datos;
        }

        // Dinero descontado = (precioOriginal * porcentaje) / 100
        // Precio final = precioOriginal - dinero descontado
        float valorDescuento = (precioOriginal * porcentaje) / 100.0f;
        float precioFinal = precioOriginal - valorDescuento;

        datos.setValorDescuento(valorDescuento);
        datos.setPrecioFinal(precioFinal);
        datos.setMensaje("Descuento aplicado correctamente");

        System.out.printf("  > Dinero descontado        : $ %,.2f%n", valorDescuento);
        System.out.printf("  > Precio final calculado   : $ %,.2f%n", precioFinal);
        System.out.println("  [OK] Respuesta enviada al cliente exitosamente.");

        return datos;
    }
}
