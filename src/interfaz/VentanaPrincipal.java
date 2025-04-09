package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("EduSmart - Bienvenida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Hola, ¡Bienvenido al Sistema EduSmart!");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(40, 50, 320, 30);
        getContentPane().add(lblTitulo);

        JButton btnInicio = new JButton("Inicio");
        btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnInicio.setBounds(140, 120, 100, 30);
        getContentPane().add(btnInicio);

        // Acción al presionar el botón
        btnInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Abrir la VentanaFacade y cerrar la actual
                VentanaFacade ventanaFacade = new VentanaFacade();
                ventanaFacade.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaPrincipal frame = new VentanaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
