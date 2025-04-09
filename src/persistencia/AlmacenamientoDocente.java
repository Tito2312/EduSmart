package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import mundo.Docente;

public class AlmacenamientoDocente {
	private  File archivo;

    public AlmacenamientoDocente() {
        archivo = new File("Docentes.obj");
    }

    public void guardarDocente(List<Docente> docentes) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(docentes);
        } catch (IOException e) {
        	
            System.out.println("Error al guardar los docentes: " + e.getMessage());
        }
    }

	public List<Docente> cargarDocente() {
    	
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        	
            return (List<Docente>) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
        	
            System.out.println("Error al cargar los docentes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
