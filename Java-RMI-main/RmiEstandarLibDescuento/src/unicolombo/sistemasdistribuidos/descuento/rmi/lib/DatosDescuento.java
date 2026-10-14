package unicolombo.sistemasdistribuidos.descuento.rmi.lib;

import java.io.Serializable;

/**
 * Clase para transportar los datos del cálculo del descuento en RMI Estándar de Java.
 * Implementa Serializable para permitir la serialización y transmisión por sockets RMI.
 */
public class DatosDescuento implements Serializable {

    private static final long serialVersionUID = 1L;

    // Entradas
    private float precioOriginal;
    private float porcentajeDescuento;

    // Salidas calculadas por el servidor
    private float valorDescuento;
    private float precioFinal;
    private String mensaje;

    public DatosDescuento() {
    }

    public DatosDescuento(float precioOriginal, float porcentajeDescuento) {
        this.precioOriginal = precioOriginal;
        this.porcentajeDescuento = porcentajeDescuento;
    }

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
