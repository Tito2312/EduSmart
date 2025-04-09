package mundo;
import java.util.List;
import java.util.ArrayList;
public class Estudiante {
	private int idEstudiante;
	private String nombre, grado, correo;
	private List<Curso> cursos;
	
	public Estudiante(int idEstudiante, String nombre, String grado, String correo) {

		this.idEstudiante = idEstudiante;
		this.nombre = nombre;
		this.grado = grado;
		this.correo = correo;
		this.cursos = new ArrayList<>();
	}
	public int getIdEstudiante() {
		return idEstudiante;
	}
	public void setIdEstudiante(int idEstudiante) {
		this.idEstudiante = idEstudiante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	public void agregarCurso (Curso curso) {
		cursos.add(curso);
	}
	@Override
	public String toString() {
		return "Estudiante [idEstudiante=" + idEstudiante + ", nombre=" + nombre + ", grado=" + grado + ", correo="
				+ correo + "]";
	}
	
	
	
	

}
