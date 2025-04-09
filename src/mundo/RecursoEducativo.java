package mundo;
public class RecursoEducativo {
	private int idRecurso;
	private String nombre,fecha;
	private Tipo tipo;
	private TipoEstado tipoEstado;
	
	public RecursoEducativo(int idRecurso, String nombre, String fecha, Tipo tipo, TipoEstado tipoEstado) {
		super();
		this.idRecurso = idRecurso;
		this.nombre = nombre;
		this.fecha = fecha;
		this.tipo = tipo;
		this.tipoEstado = tipoEstado;
	}
	
	public int getIdRecurso() {
		return idRecurso;
	}
	
	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public TipoEstado getTipoEstado() {
		return tipoEstado;
	}
	
	public void setTipoEstado(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado;
	}
	
	@Override
	public String toString() {
		return "RecursoEducativo [idRecurso=" + idRecurso + ", nombre=" + nombre + ", fecha=" + fecha + "]";
	}

}
