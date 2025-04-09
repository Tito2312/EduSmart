package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mundo.*;
import mundo.InstitucionFacade;
import mundo.Tipo;
import mundo.TipoEstado;
import mundo.TipoEspecialidad;

public class VentanaFacade extends JFrame {

    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtDetalle1;
    private JTextField txtDetalle2;
    private JLabel lblDetalle1;
    private JLabel lblDetalle2;
    private JComboBox<String> comboBoxEntidades;
    private JComboBox<TipoEspecialidad> comboEspecialidad;
    private JComboBox<Tipo> comboTipo;
    private JComboBox<TipoEstado> comboEstado;
    private JTable table;
    private DefaultTableModel modeloTabla;
    
    private InstitucionFacade institucionFacade;
    private List<Curso> cursos;
    private List<Docente> docentes;
    private List<Estudiante> estudiantes;
    private List<Proveedor> proveedores;
    private List<RecursoEducativo> recursos;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaFacade frame = new VentanaFacade();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, 
                        "Error al iniciar la aplicación: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public VentanaFacade() {
        // Inicializar las listas y el facade
        institucionFacade = new InstitucionFacade("Mi Institución Educativa");
        cursos = new ArrayList<>();
        docentes = new ArrayList<>();
        estudiantes = new ArrayList<>();
        proveedores = new ArrayList<>();
        recursos = new ArrayList<>();
        
        // Crear algunos datos de prueba
        inicializarDatosPrueba();
        
