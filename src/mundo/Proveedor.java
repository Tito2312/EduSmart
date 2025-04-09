package mundo;
import java.util.List;
import java.util.ArrayList;
public class Proveedor {
	private int idProveedor;
	private String nombre, direccion, numTelefono;
	private List<Producto> productos;
<<<<<<< HEAD
	
	public Proveedor(int idProveedor, String nombre, String direccion, String numTelefono) {
=======
	public Proveedor(int idProveedor, String nombre, String direccion, String numTelefono) {
		super();
>>>>>>> 762d7fd28bf513f9016848a84d43e2f9406c3dd7
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numTelefono = numTelefono;
		this.productos = new ArrayList<>();
	}
<<<<<<< HEAD
	
	public int getIdProveedor() {
		return idProveedor;
	}
	
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNumTelefono() {
		return numTelefono;
	}
	
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void agregarProducto(Producto producto) {
		productos.add(producto);
	}
	
=======
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNumTelefono() {
		return numTelefono;
	}
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public void agregarProducto(Producto producto) {
		productos.add(producto);
	}
>>>>>>> 762d7fd28bf513f9016848a84d43e2f9406c3dd7
	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", numTelefono=" + numTelefono + "]";
	}
	

}
