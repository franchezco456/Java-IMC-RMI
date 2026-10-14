package unicolombo.sistemasdistribuidos.descuento.rmi.lib;

/**
 * Interfaz remota que define los métodos que el servidor ofrece y
 * que el cliente puede invocar a través de RMI con LipeRMI.
 */
public interface IRemotaCalculoDescuento {

    /**
     * Calcula el dinero a descontar y el precio final de una compra.
     * 
     * @param datos Objeto con el precioOriginal y porcentajeDescuento.
     * @return El mismo objeto con los campos valorDescuento y precioFinal calculados.
     */
    public DatosDescuento calcularDescuento(DatosDescuento datos);

}
