package interfaz;
import javax.swing.*;
import java.awt.event.*;

public class VentanaFacade extends JFrame {

    public VentanaFacade() {
        setTitle("Bienvenido al sistema de eduSmart");
        setBounds(100, 100, 400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Bienvenido al sistema de eduSmart");
        lblTitulo.setBounds(80, 20, 250, 25);
        getContentPane().add(lblTitulo);

        JLabel lblPregunta = new JLabel("¿A quién desea gestionar?");
        lblPregunta.setBounds(110, 50, 200, 25);
        getContentPane().add(lblPregunta);

        JButton btnCurso = new JButton("Curso");
        btnCurso.setBounds(130, 90, 120, 25);
        btnCurso.addActionListener(e -> {
            new VentanaCurso().setVisible(true);
            dispose();
        });
        getContentPane().add(btnCurso);

        JButton btnEstudiante = new JButton("Estudiante");
        btnEstudiante.setBounds(130, 120, 120, 25);
        btnEstudiante.addActionListener(e -> {
            new VentanaEstudiante().setVisible(true);
            dispose();
        });
        getContentPane().add(btnEstudiante);

        JButton btnDocente = new JButton("Docente");
        btnDocente.setBounds(130, 150, 120, 25);
        btnDocente.addActionListener(e -> {
            new VentanaDocente().setVisible(true);
            dispose();
        });
        getContentPane().add(btnDocente);

        JButton btnRecurso = new JButton("RecursoEducativo");
        btnRecurso.setBounds(110, 180, 160, 25);
        btnRecurso.addActionListener(e -> {
            new VentanaRecursoEducativo().setVisible(true);
            dispose();
        });
        getContentPane().add(btnRecurso);

        JButton btnProveedor = new JButton("Proveedor");
        btnProveedor.setBounds(130, 210, 120, 25);
        btnProveedor.addActionListener(e -> {
            new VentanaProveedor().setVisible(true);
            dispose();
        });
        getContentPane().add(btnProveedor);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(130, 240, 120, 25);
        btnSalir.addActionListener(e -> System.exit(0));
        getContentPane().add(btnSalir);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaFacade().setVisible(true));
    }
}
