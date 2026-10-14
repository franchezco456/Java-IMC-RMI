package unicolombo.sistemasdistribuidos.descuento.rmi.vistas;

import java.awt.Color;
import java.awt.Font;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.DatosDescuento;
import unicolombo.sistemasdistribuidos.descuento.rmi.lib.IRemotaCalculoDescuento;

/**
 * Interfaz Gráfica para el Cliente usando RMI Estándar de Java (java.rmi.*).
 * No depende de ninguna librería externa de terceros.
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    // Variables de conexión RMI estándar
    private String ipServidor = "localhost";
    private int puerto = 1099; // Puerto estándar de RMI Registry
    private String nombreServicio = "CalculoDescuentoServicio";
    private IRemotaCalculoDescuento calculoDescuentoRemoto;

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

    // Pestaña 2: Cálculo
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
        lblTitulo = new JLabel("CLIENTE DESCUENTO (RMI ESTANDAR)");
        jTabbedPane1 = new JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente Java RMI Estándar - Descuento de Compra");
        setResizable(false);

        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
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

        lblPuerto = new JLabel("PUERTO RMI:");
        lblPuerto.setBounds(50, 80, 120, 25);
        panelConexion.add(lblPuerto);

        campoPuertoServidor = new JTextField("1099");
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

                // 1. Obtener referencia al Registry remoto de Java RMI
                Registry registro = LocateRegistry.getRegistry(ipServidor, puerto);

                // 2. Buscar (lookup) el stub del servicio remoto publicado por el servidor
                calculoDescuentoRemoto = (IRemotaCalculoDescuento) registro.lookup(nombreServicio);

                btnIniciar.setText("Desconectar");
                btnIniciar.setForeground(Color.RED);
                txtEstado.setText("Conectado");
                txtEstado.setForeground(new Color(0, 153, 51));
                JOptionPane.showMessageDialog(this, "Conexión RMI exitosa con el servicio '" + nombreServicio + "' en " + ipServidor + ":" + puerto, "Conexión", JOptionPane.INFORMATION_MESSAGE);
            } else if (btnIniciar.getText().equalsIgnoreCase("Desconectar")) {
                calculoDescuentoRemoto = null;
                btnIniciar.setText("Conectar");
                txtEstado.setText("Desconectado");
                btnIniciar.setForeground(new Color(0, 153, 51));
                txtEstado.setForeground(new Color(220, 20, 60));
                JOptionPane.showMessageDialog(this, "Desconectado del servidor RMI.", "Conexión", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            System.err.println("ERROR AL CONECTAR RMI: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "No fue posible conectar con el servicio RMI: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
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
                        System.out.println("Cliente RMI enviando: Precio=" + precio + ", Porcentaje=" + porcentaje);
                        DatosDescuento datos = new DatosDescuento(precio, porcentaje);

                        // Invocación del método remoto estándar
                        datos = calculoDescuentoRemoto.calcularDescuento(datos);

                        txtValorDescuento.setText(String.format("$ %,.2f", datos.getValorDescuento()));
                        txtPrecioFinal.setText(String.format("$ %,.2f", datos.getPrecioFinal()));
                        txtMensaje.setText(datos.getMensaje());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "Error en invocación RMI: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            });
            hilo.start();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos para el precio y el descuento.", "Datos Inválidos", JOptionPane.WARNING_MESSAGE);
        }
    }
}
