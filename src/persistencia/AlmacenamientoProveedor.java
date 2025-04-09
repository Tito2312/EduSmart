package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import mundo.Proveedor;

public class AlmacenamientoProveedor {
	private  File archivo;

    public AlmacenamientoProveedor() {
        archivo = new File("Proveedor.obj");
    }

    public void guardarProveedor(List<Proveedor> proveedor) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(proveedor);
        } catch (IOException e) {
        	
            System.out.println("Error al guardar los proveedores: " + e.getMessage());
        }
    }

	public List<Proveedor> cargarProveedor() {
    	
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        	
            return (List<Proveedor>) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
        	
            System.out.println("Error al cargar los proveedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
