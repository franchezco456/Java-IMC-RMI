package johnarrieta.seminario.imc.rmi.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import johnarrieta.seminario.imc.rmi.lib.*;

/**
 * Ventana del cliente IMC (version RMI estandar de Java, sin librerias externas).
 *
 * @author JOHN CARLOS ARRIETA ARRIETA
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    Registry registro;
    String ipServidor = "localhost";
    int puerto = 9007;
    IRemotaCalculoImc calculoImcRemoto;

    public VentanaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente IMC - RMI Java");

        jLabel1 = new JLabel("CLIENTE IMC", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        jTabbedPane1 = new JTabbedPane();

        // ---------- Pestana CONEXION ----------
        jPanel1 = new JPanel(new GridBagLayout());
        jLabel2 = new JLabel("DIRECCION IP: ");
        campoIPServidor = new JTextField("localhost", 15);
        jLabel3 = new JLabel("PUERTO DE RED:");
        campoPuertoServidor = new JTextField("9007", 15);
        jLabel4 = new JLabel("ESTADO: ");
        txtEstado = new JLabel("Desconectado");
        txtEstado.setForeground(new Color(255, 0, 51));
        btnIniciar = new JButton("Conectar");
        btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar.setForeground(new Color(0, 153, 51));
        btnIniciar.addActionListener(evt -> btnIniciarActionPerformed(evt));

        agregar(jPanel1, jLabel2, 0, 0, 1);
        agregar(jPanel1, campoIPServidor, 1, 0, 1);
        agregar(jPanel1, jLabel3, 0, 1, 1);
        agregar(jPanel1, campoPuertoServidor, 1, 1, 1);
        agregar(jPanel1, jLabel4, 0, 2, 1);
        agregar(jPanel1, txtEstado, 1, 2, 1);
        agregar(jPanel1, btnIniciar, 1, 3, 1);

        // ---------- Pestana CALCULAR IMC ----------
        jPanel3 = new JPanel(new GridBagLayout());
        jLabel5 = new JLabel("PESO (kg):");
        campoPeso = new JTextField(12);
        jLabel6 = new JLabel("ALTURA (m):");
        campoAltura = new JTextField(12);
        jLabel7 = new JLabel("IMC:");
        txtResultado = new JLabel("0.0");
        txtResultado.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtResultado.setForeground(new Color(255, 0, 51));
        txtMensaje = new JLabel(" ");
        txtMensaje.setBorder(BorderFactory.createEtchedBorder());
        btnIniciar1 = new JButton("CALCULAR");
        btnIniciar1.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIniciar1.setForeground(new Color(0, 153, 51));
        btnIniciar1.addActionListener(evt -> btnIniciar1ActionPerformed(evt));

        agregar(jPanel3, jLabel5, 0, 0, 1);
        agregar(jPanel3, campoPeso, 1, 0, 1);
        agregar(jPanel3, jLabel6, 0, 1, 1);
        agregar(jPanel3, campoAltura, 1, 1, 1);
        agregar(jPanel3, btnIniciar1, 2, 0, 1);
        agregar(jPanel3, jLabel7, 0, 2, 1);
        agregar(jPanel3, txtResultado, 1, 2, 1);
        agregar(jPanel3, txtMensaje, 0, 3, 3);

        jTabbedPane1.addTab("CONEXION", jPanel1);
        jTabbedPane1.addTab("CALCULAR IMC", jPanel3);

        JPanel contenido = new JPanel(new java.awt.BorderLayout(10, 10));
        contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contenido.add(jLabel1, java.awt.BorderLayout.NORTH);
        contenido.add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        setContentPane(contenido);
        setSize(480, 320);
    }

    private void agregar(JPanel panel, java.awt.Component c, int x, int y, int ancho) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx = x;
        g.gridy = y;
        g.gridwidth = ancho;
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;
        panel.add(c, g);
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            if (btnIniciar.getText().equalsIgnoreCase("Conectar")) {

                puerto = Integer.parseInt(campoPuertoServidor.getText().trim());
                ipServidor = campoIPServidor.getText().trim();
                // Localiza el RMI Registry del servidor (conexion TCP)
                registro = LocateRegistry.getRegistry(ipServidor, puerto);
                // Busca el objeto remoto por su nombre y obtiene el stub
                calculoImcRemoto = (IRemotaCalculoImc) registro.lookup(IRemotaCalculoImc.NOMBRE_SERVICIO);
                btnIniciar.setText("Desconectar");
                btnIniciar.setForeground(Color.RED);
                txtEstado.setText("Conectado");
                txtEstado.setForeground(new Color(0, 153, 51));
            } else if (btnIniciar.getText().equalsIgnoreCase("Desconectar")) {
                // En RMI estandar no hay un "close": basta con soltar el stub
                calculoImcRemoto = null;
                registro = null;
                btnIniciar.setText("Conectar");
                txtEstado.setText("Desconectado");
                btnIniciar.setForeground(new Color(0, 153, 51));
                txtEstado.setForeground(Color.RED);
            }
        } catch (RemoteException | NotBoundException ex) {
            System.out.println("ERROR AL CONECTAR");
            JOptionPane.showMessageDialog(this, "ERROR AL CONECTAR: " + ex.getMessage());
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El puerto debe ser un numero");
        }
    }

    private void btnIniciar1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (calculoImcRemoto == null) {
            JOptionPane.showMessageDialog(this, "Primero conectese al servidor (pestana CONEXION)");
            return;
        }
        float peso;
        float altura;
        try {
            peso = Float.parseFloat(campoPeso.getText().trim());
            altura = Float.parseFloat(campoAltura.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Peso y altura deben ser numeros (ej: 70 y 1.75)");
            return;
        }
        // La llamada remota se hace en otro hilo para no congelar la GUI
        Thread hilo = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("Peso: " + peso);
                    System.out.println("Altura: " + altura);
                    DatosImc datos = new DatosImc();
                    datos.setAltura(altura);
                    datos.setPeso(peso);
                    System.out.println("Enviados los datos\nEsperando respuesta");
                    DatosImc respuesta = calculoImcRemoto.calcularImc(datos);
                    System.out.println("IMC: " + respuesta.getResultado()
                            + "\nMensaje: " + respuesta.getInterpretacion());
                    SwingUtilities.invokeLater(() -> {
                        txtResultado.setText(String.format("%.2f", respuesta.getResultado()));
                        txtMensaje.setText(respuesta.getInterpretacion());
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(VentanaPrincipal.this,
                            "ERROR con el cliente " + ex.getMessage()));
                    System.out.println("ERROR con el cliente " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        };
        hilo.start();
    }

    public JLabel getTxtEstado() {
        return txtEstado;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    // Variables declaration
    private JButton btnIniciar;
    private JButton btnIniciar1;
    private JTextField campoAltura;
    private JTextField campoIPServidor;
    private JTextField campoPeso;
    private JTextField campoPuertoServidor;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JTabbedPane jTabbedPane1;
    private JLabel txtEstado;
    private JLabel txtMensaje;
    private JLabel txtResultado;
    // End of variables declaration
}
