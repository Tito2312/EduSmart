package mundo;
import java.util.List;
import java.util.ArrayList;
public class Institucion {
<<<<<<< HEAD
	
=======
>>>>>>> 762d7fd28bf513f9016848a84d43e2f9406c3dd7
	private String nombre;
	private List<Curso> cursos = new ArrayList<>();
	private List<Estudiante> estudiantes = new ArrayList<>();
	private List<Docente> docentes = new ArrayList<>();
	private List<RecursoEducativo> recursos = new ArrayList<>();
	private List<Proveedor> proveedores = new ArrayList<>();
<<<<<<< HEAD
	
	//GETTERS Y SETTER
	public Institucion(String nombre) {
=======
	//GETTERS Y SETTER
	public Institucion(String nombre) {
		super();
>>>>>>> 762d7fd28bf513f9016848a84d43e2f9406c3dd7
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
