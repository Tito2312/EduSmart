package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import mundo.Estudiante;

public class AlmacenamientoEstudiante {
	private  File archivo;

    public AlmacenamientoEstudiante() {
        archivo = new File("Estudiante.obj");
    }

    public void guardarEstudiante(List<Estudiante> estudiante) {
    	
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(estudiante);
        } catch (IOException e) {
        	
            System.out.println("Error al guardar los estudiantes: " + e.getMessage());
        }
    }

	public List<Estudiante> cargarEstudiante() {
    	
        if (!archivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        	
            return (List<Estudiante>) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
        	
            System.out.println("Error al cargar los estudiantes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
