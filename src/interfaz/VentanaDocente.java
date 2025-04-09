package interfaz;

import mundo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaDocente extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtIdDocente, txtNombreDocente, txtTelefono, txtHorario;
    private JComboBox<TipoEspecialidad> comboTipoEspecialidad;

    private InstitucionFacade institucionFacade;

    public VentanaDocente() {
        institucionFacade = new InstitucionFacade("eduSmart");

        setTitle("Gestión de Docentes");
        setBounds(100, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblIdDocente = new JLabel("ID Docente:");
        lblIdDocente.setBounds(30, 20, 100, 25);
        getContentPane().add(lblIdDocente);

        txtIdDocente = new JTextField();
        txtIdDocente.setBounds(140, 20, 150, 25);
        getContentPane().add(txtIdDocente);

        JLabel lblNombreDocente = new JLabel("Nombre Docente:");
        lblNombreDocente.setBounds(30, 60, 100, 25);
        getContentPane().add(lblNombreDocente);

        txtNombreDocente = new JTextField();
        txtNombreDocente.setBounds(140, 60, 150, 25);
        getContentPane().add(txtNombreDocente);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 100, 100, 25);
        getContentPane().add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 100, 150, 25);
        getContentPane().add(txtTelefono);

        JLabel lblHorario = new JLabel("Horario:");
        lblHorario.setBounds(30, 140, 100, 25);
        getContentPane().add(lblHorario);

        txtHorario = new JTextField();
        txtHorario.setBounds(140, 140, 150, 25);
        getContentPane().add(txtHorario);

        JLabel lblTipoEspecialidad = new JLabel("Especialidad:");
        lblTipoEspecialidad.setBounds(310, 20, 100, 25);
        getContentPane().add(lblTipoEspecialidad);

        comboTipoEspecialidad = new JComboBox<>();
        comboTipoEspecialidad.setBounds(420, 20, 200, 25);
        getContentPane().add(comboTipoEspecialidad);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(650, 20, 100, 25);
        btnAgregar.addActionListener(e -> agregarDocente());
        getContentPane().add(btnAgregar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(650, 60, 100, 25);
        btnActualizar.addActionListener(e -> actualizarDocente());
        getContentPane().add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(650, 100, 100, 25);
        btnEliminar.addActionListener(e -> eliminarDocente());
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(650, 140, 100, 25);
        btnAtras.addActionListener(e -> {
            new VentanaFacade().setVisible(true);
            dispose();
        });
        getContentPane().add(btnAtras);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Teléfono", "Horario", "Especialidad"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 200, 720, 230);
        getContentPane().add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                txtIdDocente.setText(tableModel.getValueAt(fila, 0).toString());
                txtNombreDocente.setText(tableModel.getValueAt(fila, 1).toString());
                txtTelefono.setText(tableModel.getValueAt(fila, 2).toString());
                txtHorario.setText(tableModel.getValueAt(fila, 3).toString());

                String tipoEspecialidad = tableModel.getValueAt(fila, 4).toString();
                for (int i = 0; i < comboTipoEspecialidad.getItemCount(); i++) {
                    if (comboTipoEspecialidad.getItemAt(i).toString().equals(tipoEspecialidad)) {
                        comboTipoEspecialidad.setSelectedIndex(i);
                        break;
                    }
                }
            }
        });

        actualizarComboEspecialidad();
        actualizarTabla();
    }

    private void agregarDocente() {
        try {
            int idDocente = Integer.parseInt(txtIdDocente.getText());
            String nombreDocente = txtNombreDocente.getText();
            String telefono = txtTelefono.getText();
            String horario = txtHorario.getText();
            TipoEspecialidad tipoEspecialidadSeleccionado = (TipoEspecialidad) comboTipoEspecialidad.getSelectedItem();

            if (tipoEspecialidadSeleccionado == null) throw new Exception("Debe seleccionar una especialidad");

            Docente docente = new Docente(idDocente, nombreDocente, telefono, horario, tipoEspecialidadSeleccionado);
            institucionFacade.agregarDocente(institucionFacade.obtenerDocentes(), docente);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizarDocente() {
        try {
            int idDocente = Integer.parseInt(txtIdDocente.getText());
            String nombreDocente = txtNombreDocente.getText();
            String telefono = txtTelefono.getText();
            String horario = txtHorario.getText();
            TipoEspecialidad tipoEspecialidadSeleccionado = (TipoEspecialidad) comboTipoEspecialidad.getSelectedItem();

            if (tipoEspecialidadSeleccionado == null) throw new Exception("Debe seleccionar una especialidad");

            Docente docente = new Docente(idDocente, nombreDocente, telefono, horario, tipoEspecialidadSeleccionado);
            institucionFacade.actualizarDocente(institucionFacade.obtenerDocentes(), docente);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarDocente() {
        try {
            int id = Integer.parseInt(txtIdDocente.getText());
            institucionFacade.eliminarDocente(institucionFacade.obtenerDocentes(), id);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Docente d : institucionFacade.obtenerDocentes()) {
            tableModel.addRow(new Object[]{
                d.getIdDocente(),
                d.getNombre(),
                d.getNumTelefono(),
                d.getHorario(),
                d.getTipoEspecialidad().toString()
            });
        }
    }

    private void actualizarComboEspecialidad() {
        comboTipoEspecialidad.removeAllItems();
        for (TipoEspecialidad tipo : TipoEspecialidad.values()) {
            comboTipoEspecialidad.addItem(tipo);
        }
    }

    private void limpiarCampos() {
        txtIdDocente.setText("");
        txtNombreDocente.setText("");
        txtTelefono.setText("");
        txtHorario.setText("");
        comboTipoEspecialidad.setSelectedIndex(-1);
    }
}
