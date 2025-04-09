package mundo;

import java.util.List;
import persistencia.*;

public class InstitucionFacade {

    private Institucion institucion;
    private AlmacenamientoCurso almacenamientoCurso;
    private AlmacenamientoDocente almacenamientoDocente;
    private AlmacenamientoEstudiante almacenamientoEstudiante;
    private AlmacenamientoProveedor almacenamientoProveedor;
    
    public InstitucionFacade(String nombreInstitucion) {
        this.institucion = new Institucion(nombreInstitucion);
        this.almacenamientoCurso = new AlmacenamientoCurso();
        this.almacenamientoDocente = new AlmacenamientoDocente();
        this.almacenamientoEstudiante = new AlmacenamientoEstudiante();
        this.almacenamientoProveedor = new AlmacenamientoProveedor();
        this.institucion.setCursos(almacenamientoCurso.cargarCursos());
        this.institucion.setDocentes(almacenamientoDocente.cargarDocente());
        this.institucion.setEstudiantes(almacenamientoEstudiante.cargarEstudiante());
        this.institucion.setProveedores(almacenamientoProveedor.cargarProveedor());
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    //APLICACION DEL PATRÓN FACADE (CRUD NORMAL CAMBIA LA INTERFAZ)

    // CRUD CURSO
    public void agregarCurso(List<Curso> listaCursos, Curso curso) {
        listaCursos.add(curso);
        almacenamientoCurso.guardarCursos(listaCursos);
    }

    public void actualizarCurso(List<Curso> listaCursos, Curso cursoActualizado) {
        for (int i = 0; i < listaCursos.size(); i++) {
            if (listaCursos.get(i).getIdCurso() == cursoActualizado.getIdCurso()) {
                listaCursos.set(i, cursoActualizado);
                almacenamientoCurso.guardarCursos(listaCursos);
                return;
            }
        }
    }

    public void eliminarCurso(List<Curso> listaCursos, int idCurso) {
        listaCursos.removeIf(curso -> curso.getIdCurso() == idCurso);
        almacenamientoCurso.guardarCursos(listaCursos);
    }

    public List<Curso> getCursos() {
        return institucion.getCursos();
    }
    

    // CRUD DOCENTE
    public void agregarDocente(List<Docente> docentes, Docente docente) {
        docentes.add(docente);
        almacenamientoDocente.guardarDocente(docentes);
    }

    public Docente obtenerDocentePorId(List<Docente> docentes, int idDocente) {
        for (Docente docente : docentes) {
            if (docente.getIdDocente() == idDocente) {
                return docente;
            }
        }
        return null;
    }
    
    public List<Docente> obtenerDocentes(){
    	return institucion.getDocentes();
    }

    public boolean actualizarDocente(List<Docente> docentes, Docente docenteActualizado) {
        for (int i = 0; i < docentes.size(); i++) {
            if (docentes.get(i).getIdDocente() == docenteActualizado.getIdDocente()) {
                docentes.set(i, docenteActualizado);
                almacenamientoDocente.guardarDocente(docentes);
                return true;
            }
        }
        return false;
    }

    public void eliminarDocente(List<Docente> docentes, int idDocente) {
        docentes.removeIf(docente -> docente.getIdDocente() == idDocente);
        almacenamientoDocente.guardarDocente(docentes);
    }

    // CRUD ESTUDIANTE
    public void agregarEstudiante(List<Estudiante> estudiantes, Estudiante estudiante) {
        estudiantes.add(estudiante);
        almacenamientoEstudiante.guardarEstudiante(estudiantes);
    }

    public Estudiante obtenerEstudiantePorId(List<Estudiante> estudiantes, int idEstudiante) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getIdEstudiante() == idEstudiante) {
                return estudiante;
            }
        }
        return null;
    }

    public boolean actualizarEstudiante(List<Estudiante> estudiantes, Estudiante estudianteActualizado) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getIdEstudiante() == estudianteActualizado.getIdEstudiante()) {
                estudiantes.set(i, estudianteActualizado);
                almacenamientoEstudiante.guardarEstudiante(estudiantes);
                return true;
            }
        }
        return false;
    }

    public void eliminarEstudiante(List<Estudiante> estudiantes, int idEstudiante) {
        estudiantes.removeIf(estudiante -> estudiante.getIdEstudiante() == idEstudiante);
        almacenamientoEstudiante.guardarEstudiante(estudiantes);
    }
    
    public List<Estudiante> obtenerEstudiante(){
    	return institucion.getEstudiantes();
    }

    // CRUD PROVEEDOR
    public void agregarProveedor(List<Proveedor> proveedores, Proveedor proveedor) {
        proveedores.add(proveedor);
        almacenamientoProveedor.guardarProveedor(proveedores);
    }

    public Proveedor obtenerProveedorPorId(List<Proveedor> proveedores, int idProveedor) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdProveedor() == idProveedor) {
                return proveedor;
            }
        }
        return null;
    }

    public boolean actualizarProveedor(List<Proveedor> proveedores, Proveedor proveedorActualizado) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getIdProveedor() == proveedorActualizado.getIdProveedor()) {
                proveedores.set(i, proveedorActualizado);
                almacenamientoProveedor.guardarProveedor(proveedores);
                return true;
            }
        }
        return false;
    }

    public void eliminarProveedor(List<Proveedor> proveedores, int idProveedor) {
        proveedores.removeIf(proveedor -> proveedor.getIdProveedor() == idProveedor);
        almacenamientoProveedor.guardarProveedor(proveedores);
    }
    
    public List<Proveedor> obtenerProveedores(){
    	return institucion.getProveedores();
    }

    // CRUD RECURSO EDUCATIVO
    public void agregarRecurso(List<RecursoEducativo> recursos, RecursoEducativo recurso) {
        recursos.add(recurso);
    }

    public RecursoEducativo obtenerRecursoPorId(List<RecursoEducativo> recursos, int idRecurso) {
        for (RecursoEducativo recurso : recursos) {
            if (recurso.getIdRecurso() == idRecurso) {
                return recurso;
            }
        }
        return null;
    }

    public boolean actualizarRecurso(List<RecursoEducativo> recursos, RecursoEducativo recursoActualizado) {
        for (int i = 0; i < recursos.size(); i++) {
            if (recursos.get(i).getIdRecurso() == recursoActualizado.getIdRecurso()) {
                recursos.set(i, recursoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarRecurso(List<RecursoEducativo> recursos, int idRecurso) {
        return recursos.removeIf(recurso -> recurso.getIdRecurso() == idRecurso);
    }
}