        setTitle("Sistema de Gestión Educativa - Facade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblEntidad = new JLabel("Entidad:");
        lblEntidad.setBounds(20, 20, 80, 20);
        contentPane.add(lblEntidad);
        
        comboBoxEntidades = new JComboBox<>();
        comboBoxEntidades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Curso", "Docente", "Estudiante", "Proveedor", "Recurso Educativo"}));
        comboBoxEntidades.setBounds(100, 20, 150, 20);
        comboBoxEntidades.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCamposSegunEntidad();
                actualizarTabla();
            }
        });
        contentPane.add(comboBoxEntidades);
        
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 60, 80, 20);
        contentPane.add(lblId);
        
        txtId = new JTextField();
        txtId.setBounds(100, 60, 150, 20);
        contentPane.add(txtId);
        txtId.setColumns(10);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 100, 80, 20);
        contentPane.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(100, 100, 150, 20);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);
        
        // CORRECCIÓN: Usar las variables de clase declaradas arriba, no redeclarar
        lblDetalle1 = new JLabel("Detalle 1:");
        lblDetalle1.setBounds(20, 140, 80, 20);
        contentPane.add(lblDetalle1);
        
        txtDetalle1 = new JTextField();
        txtDetalle1.setBounds(100, 140, 150, 20);
        contentPane.add(txtDetalle1);
        txtDetalle1.setColumns(10);
        
        lblDetalle2 = new JLabel("Detalle 2:");
        lblDetalle2.setBounds(20, 180, 80, 20);
        contentPane.add(lblDetalle2);
        
        txtDetalle2 = new JTextField();
        txtDetalle2.setBounds(100, 180, 150, 20);
        contentPane.add(txtDetalle2);
        txtDetalle2.setColumns(10);
        
        JLabel lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setBounds(270, 140, 100, 20);
        contentPane.add(lblEspecialidad);
        
        comboEspecialidad = new JComboBox<>(TipoEspecialidad.values());
        comboEspecialidad.setBounds(370, 140, 150, 20);
        contentPane.add(comboEspecialidad);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(270, 180, 100, 20);
        contentPane.add(lblTipo);
        
        comboTipo = new JComboBox<>(Tipo.values());
        comboTipo.setBounds(370, 180, 150, 20);
        contentPane.add(comboTipo);
        
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(270, 220, 100, 20);
        contentPane.add(lblEstado);
        
        comboEstado = new JComboBox<>(TipoEstado.values());
        comboEstado.setBounds(370, 220, 150, 20);
        contentPane.add(comboEstado);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    agregarElemento();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(VentanaFacade.this, 
                        "Error al agregar: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAgregar.setBounds(20, 260, 100, 30);
        contentPane.add(btnAgregar);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarElemento();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(VentanaFacade.this, 
                        "Error al actualizar: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnActualizar.setBounds(130, 260, 100, 30);
        contentPane.add(btnActualizar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarElemento();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(VentanaFacade.this, 
                        "Error al eliminar: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEliminar.setBounds(240, 260, 100, 30);
        contentPane.add(btnEliminar);
        
        JButton btnBuscar = new JButton("Buscar por ID");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarPorId();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(VentanaFacade.this, 
                        "Error al buscar: " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnBuscar.setBounds(350, 260, 120, 30);
        contentPane.add(btnBuscar);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 320, 850, 230);
        contentPane.add(scrollPane);
        
        table = new JTable();
        modeloTabla = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nombre", "Detalle 1", "Detalle 2", "Otros"}
        );
        table.setModel(modeloTabla);
        scrollPane.setViewportView(table);
        
        // Actualizar campos según la entidad seleccionada
        actualizarCamposSegunEntidad();
        // Actualizar tabla inicial
        actualizarTabla();
    }
    
    private void inicializarDatosPrueba() {
        // Docentes de prueba
        Docente docente1 = new Docente(1, "Juan Pérez", "555-1234", "L-V 8am-12pm", TipoEspecialidad.MATEMATICAS);
        Docente docente2 = new Docente(2, "María Gómez", "555-5678", "L-V 2pm-6pm", TipoEspecialidad.CIENCIAS);
        docentes.add(docente1);
        docentes.add(docente2);
        
        // Estudiantes de prueba
        estudiantes.add(new Estudiante(1, "Carlos Ruiz", "10°", "carlos@email.com"));
        estudiantes.add(new Estudiante(2, "Ana López", "11°", "ana@email.com"));
        
        // Cursos de prueba
        cursos.add(new Curso(1, "Álgebra", "Matemáticas", 60, docente1));
        cursos.add(new Curso(2, "Biología", "Ciencias", 40, docente2));
        
        // Proveedores de prueba
        proveedores.add(new Proveedor(1, "Libros S.A.", "Calle 123", "555-9012"));
        proveedores.add(new Proveedor(2, "Materiales Educativos", "Avenida 456", "555-3456"));
        
        // Recursos de prueba
        recursos.add(new RecursoEducativo(1, "Proyector", "2023-01-15", Tipo.DIGITAL, TipoEstado.DISPONIBLE));
        recursos.add(new RecursoEducativo(2, "Libro de Texto", "2023-02-20", Tipo.FISICO, TipoEstado.PRESTADO));
    }
    
    private void actualizarCamposSegunEntidad() {
        String entidad = (String) comboBoxEntidades.getSelectedItem();
        
        // Ocultar todos los campos adicionales primero
        txtDetalle1.setVisible(false);
        txtDetalle2.setVisible(false);
        comboEspecialidad.setVisible(false);
        comboTipo.setVisible(false);
        comboEstado.setVisible(false);
        
        // Configurar etiquetas y campos según la entidad
        if (lblDetalle1 != null && lblDetalle2 != null) {
            switch(entidad) {
                case "Curso":
                    lblDetalle1.setText("Área:");
                    lblDetalle2.setText("Horas:");
                    txtDetalle1.setVisible(true);
                    txtDetalle2.setVisible(true);
                    break;
                case "Docente":
                    lblDetalle1.setText("Teléfono:");
                    lblDetalle2.setText("Horario:");
                    txtDetalle1.setVisible(true);
                    txtDetalle2.setVisible(true);
                    comboEspecialidad.setVisible(true);
                    break;
                case "Estudiante":
                    lblDetalle1.setText("Grado:");
                    lblDetalle2.setText("Correo:");
                    txtDetalle1.setVisible(true);
                    txtDetalle2.setVisible(true);
                    break;
                case "Proveedor":
                    lblDetalle1.setText("Dirección:");
                    lblDetalle2.setText("Teléfono:");
                    txtDetalle1.setVisible(true);
                    txtDetalle2.setVisible(true);
                    break;
                case "Recurso Educativo":
                    lblDetalle1.setText("Fecha:");
                    txtDetalle1.setVisible(true);
                    comboTipo.setVisible(true);
                    comboEstado.setVisible(true);
                    break;
            }
        }
    }
    
    
    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        
        String entidadSeleccionada = (String) comboBoxEntidades.getSelectedItem();
        
        switch(entidadSeleccionada) {
            case "Curso":
                for(Curso curso : cursos) {
                    modeloTabla.addRow(new Object[] {
                        curso.getIdCurso(),
                        curso.getNombre(),
                        curso.getArea(),
                        curso.getNumHoras(),
                        "Docente: " + curso.getDocente().getNombre()
                    });
                }
                break;
            case "Docente":
                for(Docente docente : docentes) {
                    modeloTabla.addRow(new Object[] {
                        docente.getIdDocente(),
                        docente.getNombre(),
                        docente.getNumTelefono(),
                        docente.getHorario(),
                        "Especialidad: " + docente.getTipoEspecialidad()
                    });
                }
                break;
            case "Estudiante":
                for(Estudiante estudiante : estudiantes) {
                    modeloTabla.addRow(new Object[] {
                        estudiante.getIdEstudiante(),
                        estudiante.getNombre(),
                        estudiante.getGrado(),
                        estudiante.getCorreo(),
                        "Cursos: " + estudiante.getCursos().size()
                    });
                }
                break;
            case "Proveedor":
                for(Proveedor proveedor : proveedores) {
                    modeloTabla.addRow(new Object[] {
                        proveedor.getIdProveedor(),
                        proveedor.getNombre(),
                        proveedor.getDireccion(),
                        proveedor.getNumTelefono(),
                        "Productos: " + proveedor.getProductos().size()
                    });
                }
                break;
            case "Recurso Educativo":
                for(RecursoEducativo recurso : recursos) {
                    modeloTabla.addRow(new Object[] {
                        recurso.getIdRecurso(),
                        recurso.getNombre(),
                        recurso.getFecha(),
                        recurso.getTipo(),
                        "Estado: " + recurso.getTipoEstado()
                    });
                }
                break;
        }
    }
    
    private void agregarElemento() {
        String entidadSeleccionada = (String) comboBoxEntidades.getSelectedItem();
        String idText = txtId.getText();
        String nombre = txtNombre.getText();
        
        if(idText.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID y Nombre son campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idText);
            
            switch(entidadSeleccionada) {
                case "Curso":
                    String area = txtDetalle1.getText();
                    int numHoras = Integer.parseInt(txtDetalle2.getText());
                    // Seleccionar docente (simplificado - en realidad debería ser un selector)
                    Docente docente = docentes.isEmpty() ? null : docentes.get(0);
                    Curso nuevoCurso = new Curso(id, nombre, area, numHoras, docente);
                    institucionFacade.agregarCurso(cursos, nuevoCurso);
                    break;
                case "Docente":
                    String telefono = txtDetalle1.getText();
                    String horario = txtDetalle2.getText();
                    TipoEspecialidad especialidad = (TipoEspecialidad) comboEspecialidad.getSelectedItem();
                    Docente nuevoDocente = new Docente(id, nombre, telefono, horario, especialidad);
                    institucionFacade.agregarDocente(docentes, nuevoDocente);
                    break;
                case "Estudiante":
                    String grado = txtDetalle1.getText();
                    String correo = txtDetalle2.getText();
                    Estudiante nuevoEstudiante = new Estudiante(id, nombre, grado, correo);
                    institucionFacade.agregarEstudiante(estudiantes, nuevoEstudiante);
                    break;
                case "Proveedor":
                    String direccion = txtDetalle1.getText();
                    String telProveedor = txtDetalle2.getText();
                    Proveedor nuevoProveedor = new Proveedor(id, nombre, direccion, telProveedor);
                    institucionFacade.agregarProveedor(proveedores, nuevoProveedor);
                    break;
                case "Recurso Educativo":
                    String fecha = txtDetalle1.getText();
                    Tipo tipo = (Tipo) comboTipo.getSelectedItem();
                    TipoEstado estado = (TipoEstado) comboEstado.getSelectedItem();
                    RecursoEducativo nuevoRecurso = new RecursoEducativo(id, nombre, fecha, tipo, estado);
                    institucionFacade.agregarRecurso(recursos, nuevoRecurso);
                    break;
            }
            
            actualizarTabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Elemento agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID y campos numéricos deben contener valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarElemento() {
        String entidadSeleccionada = (String) comboBoxEntidades.getSelectedItem();
        String idText = txtId.getText();
        
        if(idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar un ID para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idText);
            boolean actualizado = false;
            
            switch(entidadSeleccionada) {
                case "Curso":
                    Curso cursoExistente = institucionFacade.obtenerCursoPorId(cursos, id);
                    if(cursoExistente != null) {
                        cursoExistente.setNombre(txtNombre.getText());
                        cursoExistente.setArea(txtDetalle1.getText());
                        cursoExistente.setNumHoras(Integer.parseInt(txtDetalle2.getText()));
                        actualizado = true;
                    }
                    break;
                case "Docente":
                    Docente docenteExistente = institucionFacade.obtenerDocentePorId(docentes, id);
                    if(docenteExistente != null) {
                        docenteExistente.setNombre(txtNombre.getText());
                        docenteExistente.setNumTelefono(txtDetalle1.getText());
                        docenteExistente.setHorario(txtDetalle2.getText());
                        docenteExistente.setTipoEspecialidad((TipoEspecialidad) comboEspecialidad.getSelectedItem());
                        actualizado = true;
                    }
                    break;
                case "Estudiante":
                    Estudiante estudianteExistente = institucionFacade.obtenerEstudiantePorId(estudiantes, id);
                    if(estudianteExistente != null) {
                        estudianteExistente.setNombre(txtNombre.getText());
                        estudianteExistente.setGrado(txtDetalle1.getText());
                        estudianteExistente.setCorreo(txtDetalle2.getText());
                        actualizado = true;
                    }
                    break;
                case "Proveedor":
                    Proveedor proveedorExistente = institucionFacade.obtenerProveedorPorId(proveedores, id);
                    if(proveedorExistente != null) {
                        proveedorExistente.setNombre(txtNombre.getText());
                        proveedorExistente.setDireccion(txtDetalle1.getText());
                        proveedorExistente.setNumTelefono(txtDetalle2.getText());
                        actualizado = true;
                    }
                    break;
                case "Recurso Educativo":
                    RecursoEducativo recursoExistente = institucionFacade.obtenerRecursoPorId(recursos, id);
                    if(recursoExistente != null) {
                        recursoExistente.setNombre(txtNombre.getText());
                        recursoExistente.setFecha(txtDetalle1.getText());
                        recursoExistente.setTipo((Tipo) comboTipo.getSelectedItem());
                        recursoExistente.setTipoEstado((TipoEstado) comboEstado.getSelectedItem());
                        actualizado = true;
                    }
                    break;
            }
            
            if(actualizado) {
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Elemento actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el elemento con ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID y campos numéricos deben contener valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarElemento() {
        String entidadSeleccionada = (String) comboBoxEntidades.getSelectedItem();
        String idText = txtId.getText();
        
        if(idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar un ID para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idText);
            boolean eliminado = false;
            
            switch(entidadSeleccionada) {
                case "Curso":
                    eliminado = institucionFacade.eliminarCurso(cursos, id);
                    break;
                case "Docente":
                    eliminado = institucionFacade.eliminarDocente(docentes, id);
                    break;
                case "Estudiante":
                    eliminado = institucionFacade.eliminarEstudiante(estudiantes, id);
                    break;
                case "Proveedor":
                    eliminado = institucionFacade.eliminarProveedor(proveedores, id);
                    break;
                case "Recurso Educativo":
                    eliminado = institucionFacade.eliminarRecurso(recursos, id);
                    break;
            }
            
            if(eliminado) {
                actualizarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Elemento eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el elemento con ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void buscarPorId() {
        String entidadSeleccionada = (String) comboBoxEntidades.getSelectedItem();
        String idText = txtId.getText();
        
        if(idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar un ID para buscar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int id = Integer.parseInt(idText);
            Object elemento = null;
            
            switch(entidadSeleccionada) {
                case "Curso":
                    elemento = institucionFacade.obtenerCursoPorId(cursos, id);
                    if(elemento != null) {
                        Curso curso = (Curso) elemento;
                        txtNombre.setText(curso.getNombre());
                        txtDetalle1.setText(curso.getArea());
                        txtDetalle2.setText(String.valueOf(curso.getNumHoras()));
                    }
                    break;
                case "Docente":
                    elemento = institucionFacade.obtenerDocentePorId(docentes, id);
                    if(elemento != null) {
                        Docente docente = (Docente) elemento;
                        txtNombre.setText(docente.getNombre());
                        txtDetalle1.setText(docente.getNumTelefono());
                        txtDetalle2.setText(docente.getHorario());
                        comboEspecialidad.setSelectedItem(docente.getTipoEspecialidad());
                    }
                    break;
                case "Estudiante":
                    elemento = institucionFacade.obtenerEstudiantePorId(estudiantes, id);
                    if(elemento != null) {
                        Estudiante estudiante = (Estudiante) elemento;
                        txtNombre.setText(estudiante.getNombre());
                        txtDetalle1.setText(estudiante.getGrado());
                        txtDetalle2.setText(estudiante.getCorreo());
                    }
                    break;
                case "Proveedor":
                    elemento = institucionFacade.obtenerProveedorPorId(proveedores, id);
                    if(elemento != null) {
                        Proveedor proveedor = (Proveedor) elemento;
                        txtNombre.setText(proveedor.getNombre());
                        txtDetalle1.setText(proveedor.getDireccion());
                        txtDetalle2.setText(proveedor.getNumTelefono());
                    }
                    break;
                case "Recurso Educativo":
                    elemento = institucionFacade.obtenerRecursoPorId(recursos, id);
                    if(elemento != null) {
                        RecursoEducativo recurso = (RecursoEducativo) elemento;
                        txtNombre.setText(recurso.getNombre());
                        txtDetalle1.setText(recurso.getFecha());
                        comboTipo.setSelectedItem(recurso.getTipo());
                        comboEstado.setSelectedItem(recurso.getTipoEstado());
                    }
                    break;
            }
            
            if(elemento != null) {
                JOptionPane.showMessageDialog(this, "Elemento encontrado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "No se encontró el elemento con ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDetalle1.setText("");
        txtDetalle2.setText("");
        if (comboEspecialidad != null) comboEspecialidad.setSelectedIndex(0);
        if (comboTipo != null) comboTipo.setSelectedIndex(0);
        if (comboEstado != null) comboEstado.setSelectedIndex(0);
    }
}