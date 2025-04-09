package mundo;
import java.util.List;
import java.util.ArrayList;

public class Curso {
	private int idCurso;
	private String nombre, area;
	private int numHoras;
	private Docente docente;
	private List<Estudiante> estudiantes;
	
	public Curso(int idCurso, String nombre, String area, int numHoras, Docente docente, List<Estudiante> estudiantes) {
		this.idCurso = idCurso;
		this.nombre = nombre;
		this.area = area;
		this.numHoras = numHoras;
		this.docente = docente;
		this.estudiantes= new ArrayList<>();
	}
	
	public int getIdCurso() {
		return idCurso;
	}
	
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public int getNumHoras() {
		return numHoras;
	}
	
	public void setNumHoras(int numHoras) {
		this.numHoras = numHoras;
	}
	
	public Docente getDocente() {
		return docente;
	}
	
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	
	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public void agregarEstudiante (Estudiante estudiante) {
		estudiantes.add(estudiante);
	}
	
	public String toString() {
		return "Curso [idCurso=" + idCurso + ", nombre=" + nombre + ", area=" + area + ", numHoras=" + numHoras
				+ ", docente=" + docente + ", estudiantes=" + estudiantes + "]";
	}
	

}
