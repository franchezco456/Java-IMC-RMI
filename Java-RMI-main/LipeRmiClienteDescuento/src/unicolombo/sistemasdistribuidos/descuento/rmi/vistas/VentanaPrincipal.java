package unicolombo.sistemasdistribuidos.descuento.rmi.vistas;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.Client;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.DatosDescuento;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.IRemotaCalculoDescuento;

/**
 * Interfaz Gráfica para el Cliente RMI de Cálculo de Descuento.
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    // Variables de red y RMI
    private CallHandler invocadorRemoto;
    private String ipServidor = "localhost";
    private int puerto = 9007;
    private IRemotaCalculoDescuento calculoDescuentoRemoto;
    private Client cliente;

    // Componentes de la GUI
    private JLabel lblTitulo;
    private JTabbedPane jTabbedPane1;

    // Pestaña 1: Conexión
    private JPanel panelConexion;
    private JLabel lblIP;
    private JTextField campoIPServidor;
    private JLabel lblPuerto;
    private JTextField campoPuertoServidor;
    private JLabel lblEstado;
    private JLabel txtEstado;
    private JButton btnIniciar;

    // Pestaña 2: Cálculo de Descuento
    private JPanel panelCalculo;
    private JLabel lblPrecioOriginal;
    private JTextField campoPrecioOriginal;
    private JLabel lblPorcentaje;
    private JTextField campoPorcentajeDescuento;
    private JButton btnCalcular;
    private JLabel lblResDescuento;
    private JLabel txtValorDescuento;
    private JLabel lblResPrecioFinal;
    private JLabel txtPrecioFinal;
    private JLabel txtMensaje;

    public VentanaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("CLIENTE CALCULO DE DESCUENTO");
        jTabbedPane1 = new JTabbedPane();

        // Configuración de la Ventana
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente LipeRMI - Descuento de una Compra");
        setResizable(false);

        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        // --- PESTAÑA 1: CONEXIÓN ---
        panelConexion = new JPanel();
        panelConexion.setLayout(null);

        lblIP = new JLabel("DIRECCIÓN IP:");
        lblIP.setBounds(50, 40, 120, 25);
        panelConexion.add(lblIP);

        campoIPServidor = new JTextField("localhost");
        campoIPServidor.setBounds(180, 40, 200, 25);
        panelConexion.add(campoIPServidor);

        lblPuerto = new JLabel("PUERTO DE RED:");
        lblPuerto.setBounds(50, 80, 120, 25);
        panelConexion.add(lblPuerto);

        campoPuertoServidor = new JTextField("9007");
        campoPuertoServidor.setBounds(180, 80, 200, 25);
        panelConexion.add(campoPuertoServidor);

        lblEstado = new JLabel("ESTADO:");
        lblEstado.setBounds(50, 130, 120, 25);
        panelConexion.add(lblEstado);

        txtEstado = new JLabel("Desconectado");
        txtEstado.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtEstado.setForeground(new Color(220, 20, 60));
        txtEstado.setBounds(180, 130, 200, 25);
        panelConexion.add(txtEstado);

        btnIniciar = new JButton("Conectar");
        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar.setForeground(new Color(0, 153, 51));
        btnIniciar.setBounds(150, 180, 150, 35);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        panelConexion.add(btnIniciar);

        jTabbedPane1.addTab("CONEXION", panelConexion);

        // --- PESTAÑA 2: CALCULAR DESCUENTO ---
        panelCalculo = new JPanel();
        panelCalculo.setLayout(null);

        lblPrecioOriginal = new JLabel("PRECIO ORIGINAL ($):");
        lblPrecioOriginal.setBounds(30, 25, 160, 25);
        panelCalculo.add(lblPrecioOriginal);

        campoPrecioOriginal = new JTextField();
        campoPrecioOriginal.setBounds(190, 25, 120, 25);
        panelCalculo.add(campoPrecioOriginal);

        lblPorcentaje = new JLabel("DESCUENTO (%):");
        lblPorcentaje.setBounds(30, 65, 160, 25);
        panelCalculo.add(lblPorcentaje);

        campoPorcentajeDescuento = new JTextField();
        campoPorcentajeDescuento.setBounds(190, 65, 120, 25);
        panelCalculo.add(campoPorcentajeDescuento);

        btnCalcular = new JButton("CALCULAR");
        btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCalcular.setForeground(new Color(0, 128, 0));
        btnCalcular.setBounds(330, 25, 120, 65);
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });
        panelCalculo.add(btnCalcular);

        lblResDescuento = new JLabel("VALOR DESCUENTO:");
        lblResDescuento.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResDescuento.setBounds(30, 115, 150, 25);
        panelCalculo.add(lblResDescuento);

        txtValorDescuento = new JLabel("$ 0.00");
        txtValorDescuento.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtValorDescuento.setForeground(new Color(200, 0, 0));
        txtValorDescuento.setBounds(190, 115, 260, 25);
        panelCalculo.add(txtValorDescuento);

        lblResPrecioFinal = new JLabel("PRECIO FINAL:");
        lblResPrecioFinal.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResPrecioFinal.setBounds(30, 150, 150, 25);
        panelCalculo.add(lblResPrecioFinal);

        txtPrecioFinal = new JLabel("$ 0.00");
        txtPrecioFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtPrecioFinal.setForeground(new Color(0, 102, 204));
        txtPrecioFinal.setBounds(190, 150, 260, 25);
        panelCalculo.add(txtPrecioFinal);

        txtMensaje = new JLabel("");
        txtMensaje.setFont(new Font("Tahoma", Font.ITALIC, 11));
        txtMensaje.setForeground(Color.DARK_GRAY);
        txtMensaje.setBounds(30, 190, 420, 25);
        panelCalculo.add(txtMensaje);

        jTabbedPane1.addTab("CALCULAR DESCUENTO", panelCalculo);

        // Layout Principal
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitulo)
                .addGap(15, 15, 15)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (btnIniciar.getText().equalsIgnoreCase("Conectar")) {
                puerto = Integer.parseInt(campoPuertoServidor.getText().trim());
                ipServidor = campoIPServidor.getText().trim();
                invocadorRemoto = new CallHandler();
                cliente = new Client(ipServidor, puerto, invocadorRemoto);
                calculoDescuentoRemoto = (IRemotaCalculoDescuento) cliente.getGlobal(IRemotaCalculoDescuento.class);
                
                btnIniciar.setText("Desconectar");
                btnIniciar.setForeground(Color.RED);
                txtEstado.setText("Conectado");
                txtEstado.setForeground(new Color(0, 153, 51));
                JOptionPane.showMessageDialog(this, "Conexión exitosa con el servidor en " + ipServidor + ":" + puerto, "Conexión", JOptionPane.INFORMATION_MESSAGE);
            } else if (btnIniciar.getText().equalsIgnoreCase("Desconectar")) {
                cliente.close();
                btnIniciar.setText("Conectar");
                txtEstado.setText("Desconectado");
                btnIniciar.setForeground(new Color(0, 153, 51));
                txtEstado.setForeground(new Color(220, 20, 60));
                calculoDescuentoRemoto = null;
                JOptionPane.showMessageDialog(this, "Desconectado del servidor.", "Conexión", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            System.out.println("ERROR AL CONECTAR");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "No fue posible conectar con el servidor: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un número entero válido.", "Puerto inválido", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {
        if (calculoDescuentoRemoto == null) {
            JOptionPane.showMessageDialog(this, "Primero debe conectarse al servidor en la pestaña CONEXION.", "Sin Conexión", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            float precio = Float.parseFloat(campoPrecioOriginal.getText().trim());
            float porcentaje = Float.parseFloat(campoPorcentajeDescuento.getText().trim());

            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("Precio original: " + precio);
                        System.out.println("Porcentaje descuento: " + porcentaje);
                        DatosDescuento datos = new DatosDescuento();
                        datos.setPrecioOriginal(precio);
                        datos.setPorcentajeDescuento(porcentaje);

                        System.out.println("Enviados los datos\nEsperando respuesta...");
                        datos = calculoDescuentoRemoto.calcularDescuento(datos);

                        System.out.println("Valor Descuento: " + datos.getValorDescuento() +
                                           " | Precio Final: " + datos.getPrecioFinal() +
                                           " | Mensaje: " + datos.getMensaje());

                        txtValorDescuento.setText(String.format("$ %,.2f", datos.getValorDescuento()));
                        txtPrecioFinal.setText(String.format("$ %,.2f", datos.getPrecioFinal()));
                        txtMensaje.setText(datos.getMensaje());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "ERROR con el cliente: " + ex.getMessage());
                        System.out.println("ERROR con el cliente " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            });
            hilo.start();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos en el precio y descuento.", "Datos Inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }

    public JLabel getTxtEstado() {
        return txtEstado;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }
}
