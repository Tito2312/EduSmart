package interfaz;

import mundo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaCurso extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtIdCurso, txtNombreCurso, txtArea, txtHoras;
    private JComboBox<Docente> comboDocentes;

    private List<Curso> listaCursos;
    private InstitucionFacade institucionFacade;

    public VentanaCurso() {
        institucionFacade = new InstitucionFacade("eduSmart");
        listaCursos = new ArrayList<>();

        setTitle("Gestión de Cursos");
        setBounds(100, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblIdCurso = new JLabel("ID Curso:");
        lblIdCurso.setBounds(30, 20, 100, 25);
        getContentPane().add(lblIdCurso);

        txtIdCurso = new JTextField();
        txtIdCurso.setBounds(140, 20, 150, 25);
        getContentPane().add(txtIdCurso);

        JLabel lblNombreCurso = new JLabel("Nombre Curso:");
        lblNombreCurso.setBounds(30, 60, 100, 25);
        getContentPane().add(lblNombreCurso);

        txtNombreCurso = new JTextField();
        txtNombreCurso.setBounds(140, 60, 150, 25);
        getContentPane().add(txtNombreCurso);

        JLabel lblArea = new JLabel("Área:");
        lblArea.setBounds(30, 100, 100, 25);
        getContentPane().add(lblArea);

        txtArea = new JTextField();
        txtArea.setBounds(140, 100, 150, 25);
        getContentPane().add(txtArea);

        JLabel lblHoras = new JLabel("N° Horas:");
        lblHoras.setBounds(30, 140, 100, 25);
        getContentPane().add(lblHoras);

        txtHoras = new JTextField();
        txtHoras.setBounds(140, 140, 150, 25);
        getContentPane().add(txtHoras);

        JLabel lblDocente = new JLabel("Docente:");
        lblDocente.setBounds(310, 20, 100, 25);
        getContentPane().add(lblDocente);

        comboDocentes = new JComboBox<>();
        comboDocentes.setBounds(420, 20, 200, 25);
        getContentPane().add(comboDocentes);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(650, 20, 100, 25);
        btnAgregar.addActionListener(e -> agregarCurso());
        getContentPane().add(btnAgregar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(650, 60, 100, 25);
        btnActualizar.addActionListener(e -> actualizarCurso());
        getContentPane().add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(650, 100, 100, 25);
        btnEliminar.addActionListener(e -> eliminarCurso());
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(650, 140, 100, 25);
        btnAtras.addActionListener(e -> {
            new VentanaFacade().setVisible(true);
            dispose();
        });
        getContentPane().add(btnAtras);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Área", "Horas", "Docente"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 200, 720, 230);
        getContentPane().add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                txtIdCurso.setText(tableModel.getValueAt(fila, 0).toString());
                txtNombreCurso.setText(tableModel.getValueAt(fila, 1).toString());
                txtArea.setText(tableModel.getValueAt(fila, 2).toString());
                txtHoras.setText(tableModel.getValueAt(fila, 3).toString());

                String nombreDocente = tableModel.getValueAt(fila, 4).toString();
                for (int i = 0; i < comboDocentes.getItemCount(); i++) {
                    if (comboDocentes.getItemAt(i).getNombre().equals(nombreDocente)) {
                        comboDocentes.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

        actualizarComboDocentes();
        actualizarTabla();
    }

    private void agregarCurso() {
        try {
            int idCurso = Integer.parseInt(txtIdCurso.getText());
            String nombreCurso = txtNombreCurso.getText();
            String area = txtArea.getText();
            int horas = Integer.parseInt(txtHoras.getText());

            Docente docenteSeleccionado = (Docente) comboDocentes.getSelectedItem();
            if (docenteSeleccionado == null) throw new Exception("Debe seleccionar un docente");

            Curso curso = new Curso(idCurso, nombreCurso, area, horas, docenteSeleccionado, new ArrayList<>());
            institucionFacade.agregarCurso(listaCursos, curso);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizarCurso() {
        try {
            int idCurso = Integer.parseInt(txtIdCurso.getText());
            String nombreCurso = txtNombreCurso.getText();
            String area = txtArea.getText();
            int horas = Integer.parseInt(txtHoras.getText());

            Docente docenteSeleccionado = (Docente) comboDocentes.getSelectedItem();
            if (docenteSeleccionado == null) throw new Exception("Debe seleccionar un docente");

            Curso curso = new Curso(idCurso, nombreCurso, area, horas, docenteSeleccionado, new ArrayList<>());
            institucionFacade.actualizarCurso(listaCursos, curso);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarCurso() {
        try {
            int id = Integer.parseInt(txtIdCurso.getText());
            institucionFacade.eliminarCurso(listaCursos, id);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Curso c : listaCursos) {
            tableModel.addRow(new Object[]{
                c.getIdCurso(),
                c.getNombre(),
                c.getArea(),
                c.getNumHoras(),
                c.getDocente().getNombre()
            });
        }
    }

    private void actualizarComboDocentes() {
        comboDocentes.removeAllItems();
        List<Docente> docentes = institucionFacade.getInstitucion().getDocentes();
        for (Docente d : docentes) {
            comboDocentes.addItem(d);
        }
    }

    private void limpiarCampos() {
        txtIdCurso.setText("");
        txtNombreCurso.setText("");
        txtArea.setText("");
        txtHoras.setText("");
        comboDocentes.setSelectedIndex(-1);
    }
}
