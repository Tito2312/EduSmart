package mundo;
import java.util.List;
import java.util.ArrayList;
public class Institucion {
	private String nombre;
	private List<Curso> cursos = new ArrayList<>();
	private List<Estudiante> estudiantes = new ArrayList<>();
	private List<Docente> docentes = new ArrayList<>();
	private List<RecursoEducativo> recursos = new ArrayList<>();
	private List<Proveedor> proveedores = new ArrayList<>();
	//GETTERS Y SETTER
	public Institucion(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public List<RecursoEducativo> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoEducativo> recursos) {
		this.recursos = recursos;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	

}
