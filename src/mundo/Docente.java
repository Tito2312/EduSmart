package mundo;
//import java.time.LocalTime;

public class Docente {
	private int idDocente;
	private String nombre, numTelefono, horario;
	private TipoEspecialidad tipoEspecialidad;
	
	public Docente(int idDocente, String nombre, String numTelefono, String horario,TipoEspecialidad tipoEspecialidad) {

		this.idDocente = idDocente;
		this.nombre = nombre;
		this.numTelefono = numTelefono;
		this.horario = horario;
		this.tipoEspecialidad = tipoEspecialidad;
	}
	
	public int getIdDocente() {
		return idDocente;
	}
	
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNumTelefono() {
		return numTelefono;
	}
	
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	public TipoEspecialidad getTipoEspecialidad() {
		return tipoEspecialidad;
	}
	
	public void setTipoEspecialidad(TipoEspecialidad tipoEspecialidad) {
		this.tipoEspecialidad = tipoEspecialidad;
	}
	
	public String toString() {
		return "Docente [idDocente=" + idDocente + ", nombre=" + nombre + ", numTelefono=" + numTelefono + ", horario="
				+ horario + "]";
	}
	
	

}
