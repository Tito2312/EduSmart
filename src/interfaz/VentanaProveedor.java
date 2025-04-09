package interfaz;

import mundo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaProveedor extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtIdProveedor, txtNombre, txtDireccion, txtTelefono;

    private List<Proveedor> listaProveedores;
    private InstitucionFacade institucionFacade;

    public VentanaProveedor() {
        institucionFacade = new InstitucionFacade("eduSmart");
        listaProveedores = new ArrayList<>();

        setTitle("Gestión de Proveedores");
        setBounds(100, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblIdProveedor = new JLabel("ID Proveedor:");
        lblIdProveedor.setBounds(30, 20, 100, 25);
        getContentPane().add(lblIdProveedor);

        txtIdProveedor = new JTextField();
        txtIdProveedor.setBounds(140, 20, 150, 25);
        getContentPane().add(txtIdProveedor);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 100, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 150, 25);
        getContentPane().add(txtNombre);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(30, 100, 100, 25);
        getContentPane().add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(140, 100, 150, 25);
        getContentPane().add(txtDireccion);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 140, 100, 25);
        getContentPane().add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 140, 150, 25);
        getContentPane().add(txtTelefono);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(650, 20, 100, 25);
        btnAgregar.addActionListener(e -> agregarProveedor());
        getContentPane().add(btnAgregar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(650, 60, 100, 25);
        btnActualizar.addActionListener(e -> actualizarProveedor());
        getContentPane().add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(650, 100, 100, 25);
        btnEliminar.addActionListener(e -> eliminarProveedor());
        getContentPane().add(btnEliminar);

        JButton btnAtras = new JButton("Atrás");
        btnAtras.setBounds(650, 140, 100, 25);
        btnAtras.addActionListener(e -> {
            new VentanaFacade().setVisible(true);
            dispose();
        });
        getContentPane().add(btnAtras);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Dirección", "Teléfono"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 200, 720, 230);
        getContentPane().add(scrollPane);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = table.getSelectedRow();
                txtIdProveedor.setText(tableModel.getValueAt(fila, 0).toString());
                txtNombre.setText(tableModel.getValueAt(fila, 1).toString());
                txtDireccion.setText(tableModel.getValueAt(fila, 2).toString());
                txtTelefono.setText(tableModel.getValueAt(fila, 3).toString());
            }
        });

        actualizarTabla();
    }

    private void agregarProveedor() {
        try {
            int id = Integer.parseInt(txtIdProveedor.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();

            Proveedor proveedor = new Proveedor(id, nombre, direccion, telefono);
            institucionFacade.agregarProveedor(listaProveedores, proveedor);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void actualizarProveedor() {
        try {
            int id = Integer.parseInt(txtIdProveedor.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String telefono = txtTelefono.getText();

            Proveedor proveedor = new Proveedor(id, nombre, direccion, telefono);
            institucionFacade.actualizarProveedor(listaProveedores, proveedor);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarProveedor() {
        try {
            int id = Integer.parseInt(txtIdProveedor.getText());
            institucionFacade.eliminarProveedor(listaProveedores, id);
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0);
        for (Proveedor p : listaProveedores) {
            tableModel.addRow(new Object[]{
                p.getIdProveedor(),
                p.getNombre(),
                p.getDireccion(),
                p.getNumTelefono()
            });
        }
    }

    private void limpiarCampos() {
        txtIdProveedor.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
}
