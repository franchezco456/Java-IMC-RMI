package unicolombo.sistemasdistribuidos.descuento.rmi.lib;

import java.io.Serializable;

/**
 * Clase para transportar los datos del cálculo del descuento.
 * Debe implementar Serializable para poder viajar a través de la red (RMI).
 */
public class DatosDescuento implements Serializable {

    private static final long serialVersionUID = 1L;

    // Datos de entrada enviados por el cliente
    private float precioOriginal;
    private float porcentajeDescuento;

    // Datos de salida calculados por el servidor
    private float valorDescuento;   // Dinero que se descuenta
    private float precioFinal;      // Precio final a pagar
    private String mensaje;         // Mensaje de estado o validación

    // Constructor vacío por defecto
    public DatosDescuento() {
    }

    // Constructor con los parámetros de entrada
    public DatosDescuento(float precioOriginal, float porcentajeDescuento) {
        this.precioOriginal = precioOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Getters y Setters
    public float getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(float precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public float getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(float valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
