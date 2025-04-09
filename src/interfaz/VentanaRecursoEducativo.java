package interfaz;

import mundo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaRecursoEducativo extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtIdRecurso, txtNombre, txtFecha;
    private JComboBox<Tipo> cbTipo;
    private JComboBox<TipoEstado> cbTipoEstado;

    private List<RecursoEducativo> listaRecursos;
    private InstitucionFacade institucionFacade;

    public VentanaRecursoEducativo() {
        institucionFacade = new InstitucionFacade("eduSmart");
        listaRecursos = new ArrayList<>();

        setTitle("Gestión de Recursos Educativos");
        setBounds(100, 100, 850, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblId = new JLabel("ID Recurso:");
        lblId.setBounds(30, 20, 100, 25);
        getContentPane().add(lblId);

        txtIdRecurso = new JTextField();
        txtIdRecurso.setBounds(140, 20, 150, 25);
        getContentPane().add(txtIdRecurso);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 100, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 150, 25);
        getContentPane().add(txtNombre);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 100, 100, 25);
        getContentPane().add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(140, 100, 150, 25);
        getContentPane().add(txtFecha);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(320, 20, 100, 25);
        getContentPane().add(lblTipo);

        cbTipo = new JComboBox<>(Tipo.values());
        cbTipo.setBounds(420, 20, 150, 25);
        getContentPane().add(cbTipo);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(320, 60, 100, 25);
        getContentPane().add(lblEstado);

        cbTipoEstado = new JComboBox<>(TipoEstado.values());
        cbTipoEstado.setBounds(420, 60, 150, 25);
        getContentPane().add(cbTipoEstado);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(650, 20, 120, 25);
        btnAgregar.addActionListener(e -> agregarRecurso());
        getContentPane().add(btnAgregar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(650, 60, 120, 25);
        btnActualizar.addActionListener(e -> actualizarRecurso());
        getContentPane().add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(650, 100, 120, 25);
        btnEliminar.addActionListener(e -> eliminarRecurso());
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(650, 140, 120, 25);
        btnAtras.addActionListener(e -> {
            new VentanaFacade().setVisible(true);
            dispose();
        });
        getContentPane().add(btnAtras);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Fecha", "Tipo", "Estado"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 200, 770, 230);
        getContentPane().add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                txtIdRecurso.setText(tableModel.getValueAt(fila, 0).toString());
                txtNombre.setText(tableModel.getValueAt(fila, 1).toString());
                txtFecha.setText(tableModel.getValueAt(fila, 2).toString());
                cbTipo.setSelectedItem(Tipo.valueOf(tableModel.getValueAt(fila, 3).toString()));
                cbTipoEstado.setSelectedItem(TipoEstado.valueOf(tableModel.getValueAt(fila, 4).toString()));
            }
        });

        actualizarTabla();
    }

    private void agregarRecurso() {
        try {
            int id = Integer.parseInt(txtIdRecurso.getText());
            String nombre = txtNombre.getText();
            String fecha = txtFecha.getText();
            Tipo tipo = (Tipo) cbTipo.getSelectedItem();
            TipoEstado estado = (TipoEstado) cbTipoEstado.getSelectedItem();

            RecursoEducativo recurso = new RecursoEducativo(id, nombre, fecha, tipo, estado);
            institucionFacade.agregarRecurso(listaRecursos, recurso);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizarRecurso() {
        try {
            int id = Integer.parseInt(txtIdRecurso.getText());
            String nombre = txtNombre.getText();
            String fecha = txtFecha.getText();
            Tipo tipo = (Tipo) cbTipo.getSelectedItem();
            TipoEstado estado = (TipoEstado) cbTipoEstado.getSelectedItem();

            RecursoEducativo recurso = new RecursoEducativo(id, nombre, fecha, tipo, estado);
            institucionFacade.actualizarRecurso(listaRecursos, recurso);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarRecurso() {
        try {
            int id = Integer.parseInt(txtIdRecurso.getText());
            institucionFacade.eliminarRecurso(listaRecursos, id);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (RecursoEducativo r : listaRecursos) {
            tableModel.addRow(new Object[]{
                r.getIdRecurso(),
                r.getNombre(),
                r.getFecha(),
                r.getTipo(),
                r.getTipoEstado()
            });
        }
    }

    private void limpiarCampos() {
        txtIdRecurso.setText("");
        txtNombre.setText("");
        txtFecha.setText("");
        cbTipo.setSelectedIndex(0);
        cbTipoEstado.setSelectedIndex(0);
    }
}
