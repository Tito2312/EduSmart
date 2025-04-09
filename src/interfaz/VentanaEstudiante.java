package interfaz;

import mundo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaEstudiante extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtIdEstudiante, txtNombre, txtGrado, txtCorreo;

    private List<Estudiante> listaEstudiantes;
    private InstitucionFacade institucionFacade;

    public VentanaEstudiante() {
        institucionFacade = new InstitucionFacade("eduSmart");
        listaEstudiantes = new ArrayList<>();

        setTitle("Gestión de Estudiantes");
        setBounds(100, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblIdEstudiante = new JLabel("ID Estudiante:");
        lblIdEstudiante.setBounds(30, 20, 100, 25);
        getContentPane().add(lblIdEstudiante);

        txtIdEstudiante = new JTextField();
        txtIdEstudiante.setBounds(140, 20, 150, 25);
        getContentPane().add(txtIdEstudiante);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 100, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 150, 25);
        getContentPane().add(txtNombre);

        JLabel lblGrado = new JLabel("Grado:");
        lblGrado.setBounds(30, 100, 100, 25);
        getContentPane().add(lblGrado);

        txtGrado = new JTextField();
        txtGrado.setBounds(140, 100, 150, 25);
        getContentPane().add(txtGrado);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 140, 100, 25);
        getContentPane().add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 140, 150, 25);
        getContentPane().add(txtCorreo);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(650, 20, 100, 25);
        btnAgregar.addActionListener(e -> agregarEstudiante());
        getContentPane().add(btnAgregar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(650, 60, 100, 25);
        btnActualizar.addActionListener(e -> actualizarEstudiante());
        getContentPane().add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(650, 100, 100, 25);
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(650, 140, 100, 25);
        btnAtras.addActionListener(e -> {
            new VentanaFacade().setVisible(true);
            dispose();
        });
        getContentPane().add(btnAtras);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Grado", "Correo"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 200, 720, 230);
        getContentPane().add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                txtIdEstudiante.setText(tableModel.getValueAt(fila, 0).toString());
                txtNombre.setText(tableModel.getValueAt(fila, 1).toString());
                txtGrado.setText(tableModel.getValueAt(fila, 2).toString());
                txtCorreo.setText(tableModel.getValueAt(fila, 3).toString());
            }
        });

        actualizarTabla();
    }

    private void agregarEstudiante() {
        try {
            int idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
            String nombre = txtNombre.getText();
            String grado = txtGrado.getText();
            String correo = txtCorreo.getText();

            Estudiante estudiante = new Estudiante(idEstudiante, nombre, grado, correo);
            institucionFacade.agregarEstudiante(listaEstudiantes, estudiante);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizarEstudiante() {
        try {
            int idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
            String nombre = txtNombre.getText();
            String grado = txtGrado.getText();
            String correo = txtCorreo.getText();

            Estudiante estudiante = new Estudiante(idEstudiante, nombre, grado, correo);
            institucionFacade.actualizarEstudiante(listaEstudiantes, estudiante);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarEstudiante() {
        try {
            int id = Integer.parseInt(txtIdEstudiante.getText());
            institucionFacade.eliminarEstudiante(listaEstudiantes, id);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Estudiante e : listaEstudiantes) {
            tableModel.addRow(new Object[]{
                e.getIdEstudiante(),
                e.getNombre(),
                e.getGrado(),
                e.getCorreo()
            });
        }
    }

    private void limpiarCampos() {
        txtIdEstudiante.setText("");
        txtNombre.setText("");
        txtGrado.setText("");
        txtCorreo.setText("");
    }
}
