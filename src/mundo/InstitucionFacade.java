package mundo;

import java.util.List;

public class InstitucionFacade {

    private Institucion institucion;
    
    public InstitucionFacade(String nombreInstitucion) {
        this.institucion = new Institucion(nombreInstitucion);
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    //APLICACION DEL PATRÓN FACADE (CRUD NORMAL CAMBIA LA INTERFAZ)

    // CRUD CURSO
    public void agregarCurso(List<Curso> cursos, Curso curso) {
        cursos.add(curso);
    }

    public Curso obtenerCursoPorId(List<Curso> cursos, int idCurso) {
        for (Curso curso : cursos) {
            if (curso.getIdCurso() == idCurso) {
                return curso;
            }
        }
        return null;
    }

    public boolean actualizarCurso(List<Curso> cursos, Curso cursoActualizado) {
        for (int i = 0; i < cursos.size(); i++) {
            if (cursos.get(i).getIdCurso() == cursoActualizado.getIdCurso()) {
                cursos.set(i, cursoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCurso(List<Curso> cursos, int idCurso) {
        return cursos.removeIf(curso -> curso.getIdCurso() == idCurso);
    }

    // CRUD DOCENTE
    public void agregarDocente(List<Docente> docentes, Docente docente) {
        docentes.add(docente);
    }

    public Docente obtenerDocentePorId(List<Docente> docentes, int idDocente) {
        for (Docente docente : docentes) {
            if (docente.getIdDocente() == idDocente) {
                return docente;
            }
        }
        return null;
    }

    public boolean actualizarDocente(List<Docente> docentes, Docente docenteActualizado) {
        for (int i = 0; i < docentes.size(); i++) {
            if (docentes.get(i).getIdDocente() == docenteActualizado.getIdDocente()) {
                docentes.set(i, docenteActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarDocente(List<Docente> docentes, int idDocente) {
        return docentes.removeIf(docente -> docente.getIdDocente() == idDocente);
    }

    // CRUD ESTUDIANTE
    public void agregarEstudiante(List<Estudiante> estudiantes, Estudiante estudiante) {
        estudiantes.add(estudiante);
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
                return true;
            }
        }
        return false;
    }

    public boolean eliminarEstudiante(List<Estudiante> estudiantes, int idEstudiante) {
        return estudiantes.removeIf(estudiante -> estudiante.getIdEstudiante() == idEstudiante);
    }

    // CRUD PROVEEDOR
    public void agregarProveedor(List<Proveedor> proveedores, Proveedor proveedor) {
        proveedores.add(proveedor);
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
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProveedor(List<Proveedor> proveedores, int idProveedor) {
        return proveedores.removeIf(proveedor -> proveedor.getIdProveedor() == idProveedor);
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
