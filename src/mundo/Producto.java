package mundo;

public class Producto {

	private String nombre;
	private int idProducto;

	public Producto(String nombre, int idProducto) {
		this.nombre = nombre;
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	

}
